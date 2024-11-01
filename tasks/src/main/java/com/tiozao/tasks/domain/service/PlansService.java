package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.domain.entity.PersonPlansEntity;
import com.tiozao.tasks.domain.entity.SubscriptionPlansEntity;
import com.tiozao.tasks.domain.exceptions.GatewayPagamentoException;

import com.tiozao.tasks.domain.service.providers.gatewaypagamento.CheckoutService;
import com.tiozao.tasks.domain.service.providers.gatewaypagamento.model.Produto;
import com.tiozao.tasks.domain.service.providers.useraccess.UserRolesService;
import com.tiozao.tasks.resources.repositories.PersonPlansRepository;
import com.tiozao.tasks.resources.repositories.PersonRepository;
import com.tiozao.tasks.resources.repositories.SubscriptionPlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlansService {

    @Autowired
    private SubscriptionPlansRepository subscriptionRepository;

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonPlansRepository personPlansRepository;

    @Autowired
    private UserRolesService userRolesService;

    public List<SubscriptionPlansEntity> getAllPlans() {
        return (List<SubscriptionPlansEntity>) subscriptionRepository.findAll();
    }

    public String getUrlCheckout(List<Produto> produtos, String externalId, String urlSuccess, String urlCancel) throws GatewayPagamentoException {
        return checkoutService.efetuarPagamento(produtos, externalId, urlSuccess, urlCancel);
    }

    public Boolean confirmPayment(String pessoaID, String checkoutId, String status) throws GatewayPagamentoException {
        if("cancel".equals(status))
            throw new IllegalStateException(("Erro validando pagamento"));
        boolean gatewayStatus = checkoutService.confirmPayment( pessoaID, checkoutId);
        if(gatewayStatus && "success".equals(status)){
            userRolesService.addRole(pessoaID,"OWNER_ROLE");
            salvaPlanoContratado(pessoaID, checkoutId);
        }else{
            throw new IllegalStateException(("Erro validando pagamento"));
        }
        return gatewayStatus;
    }

    private void salvaPlanoContratado(String pessoaID, String checkoutId){
        List<Produto> produtos = checkoutService.getCheckoutProdutos( pessoaID, checkoutId);
        for(Produto produto : produtos){
            subscriptionRepository.findByNome(produto.getNome()).ifPresent(
                    subscriptionPlansEntity -> {
                        personPlansRepository.save(
                                new PersonPlansEntity(
                                        subscriptionPlansEntity,
                                        personService.findPersonByUserId(pessoaID))
                        );
                    }
            );
        }
    }
}
