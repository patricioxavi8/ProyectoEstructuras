/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnos;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import TDAs.CircularDoublyList;
import TDAs.Node;
import Modelo.Puesto;
import Modelo.Tuple;
import Modelo.Turno;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
/**
 *
 * @author Patricio
 */
public class FXMLDocumentController implements Initializable {
    private boolean running;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.US);
    private static final Queue<Tuple<Label>> emptySpots=new LinkedList<>();
   
    @FXML
    private MediaView media_video;
    
    private MediaPlayer player;
    private CircularDoublyList videos;
    private Node<String> videoactual;
    @FXML
    private Button nextButton;
    @FXML
    private Button backButton;
    
    
    @FXML
  
    
    private  VBox vbox;
    @FXML
    
    
    private GridPane gridPane;
    private static final int ROWS = 3;
    @FXML
    private Label timeLabel;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        running=true;
        new Thread(new TimeThread()).start();
        Label turno = new Label("Turno");
		
		Label puesto = new Label("Puesto");
		
		gridPane.addRow(0,turno,puesto);
                
		gridPane.setGridLinesVisible(true);
                for(int i=0;i<ROWS;i++){
			Label tmp1 = new Label();
			Label tmp2 = new Label();
			
			emptySpots.offer(new Tuple<>(tmp1,tmp2));
			gridPane.addRow(i+1,tmp1, tmp2);
		}
                gridPane.getColumnConstraints().addAll(new ColumnConstraints(),new ColumnConstraints());
		gridPane.getColumnConstraints().forEach(a->{
			a.setHgrow(Priority.ALWAYS);
			a.setHalignment(HPos.CENTER);
			
		});
                gridPane.getRowConstraints().addAll(new RowConstraints(),new RowConstraints());
		gridPane.getRowConstraints().forEach(a->{
			a.setValignment(VPos.CENTER);
		
		});
        Platform.runLater(()->{
        
        
            try {
                
                videos=Operaciones.obtenerVideoFile("src/recursos/videos.txt");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        videoactual=videos.first;
      
        
        playVideo();
        });
    } 
    
    public void playVideo(){
        
        final File file = new File(videoactual.getData());
  
    final String MEDIA_URL = file.toURI().toString();
    // Create a Media
       Media media;
       media = new Media(MEDIA_URL);
    // Create a Media Player
        player = new MediaPlayer(media);
        
        media_video.setMediaPlayer(player);
    
       
        media_video.setPreserveRatio(false);
         
         media_video.setSmooth(true);
         media_video.setVisible(true);
         player.play();
         player.setOnEndOfMedia(() -> {
             videoactual=videoactual.getNext();
             playVideo();
        });
                 }
    @FXML
    private void accionSiguiente(ActionEvent event) {
        player.stop();
                
       
        videoactual=videoactual.getNext();
        playVideo();
 
         player.play();
         player.setOnEndOfMedia(() -> {
             videoactual=videoactual.getNext();
             playVideo();
        });
    }
    @FXML
    private void accionAtras(ActionEvent event) {
        player.stop();
        
        videoactual=videoactual.getPrevious();
        playVideo();
   

         player.play();
         player.setOnEndOfMedia(() -> {
             videoactual=videoactual.getNext();
             playVideo();
        });
    }
    
	private static Tuple<Label> getSpot(){
		Tuple<Label> tmp = emptySpots.poll();
		emptySpots.offer(tmp);
		return tmp;
	}
	public static void updateLabels(Turno t, Puesto p) {
		
		
		for (int i = 0; i < 3; i++) {
			Tuple<Label> tmp = getSpot();
			if (tmp.getSecond().getText().equals(p.getPuesto())) {
				tmp.getFirst().setText(t.getTurno());
				return;
			}
		}
		Tuple<Label> tmp = getSpot();
		tmp.getFirst().setText(t.getTurno());
		tmp.getSecond().setText(p.getPuesto());
	}
    
        private class TimeThread implements Runnable {
		@Override
		public void run() {
			
			while (running) {
				Platform.runLater(() -> {
					timeLabel.setText(LocalTime.now().format(formatter));
				});
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
                                    System.exit(1);
				}
			}
		}
	}
    }

