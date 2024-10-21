package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.domain.entity.CheckoutEntity;
import com.tiozao.tasks.domain.entity.PersonEntity;
import com.tiozao.tasks.domain.exceptions.GatewayPagamentoException;
import com.tiozao.tasks.domain.service.providers.StripeGatewayPagamento;
import com.tiozao.tasks.domain.service.providers.model.Produto;
import com.tiozao.tasks.resources.repositories.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CheckoutService {

    @Autowired
    private StripeGatewayPagamento stripeGatewayPagamento;

    @Autowired
    private CheckoutRepository repository;

    public String efetuarPagamento(List<Produto> produtos, String personId, String urlSuccess, String urlCancel) throws GatewayPagamentoException {
        CheckoutEntity entity = repository.save(new CheckoutEntity( UUID.randomUUID(), personId, produtos));
        return stripeGatewayPagamento.getCheckout(entity.getExternalId(),urlSuccess,urlCancel,produtos);
    }


}
