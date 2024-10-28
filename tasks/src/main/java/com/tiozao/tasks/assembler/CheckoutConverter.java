package com.tiozao.tasks.assembler;

import com.tiozao.tasks.assembler.models.CheckoutIn;
import com.tiozao.tasks.assembler.models.CheckoutOut;
import com.tiozao.tasks.domain.service.providers.model.Produto;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class CheckoutConverter extends Converter<CheckoutIn, CheckoutOut>{
    public CheckoutConverter() {
        super(CheckoutConverter::originToDomain,null);
    }

    public static CheckoutOut originToDomain(CheckoutIn checkoutIn){
        List<Produto> produtos = new ArrayList<>();

        produtos.add(new Produto(
                checkoutIn.getPricePlanRequest().getPlanName(),
                checkoutIn.getPricePlanRequest().getPrice(),
                1l
                ));

        Principal principal = checkoutIn.getPrincipal();
        String sub = ((JwtAuthenticationToken) principal).getToken().getClaim("sub");


        return new CheckoutOut(produtos,
                checkoutIn.getPricePlanRequest().getUrlSuccess(),
                checkoutIn.getPricePlanRequest().getUrlCancel(),
                sub);

    }

}
