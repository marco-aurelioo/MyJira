package com.tiozao.tasks.domain.service;

import com.tiozao.tasks.domain.entity.SubscriptionPlansEntity;
import com.tiozao.tasks.resources.repositories.SubscriptionPlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionPlansService {


    @Autowired
    private SubscriptionPlansRepository repository;

    public List<SubscriptionPlansEntity> getAllPlans() {
        return (List<SubscriptionPlansEntity>) repository.findAll();
    }


}
