/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import java.io.Serializable;

/**
 *
 * @author Patricio
 */
public class Paciente implements Serializable{
    
    private String nombres;
    private String apellidos;
    private String cedula;
    private String genero;
    private int edad;
    private Sintoma sintoma;

    public Paciente(String nombres, String apellidos, String cedula, String genero, int edad, Sintoma sintoma) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.genero = genero;
        this.edad = edad;
        this.sintoma = sintoma;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Sintoma getSintoma() {
        return sintoma;
    }

    public void setSintoma(Sintoma sintoma) {
        this.sintoma = sintoma;
    }
    public int compareTo(Paciente o) {
		int res= Integer.compare(this.sintoma.getPrioridad(), o.sintoma.getPrioridad());

		return res;
	} 
    
}
