package com.TurkcellTakim7.member_service.domain.valueobjects;

import java.io.Serializable;
import java.util.Objects;

public record MembershipLevel(String value) implements Serializable {

    public static final MembershipLevel STANDARD = new MembershipLevel("STANDARD");
    public static final MembershipLevel GOLD = new MembershipLevel("GOLD");
    public static final MembershipLevel BANNED = new MembershipLevel("BANNED");

    public MembershipLevel {
        Objects.requireNonNull(value, "MembershipLevel cannot be null");
        if (!value.equals("BANNED") && !value.equals("STANDARD") && !value.equals("GOLD")) {
            throw new IllegalArgumentException("Invalid MembershipLevel: " + value);
        }
    }

    public static MembershipLevel from(String s) {

        if (s.startsWith("MembershipLevel[value=") && s.endsWith("]")) {
            s = s.substring("MembershipLevel[value=".length(), s.length() - 1);
        }

        return switch (s) {
            case "STANDARD" -> STANDARD;
            case "GOLD" -> GOLD;
            case "BANNED" -> BANNED;
            default -> throw new IllegalArgumentException("Invalid MembershipLevel: " + s);
        };
    }

    @Override
    public String toString() {
        return value;
    }
}