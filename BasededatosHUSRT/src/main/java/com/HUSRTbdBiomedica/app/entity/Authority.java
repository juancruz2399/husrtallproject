package com.HUSRTbdBiomedica.app.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.HUSRTbdBiomedica.app.enums.RolName;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id_authority")
    private Long id_Authority;
	
	@Column(name = "authority")
	private String authority;
	/********************* GET Y SET *****************************/

	public static long getSerialVersionUID() {
        return serialVersionUID;
    }

	public Long getId_Authority() {
		return id_Authority;
	}

	public void setId_Authority(Long id_Authority) {
		this.id_Authority = id_Authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	

	
	



	
	

}
