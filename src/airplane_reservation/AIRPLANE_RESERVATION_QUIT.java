/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airplane_reservation;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Myles
 */
public class AIRPLANE_RESERVATION_QUIT 
{
     AIRPLANE_RESERVATION_MAIN createReservation;
     
    
    Label Title = new Label("COLOSSUS AIRLINES");
    Label header = new Label("Are you sure you want to quit ?");
    
    Button yesBtn = new Button("Yes");
    Button noBtn = new Button("No");
    
    private Stage window = new Stage();
   
    AIRPLANE_RESERVATION_QUIT() 
    {

        final int APP_H = 160, APP_W = 240;
        
        StackPane root = new StackPane();
       
        root.setPadding(new Insets(10,10,10,10));
        
        setRoot(root);
        
        yesBtnAction(yesBtn, window);
        
        noBtnAction(noBtn, window);
        
        Scene scene = new Scene(root, APP_W, APP_H);
        
        window.setTitle("Myles Pruitt - Quit");
        window.setScene(scene);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void yesBtnAction(Button btn, Stage window)
    {
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
                window.close();
            }
        });
    }
    
    public void noBtnAction(Button btn, Stage window)
    {
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
            }
        });
    }
    
    public void setRoot(StackPane root)
    {
    root.getChildren().add(Title);
    root.getChildren().add(header);
    
    root.getChildren().add(yesBtn);
    root.getChildren().add(noBtn);
    
    Title.setTranslateY(-60);
    header.setTranslateY(-40);
    
    yesBtn.setTranslateX(-50);
    noBtn.setTranslateX(50);
    }
}
