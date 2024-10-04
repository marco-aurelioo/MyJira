package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.controllers.response.SubscriptionPlansResponse;
import com.tiozao.tasks.assembler.PlansConverter;
import com.tiozao.tasks.assembler.models.PlansInputs;
import com.tiozao.tasks.domain.service.SubscriptionPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubscriptionPlansController {


    @Autowired
    private SubscriptionPlansService service;

    @Autowired
    private PlansConverter serviceConverter;

    @GetMapping("/public/subscriptions")
    public ResponseEntity<List<SubscriptionPlansResponse>> getAllSubscriptions(){
        return ResponseEntity.ok(
                serviceConverter.convertOrigin(
                        new PlansInputs(service.getAllPlans())).getAllPlans());
    }

}
