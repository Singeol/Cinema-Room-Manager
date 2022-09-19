package cinema;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Cinema {

    private static int purchased = 0, currentIncome = 0, totalIncome = 0;

    public static void calculateIncome(int rows, int seats) {
        if (rows * seats <= 60) {
            totalIncome = rows * seats * 10;
        }
        else totalIncome = (rows / 2) * seats * 10 + (rows - rows / 2) * seats * 8;
    }
    public static void showStatistic(int rows, int seats) {
        System.out.printf("\nNumber of purchased tickets: %d\n", purchased);
        System.out.printf(Locale.US, "Percentage: %.2f%%\n", (((double) purchased) / (rows*seats) * 100 ));
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", totalIncome);
    }
    public static void buyTicket(String[][] hall, int rows, int seats) {
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEnter a row number:");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt();
            if (row > rows || seat > seats) {
                System.out.println("\nWrong input!");
                continue;
            }
            else if (Objects.equals(hall[row][seat], "B")) {
                System.out.println("\nThat ticket has already been purchased!");
                continue;
            }
            int price = (row <= (rows / 2)) || (rows * seats <= 60) ? 10 : 8;
            System.out.println("Ticket price: $" + price);
            System.out.println();
            purchased++;
            currentIncome += price;
            hall[row][seat] = "B";
            break;
        } while(true);
    }

    public static String[][] initHall(int rows, int seats) {
        String[][] hall = new String[rows+1][seats+1];
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                if (i == 0 && j == 0) hall[i][j] = " ";
                else if (i == 0) hall[i][j] = Integer.toString(j);
                else if (j == 0) hall[i][j] = Integer.toString(i);
                else hall[i][j] = "S";
            }
        }
        return hall;
    }

    public static void showSeats(String[][] hall) {
        System.out.println("\nCinema:");
        for (String[] el: hall) {
            for (String ch: el) {
                System.out.print(ch + ' ');
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choose;
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        calculateIncome(rows, seats);
        String[][] hall = initHall(rows, seats);
        do {
            System.out.println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            choose = scanner.nextInt();
            switch (choose) {
                case 1 -> showSeats(hall);
                case 2 -> buyTicket(hall, rows, seats);
                case 3 -> showStatistic(rows, seats);
            }
        } while(choose != 0);
    }
}