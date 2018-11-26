/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.Puesto;

/**
 *
 * @author Patricio
 */
public class Turno implements Comparable<Turno>{
    	private String sintoma;
	private int prioridad;
	private String turno;
	private Puesto puesto;
        private long time;
        private Paciente paciente;

    public Turno() {
        this.time=System.currentTimeMillis();
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }


    

    public String getSintoma() {
        return sintoma;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Puesto getPuesto() {
        return puesto;
    }
    

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
    
            @Override
    public int compareTo(Turno o) {
		int res= Integer.compare(this.getPrioridad(), o.getPrioridad());
		if(res==0){
			return Long.compare(time, o.getTime());
		}
		return res;
	}
}
