package com.returnorder.portal.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "LoginDetails")

@Getter
@Setter
@ToString
public class LoginModel {
	
	@Id
   
    @Column(name="UserName")
	private String userName;
	
	@Column(name="password")
	private String password;
}
