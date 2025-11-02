package com.TurkcellTakim7.member_service.application.dto;


import java.time.LocalDate;

public record CreatedMemberRepsonse(String name, String surname,
String email, String phoneNumber,String  address, LocalDate membershipDate,
String membershipLeve){

}

