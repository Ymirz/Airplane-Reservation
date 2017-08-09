/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airplane_reservation;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Myles
 */
public class AIRPLANE_RESERVATION_DISPLAY_ALL {

    final int APP_H = 360, APP_W = 640;

    private Stage window = new Stage();

    private GridPane displayRoot = new GridPane();

    private Scene displayPane = new Scene(displayRoot, APP_W, APP_H);

    private TextArea txtArea = new TextArea();

    private Button displayBtn = new Button("Display List");

    AIRPLANE_RESERVATION_DISPLAY_ALL(LinkedList customerInfo) {
        rootSetUp(displayRoot);

        setDisplayBtn(displayBtn, customerInfo);

        window.setScene(displayPane);
        window.setTitle("AIRPLANE RESERVATION - DISPLAY ALL SEATS");
        window.show();
    }

    public void setDisplayBtn(Button btn, LinkedList customerInfo) {
        btn.setOnAction((ActionEvent event) -> {
            System.out.println(customerInfo.toString());
            txtArea.appendText(customerInfo.toString());
        });
    }

    public void rootSetUp(GridPane displayRoot) {
        displayRoot.add(txtArea, 0, 0);
        displayRoot.add(displayBtn, 0, 1);
    }
}
