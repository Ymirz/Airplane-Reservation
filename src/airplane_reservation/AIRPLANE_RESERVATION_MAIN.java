/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airplane_reservation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Myles
 */
public class AIRPLANE_RESERVATION_MAIN {

    LinkedList customerInfo = new LinkedList();

    Stage window = new Stage();

    Stage searchWindow = new Stage();

    MenuBar menuBar = new MenuBar();

    Menu option = new Menu("Options");

    MenuItem cancelSeatItm = new MenuItem("Cancel Seat");

    MenuItem displaySeatItm = new MenuItem("Display Seat");

    MenuItem displayAllSeatsItm = new MenuItem("Display All Seats");

    MenuItem printAndDisplayItm = new MenuItem("Print/Display");

    MenuItem quitItm = new MenuItem("Quit");

    public int totalSeats = 25;

    public boolean[] seatingChart = new boolean[totalSeats];

    public boolean isSmoking = false;
    
    public Label smkSectionFilledLb = new Label("[ Smoke section ] filled");
    
    public Label nSmkSectionFilledLb = new Label("[ Non smoke section ] filled");

    public TextField tfFlightNum = new TextField();
    public TextField tfSeatNum = new TextField();
    public TextField tfFirstName = new TextField();
    public TextField tfLastName = new TextField();
    public TextField tfStrtAddress = new TextField();
    public TextField tfCity = new TextField();
    public TextField tfZipCode = new TextField();

    private Label flightNumLb = new Label("Flight Number: ");
    private Label seatNumLb = new Label("Seat Number: ");
    private Label firstNameLb = new Label("First Name: ");
    private Label lastNameLb = new Label("Last Name: ");
    private Label strtAddressLb = new Label("Street Address: ");
    private Label cityLb = new Label("City: ");
    private Label zipCodeLb = new Label("Zip Code: ");
    
    private Label nFlightLb = new Label("New Flight Created !");
    private Label rFlightLb = new Label("Exceeded capacity, Flight full taking off !");

    private Label seatsFilledLb = new Label("Error: Seats Filled On This Flight");
    
    private Label smkSecFilledLb = new Label("Error: Smoking Section Filled");
    private Label nSmkSecFilledLb = new Label("Error: Non Smoking Section Filled");

    RadioButton f100Btn = new RadioButton("100");
    RadioButton f110Btn = new RadioButton("110");
    RadioButton f120Btn = new RadioButton("120");
    RadioButton f130Btn = new RadioButton("130");
    RadioButton f140Btn = new RadioButton("140");
    RadioButton f150Btn = new RadioButton("150");

    RadioButton smkBtn = new RadioButton("Smoking");
    RadioButton nSmkBtn = new RadioButton("Not Smoking");

    ToggleGroup flightTg = new ToggleGroup();
    ToggleGroup smkTg = new ToggleGroup();

    Button createBtn = new Button("Confirm");

    Button clearBtn = new Button("Clear");

    Button displaySeatBtn = new Button("Display Seat");

    File file = new File("CustomerInfo.txt");
    
    AIRPLANE_RESERVATION_QUIT resQuit;

    AIRPLANE_RESERVATION_VALIDATION validRes;

    AIRPLANE_RESERVATION_DISPLAY_ALL resDisplay;

    AIRPLANE_RESERVATION_CANCEL resCancel;

    AIRPLANE_RESERVATION_SEAT_ASSIGNMENT resAssign;
    
     AIRPLANE_RESERVATION_FILE_OUT resFile;
     
     AIRPLANE_RESERVATION_PRINT_AND_DISPLAY resPrintDisplay;
     
     public int passCount = 0;
   
     int currentFlightNum ;
     
     boolean isLabelActive = false;

    AIRPLANE_RESERVATION_MAIN() throws FileNotFoundException, IOException {

        final int APP_H = 360, APP_W = 700;
        
         nSmkSectionFilledLb.setVisible(false);
         smkSectionFilledLb.setVisible(false);
  
        // Default set up
        tfFlightNum.setEditable(false);
        nSmkBtn.setSelected(true);

        seatsFilledLb.setVisible(false);
        smkSecFilledLb.setVisible(false);
        nSmkSecFilledLb.setVisible(false);
        nFlightLb.setVisible(false);
        rFlightLb.setVisible(false);

        checkFileExists(file);

        storeCustomerInfo(file);

        GridPane root = new GridPane();

        root.setVgap(5);
        root.setHgap(5);
        root.setPadding(new Insets(10, 10, 10, 10));

        setRoot(root);

        setToggleGroups(flightTg, smkTg);

        setF100BtnAction(f100Btn);
        setF110BtnAction(f110Btn);
        setF120BtnAction(f120Btn);
        setF130BtnAction(f130Btn);
        setF140BtnAction(f140Btn);
        setF150BtnAction(f150Btn);

        createReservationBtn(createBtn);
        
        clearReservationBtn(clearBtn);

        reservationCancelMenu(cancelSeatItm);

        displaySeatMenu(displaySeatItm);

        displayAllSeatsMenu(displayAllSeatsItm);
        
        printAndDisplayMenu(printAndDisplayItm);
        
        quitMenu(quitItm);
        
        setSmokingBtnAction(smkBtn);

        setNonSmokingBtnAction(nSmkBtn);

        Scene scene = new Scene(root, APP_W, APP_H);
        
        window.setTitle("Myles Pruitt - Airplane Reservation System");
        window.setScene(scene);
        window.show();
    }

    public void createReservationBtn(Button btn) {
        btn.setOnAction((ActionEvent event) -> {
        
            try {
                validRes = new AIRPLANE_RESERVATION_VALIDATION(seatingChart, isSmoking, tfFlightNum, tfSeatNum, tfFirstName, tfLastName, tfStrtAddress, tfCity, tfZipCode);
            } catch (IOException ex) {
                Logger.getLogger(AIRPLANE_RESERVATION_MAIN.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!validRes.validSeatingSmk && !validRes.validSeatingSmk) { }
           
            System.out.println("Valid smoking area?  " + validRes.validSeatingSmk + " Valid non smoking area? " + validRes.validSeatingNoSmk);
            
            if(currentFlightNum != validRes.flightNumber)
            {
            clearSeatChart();
            currentFlightNum = validRes.flightNumber;
            passCount = 0;
            System.out.println("Setting up new flight, starting at passenger: " + passCount);
            }
            
            if (validRes.validSeatingSmk && validRes.validSeatingNoSmk && validRes.isCustomerValid) 
            {
                if (validRes.flightNumber == 100 && passCount <= 24) {
                    if (passCount == 0) {
                        clearSeatChart();
                        nFlightLb.setVisible(true);
                    } else {
                        nFlightLb.setVisible(false);
                    }
                    customerInfo.create(validRes.flightNumber, validRes.seatNumber, validRes.firstName, validRes.lastName, validRes.streetAddress, validRes.city, validRes.zipCode);

                    passCount++;
                    System.out.println("Passengers on flight 100: " + passCount);

                    if (passCount == 24) {
                        deSelectBtns();
                        passCount = 0;
                        clearField();
                    }
                }
            }
            
            if (passCount == 0) 
            {
                 rFlightLb.setVisible(true);
                 seatingChart[10] = false;
                 seatingChart[24] = false;
            }
            else
               rFlightLb.setVisible(false); 
            
               checkSeatChart();
               
               clearField();
      
            
        });
        
      
    }

    public void clearReservationBtn(Button btn) {
        btn.setOnAction((ActionEvent event) -> {

            tfSeatNum.setText("");
            tfFirstName.setText("");
            tfLastName.setText("");
            tfStrtAddress.setText("");
            tfCity.setText("");
            tfZipCode.setText("");

            f110Btn.setSelected(false);
            f120Btn.setSelected(false);
            f130Btn.setSelected(false);
            f140Btn.setSelected(false);
            f150Btn.setSelected(false);
            smkBtn.setSelected(false);
            nSmkBtn.setSelected(false);
        });
    }

    public void reservationCancelMenu(MenuItem option) {
        option.setOnAction((ActionEvent event) -> {
            resCancel = new AIRPLANE_RESERVATION_CANCEL(customerInfo);

        });

    }

    public void displaySeatMenu(MenuItem option) {
        option.setOnAction((ActionEvent event) -> {
            resAssign = new AIRPLANE_RESERVATION_SEAT_ASSIGNMENT(customerInfo);

        });
    }

    public void displayAllSeatsMenu(MenuItem option) {
        option.setOnAction((ActionEvent event) -> {
            resDisplay = new AIRPLANE_RESERVATION_DISPLAY_ALL(customerInfo);
        });
    }
    
     public void printAndDisplayMenu(MenuItem option) {
        option.setOnAction((ActionEvent event) -> {
            resPrintDisplay = new AIRPLANE_RESERVATION_PRINT_AND_DISPLAY(customerInfo);
        });
    }
     
       public void quitMenu(MenuItem option) {
        option.setOnAction((ActionEvent event) -> {
            resQuit = new AIRPLANE_RESERVATION_QUIT();
        });
    }

    public void setSmokingBtnAction(RadioButton btn) {
        btn.setOnAction((ActionEvent event) -> {
            if (btn.isSelected()) {
                isSmoking = true;
            }
        });
    }

    public void setNonSmokingBtnAction(RadioButton btn) {
        btn.setOnAction((ActionEvent event) -> {
            if (btn.isSelected()) {
                isSmoking = false;
            }
        });
    }

    public void setF100BtnAction(RadioButton f100) {
        f100.setOnAction((ActionEvent event) -> {
            if (f100.isSelected()) {
                tfFlightNum.setText("100");
            }
        });
    }

    public void setF110BtnAction(RadioButton f110) {
        f110.setOnAction((ActionEvent event) -> {
            if (f110.isSelected()) {
                tfFlightNum.setText("110");
            }
        });
    }

    public void setF120BtnAction(RadioButton f120) {
        f120.setOnAction((ActionEvent event) -> {
            if (f120.isSelected()) {
                tfFlightNum.setText("120");

            }
        });
    }

    public void setF130BtnAction(RadioButton f130) {
        f130.setOnAction((ActionEvent event) -> {
            if (f130.isSelected()) {
                tfFlightNum.setText("130");
            }
        });
    }

    public void setF140BtnAction(RadioButton f140) {
        f140.setOnAction((ActionEvent event) -> {
            if (f140.isSelected()) {
                tfFlightNum.setText("140");
            }
        });
    }

    public void setF150BtnAction(RadioButton f150) {
        f150.setOnAction((ActionEvent event) -> {
            if (f150.isSelected()) {
                tfFlightNum.setText("150");
            }
        });
    }

    public void clearSeatChart() {
        for (int i = 1; i <= 24; i++) 
        {
            seatingChart[i] = false;
        }
        validRes.validSeatingNoSmk = true;
        validRes.validSeatingSmk = true;
        validRes.seatsFilled = false;
    }
    
     public void checkSeatChart() {
     if (seatingChart[10] == true) 
         smkSectionFilledLb.setVisible(true);
     else if(seatingChart[24] == true)
         nSmkSectionFilledLb.setVisible(true);
   
     if(passCount == 0)
     {
         smkSectionFilledLb.setVisible(false);
         nSmkSectionFilledLb.setVisible(false);
     }
     
    
    }

    public void setRoot(GridPane root) {

        // Add Item to option menu
        option.getItems().addAll(cancelSeatItm, displaySeatItm, displayAllSeatsItm, printAndDisplayItm, quitItm);

        // Create Menu bar to hold menu
        menuBar.getMenus().add(option);

        root.add(menuBar, 0, 0);

        root.add(f100Btn, 3, 3);
        root.add(f110Btn, 4, 3);
        root.add(f120Btn, 5, 3);
        root.add(f130Btn, 6, 3);
        root.add(f140Btn, 7, 3);
        root.add(f150Btn, 8, 3);

        root.add(seatsFilledLb, 0, 2);
        root.add(smkSecFilledLb, 0, 2);
        root.add(nSmkSecFilledLb, 0, 2);

        root.add(flightNumLb, 0, 3);
        root.add(tfFlightNum, 1, 3);

        root.add(seatNumLb, 0, 4);
        root.add(tfSeatNum, 1, 4);

        root.add(firstNameLb, 0, 5);
        root.add(tfFirstName, 1, 5);

        root.add(lastNameLb, 0, 6);
        root.add(tfLastName, 1, 6);

        root.add(strtAddressLb, 0, 7);
        root.add(tfStrtAddress, 1, 7);

        root.add(cityLb, 0, 8);
        root.add(tfCity, 1, 8);

        root.add(zipCodeLb, 0, 9);
        root.add(tfZipCode, 1, 9);

        root.add(smkBtn, 0, 10);
        root.add(nSmkBtn, 1, 10);

        root.add(createBtn, 0, 11);
        root.add(clearBtn, 1, 11);
        
        root.add(nFlightLb,0,12);
        root.add(rFlightLb,0,12);
        
        root.add(smkSectionFilledLb,1,12);
        root.add(nSmkSectionFilledLb,1,12);

    }

    public void setToggleGroups(ToggleGroup flightTg, ToggleGroup smkTg) {
        f100Btn.setToggleGroup(flightTg);
        f110Btn.setToggleGroup(flightTg);
        f120Btn.setToggleGroup(flightTg);
        f130Btn.setToggleGroup(flightTg);
        f140Btn.setToggleGroup(flightTg);
        f150Btn.setToggleGroup(flightTg);

        smkBtn.setToggleGroup(smkTg);
        nSmkBtn.setToggleGroup(smkTg);
    }

    public void setUpSearchPanel(GridPane searchRoot) {
        searchRoot.setPadding(new Insets(10, 10, 10, 10));
        searchRoot.setVgap(5);
        searchRoot.setHgap(5);
        searchRoot.setStyle("-fx-background-color: #333333;");
    }

    public void storeCustomerInfo(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);

        scan.useDelimiter(":");

        while (scan.hasNext()) {
            String tmpFlightNumber = scan.next();
            String tmpSeatNumber = scan.next();
            String tmpFirstName = scan.next();
            String tmpLastName = scan.next();
            String tmpStreetAddress = scan.next();
            String tmpCity = scan.next();
            String tmpZipCode = scan.next();

            customerInfo.create(Integer.valueOf(tmpFlightNumber), tmpSeatNumber, tmpFirstName, tmpLastName, tmpStreetAddress, tmpCity, tmpZipCode);

        }
        customerInfo.display();
        scan.close();
    }

    public void checkFileExists(File file) throws IOException {

        if (file.createNewFile()) {
             resFile = new AIRPLANE_RESERVATION_FILE_OUT(0, "XXX", "XXX", "FILE_ACCESS", "XXX", "XXX", "0");     
        } else {
            System.out.println("File already exists");
        }
    }

    public void deSelectBtns() {
        f100Btn.setSelected(false);
        f110Btn.setSelected(false);
        f120Btn.setSelected(false);
        f130Btn.setSelected(false);
        f140Btn.setSelected(false);
        f150Btn.setSelected(false);
    }

    public void clearField() {
        tfSeatNum.setText("");
        tfFirstName.setText("");
        tfLastName.setText("");
        tfStrtAddress.setText("");
        tfCity.setText("");
        tfZipCode.setText("");
    }
}
