package com.app.runadoc.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "etablissements")
public class Etablissement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String address;

	private String type;

	private String image;

	private int nbrePersonneReserve;

	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean disponible;

	@Column(name = "created_at", nullable = false)
	@CreationTimestamp
	private Date createdAt;

	@OneToMany(targetEntity = RendezVous.class, mappedBy = "id", orphanRemoval = false, fetch = FetchType.LAZY)
	private Set<RendezVous> rendezVous;

	public Long getId() {
		return id;
	}

	public Etablissement(String name, String address, boolean disponible, Date createdAt, int nbrePersonneReserve,
			String image, String type) {
		super();
		this.name = name;
		this.address = address;
		this.disponible = disponible;
		this.createdAt = createdAt;
		this.nbrePersonneReserve = nbrePersonneReserve;
		this.image = image;
		this.type = type;
	}

	public Etablissement() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public int getNbrePersonneReserve() {
		return nbrePersonneReserve;
	}

	public void setNbrePersonneReserve(int nbrePersonneReserve) {
		this.nbrePersonneReserve = nbrePersonneReserve;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<RendezVous> getRendezVous() {
		return rendezVous;
	}

	public void setRendezVous(Set<RendezVous> rendezVous) {
		this.rendezVous = rendezVous;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
