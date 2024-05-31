package iit.concurrentAssignment.w1810216.PassengerInfo;

/**
 * Represents information about a passenger including name, phone number, and email address.
 */
public class PassengerInfo {
    // Passenger's name
    private final String passengerName;

    // Passenger's phone number
    private String phoneNumber;

    // Passenger's email address
    private String emailAddress;

    /**
     * Constructs a PassengerInfo object with the specified information.
     *
     * @param passengerName The name of the passenger.
     * @param phoneNumber   The phone number of the passenger.
     * @param emailAddress  The email address of the passenger.
     */
    public PassengerInfo(String passengerName, String phoneNumber, String emailAddress) {
        this.passengerName = passengerName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    /**
     * Gets the passenger's name.
     *
     * @return The passenger's name.
     */
    public String getPassengerName() {
        return passengerName;
    }

    /**
     * Gets the passenger's phone number.
     *
     * @return The passenger's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the passenger's phone number.
     *
     * @param phoneNumber The new phone number to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the passenger's email address.
     *
     * @return The passenger's email address.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the passenger's email address.
     *
     * @param emailAddress The new email address to set.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Returns a string representation of the PassengerInfo object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "PassengerInfo{" +
                "passengerName='" + passengerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
