/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airplane_reservation;

/**
 *
 * @author Myles
 */
class Node implements Comparable<Node> {

    private int cFlightNumber;
    private String cSeatNumber;
    private String cFirstName;
    private String cLastName;
    private String cStreetAddress;
    private String cCity;
    private String cZipCode;

    public Node next;

    Node(int fn, String sn, String cfa, String cla, String cc, String cty, String zp) {
        cFlightNumber = fn;
        cSeatNumber = sn;
        cFirstName = cfa;
        cLastName = cla;
        cStreetAddress = cc;
        cCity = cty;
        cZipCode = zp;
    }

    public int getFlightNumber() {
        return this.cFlightNumber;
    }

    public String getSeatNumber() {
        return this.cSeatNumber;
    }

    public String getFirstName() {
        return this.cFirstName;
    }

    public String getLastName() {
        return this.cLastName;
    }

    public String getStreetAddress() {
        return this.cStreetAddress;
    }

    public String getCity() {
        return this.cCity;
    }

    public String getZipCode() {
        return this.cZipCode;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public int compareTo(Node o) {
        int i = o.cFlightNumber;

        if (cFirstName.compareTo(o.cFirstName) < 0) {
            if (i != 0) {
                return i;
            }
        }

        return 0;
    }
}

public class LinkedList {

    private Node start;
    public boolean fNameInvalid = true;
    public boolean lNameInvalid = true;
    public boolean sNumInvalid = true;

    public void create(int cFlightNumber, String cSeatNumber, String cFirstName, String cLastName, String cStreetAddress, String cCity, String cZipCode) 
    {
        Node newNode = new Node(cFlightNumber, cSeatNumber, cFirstName, cLastName, cStreetAddress, cCity, cZipCode);

        Node prev = null;
        Node current = start;
        
        while (current != null && current.getFlightNumber() < cFlightNumber) {
            prev = current;
            current = current.next;
        }
            
        while (current != null && current.getLastName().compareTo(cLastName) <= 0) {
            prev = current;
            current = current.next;
        }
        
          while (current != null && current.getFirstName().compareTo(cFirstName) <= 0) {
            prev = current;
            current = current.next;
        }

        if (prev == null) {
            start = newNode;
        } else {
            prev.next = newNode;
        }
        newNode.next = current;
    }

    public void display() {
        if (start == null) {
            System.out.println("\nThe list is empty!");
        } else {
            Node temp = start;

            while (temp != null) {
                System.out.println("Flight Number : " + temp.getFlightNumber() + " Seat Number: " + temp.getSeatNumber() + " Name : " + temp.getFirstName() + " " + temp.getLastName() + " " + temp.getStreetAddress() + " " + temp.getCity() + " " + temp.getZipCode());
                temp = temp.getNext();
            }
        }
    }

    public boolean search(String seatNumber, String fName, String lName, Boolean isFound) {
        Node prev = null;
        Node current = start;

        while (current.next != null) {
            if (current.getNext().getSeatNumber().equals(seatNumber) && current.getNext().getFirstName().equals(fName) && current.getNext().getLastName().equals(lName)) {
                System.out.println("Found You: " + current.getNext().getFirstName() + " " + current.getNext().getLastName());
                return isFound = true;
            } else {
                System.out.println("Not Found");
                current = current.getNext();
            }
        }
        return false;
    }
    
     public boolean printSearch(int FlightNumber, String seatNumber, String fName, String lName, Boolean isFound) {
        Node prev = null;
        Node current = start;

        while (current.next != null) {
            if (current.getNext().getFlightNumber() == FlightNumber && current.getNext().getSeatNumber().equals(seatNumber) && current.getNext().getFirstName().equals(fName) && current.getNext().getLastName().equals(lName)) {
                System.out.println("Found You: " + current.getNext().getFirstName() + " " + current.getNext().getLastName());
                return isFound = true;
            } else {
                System.out.println("Not Found");
                current = current.getNext();
            }
        }
        return false;
    }

    public void delete(String seatNumber, String fName, String lName) {
        Node previous = start;
        Node temp = start;

        Node nextPosition1, nextPosition2;

        boolean foundFirstName = false;

        while (temp.next != null) {
            if (temp.getNext().getSeatNumber().equals(seatNumber) && temp.getNext().getFirstName().equals(fName) && temp.getNext().getLastName().equals(lName)) {
                nextPosition1 = temp.getNext();
                nextPosition2 = nextPosition1.getNext();
                temp.setNext(nextPosition2);

                System.out.println("Found " + fName);
                System.out.println("Found " + lName);
                System.out.println("Found " + seatNumber);
                foundFirstName = true;
            } else {
                System.out.println("No element, moving to next");
                System.out.println("Looking for " + fName);
                System.out.println("Looking for " + lName);
                System.out.println("Looking for " + seatNumber);

                temp = temp.getNext();
            }
        }
    }

    @Override
    public String toString() {
        String result = " ";
        Node current = start;

        while (current.next != null) {
            result += current.getFlightNumber() + ", " + current.getSeatNumber() + ", " + current.getFirstName() + ", " + current.getLastName() + ", " + current.getStreetAddress() + ", " + current.getCity() + ", " + current.getZipCode() + "\n";
            current = current.next;
        }
        result += current.getFlightNumber() + ", " + current.getSeatNumber() + ", " + current.getFirstName() + ", " + current.getLastName() + ", " + current.getStreetAddress() + ", " + current.getCity() + ", " + current.getZipCode() + "\n\n";
        current = current.next;

        return result;
    }

}
