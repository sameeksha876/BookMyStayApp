import java.util.*;

/*
BookMyStayApp
Use Case 6 – Reservation Confirmation & Room Allocation
Version 6.0
*/


/* ROOM HIERARCHY */

abstract class Room{

    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int beds,int size,double price){

        numberOfBeds=beds;
        squareFeet=size;
        pricePerNight=price;

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



/* INVENTORY */

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



/* RESERVATION */

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



/* QUEUE */

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



/* UC6 ROOM ALLOCATION SERVICE */

class RoomAllocationService{

    private Set<String> allocatedRoomIds;

    private Map<String,Set<String>> assignedRoomsByType;

    public RoomAllocationService(){

        allocatedRoomIds=new HashSet<>();

        assignedRoomsByType=new HashMap<>();

    }


    public void allocateRoom(
            Reservation reservation,
            RoomInventory inventory){

        String roomType=reservation.getRoomType();

        Map<String,Integer> availability=
                inventory.getRoomAvailability();


        if(availability.get(roomType)>0){

            String roomId=
                    generateRoomId(roomType);

            allocatedRoomIds.add(roomId);

            assignedRoomsByType
                    .putIfAbsent(roomType,new HashSet<>());

            assignedRoomsByType
                    .get(roomType)
                    .add(roomId);


            availability.put(
                    roomType,
                    availability.get(roomType)-1);


            System.out.println(
                    "Booking confirmed for Guest: "
                            +reservation.getGuestName()
                            +", Room ID: "
                            +roomId);

        }

        else{

            System.out.println(
                    "No rooms available for "
                            +reservation.getGuestName());

        }

    }


    private String generateRoomId(String roomType){

        int count=
                assignedRoomsByType
                        .getOrDefault(roomType,
                                new HashSet<>())
                        .size()+1;

        return roomType+"-"+count;

    }

}



/* MAIN */

public class BookMyStayApp{

    public static void main(String[] args){

        BookingRequestQueue queue=
                new BookingRequestQueue();

        RoomInventory inventory=
                new RoomInventory();

        RoomAllocationService service=
                new RoomAllocationService();


        Reservation r1=
                new Reservation("Abhi","Single");

        Reservation r2=
                new Reservation("Subha","Single");

        Reservation r3=
                new Reservation("Vanmathi","Suite");


        queue.addRequest(r1);
        queue.addRequest(r2);
        queue.addRequest(r3);


        System.out.println("Room Allocation Processing");


        while(queue.hasPendingRequests()){

            Reservation r=
                    queue.getNextRequest();

            service.allocateRoom(r,inventory);

        }

    }

}