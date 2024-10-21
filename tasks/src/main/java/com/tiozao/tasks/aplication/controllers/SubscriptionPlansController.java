package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.controllers.request.CheckoutStatus;
import com.tiozao.tasks.aplication.controllers.request.PricePlanRequest;
import com.tiozao.tasks.aplication.controllers.response.SubmitPlanResponse;
import com.tiozao.tasks.aplication.controllers.response.SubscriptionPlansResponse;
import com.tiozao.tasks.assembler.CheckoutConverter;
import com.tiozao.tasks.assembler.PlansConverter;
import com.tiozao.tasks.assembler.models.CheckoutIn;
import com.tiozao.tasks.assembler.models.CheckoutOut;
import com.tiozao.tasks.assembler.models.PlansInputs;
import com.tiozao.tasks.domain.exceptions.GatewayPagamentoException;
import com.tiozao.tasks.domain.service.PlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SubscriptionPlansController {


    @Autowired
    private PlansService service;

    @Autowired
    private PlansConverter serviceConverter;

    @Autowired
    private CheckoutConverter checkoutConverter;

    @GetMapping("/public/subscriptions")
    public ResponseEntity<List<SubscriptionPlansResponse>> getAllSubscriptions(){
        return ResponseEntity.ok(
                serviceConverter.convertOrigin(
                        new PlansInputs(service.getAllPlans())).getAllPlans());
    }


    @PostMapping("/submit/add-plan")
    public ResponseEntity<SubmitPlanResponse> submitPlan(@RequestBody PricePlanRequest planRequest, Principal principal) throws GatewayPagamentoException {
        SubmitPlanResponse response = new SubmitPlanResponse();

        CheckoutOut checkoutOut = checkoutConverter.convertOrigin(new CheckoutIn(planRequest, principal));

        response.setUrtPaymentMethod(
                service.getUrlCheckout(
                        checkoutOut.getProdutos(),
                        checkoutOut.getExternalId(),
                        checkoutOut.getUrlSuccess(),
                        checkoutOut.getUrlSuccess()));

        return ResponseEntity.ok(response);
    }

    @PutMapping("/submit/add-plan/{idrequest}")
    public ResponseEntity<PricePlanRequest> updateSubmitPlan(
            @PathVariable String idrequest,
            @RequestBody CheckoutStatus planRequest,
            Principal principal)  {
        PricePlanRequest response = new PricePlanRequest();


        return ResponseEntity.ok(response);
    }

}
