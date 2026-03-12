/**
 * ============================================================
 * MAIN CLASS – BookMyStayApp
 * ============================================================
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Description:
 * Demonstrates room initialization using abstraction,
 * inheritance and polymorphism.
 *
 * Availability stored using simple variables.
 *
 * @version 2.0
 */

abstract class Room {

    // Common room attributes
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    // Constructor
    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    // Display common details
    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
    }
}

// Single Room
class SingleRoom extends Room {

    int availableRooms;

    public SingleRoom() {
        super(1,250,1500.0);
        availableRooms = 5;
    }

    public void display() {
        System.out.println("\nSingle Room:");
        displayRoomDetails();
        System.out.println("Available: " + availableRooms);
    }
}

// Double Room
class DoubleRoom extends Room {

    int availableRooms;

    public DoubleRoom() {
        super(2,400,2500.0);
        availableRooms = 3;
    }

    public void display() {
        System.out.println("\nDouble Room:");
        displayRoomDetails();
        System.out.println("Available: " + availableRooms);
    }
}

// Suite Room
class SuiteRoom extends Room {

    int availableRooms;

    public SuiteRoom() {
        super(3,750,5000.0);
        availableRooms = 2;
    }

    public void display() {
        System.out.println("\nSuite Room:");
        displayRoomDetails();
        System.out.println("Available: " + availableRooms);
    }
}


public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Hotel Room Initialization");

        SingleRoom single = new SingleRoom();
        DoubleRoom dbl = new DoubleRoom();
        SuiteRoom suite = new SuiteRoom();

        single.display();
        dbl.display();
        suite.display();
    }
}