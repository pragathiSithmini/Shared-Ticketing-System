package iit.concurrentAssignment.w1810216.TravelInfo;

// TravelInfo class represents information about a travel destination and departure location.
public class TravelInfo {

    // Destination of the travel.
    private final String destination;

    // Location from where the travel departs.
    private final String departureLocation;

    // Constructor to initialize the TravelInfo object with destination and departure location.
    public TravelInfo(String destination, String departureLocation) {
        this.destination = destination;
        this.departureLocation = departureLocation;
    }

    // Getter method to retrieve the destination.
    public String getDestination() {
        return destination;
    }

    // Getter method to retrieve the departure location.
    public String getDepartureLocation() {
        return departureLocation;
    }

    // Override toString method to provide a formatted string representation of the TravelInfo object.
    @Override
    public String toString() {
        return "TravelInfo[ " + "Destination: " + destination + ", Departure Location: " + departureLocation + "]";
    }

}
