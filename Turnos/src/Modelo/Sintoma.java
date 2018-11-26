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
public class Sintoma implements Serializable{
    
    private String sintoma;
    private int prioridad;

    public Sintoma(String sintoma, int prioridad) {
        this.sintoma = sintoma;
        this.prioridad = prioridad;
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

    @Override
    public String toString() {
        return sintoma;
    }
    
    
    
}
