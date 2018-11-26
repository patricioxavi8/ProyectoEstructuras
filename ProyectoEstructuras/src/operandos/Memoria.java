/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operandos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author wesley
 */
public class Memoria {
    private String direccionMemoria;
    private int valor;
    private static LinkedList<Memoria> listMemory;
    
    public Memoria(String direccion, int valor){
        this.direccionMemoria = direccion;
        this.valor = valor;
    }

    public static LinkedList<Memoria> readFromMemoria(String file){
        listMemory = new LinkedList<>();
        try{
            File f = new File(file);
            try (Scanner s = new Scanner(f)) {
                while(s.hasNextLine()){
                    String line = s.nextLine();
                    String [] a = line.split("\\|");
                    listMemory.add(new Memoria(a[0], Integer.parseInt(a[1])));
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("El archivo no existe...");
        }return listMemory;
    }
    
   
    
    public String getDireccionMemoria() {
        return direccionMemoria;
    }

    public void setDireccionMemoria(String direccionMemoria) {
        this.direccionMemoria = direccionMemoria;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public static LinkedList<Memoria> getListMemory() {
        return listMemory;
    }

    public static void setListMemory(LinkedList<Memoria> listMemory) {
        Memoria.listMemory = listMemory;
    }
    
    @Override
    public String toString() {
        return "Memoria{" + "direccionMemoria=" + direccionMemoria + ", valor=" + valor + '}';
    }
    
}
