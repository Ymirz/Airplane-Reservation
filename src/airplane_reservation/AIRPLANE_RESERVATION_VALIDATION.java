/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airplane_reservation;

import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Myles
 */
public class AIRPLANE_RESERVATION_VALIDATION {

    AIRPLANE_RESERVATION_FILE_OUT fileOut;
    AIRPLANE_RESERVATION_MAIN main;

    public boolean validZipCode = true;
    public boolean validFName = true;
    public boolean validLName = true;
    public boolean validStreetAddress = true;
    public boolean validFlightNum = true;
    public boolean validSeatNumber = true;
    public boolean validCity = true;

    public boolean validSeatingSmk = true;
    public boolean validSeatingNoSmk = true;
    public boolean isCustomerValid = false;

    public int flightNumber;
    public String seatNumber;
    public String zipCode;

    public String firstName;
    public String lastName;
    public String streetAddress;
    public String city;
    
    
    String error = "-fx-background-color: #F70000";
    String secColor = "-fx-background-color: #ffffff";

    public boolean seatsFilled = false;

    public AIRPLANE_RESERVATION_VALIDATION(boolean[] seatingChart, boolean isSmoking, TextField tfFlightNum, TextField tfSeatNum, TextField tfFirstName, TextField tfLastName, TextField tfStrtAddress, TextField tfCity, TextField tfZipCode) throws IOException {
        
        setFlightNum(tfFlightNum);
        setSeatNum(tfSeatNum);
        setZipCode(tfZipCode);
        setFirstName(tfFirstName);
        setLastName(tfLastName);
        setStreetAddress(tfStrtAddress);
        setCity(tfCity);
        
  
        checkValidation();

        System.out.println("Customer Valid: " + isCustomerValid);
        
        if (!seatsFilled && isCustomerValid) {
            
          setSeatAvailabilitySmoking(seatingChart, isSmoking);

          setSeatAvailabilityNotSmoking(seatingChart, isSmoking);

          checkSeatsFilled(seatingChart, tfFlightNum);
          
            fileOut = new AIRPLANE_RESERVATION_FILE_OUT(flightNumber, seatNumber, firstName, lastName, streetAddress, city, zipCode);
        }

    }

    public void setFlightNum(TextField tfFlightNum) {
        try {
            tfFlightNum.setStyle(secColor);
            this.flightNumber = Integer.valueOf(tfFlightNum.getText());
        } catch (NumberFormatException EO) {
            tfFlightNum.setStyle(error);
            validFlightNum = false;
        }
    }

    public void setSeatNum(TextField tfSeatNum) {
        tfSeatNum.setStyle(secColor);
        this.seatNumber = tfSeatNum.getText();

        if (seatNumber.equals("")) {
            tfSeatNum.setStyle(error);
            validSeatNumber = false;
        }
    }

    public void setZipCode(TextField tfZipCode) {

        tfZipCode.setStyle(secColor);
        this.zipCode = tfZipCode.getText();

        if (zipCode.equals("")) {
            tfZipCode.setStyle(error);
            validZipCode = false;
        }
    }

    public void setFirstName(TextField tfFirstName) {
        tfFirstName.setStyle(secColor);
        this.firstName = tfFirstName.getText();

        if (firstName.equals("")) {
            tfFirstName.setStyle(error);
            validFName = false;
        }
    }

    public void setLastName(TextField tfLastName) {
        tfLastName.setStyle(secColor);
        this.lastName = tfLastName.getText();

        if (lastName.equals("")) {
            tfLastName.setStyle(error);
            validLName = false;
        }
    }

    public void setStreetAddress(TextField tfStreetAddress) {
        tfStreetAddress.setStyle(secColor);
        this.streetAddress = tfStreetAddress.getText();

        if (streetAddress.equals("")) {
            tfStreetAddress.setStyle(error);
            validStreetAddress = false;
        }
    }

    public void setCity(TextField tfCity) {
        tfCity.setStyle(secColor);
        this.city = tfCity.getText();

        if (city.equals("")) {
            tfCity.setStyle(error);
            validCity = false;
        }
    }

    public void checkValidation() {
        if (validCity && validFName && validLName && validStreetAddress && validZipCode && validFlightNum && validSeatNumber) {
            isCustomerValid = true; 
        }
    }

    public void checkSeatsFilled(boolean[] seatingChart, TextField tfFlightNum) {

        if (seatingChart[10] == true && seatingChart[24] == true) {
            seatsFilled = true; 
        }
    }

    public void setSeatAvailabilitySmoking(boolean[] seatingChart, boolean isSmoking) {
        if (isCustomerValid && validSeatingSmk) {
            if (isSmoking) {
                for (int i = 1; i <= 10; i++) {
                    if (seatingChart[i] == false) {
                        seatingChart[i] = true;
                        System.out.println("\nSeat Taking [SMOKING] #");
                        break;
                    } else {
                        if (seatingChart[10] == true) {
                            System.out.println("Smoking Section Full");
                            validSeatingSmk = false;
                            break;
                        }
                    }
                }
            }
        }
    }

    public void setSeatAvailabilityNotSmoking(boolean[] seatingChart, boolean isSmoking) {
        if (isCustomerValid && validSeatingNoSmk) {
            if (!isSmoking) {
                for (int i = 11; i <= 24; i++) {
                    if (seatingChart[i] == false) {
                        seatingChart[i] = true;
                        System.out.println("\nSeat Taking {Not Smoking} ");
                        break;
                    } else {
                        if (seatingChart[24] == true) {
                            System.out.println("Section Full");
                            validSeatingNoSmk = false;
                            break;
                        }
                    }
                }
            }
        }
    }
}
