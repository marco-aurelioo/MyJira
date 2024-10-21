package com.tiozao.tasks.domain.service.providers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.tiozao.tasks.domain.exceptions.GatewayPagamentoException;
import com.tiozao.tasks.domain.service.providers.model.Produto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class StripeGatewayPagamento {

    @Value("${app.config.gateway.client}")
    private String apiKey;

    @Value("${app.config.gateway.secret}")
    private String apiSecret;



    public String getCheckout(UUID idCheckout, String urlSuccess, String urlCancel, List<Produto> produtos) throws GatewayPagamentoException {
        try {
            Stripe.apiKey = this.apiSecret;
            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT) // Modo de pagamento
                    .setSuccessUrl(urlSuccess + idCheckout.toString()) // URL de sucesso
                    .setCancelUrl(urlCancel) // URL de cancelamento
                    .addAllLineItem(getLineItems(produtos))
                    .build();
            Session session = Session.create(params);
            return session.getUrl();
        }catch (StripeException ex){
            throw new GatewayPagamentoException(ex.getMessage());
        }
    }

    private static List<SessionCreateParams.LineItem> getLineItems(List<Produto> produtos) {
        if(produtos.isEmpty())
            throw new IllegalStateException("Lista de produtos vazia");

        List<SessionCreateParams.LineItem> sessionParamsList = new ArrayList<>();
        for(Produto produto : produtos){
            sessionParamsList.add(SessionCreateParams.LineItem.builder()
                    .setQuantity(produto.getQuantidade())
                    .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                    .setCurrency("BRL")
                                    .setUnitAmount(produto.getPreco() * 100)
                                    .setProductData(
                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                    .setName(produto.getNome())
                                                    .build()
                                    )
                                    .build()
                    )
                    .build());
        }
        return sessionParamsList;
    }


}
