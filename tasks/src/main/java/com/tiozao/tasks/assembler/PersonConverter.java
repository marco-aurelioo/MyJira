package com.tiozao.tasks.assembler;

import com.tiozao.tasks.assembler.models.ObjectsIn;
import com.tiozao.tasks.assembler.models.ObjectsOut;
import com.tiozao.tasks.assembler.models.PersonInputs;
import com.tiozao.tasks.assembler.models.PersonOutputs;
import com.tiozao.tasks.domain.entity.PersonEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.security.Principal;

@Component
public class PersonConverter extends Converter<PersonInputs, PersonOutputs>{
    public PersonConverter() {
        super(PersonConverter::originDomain, PersonConverter::domainOrigin);
    }

    private static PersonInputs domainOrigin(PersonOutputs personOutputs) {
        return null;
    }

    static PersonOutputs originDomain(PersonInputs objectsIn) {
        PersonEntity entity = new PersonEntity();
        entity.setAvatar(objectsIn.getRequest().getAvatar());
        Principal principal = objectsIn.getPrincipal();
        String sub = ((JwtAuthenticationToken) principal).getToken().getClaim("sub");
        entity.setUserId(sub);
        entity.setName(principal.getName());
        return new PersonOutputs(entity);
    }






}

