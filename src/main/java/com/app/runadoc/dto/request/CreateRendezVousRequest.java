package com.app.runadoc.dto.request;

import java.util.Date;

import com.app.runadoc.model.Etablissement;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CreateRendezVousRequest {

	private int price;

	private String heureStart;

	private String heureEnd;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateStart;

	private Etablissement etablissement;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
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

	public Etablissement getEtablissement() {
		return etablissement;
	}

	public void setTerrain(Etablissement etablissement) {
		this.etablissement = etablissement;
	}

}
