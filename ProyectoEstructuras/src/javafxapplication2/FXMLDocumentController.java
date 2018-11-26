
package javafxapplication2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import operandos.Instruccion;
import operandos.Memoria;

/**
 *
 * @author Usuario
 */
public class FXMLDocumentController implements Initializable {
    
   
    @FXML
    private Button procesar;
    
    Deque<Instruccion> instrucciones;
    Queue<Memoria> listMemory;
    @FXML
    private ListView<String> listProcesos;
    private Stack<Integer> pilaDatos;

    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instrucciones = Instruccion.readFromInstruccion("instrucciones.txt");
     
        
         listMemory = Memoria.readFromMemoria("memoria.txt");
         pilaDatos = new Stack<>();

    }  
 

    public  void lecturaDeDatos(Deque<Instruccion> ins, Queue<Memoria> mem) throws IOException{
        Deque<Instruccion> invert = invertir(ins);
        for (Instruccion i : invert){
            mem.forEach((j) -> {
                if (i.getInstruccion().equalsIgnoreCase("push")){
                    if (i.getOperando().equals(j.getDireccionMemoria()))
                        pilaDatos.push(j.getValor());
                }else {
                }
            });
if (i.getInstruccion().equalsIgnoreCase("add")){
                int x = (int)pilaDatos.pop();
                Integer suma = (Integer)(x + (int)pilaDatos.pop());
                pilaDatos.add(suma);
            }else if(i.getInstruccion().equalsIgnoreCase("sub")){
                int x = (int)pilaDatos.pop();
                Integer resta = (Integer)(x - (int)pilaDatos.pop());
                pilaDatos.add(resta);
            }else if(i.getInstruccion().equalsIgnoreCase("mul")){
                int x = (int)pilaDatos.pop();
                Integer multiplicacion = (Integer)(x * (int)pilaDatos.pop());
                pilaDatos.add(multiplicacion);
            }else if(i.getInstruccion().equalsIgnoreCase("div")){
                int x = (int)pilaDatos.pop();
                Integer division = (Integer)(x / (int)pilaDatos.pop());
                pilaDatos.add(division);
            }else if(i.getInstruccion().equalsIgnoreCase("pop")){
                Memoria tmp = new Memoria(i.getOperando(), pilaDatos.pop());
                Memoria.getListMemory().add(tmp);
                escritura(tmp.getDireccionMemoria(),valueOf(tmp.getValor()));
            }
        }
        
    }
        
    public void Datos(){
        pilaDatos.forEach((i) -> {
            listProcesos.getItems().add(String.valueOf(i));
        });
        
    }
    
    public static Deque<Instruccion> invertir(Deque<Instruccion> ins){
        Deque<Instruccion> invert = new LinkedList<>();
        while(!ins.isEmpty()){
            invert.push(ins.poll());
        }return invert;
    }
    
    public void escritura(String op, String valor) throws IOException{
        String ruta = "memoria.txt";
        File archivo = new File(ruta);
        
        if(archivo.exists()) {
            try(BufferedWriter bw=new BufferedWriter(new FileWriter(archivo,true))){
            
            bw.write("\n"+op+"|"+valor); }
            catch(Exception e){
            System.exit(1);
        }        
        }
        }
    

    @FXML
    private void accionProcesar(ActionEvent event) throws IOException {
        lecturaDeDatos(instrucciones, listMemory);
        Datos();
    }
}
