package com.archiveLinks.Utility;

public class Validate {
    public static boolean isNotNullOrDigitFirst(String name){
        char first = name.charAt(0);

        // True se nulo ou começar com dígito
        return name.isBlank() || Character.isDigit(first);
    }
}
