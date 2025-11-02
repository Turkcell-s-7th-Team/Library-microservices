package com.TurkcellTakim7.author_service.domain.valueobjects;

public record AuthorEmail(String email) {

    public AuthorEmail {
        String e = requireNonBlank(email);
        requireNoWhitespace(e);
        requireSingleAtNotOnEdges(e);
        email = e;
    }

    private static String requireNonBlank(String s) {
        if (s == null) throw new IllegalArgumentException("Email required");
        String t = s.trim();
        if (t.isEmpty()) throw new IllegalArgumentException("Email required");
        return t;
    }

    private static void requireNoWhitespace(String v) {
        for (int i = 0; i < v.length(); i++) {
            if (Character.isWhitespace(v.charAt(i))) {
                throw new IllegalArgumentException("Email must not contain spaces");
            }
        }
    }

    private static void requireSingleAtNotOnEdges(String v) {
        int at = v.indexOf('@');
        if (at < 0 || at != v.lastIndexOf('@') || at == 0 || at == v.length() - 1) {
            throw new IllegalArgumentException("Email must contain a single '@' not at start or end");
        }
    }

}
