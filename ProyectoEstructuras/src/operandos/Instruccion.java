package operandos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 * @author wesley
 */
public class Instruccion {
    private String instruccion;
    private String operando;
    private int prioridad;
    private static Deque<Instruccion> listInstr;
    
    public Instruccion(String instruccion, String operando, int prioridad){
        this.instruccion = instruccion;
        this.operando = operando;
        this.prioridad = prioridad;
    }
    
    public static Deque<Instruccion> readFromInstruccion(String file){
        PriorityQueue<Instruccion> m = new PriorityQueue<>((Instruccion i1, Instruccion i2) 
                                                   -> i1.getPrioridad() - i2.getPrioridad());
        listInstr = new LinkedList<>();
        try{
            File f = new File(file);
            try (Scanner s = new Scanner(f)) {
                while(s.hasNextLine()){
                    String line = s.nextLine();
                    String [] a = line.split("\\|");
                    m.offer(new Instruccion(a[0], a[1], Integer.parseInt(a[2])));
                }
                while(!m.isEmpty()){
                    listInstr.push(m.poll());
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("El archivo no existe...");
        }
        return listInstr;
    }
    
    
    public String getInstruccion() {
        return instruccion;
    }

    public void setInstruccion(String instruccion) {
        this.instruccion = instruccion;
    }

    public String getOperando() {
        return operando;
    }

    public void setOperando(String operando) {
        this.operando = operando;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "Instrucciones{" + "instruccion=" + instruccion + ", operando=" + operando + ", prioridad=" + prioridad + '}';
    }
    
}
