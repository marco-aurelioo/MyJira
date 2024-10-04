package com.tiozao.tasks.assembler;

import com.tiozao.tasks.aplication.controllers.response.SubscriptionPlansResponse;
import com.tiozao.tasks.assembler.models.PlansInputs;
import com.tiozao.tasks.assembler.models.PlansOutput;
import org.springframework.stereotype.Component;

import java.util.List;

import java.util.stream.Collectors;

@Component
public class PlansConverter extends Converter<PlansInputs, PlansOutput>{

    public PlansConverter() {
        super(PlansConverter::originDomain, PlansConverter::domainOrigin);
    }

    public static PlansOutput  originDomain(PlansInputs  in) {
        return new PlansOutput(in.getAllPlans().stream().map( item -> {
            SubscriptionPlansResponse plan = new SubscriptionPlansResponse();
            plan.setPlanName(item.getNome());
            plan.setPrice(item.getPrice());
            List<String> features = item.getFeaturesList().stream().map(feature -> feature.getDescription()).collect(Collectors.toList());
            plan.setFeatures(features);
            return plan;
        }).collect(Collectors.toList()));
    }

    public static PlansInputs  domainOrigin(PlansOutput out) {

        return null;
    }
}
