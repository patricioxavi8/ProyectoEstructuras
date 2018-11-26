/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnos;



import java.io.FileNotFoundException;
import java.net.URL;

import java.util.ResourceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import Modelo.*;
import java.io.IOException;
import java.util.PriorityQueue;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Patricio
 */
public class RegistroPacientesController implements Initializable {

    @FXML
    private Button botonRegistrar;
    @FXML
    private TextField lblnombre;
    @FXML
    private TextField lblapellido;
    @FXML
    private TextField lblcedula;
    @FXML
    private TextField lbledad;
    @FXML
    private ComboBox<String> cbxgenero;
    @FXML
    private ComboBox<Sintoma> cbxsintomas;
   
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cbxgenero.getItems().addAll("Masculino","Femenino");
        try {
            cbxsintomas.getItems().addAll(Operaciones.readFromFile("sintomas.txt"));
            
            
            // TODO
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegistroPacientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void accionRegistrar(ActionEvent event) throws IOException {
        if(validarCampos()){
        
        String nombres =lblnombre.getText();
        String apellidos=lblapellido.getText();
        String cedula=lblcedula.getText();
        int edad=Integer.parseInt(lbledad.getText());
        String genero=cbxgenero.getValue();
       
        
        System.out.println("TURNO REGISTRADO");

        Paciente p1=new Paciente(nombres, apellidos, cedula, genero, edad, cbxsintomas.getValue());
        ControlController.agregarTurno(cbxsintomas.getValue(),p1);
        
        Operaciones.guardarPaciente(p1);
        
        alert("Paciente Registrado con exito");
        Stage stage = (Stage) botonRegistrar.getScene().getWindow();
   
        stage.close();
        
        }
        else {
        
            alert("NO SE HAN COMPLETADO TODOS LOS CAMPOS O EXISTE ALGUN"
                    + "ERROR CON LOS DATOS INGRESADOS");
        }

    }
    

    public boolean validarCampos(){ //validando todos los campos del formulario
        
        return !(lblnombre.getText().trim().equals("") ||
                lblapellido.getText().trim().equals("") || lbledad.getText().trim().equals("") ||
                lblcedula.getText().trim().equals("") || !isInteger(lbledad.getText()) || cbxgenero.getValue()==null
                || cbxsintomas.getValue()==null || !RegexMatcher.testcedula(lblcedula.getText()));
            
                   
    
    
    }
    

    
    public static void alert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guardado");
        alert.setHeaderText(message);
        
        alert.showAndWait();
    
    
    
    }
    
    public boolean isInteger(String numero){
    try{
        Integer.parseInt(numero);
        return true;
    }catch(NumberFormatException e){
        return false;
    }
}
    
 

}