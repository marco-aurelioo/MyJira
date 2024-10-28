package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.domain.entity.CheckoutEntity;
import com.tiozao.tasks.domain.entity.CheckoutStatus;
import com.tiozao.tasks.domain.entity.PersonEntity;
import com.tiozao.tasks.domain.exceptions.GatewayPagamentoException;
import com.tiozao.tasks.domain.service.providers.StripeGatewayPagamento;
import com.tiozao.tasks.domain.service.providers.model.CheckoutGatewayResponse;
import com.tiozao.tasks.domain.service.providers.model.Produto;
import com.tiozao.tasks.resources.repositories.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class CheckoutService {

    @Autowired
    private StripeGatewayPagamento stripeGatewayPagamento;

    @Autowired
    private CheckoutRepository repository;

    public String efetuarPagamento(List<Produto> produtos, String personId, String urlSuccess, String urlCancel) throws GatewayPagamentoException {
        UUID externalId = UUID.randomUUID();
        CheckoutGatewayResponse response = stripeGatewayPagamento.getCheckout(externalId, urlSuccess ,urlCancel, produtos);
        repository.save(new CheckoutEntity( externalId, personId, produtos, response.getGatewayReferenceId()));
        return response.getUrl();
    }


    public boolean confirmPayment(String pessoaID, String checkoutId) throws GatewayPagamentoException {
        Optional<CheckoutEntity> optEntity = repository.findByExternalId(UUID.fromString(checkoutId));
        if(optEntity.isPresent()){
            CheckoutEntity checkout = optEntity.get();
            if(!checkout.getPessoa().equals(pessoaID)){
                throw new IllegalStateException("CheckoutEntity");
            }
            checkout.setStatus(CheckoutStatus.getStatus(stripeGatewayPagamento.confirPayment(checkout.getGatewayPaymentId())));
            repository.save(checkout);
            return isSucessed(checkout.getStatus());
        }
        throw new IllegalStateException("CheckoutEntity");
    }

    private boolean isSucessed(CheckoutStatus status) {
        return Objects.requireNonNull(status) == CheckoutStatus.PAID;
    }
}
