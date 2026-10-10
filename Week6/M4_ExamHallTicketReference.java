/**
 * Week 6 - M4: Exam Hall Ticket Reference Management
 * Shows that two variables can refer to one object, and that == compares references.
 */
public class M4_ExamHallTicketReference {

    public static void main(String[] args) {
        HallTicket priya = new HallTicket("Priya", 0);
        HallTicket copy = priya;          // same object, not a new one
        copy.seatNumber = 45;             // change through the second variable

        System.out.println("Priya's seatNumber (via first variable): " + priya.seatNumber); // 45
        System.out.println("copy == priya: " + (copy == priya));                             // true

        HallTicket separate = new HallTicket("Priya", 45);                                   // identical values, different object
        System.out.println("separate == priya: " + (separate == priya));                     // false
    }
}

class HallTicket {
    String studentName;
    int seatNumber;

    HallTicket(String studentName, int seatNumber) {
        this.studentName = studentName;
        this.seatNumber = seatNumber;
    }
}
