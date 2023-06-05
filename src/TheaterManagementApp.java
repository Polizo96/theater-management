import java.util.InputMismatchException;
import java.util.Scanner;

public class TheaterManagementApp {
    public static boolean[][] seats = new boolean[12][30];
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;
        while (choice != 4) {
            displayMenu();
            choice = getChoice("Please provide your choice");
            if (choiceValid(choice)){
                onChoiceMenu(choice);
            }
        }
    }

    /**
     * Displays the menu.
     */
    public static void displayMenu(){
        System.out.println("Please provide a choice of the following menu.\n");
        System.out.println("1. Preview theater seats.");
        System.out.println("2. Book a seat.");
        System.out.println("3. Cancel a booked seat.");
        System.out.println("4. Exit.");
    }

    /**
     * It books cancel or display the theater seats using
     * user's choice
     * @param choice
     */
    public static void onChoiceMenu(int choice) {
        char col;
        int row;

        try {
            switch (choice) {
                case 1:
                    displaySeats();
                    break;
                case 2:
                    System.out.println("Please select seat column(A-L)");
                    col = in.next().trim().toUpperCase().charAt(0);
                    in.nextLine();
                    System.out.println("Please select a seat row(1-30)");
                    row = in.nextInt();

                    if (rowColValid(col, row)) {
                        book(col, row);
                    } else {
                        System.out.println("Invalid seat");
                    }
                    break;
                case 3:
                    System.out.println("Please select seat column(A-L)");
                    col = in.next().trim().toUpperCase().charAt(0);
                    in.nextLine();
                    System.out.println("Please select a seat row(1-30)");
                    row = in.nextInt();

                    if (rowColValid(col, row)) {
                        cancel(col, row);
                    } else {
                        System.out.println("Invalid seat");
                    }
                    break;
                default:
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid seat");
            in.nextLine();
        }
    }

    public static int getChoice(String s) {
        int choice = 0;

        try {
            choice = in.nextInt();
            if (!choiceValid(choice)) {
                System.out.println("Choice must be 1-4");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid choice.");
            in.nextLine();
        }

        return choice;
    }
     // Validation
    public static boolean choiceValid(int choice) {
        return  choice >= 1 && choice <= 4;
    }

    public static boolean rowColValid(char column, int row) {
        return (row >= 1 && row <= 30) && (column >= 'A' && column <= 'L');
    }

    /**
     * Display the seats of the theater
     */
    public static void displaySeats() {
        char seatRow;
        int seatNum;
        char status;

        System.out.println("Theater Seats:");
        for (int row = 0; row < seats.length; row++) {
            for (int column = 0; column < seats[row].length; column++) {
                seatRow = (char) ('A' + row);
                seatNum = column + 1;
                status = seats[row][column] ? 'X' : '-'; // Symbol for status of each seat
                System.out.printf("%c%d %c ", seatRow, seatNum, status);
            }
            System.out.println();
        }
    }

    /**
     * Books a seat or checks if seat is already booked
     * @param column
     * @param row
     */
    public static void book(char column, int row) {
        int rowIndex = row - 1;
        int columnIndex = column - 'A';

        if (rowIndex < 0 || rowIndex >= seats[0].length || columnIndex < 0 || columnIndex >= seats.length) {
            System.out.println("Invalid seat selection.");
            return;
        }

        if (seats[columnIndex][rowIndex]) {
            System.out.println("Seat is already booked.");
        } else {
            seats[columnIndex][rowIndex] = true;
            System.out.println("Seat booked successfully.");
        }
    }

    /**
     * Cancel the seat or checks if it is not booked.
     * @param column
     * @param row
     */
    public static void cancel(char column, int row) {
        int rowIndex = row - 1;
        int columnIndex = column - 'A';

        if (rowIndex < 0 || rowIndex >= seats[0].length || columnIndex < 0 || columnIndex >= seats.length) {
            System.out.println("Invalid seat selection.");
            return;
        }

        if (!seats[columnIndex][rowIndex]) {
            System.out.println("Seat is not booked.");
        } else {
            seats[columnIndex][rowIndex] = false;
            System.out.println("Booking for the seat has been canceled successfully.");
        }
    }
}
