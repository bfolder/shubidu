package com.boxedfolder.shubidu.persistence.domain.encoding;

public class Base62Encoder implements Encoder<String, Long> {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final int BASE = ALPHABET.length();

    @Override
    public String encode(Long id) {
        return fromBase10(id.intValue());
    }

    public static String fromBase10(int i) {
        StringBuilder sb = new StringBuilder("");
        while (i > 0) {
            i = fromBase10(i, sb);
        }
        return sb.reverse().toString();
    }

    private static int fromBase10(int i, final StringBuilder sb) {
        int rem = i % BASE;
        sb.append(ALPHABET.charAt(rem));
        return i / BASE;
    }
}
