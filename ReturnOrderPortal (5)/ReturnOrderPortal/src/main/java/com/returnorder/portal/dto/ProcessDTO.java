package com.returnorder.portal.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProcessDTO {

    @JsonProperty
    private String requestId;
    @JsonProperty
    private Integer processingCharge;
    @JsonProperty
    private Double packagingAndDeliveryCharge;
    @JsonProperty
    private String dateOfDelivery;
    
}
