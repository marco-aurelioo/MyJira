package com.tiozao.tasks.utils;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class NomeHashGenerator {

    private static final Set<String> palavrasIgnoradas = new HashSet<>(Arrays.asList(
            "de", "da", "do", "das", "dos", "um", "uma", "meu", "minha", "seu", "sua", "nosso", "nossa",
            "vosso", "vossa", "deles", "delas", "ele", "ela", "eles", "elas", "e", "para"
    ));


    public static String gerarHash(String nome) {

        List<String> palavrasFiltradas = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\b\\w+\\b").matcher(nome.toLowerCase());

        while (matcher.find()) {
            String palavra = matcher.group();
            if (!palavrasIgnoradas.contains(palavra)) {
                palavrasFiltradas.add(palavra.substring(0, 1).toUpperCase());
            }
        }

        StringBuilder hashGerado = new StringBuilder();
        for (int i = 0; i < Math.min(4, palavrasFiltradas.size()); i++) {
            hashGerado.append(palavrasFiltradas.get(i));
        }

        return hashGerado.toString();
    }
}
