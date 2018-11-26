/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnos;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import Modelo.*;
import javafx.scene.control.Alert;


/**
 * FXML Controller class
 *
 * @author Patricio
 */
public class ControlController implements Initializable {

    @FXML
    private Button registrar;
    @FXML
    private Button agregar;
    @FXML
    private Button atender;
    
    public static final PriorityQueue<Turno> turnos= new PriorityQueue<>();
    private static final LinkedList<Puesto> puestos=new LinkedList<>();
    private static final PriorityQueue<Paciente> pacientes=new PriorityQueue<>();

    @FXML
    private Button btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        
        
        
    
    }    

    @FXML
    private void registerPaciente(ActionEvent event) throws IOException {
         Parent rootPaciente=FXMLLoader.load(getClass().getResource("/Vista/RegistroPacientes.fxml"));
       
        Scene scene1=new Scene(rootPaciente);
        
        Stage stagePaciente= new Stage();
        stagePaciente.setScene(scene1);
        stagePaciente.show();
    }

    @FXML
    private void addPuesto(ActionEvent event) {
        
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Ingrese información sobre el nuevo puesto");
        dialog.setHeaderText(null);
		

	ButtonType guardarButtonType = new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);
	dialog.getDialogPane().getButtonTypes().addAll(guardarButtonType, ButtonType.CANCEL);

	GridPane grid = new GridPane();
	grid.setHgap(10);
	grid.setVgap(10);
	grid.setPadding(new Insets(20, 150, 10, 10));

	TextField nombreText = new TextField();
	nombreText.setPromptText("Nombre Apellido");
	TextField puestoText = new TextField();
	puestoText.setPromptText("0"+String.valueOf(puestos.size()+1));
        puestoText.setDisable(true);

        grid.add(new Label("Nombre:"), 0, 0);
	grid.add(nombreText, 1, 0);
	grid.add(new Label("Puesto:"), 0, 1);
	grid.add(puestoText, 1, 1);

	dialog.getDialogPane().setContent(grid);
	Platform.runLater(() -> nombreText.requestFocus());

	dialog.setResultConverter(dialogButton -> {
		if (dialogButton == guardarButtonType) {
			return new Pair<>(nombreText.getText(), puestoText.getText());
			}
			return null;
		});

	Optional<Pair<String, String>> result = dialog.showAndWait();

	result.ifPresent(values -> {
            if (!values.getKey().trim().isEmpty()) {
                        Puesto p=new Puesto(values.getKey(),String.valueOf(puestos.size()+1));
                           
                        puestos.add(p);
                           
				
			}
		});
        
        
    }

    @FXML
    private void atenderPaciente(ActionEvent event) throws IOException {
                
            if(!turnos.isEmpty()  && !puestos.isEmpty()){
                Paciente atendido=turnos.peek().getPaciente();
                
                Dialog<Puesto> dialog = new Dialog<>();
		dialog.setTitle("Atencion Pacientes");
		dialog.setHeaderText(null);



		ButtonType guardarButtonType = new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(guardarButtonType, ButtonType.CANCEL);


		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		
                ComboBox<Puesto> cbxPuestos=new ComboBox<>();
		
                cbxPuestos.getItems().addAll(puestos);
		TextField prescripcion=new TextField();
                grid.add(new Label("Paciente :"), 0, 0);
                grid.add(new Label(atendido.getNombres()), 1, 0);
		grid.add(new Label("Doctor:"), 0, 1);
		grid.add(cbxPuestos, 1, 1);
                grid.add(new Label("Sintoma:"), 0, 2);
                grid.add(new Label(atendido.getSintoma().getSintoma()), 1, 2);
                grid.add(new Label("Ingrese Prescripcion"),0 , 3);
                grid.add(prescripcion, 1,3);
                
		dialog.getDialogPane().setContent(grid);
		Platform.runLater(() -> cbxPuestos.requestFocus());

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == guardarButtonType) {
                               
                            
				return cbxPuestos.getValue();
			}
			return null;
		});

		Optional<Puesto> result = dialog.showAndWait();

		result.ifPresent(values -> {
			
			getTurno(values);
		});
                }else {
                
                RegistroPacientesController.alert("NO HAY PACIENTES PARA ATENDER O PUESTOS"
                        + "DISPONIBLES");
                
                }

    }
    
    
        public Turno getTurno(Puesto p){
		
		if(!turnos.isEmpty()){
			Turno t = turnos.poll();
			FXMLDocumentController.updateLabels(t, p);
			
			return t;
		}
		return null;
	}
        
        public static void agregarTurno(Sintoma s,Paciente p){
        Turno t1=new Turno();
        t1.setSintoma(s.getSintoma());
        t1.setPrioridad(s.getPrioridad());
        t1.setTurno(generarTurno(p));
        t1.setPaciente(p);
        turnos.add(t1);
    }
       
        

    @FXML
    @SuppressWarnings("empty-statement")
    private void accionSalir(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Cerrar sistema");
		alert.setHeaderText(null);
		alert.setContentText("Confirmar cierre de sistema");

		Optional<ButtonType> result = alert.showAndWait();
                result.ifPresent(values->{;
		if (result.get() == ButtonType.OK) {
			
                        System.exit(0);
		}
                });
        
    }
    
    public static void addPaciente(Paciente p){
        
        pacientes.offer(p);
    
    }
  public static String generarTurno(Paciente p){
  
      String turno=Character.toString(p.getNombres().charAt(0))+
              Character.toString(p.getApellidos().charAt(0))
              +(int)(Math.random() * 50) + 1;
              return turno;
  }
}
