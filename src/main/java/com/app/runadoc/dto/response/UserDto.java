package com.app.runadoc.dto.response;

import java.util.HashSet;
import java.util.Set;

import com.app.runadoc.model.Etablissement;
import com.app.runadoc.model.RendezVous;
import com.app.runadoc.model.Role;

public class UserDto {


	private String fullName;
	private String email;
	private String address;
	private String specialty;
	private String tel;
	private Set<Role> roles = new HashSet<>();
	private String etablissements;
	private Set<RendezVous> rendezvous;
	
	
	public UserDto(String fullName, String email, String address, String specialty, String tel, Set<Role> roles,
			String etablissements, Set<RendezVous> rendezvous) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.address = address;
		this.specialty = specialty;
		this.tel = tel;
		this.roles = roles;
		this.etablissements = etablissements;
		this.rendezvous = rendezvous;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getSpecialty() {
		return specialty;
	}


	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public String getEtablissements() {
		return etablissements;
	}


	public void setEtablissements(String etablissements) {
		this.etablissements = etablissements;
	}


	public Set<RendezVous> getRendezvous() {
		return rendezvous;
	}


	public void setRendezvous(Set<RendezVous> rendezvous) {
		this.rendezvous = rendezvous;
	}

	
}
