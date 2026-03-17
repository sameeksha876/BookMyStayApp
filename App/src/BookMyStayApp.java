import java.util.*;

/*
BookMyStayApp
Use Case 5 – Booking Request (FIFO)
Version 5.0
*/


abstract class Room{

    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int beds,int size,double price){

        numberOfBeds=beds;
        squareFeet=size;
        pricePerNight=price;

    }

    public void displayRoomDetails(){

        System.out.println("Beds: "+numberOfBeds);
        System.out.println("Size: "+squareFeet+" sqft");
        System.out.println("Price per night: "+pricePerNight);

    }

}


class SingleRoom extends Room{

    public SingleRoom(){

        super(1,250,1500);

    }

}

class DoubleRoom extends Room{

    public DoubleRoom(){

        super(2,400,2500);

    }

}

class SuiteRoom extends Room{

    public SuiteRoom(){

        super(3,750,5000);

    }

}


/* UC3 Inventory */

class RoomInventory{

    private Map<String,Integer> roomAvailability;

    public RoomInventory(){

        roomAvailability=new HashMap<>();

        roomAvailability.put("Single",5);
        roomAvailability.put("Double",3);
        roomAvailability.put("Suite",2);

    }

    public Map<String,Integer> getRoomAvailability(){

        return roomAvailability;

    }

}


/* UC4 Search */

class RoomSearchService{

    public void searchAvailableRooms(RoomInventory inventory){

        System.out.println("Hotel Room Inventory Status");

        for(String room:inventory.getRoomAvailability().keySet()){

            System.out.println(room+" Available: "
                    +inventory.getRoomAvailability().get(room));

        }

    }

}


/* UC5 Reservation */

class Reservation{

    private String guestName;
    private String roomType;

    public Reservation(String guestName,String roomType){

        this.guestName=guestName;
        this.roomType=roomType;

    }

    public String getGuestName(){

        return guestName;

    }

    public String getRoomType(){

        return roomType;

    }

}


/* UC5 Booking Queue */

class BookingRequestQueue{

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue(){

        requestQueue=new LinkedList<>();

    }

    public void addRequest(Reservation r){

        requestQueue.offer(r);

    }

    public Reservation getNextRequest(){

        return requestQueue.poll();

    }

    public boolean hasPendingRequests(){

        return !requestQueue.isEmpty();

    }

}



/* MAIN CLASS */

public class BookMyStayApp{

    public static void main(String[] args){

        BookingRequestQueue bookingQueue=
                new BookingRequestQueue();


        Reservation r1=
                new Reservation("Abhi","Single");

        Reservation r2=
                new Reservation("Subha","Double");

        Reservation r3=
                new Reservation("Vanmathi","Suite");


        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);


        System.out.println("Booking Request Queue");


        while(bookingQueue.hasPendingRequests()){

            Reservation r=
                    bookingQueue.getNextRequest();

            System.out.println(
                    "Processing booking for Guest: "
                            +r.getGuestName()
                            +", Room Type: "
                            +r.getRoomType());

        }

    }

}