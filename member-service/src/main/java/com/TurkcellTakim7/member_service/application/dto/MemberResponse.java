package com.TurkcellTakim7.member_service.application.dto;

import java.util.Date;
import java.util.UUID;

public record MemberResponse(UUID id, String name, String surname, String email, String phoneNumber, String address,
        Date membershipDate, String membershipLevel) {

}
