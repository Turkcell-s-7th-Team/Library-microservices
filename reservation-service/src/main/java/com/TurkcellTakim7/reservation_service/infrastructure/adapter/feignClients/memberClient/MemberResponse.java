package com.TurkcellTakim7.reservation_service.infrastructure.adapter.feignClients.memberClient;

import java.util.Date;
import java.util.UUID;

public record MemberResponse(
        UUID id,
        String name,
        String surname,
        String email,
        String phoneNumber,
        String address,
        Date membershipDate,
        String membershipLevel
) {
}
