package com.app.runadoc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "rendezvous")
public class RendezVous {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double price;

	private String heureStart;

	private String heureEnd;

	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name = "date_start", nullable = false)
	private Date dateStart;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "etablissement_id")
	private Etablissement etablissement;

	public RendezVous() {
		super();
	}

	public RendezVous(int price, String heureStart, String heureEnd, Date dateStart, User user, Etablissement etablissement) {
		super();
		this.price = price;
		this.heureStart = heureStart;
		this.heureEnd = heureEnd;
		this.dateStart = dateStart;
		this.user = user;
		this.etablissement = etablissement;
	}

	public Long getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getHeureStart() {
		return heureStart;
	}

	public void setHeureStart(String heureStart) {
		this.heureStart = heureStart;
	}

	public String getHeureEnd() {
		return heureEnd;
	}

	public void setHeureEnd(String heureEnd) {
		this.heureEnd = heureEnd;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Etablissement getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}

}
