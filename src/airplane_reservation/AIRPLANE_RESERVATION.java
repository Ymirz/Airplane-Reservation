/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airplane_reservation;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Myles
 */
public class AIRPLANE_RESERVATION extends Application {
    
    AIRPLANE_RESERVATION_MAIN createReservation;
    
    Label Title = new Label("COLOSSUS AIRLINES");
    
    Label header = new Label("RESERVATION SYSTEM");
    
    Button enterBtn = new Button("ENTER");
    
    Button quitBtn = new Button("QUIT");
   
    @Override
    public void start(Stage window) {

        final int APP_H = 160, APP_W = 240;
        
        StackPane root = new StackPane();
       
        root.setPadding(new Insets(10,10,10,10));
        
        setRoot(root);
        
        enterBtnAction(enterBtn, window);
        
        exitBtnAction(quitBtn);
        
        Scene scene = new Scene(root, APP_W, APP_H);
        
        window.setTitle("Myles Pruitt - Airplane Reservation System");
        window.setScene(scene);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void enterBtnAction(Button btn, Stage window)
    {
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    createReservation = new AIRPLANE_RESERVATION_MAIN();
                    window.close();
                } catch (IOException ex) {
                    Logger.getLogger(AIRPLANE_RESERVATION.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void exitBtnAction(Button btn)
    {
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                   System.exit(0);
            }
        });
    }
    
    public void setRoot(StackPane root)
    {
    root.getChildren().add(Title);
    root.getChildren().add(header);
    
    root.getChildren().add(enterBtn);
    root.getChildren().add(quitBtn);
    
    Title.setTranslateY(-60);
    header.setTranslateY(-40);
    
    enterBtn.setTranslateX(-50);
    quitBtn.setTranslateX(50);
    }
}
