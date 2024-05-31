package iit.concurrentAssignment.w1810216.Technician;

import iit.concurrentAssignment.w1810216.Machine;
import iit.concurrentAssignment.w1810216.TicketMachine;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * TicketTonerTechnician class represents a technician responsible for replacing toner cartridges in a TicketMachine.
 */
public class TicketTonerTechnician extends Technician {

    // Constant defining the number of retry attempts
    public static final int NUMBER_OF_RETRY = 3;

    // Current date and time information for logging
    Date date = new Date(System.currentTimeMillis());
    DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
    String logTime = formatter.format(date);

    // Random number generator for simulating variable processing time
    Random random = new Random();

    /**
     * Constructor for TicketTonerTechnician.
     *
     * @param name         The name of the technician.
     * @param threadGroup  The thread group to which the technician belongs.
     * @param machine      The TicketMachine associated with the technician.
     */
    public TicketTonerTechnician(String name, ThreadGroup threadGroup, Machine machine) {
        super(name, threadGroup, machine);
    }

    /**
     * Run method representing the main behavior of the TicketTonerTechnician.
     */
    @Override
    public void run() {
        // Perform replacement attempts
        for (int i = 0; i < NUMBER_OF_RETRY; i++) {
            try {
                // Log the request to replace toner cartridge
                System.out.println(
                        "[" + logTime + "] [Ticket Toner Technician] Requested to replace toner cartridge"
                );

                // Replace toner cartridge in the associated TicketMachine
                ((TicketMachine) machine).replaceTonerCartridge();

                // Log the Ticket Machine status after replacement
                System.out.println(
                        "[" + logTime + "] [Ticket Toner Technician] Ticket Machine status. " + machine.toString()
                );

                // Simulate variable processing time
                sleep(random.nextInt(1000) + 1000);

            } catch (InterruptedException e) {
                // Break the loop if interrupted
                break;
            }
        }
        // Log the completion of the technician's work
        System.out.println("Toner Technician Finished, toner cartridge used: " + TicketMachine.replaceTonerCartridgeCount);
    }
}
