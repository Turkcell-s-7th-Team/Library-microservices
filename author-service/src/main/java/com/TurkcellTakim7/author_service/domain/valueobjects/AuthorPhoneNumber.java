package com.TurkcellTakim7.author_service.domain.valueobjects;

public record AuthorPhoneNumber(String phoneNumber) {

    public AuthorPhoneNumber {
        String pn = requireNonBlank(phoneNumber);
        requireNoWhitespace(pn);
        phoneNumber = pn;
    }

    private static String requireNonBlank(String s) {
        if (s == null) throw new IllegalArgumentException("Phone required");
        String t = s.trim();
        if (t.isEmpty()) throw new IllegalArgumentException("Phone required");
        return t;
    }

    private static void requireNoWhitespace(String v) {
        for (int i = 0; i < v.length(); i++) {
            if (Character.isWhitespace(v.charAt(i))) {
                throw new IllegalArgumentException("Phone must not contain spaces");
            }
        }
    }

}
