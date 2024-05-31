package iit.concurrentAssignment.w1810216;

/**
 * Interface defining a Service Ticket Machine, extending the Machine interface.
 * This interface includes constants for paper and toner levels, as well as methods
 * to replace the toner cartridge, refill ticket paper, and retrieve current paper
 * and toner levels.
 */
public interface ServiceTicketMachine extends Machine {

    // Constants for paper tray
    int FULL_PAPER_TRAY = 10;
    int SHEETS_PER_PACK = 5;

    // Constants for toner levels
    int FULL_TONER_LEVEL = 13;
    int MINIMUM_TONER_LEVEL = 10;

    /**
     * Method to replace the toner cartridge in the ticket machine.
     */
    void replaceTonerCartridge();

    /**
     * Method to refill the ticket paper in the paper tray of the machine.
     */
    void refillTicketPaper();

    /**
     * Method to get the current paper level in the paper tray.
     *
     * @return The current paper level.
     */
    int getPaperLevel();

    /**
     * Method to get the current toner level in the machine.
     *
     * @return The current toner level.
     */
    int getTonerLevel();

}
