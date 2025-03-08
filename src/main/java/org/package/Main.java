import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean isGameStarted = false;
        System.out.println("Game player vs bot: Player");

        do{                                                     // set up number of pencils
            System.out.println("How many pencils would you like to use:");
            int input = checkInput(scanner);
            if(input != -1) {
                playPencileGame(scanner, input);
                isGameStarted = true;
            }
        }while (!isGameStarted);
        scanner.close();
    }

    public static void playPencileGame(Scanner scanner, int input){
        int pencileNumber = input;

        System.out.println("Who will be the first (Player, Bot):");
        String name = null;
        String name2 = null;
        boolean isNameValid = false;

        do {                                                // checking is name correct
            name = scanner.next();
            if (name.equals("Player") || name.equals("Bot")) {
                if (name.equals("Bot")) name2 = "Player";
                if (name.equals("Player")) name2 = "Bot";
                isNameValid = true;
            } else System.out.println("Choose between 'Player' and 'Bot'");
        } while (!isNameValid);

        printPencils(pencileNumber);
        int turn = 1;                                                   // introduce turn counter

        do {
            boolean isPlayerTurn = false;                               // check is it players turn
            if((name.equals("Player") && turn % 2 == 1) || (name2.equals("Player") && turn % 2 == 0)) {
                isPlayerTurn = true;
            }else isPlayerTurn = false;
            if(isPlayerTurn){                                           // plaert turn
                System.out.println("Player's turn:");
                int tmp = checkPlayerInput(scanner);
                if(tmp > 0 && tmp < 4) {
                    if(pencileNumber <= 3 && tmp > pencileNumber){
                        System.out.println("too many pencils");
                    }else{
                        pencileNumber -= tmp;
                        printPencils(pencileNumber);
                        turn++;
                    }
                }
            }else {                                                 // Bot turn
                System.out.println("Bot's turn:");
                int tmp = 0;
                Random random = new Random();
                switch (pencileNumber % 4) {
                    case 0:{
                        tmp = 3;
                        break;
                    }
                    case 1:{
                        tmp = random.nextInt(3) + 1;
                        if(pencileNumber == 1) tmp = 1;
                        break;
                    }
                    case 2:{
                        tmp = 1;
                        break;
                    }
                    case 3:{
                        tmp = 2;
                        break;
                    }
                    default:{
                        System.out.println("Wrong input!");
                        break;
                    }
                }
                System.out.println(tmp);
                pencileNumber -= tmp;
                printPencils(pencileNumber);

                turn++;
            }
        } while (0 < pencileNumber);

        if(0 >= pencileNumber){
            String winner = (turn % 2 == 1) ? name : name2;
            System.out.println(winner + " won!");
        }
    }

    public static int checkInput(Scanner scanner){                               // metgod checking input
        int result = -1;
        try {
            String line = scanner.nextLine();
            String[] tmpArray = line.split(" ");
            if(tmpArray.length == 1) {
                result = Integer.parseInt(line);
                if(result <= 0) {
                    System.out.println("The number of pencils should be positive");
                    result = -1;
                }
            }else System.out.println("The number of pencils should be numeric");

        }catch (NumberFormatException e){
            System.out.println("The number of pencils should be numeric");
            result = -1;
        }
        return result;
    }

    public static int checkPlayerInput(Scanner scanner){                     // metgod checking user input
        int result = -1;
        try {
            String line = scanner.next();
            String[] tmpArray = line.split(" ");
            if(tmpArray.length == 1) {
                result = Integer.parseInt(line);
                if(1 > result || result > 3) {
                    System.out.println("Possible values: '1', '2' or '3'");
                    result = -1;
                }
            }else System.out.println("Possible values: '1', '2' or '3'");

        }catch (NumberFormatException e){
            System.out.println("Possible values: '1', '2' or '3'");
            result = -1;
        }
        return result;
    }

    public static void printPencils(int numberOfPenciles) {                      // print all penciles

        for (int i = 0; i < numberOfPenciles; i++) {
            System.out.print("|");
        }
        System.out.println();
    }

}