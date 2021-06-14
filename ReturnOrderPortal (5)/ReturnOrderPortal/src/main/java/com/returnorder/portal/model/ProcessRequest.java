package com.returnorder.portal.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ReturnOrderRequest")

@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@ToString
@Component
public class ProcessRequest {
	@Id

    @Column(name="UserName")
    @JsonProperty 
    private String userName;

    @Column(name="ContactNumber")
    @JsonProperty
    private Long contactNumber;

    @Column(name="CreditCardNumber")
    @JsonProperty
    private Integer creditCardNumber;
    
    @Column(name="CardLimit")
    @JsonProperty
    private Integer cardLimit;

    @Column(name="IsPriorityRequest")
    @JsonProperty
    private Boolean isPriorityRequest;

    @Column(name="ComponentType") 
    @JsonProperty
    private String componentType;

    @Column(name="ComponentName")
    @JsonProperty
    private String componentName;

    @Column(name="Quantity")
    @JsonProperty
    private Integer quantity;
}
