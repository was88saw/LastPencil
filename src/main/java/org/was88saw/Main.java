import java.util.Random;
import java.util.Scanner;

/**
 * Simple console game that allow player - John - to compare with simple bot.
 * Program ends after one of the player wins.
 */


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isGameStarted = false;

        do {
            System.out.println("How many pencils would you like to use:");

            int input = checkInput(scanner);

            if (input != -1) {
                playPencilsGame(scanner, input);
                isGameStarted = true;
            }
        } while (!isGameStarted);
        scanner.close();
    }

    public static void playPencilsGame(Scanner scanner, int input) {
        int totalPencils = input;

        System.out.println("Who will be the first (John, Jack):");

        String  name;
        String  name2       = null;
        boolean isNameValid = false;

        do {                                                // checking is name correct
            name = scanner.next();

            if (name.equals("John") || name.equals("Jack")) {
                if (name.equals("Jack")) {
                    name2 = "John";
                } else {
                    name2 = "Jack";
                }
                isNameValid = true;
            } else {
                System.out.println("Choose between 'John' and 'Jack'");
            }
        } while (!isNameValid);

        printPencils(totalPencils);

        // introduce variable turn that store actual turn number;
        int turn = 1;

        do {
            boolean isPlayerTurn;                               // check is it players turn

            isPlayerTurn = (name.equals("John") && (turn % 2 == 1)) || (name2.equals("John") && (turn % 2 == 0));

            if (isPlayerTurn) {                                           // player turn
                System.out.println("John's turn:");

                int playerMoveValue = checkPlayerInput(scanner);

                if ((playerMoveValue > 0) && (playerMoveValue < 4)) {
                    if ((totalPencils <= 3) && (playerMoveValue > totalPencils)){
                        System.out.println("too many pencils");
                    } else {
                        totalPencils -= playerMoveValue;
                        printPencils(totalPencils);
                        turn++;
                    }
                }
            } else {                                                 // Bot turn depending on how many pencils left
                System.out.println("Jack's turn:");

                int     botMoveValue    = 0;
                Random  random          = new Random();

                switch (totalPencils % 4) {
                    case 0: {
                        botMoveValue = 3;
                        break;
                    }
                    case 1: {
                        botMoveValue = random.nextInt(3) + 1;
                        if(totalPencils == 1) botMoveValue = 1;
                        break;
                    }
                    case 2: {
                        botMoveValue = 1;
                        break;
                    }
                    case 3: {
                        botMoveValue = 2;
                        break;
                    }
                    default: {
                        System.out.println("Wrong input!");
                        break;
                    }
                }
                System.out.println(botMoveValue);

                totalPencils -= botMoveValue;
                turn++;

                printPencils(totalPencils);
            }
        } while (0 < totalPencils);

        String winner = (turn % 2 == 1) ? name : name2;

        System.out.println(winner + " won!");
    }

    // Method checking input
    public static int checkInput(Scanner scanner){
        int result = -1;

        try {
            String      line        = scanner.nextLine();
            String[]    tmpArray    = line.split(" ");

            if (tmpArray.length == 1) {
                result = Integer.parseInt(line);
                if (result <= 0) {
                    System.out.println("The number of pencils should be positive");
                    result = -1;
                }
            } else {
                System.out.println("The number of pencils should be numeric");
            }
        } catch (NumberFormatException e){
            System.out.println("The number of pencils should be numeric");
            result = -1;
        }
        return result;
    }

    // Method checking user input
    public static int checkPlayerInput(Scanner scanner){
        int input = -1;

        try {
            String playerInput = scanner.next();
            String[] playerInputArray = playerInput.split(" ");

            if (playerInputArray.length == 1) {
                input = Integer.parseInt(playerInput);
                if ((1 > input) || (input > 3)) {
                    System.out.println("Possible values: '1', '2' or '3'");
                    input = -1;
                }
            } else System.out.println("Possible values: '1', '2' or '3'");
        } catch (NumberFormatException e) {
            System.out.println("Possible values: '1', '2' or '3'");
            input = -1;
        }
        return input;
    }

    // Method printing all pencils
    public static void printPencils(int totalPencils) {
        for (int i = 0; i < totalPencils; i++) {
            System.out.print("|");
        }
        System.out.println();
    }
}