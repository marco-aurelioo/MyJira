package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.domain.entity.PersonEntity;
import com.tiozao.tasks.domain.entity.SubscriptionPlansEntity;
import com.tiozao.tasks.domain.exceptions.GatewayPagamentoException;

import com.tiozao.tasks.domain.service.providers.model.Produto;
import com.tiozao.tasks.resources.repositories.SubscriptionPlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlansService {


    @Autowired
    private SubscriptionPlansRepository repository;

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private UserRolesService userRolesService;

    public List<SubscriptionPlansEntity> getAllPlans() {
        return (List<SubscriptionPlansEntity>) repository.findAll();
    }

    public String getUrlCheckout(List<Produto> produtos, String externalId, String urlSuccess, String urlCancel) throws GatewayPagamentoException {
        return checkoutService.efetuarPagamento(produtos, externalId, urlSuccess, urlCancel);
    }

    public Boolean confirmPayment(String pessoaID, String checkoutId, String status) throws GatewayPagamentoException {
        if("success".equals(status)) {
            boolean gatewayStatus = checkoutService.confirmPayment( pessoaID, checkoutId);
            if(gatewayStatus){
                userRolesService.addRole(pessoaID,"OWNER_ROLE");
            }else{
                throw new IllegalStateException(("Erro validando pagamento"));
            }
        }
        return checkoutService.confirmPayment( pessoaID, checkoutId);
    }


}
