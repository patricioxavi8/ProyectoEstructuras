/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Objects;

/**
 *
 * @author Patricio
 */
public class Puesto {
    private String encargado;
	private String puesto;
	public Puesto(String encargado, String puesto) {
		this.encargado = encargado;
		this.puesto = puesto;
	}

	public Puesto(){
	
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	
	
	public String getEncargado() {
		return encargado;
	}

	public void setEncargado(String encargado) {
		this.encargado = encargado;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 23 * hash + Objects.hashCode(this.encargado);
		hash = 23 * hash + Objects.hashCode(this.puesto);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Puesto other = (Puesto) obj;
		if (!Objects.equals(this.encargado, other.encargado)) {
			return false;
		}
		if (!Objects.equals(this.puesto, other.puesto)) {
			return false;
		}
		return true;
	}
	
	

	@Override
	public String toString() {
		return  puesto+ " - "+encargado;
	}
	
    
}
