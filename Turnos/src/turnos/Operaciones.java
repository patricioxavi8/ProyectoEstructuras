/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnos;

import Modelo.Paciente;
import Modelo.Sintoma;
import TDAs.CircularDoublyList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.util.LinkedList;
import java.util.List;



/**
 *
 * @author Patricio
 */
public class Operaciones {
    public static void guardarPaciente(Paciente p) throws IOException{
           
           try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("pacientes.dat"))){
               
                
               oos.writeObject(p);
               
           } catch (Exception e) {
               System.exit(1);
           }
               
       }
        

   
   public static List<Sintoma> readFromFile(String filename) throws FileNotFoundException{
    
        LinkedList<Sintoma> procesos;
        procesos = new LinkedList<>();
        
        try(BufferedReader oi =new BufferedReader(new FileReader(filename))){   
            String line=null;
            while ((line=oi.readLine())!=null){
            String[] l = line.split("\\|");
            procesos.add(new Sintoma(
                    String.valueOf(l[0]),
                   
                    Integer.parseInt(l[1])
                   
            ));
           
            }
        }catch(Exception e){
            
            System.exit(1);
        }
        
        return procesos;
   
   
   
   }
    
   
    public static CircularDoublyList<String> obtenerVideoFile(String filename) throws FileNotFoundException{
        CircularDoublyList<String> procesos;
        procesos = new CircularDoublyList<>();
        try(BufferedReader oi =new BufferedReader(new FileReader(filename))){   
            String line=null;
            while ((line=oi.readLine())!=null){
            procesos.addLast(line);
           
            }
        }catch(Exception e){
            
            System.exit(1);
        }

        
        
        return procesos;
        
        
       
        }
}
