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

    public List<SubscriptionPlansEntity> getAllPlans() {
        return (List<SubscriptionPlansEntity>) repository.findAll();
    }

    public String getUrlCheckout(List<Produto> produtos, String externalId, String urlSuccess, String urlCancel) throws GatewayPagamentoException {
        return checkoutService.efetuarPagamento(produtos, externalId, urlSuccess, urlCancel);
    }


}
