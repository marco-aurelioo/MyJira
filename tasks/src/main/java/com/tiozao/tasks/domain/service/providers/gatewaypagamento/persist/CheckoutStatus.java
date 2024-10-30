package com.tiozao.tasks.domain.service.providers.gatewaypagamento.persist;

public enum CheckoutStatus {
    PENDING,
    IN_PROGRESS,
    PAID,
    FAILED,
    CANCELED;

    public static CheckoutStatus getStatus(String s) {
        if(s == null)
            throw new IllegalStateException("Unknown CheckoutStatus");
        for(CheckoutStatus status : CheckoutStatus.values()){
            if(status.name().equals(s.toUpperCase())){
                return status;
            }
        }
        throw new IllegalStateException("Unknown CheckoutStatus");
    }
}
