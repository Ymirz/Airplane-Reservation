/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airplane_reservation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Myles
 */
public class AIRPLANE_RESERVATION_FILE_OUT 
{
    
    
    File file = new File("CustomerInfo.txt");
    
    FileOutputStream fos = new FileOutputStream(file, true);
    
    BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
    
    AIRPLANE_RESERVATION_FILE_OUT(int flightNumber, String seatNumber, String firstName, String lastName, String streetAddress, String city, String zipCode) throws FileNotFoundException, IOException
    {  
    writeToFile(writer, flightNumber, seatNumber, firstName, lastName, streetAddress, city, zipCode);        
    }
    
    public void writeToFile(BufferedWriter writer, int flightNumber, String seatNumber, String firstName, String lastName, String streetAddress, String city, String zipCode) throws IOException
    {
    writer.write(String.valueOf(flightNumber));
    writer.write(':');
    writer.write(seatNumber);
    writer.write(':');
    writer.write(firstName);
    writer.write(':');
    writer.write(lastName);
    writer.write(':');
    writer.write(streetAddress);
    writer.write(':');
    writer.write(city);
    writer.write(':');
    writer.write(zipCode);
    writer.write(':');
    
    writer.close();
    }
}
