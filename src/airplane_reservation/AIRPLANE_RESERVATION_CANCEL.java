/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airplane_reservation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Myles
 */
public class AIRPLANE_RESERVATION_CANCEL {

    final int APP_H = 360, APP_W = 640;

    private Stage window = new Stage();

    private GridPane displayRoot = new GridPane();

    private Scene displayPane = new Scene(displayRoot, APP_W, APP_H);

    private TextArea txtArea = new TextArea();

    private TextField searchSeatNumber = new TextField();

    private TextField searchFirstName = new TextField();

    private TextField searchLastName = new TextField();

    private Label searchSeatNumLb = new Label("Seat Number : ");

    private Label searchFirstLb = new Label("First Name : ");

    private Label searchLastLb = new Label("LastName : ");

    public String findSeatNum;

    public String findFirstName;

    public String findLastName;

    private Button listBtn = new Button("Cancel Reservation");
    
    String error = "-fx-background-color: #F70000";
    
    String secColor = "-fx-background-color: #ffffff";

    AIRPLANE_RESERVATION_CANCEL(LinkedList customerInfo) {
        txtArea.setEditable(false);

        rootSetUp(displayRoot);

        setListBtn(listBtn, customerInfo);

        window.setScene(displayPane);
        window.setTitle("AIRPLANE RESERVATION - CANCEL SEAT");
        window.show();
    }

    public void setListBtn(Button btn, LinkedList customerInfo) {
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setSeatNum(findSeatNum);
                setFirstName(findFirstName);
                setLastName(findLastName);
                clearField();
                customerInfo.delete(findSeatNum, findFirstName, findLastName);
                txtArea.appendText(customerInfo.toString());
            }
        });
    }

    public void setSeatNum(String findSeatNum) {
        this.findSeatNum = searchSeatNumber.getText();
    }

    public void setFirstName(String findFirstName) {
        this.findFirstName = searchFirstName.getText();
    }

    public void setLastName(String findLastName) {
        this.findLastName = searchLastName.getText();
    }

    public void rootSetUp(GridPane displayRoot) {
        displayRoot.setVgap(5);
        displayRoot.setHgap(5);

        displayRoot.setPadding(new Insets(10, 10, 10, 10));

        displayRoot.add(txtArea, 1, 0);

        displayRoot.add(searchSeatNumLb, 0, 2);
        displayRoot.add(searchSeatNumber, 1, 2);

        displayRoot.add(searchFirstLb, 0, 3);
        displayRoot.add(searchFirstName, 1, 3);

        displayRoot.add(searchLastLb, 0, 4);
        displayRoot.add(searchLastName, 1, 4);

        displayRoot.add(listBtn, 1, 5);
    }

    public void clearField() {
        searchSeatNumber.setText("");
        searchFirstName.setText("");
        searchLastName.setText("");
    }
}
