/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airplane_reservation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AIRPLANE_RESERVATION_PRINT_AND_DISPLAY {

    final int APP_H = 360, APP_W = 640;

    private Stage window = new Stage();

    private GridPane displayRoot = new GridPane();

    private Scene displayPane = new Scene(displayRoot, APP_W, APP_H);

    private TextArea txtArea = new TextArea();

    private TextField searchFlightNumber = new TextField();
    private TextField searchSeatNumber = new TextField();
    private TextField searchFirstName = new TextField();
    private TextField searchLastName = new TextField();

    private Label searchFlightLb = new Label("Flight Number : ");
    private Label searchSeatNumLb = new Label("Seat Number : ");
    private Label searchFirstLb = new Label("First Name : ");
    private Label searchLastLb = new Label("LastName : ");

    public int findFlightNum;

    public String findSeatNum;

    public String findFirstName;

    public String findLastName;

    private Button listBtn = new Button("Display Info");

    private Button printBtn = new Button("Print Info");

    boolean isFound;

    String error = "-fx-background-color: #F70000";

    String secColor = "-fx-background-color: #ffffff";

    AIRPLANE_RESERVATION_PRINT_AND_DISPLAY(LinkedList customerInfo) {
        txtArea.setEditable(false);

        rootSetUp(displayRoot);

        setDisplayBtn(listBtn, customerInfo);

        setPrintBtn(printBtn, customerInfo);
        
        window.setScene(displayPane);
        window.setTitle("AIRPLANE RESERVATION - PRINT AND DISPLAY");
        window.show();
    }

    public void setDisplayBtn(Button btn, LinkedList customerInfo) {
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setSeatNum(findSeatNum);
                setFirstName(findFirstName);
                setLastName(findLastName);
                setFlightNumber(findFlightNum);
                clearField();

                isFound = customerInfo.printSearch(findFlightNum, findSeatNum, findFirstName, findLastName, isFound);

                if (isFound) {
                    txtArea.appendText(findFlightNum + " " + findSeatNum + " " + findFirstName + " " + findLastName + "\n");
                } else {
                    txtArea.appendText("Could not find you\n");
                }

                isFound = false;
            }
        });
    }

    public void setPrintBtn(Button btn, LinkedList customerInfo) {
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setSeatNum(findSeatNum);
                setFirstName(findFirstName);
                setLastName(findLastName);
                setFlightNumber(findFlightNum);
                clearField();

                isFound = customerInfo.printSearch(findFlightNum, findSeatNum, findFirstName, findLastName, isFound);

                if (isFound) {
                    txtArea.appendText("Printing...\n");
                    printOperation();
                } else {
                    txtArea.appendText("Could not find you, printing operation failed... \n");
                }

                isFound = false;
            }
        });
    }

    public void setFlightNumber(int findFlightNum) {
        try {
            searchFlightNumber.setStyle(secColor);
            this.findFlightNum = Integer.valueOf(searchFlightNumber.getText());
        } catch (NumberFormatException EO) {
            searchFlightNumber.setStyle(error);
        }
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

        displayRoot.add(searchFlightLb, 0, 2);
        displayRoot.add(searchFlightNumber, 1, 2);

        displayRoot.add(searchSeatNumLb, 0, 3);
        displayRoot.add(searchSeatNumber, 1, 3);

        displayRoot.add(searchFirstLb, 0, 4);
        displayRoot.add(searchFirstName, 1, 4);

        displayRoot.add(searchLastLb, 0, 5);
        displayRoot.add(searchLastName, 1, 5);

        displayRoot.add(listBtn, 0, 6);
        displayRoot.add(printBtn, 1, 6);
    }

    public void clearField() {
        searchFlightNumber.setText("");
        searchSeatNumber.setText("");
        searchFirstName.setText("");
        searchLastName.setText("");
    }

    public void printOperation() {
        String header = "COLLOSUS AIRLINES BOARDING PASS\n";
        String line1 = "\nFlight Number: " + findFlightNum;
        String line2 = "\nSeat Assignment: " + findSeatNum;
        String line3 = "\nPassenger Name: " + findFirstName + " " + findLastName;
        try {
            FileOutputStream outFile = new FileOutputStream("\\\\cts-fp.bhcc.dom\\D119");
            PrintWriter printer = new PrintWriter(outFile, true);

            printer.println(header);
            printer.println(line1);
            printer.println(line2);
            printer.println(line3);
            
            printer.print('\f');
            printer.close();
            outFile.close();
            printer = null;
            outFile = null;
            txtArea.appendText("Finished printing operation\n");

        } catch (IOException e) {
            txtArea.appendText("Error with the printer ! \n" + e);
        }

    }
}
