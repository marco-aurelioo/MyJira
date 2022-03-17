package com.tiozao.tasks.assembler;


import java.util.function.Function;

abstract class Converter<O,D> {

    private final Function<O,D> originDomain;
    private final Function<D,O> domainOrigin;

    public Converter(Function<O, D> originDomain, Function<D, O> domainOrigin) {
        this.originDomain = originDomain;
        this.domainOrigin = domainOrigin;
    }

    public final O convertDomain(D d){
        return domainOrigin.apply(d);
    }

    public final D convertOrigin(O o){
        return originDomain.apply(o);
    }

}