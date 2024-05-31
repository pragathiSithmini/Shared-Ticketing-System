package iit.concurrentAssignment.w1810216;

import iit.concurrentAssignment.w1810216.PassengerInfo.PassengerInfo;
import iit.concurrentAssignment.w1810216.Technician.Technician;
import iit.concurrentAssignment.w1810216.Technician.TicketPaperTechnician;
import iit.concurrentAssignment.w1810216.Technician.TicketTonerTechnician;
import iit.concurrentAssignment.w1810216.TicketInfo.Ticket;
import iit.concurrentAssignment.w1810216.TravelInfo.TravelInfo;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketPrintingSystem {

    public static void main(String[] args) {

        // Get current time for logging
        Date date = new Date(System.currentTimeMillis());
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        String logTime = formatter.format(date);

        // Create Passenger ThreadGroup
        ThreadGroup passengerThreadGroup = new ThreadGroup("PassengerThreadGroup");
        System.out.println("[" + logTime + "] [TICKET_PRINTING_SYSTEM] Created ThreadGroup: \"" + passengerThreadGroup.getName());

        // Create Technician ThreadGroup
        ThreadGroup technicianThreadGroup = new ThreadGroup("TechnicianThreadGroup");
        System.out.println("[" + logTime + "] [TICKET_PRINTING_SYSTEM] Created ThreadGroup: \"" + passengerThreadGroup.getName());

        // Initialize Ticket Machine
        TicketMachine ticketMachine = new TicketMachine(" TicketMachine 001", passengerThreadGroup);
        System.out.println("[" + logTime + "] [TICKET_PRINTING_SYSTEM] Initialized Ticket Machine");

        // Passenger Info
        PassengerInfo passengerInfo1 = new PassengerInfo("Marry", "0112332332", "Marry@gmail.com");
        PassengerInfo passengerInfo2 = new PassengerInfo("Shane", "0321231231", "Shane@gmail.com");
        PassengerInfo passengerInfo3 = new PassengerInfo("Wikes", "0112312312", "Wikes@gmail.com");
        PassengerInfo passengerInfo4 = new PassengerInfo("Yourk", "0771234567", "Yourk@gmail.com");

        // Travel Info
        TravelInfo travelInfo1 = new TravelInfo("Galle", "Kandy");
        TravelInfo travelInfo2 = new TravelInfo("Matara", "Galle");
        TravelInfo travelInfo3 = new TravelInfo("Kandy", "Matara");
        TravelInfo travelInfo4 = new TravelInfo("Colombo", "Colombo");

        // Tickets Info
        Ticket ticket1 = new Ticket(9999, new BigDecimal(1000), passengerInfo1, travelInfo1);
        Ticket ticket2 = new Ticket(9998, new BigDecimal(2000), passengerInfo2, travelInfo2);
        Ticket ticket3 = new Ticket(9997, new BigDecimal(3000), passengerInfo3, travelInfo3);
        Ticket ticket4 = new Ticket(9996, new BigDecimal(4000), passengerInfo4, travelInfo4);

        // Create Passenger objects
        Passenger passenger1 = new Passenger(ticketMachine, ticket1);
        Passenger passenger2 = new Passenger(ticketMachine, ticket2);
        Passenger passenger3 = new Passenger(ticketMachine, ticket3);
        Passenger passenger4 = new Passenger(ticketMachine, ticket4);

        // Create Passenger threads
        Thread passengerThread1 = new Thread(passengerThreadGroup, passenger1, "Marry");
        System.out.println("[" + logTime + "] [TICKET_PRINTING_SYSTEM] Initialized passenger: \"" + passengerThread1.getName());

        Thread passengerThread2 = new Thread(passengerThreadGroup, passenger2, "Shane");
        System.out.println("[" + logTime + "] [TICKET_PRINTING_SYSTEM] Initialized passenger: \"" + passengerThread2.getName());

        Thread passengerThread3 = new Thread(passengerThreadGroup, passenger3, "Wikes");
        System.out.println("[" + logTime + "] [TICKET_PRINTING_SYSTEM] Initialized passenger: \"" + passengerThread3.getName());

        Thread passengerThread4 = new Thread(passengerThreadGroup, passenger4, "Yourk");
        System.out.println("[" + logTime + "] [TICKET_PRINTING_SYSTEM] Initialized passenger: \"" + passengerThread4.getName());

        // Create Technician threads
        Technician paperTechnician = new TicketPaperTechnician("Paper Technician", technicianThreadGroup, ticketMachine);
        System.out.println("[" + logTime + "] [TICKET_PRINTING_SYSTEM] Initialized paper technician");

        Technician tonerTechnician = new TicketTonerTechnician("Toner Technician", technicianThreadGroup, ticketMachine);
        System.out.println("[" + logTime + "] [TICKET_PRINTING_SYSTEM] Initialized toner technician");

        Thread paperTechnicianThread = new Thread(technicianThreadGroup, paperTechnician, "Paper Technician");
        System.out.println("[" + logTime + "] [TICKET_PRINTING_SYSTEM] Initialized paper technician: \"" + paperTechnicianThread.getName());

        Thread tonerTechnicianThread = new Thread(technicianThreadGroup, tonerTechnician, "Toner Technician");
        System.out.println("[" + logTime + "] [TICKET_PRINTING_SYSTEM] Initialized toner technician: \"" + tonerTechnicianThread.getName());

        // Start all the threads
        passengerThread1.start();
        System.out.println("[" + logTime + "] [TICKET_PRINTING_SYSTEM] Started passenger: \"" + passengerThread1.getName());

        passengerThread2.start();
        System.out.println("[" + logTime + "] [TICKET_PRINTING_SYSTEM] Started passenger: \"" + passengerThread2.getName());

        passengerThread3.start();
        System.out.println("[" + logTime + "] [TICKET_PRINTING_SYSTEM] Started passenger: \"" + passengerThread3.getName());

        passengerThread4.start();
        System.out.println("[" + logTime + "] [TICKET_PRINTING_SYSTEM] Started passenger: \"" + passengerThread4.getName());

        paperTechnicianThread.start();
        System.out.println("[" + logTime + "] [TICKET_PRINTING_SYSTEM] Started paper technician");

        tonerTechnicianThread.start();
        System.out.println("[" + logTime + "] [TICKET_PRINTING_SYSTEM] Started toner technician");

        // Set max priority for Technician ThreadGroup
        technicianThreadGroup.setMaxPriority(10);

        // Wait until all threads are completed
        while (technicianThreadGroup.activeCount() > 0 || passengerThreadGroup.activeCount() > 0) {
            if (passengerThreadGroup.activeCount() == 0) {
                technicianThreadGroup.interrupt();
                System.out.println("All passengers have been served. Terminating the technician threads.");
                break;
            }
        }
    }
}
