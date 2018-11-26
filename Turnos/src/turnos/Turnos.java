/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Patricio
 */
public class Turnos extends Application {
    
   
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setOnCloseRequest(event->event.consume());
        stage.show();
        
        Parent control=FXMLLoader.load(getClass().getResource("/Vista/Control.fxml"));
        Scene scene2=new Scene(control);
        
        Stage stagePuesto= new Stage();
        stagePuesto.setScene(scene2);
        stagePuesto.setOnCloseRequest(e-> e.consume());
        stagePuesto.show();
        
        
        
        

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }
    

    
}
