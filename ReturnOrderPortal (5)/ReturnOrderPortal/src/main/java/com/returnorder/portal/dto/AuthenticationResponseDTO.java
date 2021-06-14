package com.returnorder.portal.dto;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthenticationResponseDTO {
   
    @JsonProperty
    private String username;
    @JsonProperty
    private String jwtAuthToken;


}
