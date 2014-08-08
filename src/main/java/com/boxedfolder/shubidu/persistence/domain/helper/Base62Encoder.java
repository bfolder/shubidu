package com.boxedfolder.shubidu.persistence.domain.helper;

public class Base62Encoder implements Encoder {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final int BASE = ALPHABET.length();

    @Override
    public String encode(String string) {
        return String.valueOf(toBase10(new StringBuilder(string).reverse().toString().toCharArray()));
    }

    private int toBase10(char[] chars) {
        int n = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            n += toBase10(Base62Encoder.ALPHABET.indexOf(chars[i]), i);
        }
        return n;
    }

    private int toBase10(int n, int pow) {
        return n * (int)Math.pow(BASE, pow);
    }
}
