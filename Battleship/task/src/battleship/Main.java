package battleship;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    boolean convertedInput = false;

    public static void main(String[] args) {
        // Write your code here
        Board board = new Board();
        Board board2 = new Board();
        Scanner scanner = new Scanner(System.in);
        String userInput = "";


        Ship Carrier = new Ship("Aircraft Carrier", 5);
        Ship Battleship = new Ship("Battleship", 4);
        Ship Sub = new Ship("Submarine", 3);
        Ship Cruiser = new Ship("Cruiser", 3);
        Ship Destroyer = new Ship("Destroyer", 2);
        Ship Carrier2 = new Ship("Aircraft Carrier", 5);
        Ship Battleship2 = new Ship("Battleship", 4);
        Ship Sub2 = new Ship("Submarine", 3);
        Ship Cruiser2 = new Ship("Cruiser", 3);
        Ship Destroyer2 = new Ship("Destroyer", 2);
        ShipArmada shipArmada;
        ShipArmada shipArmada2;

        int[] shipCoordinates = new int[4];
        int[] targetArray = new int[2];
        shipCoordinates[0] = -1;
        shipCoordinates[1] = -1;
        shipCoordinates[2] = -1;
        shipCoordinates[3] = -1;
        int shipSize;
        int convertedTargetArray = -1;

        boolean shipCoordinateCheck = (shipCoordinates[0] == -1 || shipCoordinates[1] == -1 || shipCoordinates[2] == -1 || shipCoordinates[3] == -1);

        System.out.println("Player 1, place your ships on the game field");
        System.out.println();

        board.createBoard();
        board.printBoard();

        shipCoordinates = askForInput(shipCoordinates, scanner, Carrier, shipCoordinateCheck, board);
        Carrier.buildShip(shipCoordinates);
        board.placeShip(Carrier);
        System.out.println();
        board.printBoard();
        resetShipCoordinates(shipCoordinates);

        shipCoordinates = askForInput(shipCoordinates, scanner, Battleship, shipCoordinateCheck, board);
        Battleship.buildShip(shipCoordinates);
        board.placeShip(Battleship);
        System.out.println();
        board.printBoard();
        resetShipCoordinates(shipCoordinates);

        shipCoordinates = askForInput(shipCoordinates, scanner, Sub, shipCoordinateCheck, board);
        Sub.buildShip(shipCoordinates);
        board.placeShip(Sub);
        System.out.println();
        board.printBoard();
        resetShipCoordinates(shipCoordinates);

        shipCoordinates = askForInput(shipCoordinates, scanner, Cruiser, shipCoordinateCheck, board);
        Cruiser.buildShip(shipCoordinates);
        board.placeShip(Cruiser);
        System.out.println();
        board.printBoard();
        resetShipCoordinates(shipCoordinates);

        shipCoordinates = askForInput(shipCoordinates, scanner, Destroyer, shipCoordinateCheck, board);
        Destroyer.buildShip(shipCoordinates);
        board.placeShip(Destroyer);
        System.out.println();
        board.printBoard();
        resetShipCoordinates(shipCoordinates);

        shipArmada = new ShipArmada(Carrier, Battleship, Sub, Cruiser, Destroyer);
        shipArmada.fillValidTargetArrays();
        board.placeShipArmada(shipArmada);

        System.out.println("Press Enter and pass the move to another player");
        String pause = scanner.nextLine();

        board2.createBoard();
        board2.printBoard();

        shipCoordinates = askForInput(shipCoordinates, scanner, Carrier2, shipCoordinateCheck, board2);
        Carrier2.buildShip(shipCoordinates);
        board2.placeShip(Carrier2);
        System.out.println();
        board2.printBoard();
        resetShipCoordinates(shipCoordinates);

        shipCoordinates = askForInput(shipCoordinates, scanner, Battleship2, shipCoordinateCheck, board2);
        Battleship2.buildShip(shipCoordinates);
        board2.placeShip(Battleship2);
        System.out.println();
        board2.printBoard();
        resetShipCoordinates(shipCoordinates);

        shipCoordinates = askForInput(shipCoordinates, scanner, Sub2, shipCoordinateCheck, board2);
        Sub2.buildShip(shipCoordinates);
        board2.placeShip(Sub2);
        System.out.println();
        board2.printBoard();
        resetShipCoordinates(shipCoordinates);

        shipCoordinates = askForInput(shipCoordinates, scanner, Cruiser2, shipCoordinateCheck, board2);
        Cruiser2.buildShip(shipCoordinates);
        board2.placeShip(Cruiser2);
        System.out.println();
        board2.printBoard();
        resetShipCoordinates(shipCoordinates);

        shipCoordinates = askForInput(shipCoordinates, scanner, Destroyer2, shipCoordinateCheck, board2);
        Destroyer2.buildShip(shipCoordinates);
        board2.placeShip(Destroyer2);
        System.out.println();
        board2.printBoard();
        resetShipCoordinates(shipCoordinates);

        shipArmada2 = new ShipArmada(Carrier2, Battleship2, Sub2, Cruiser2, Destroyer2);
        shipArmada2.fillValidTargetArrays();
        board2.placeShipArmada(shipArmada2);

        System.out.println("Press enter and pass the move to another player");
        pause = scanner.nextLine();
        //new loop
        while(!shipArmada.allShipsSunk() && !shipArmada2.allShipsSunk() ) {
            int currentSunkShipCountP2 = shipArmada2.getShipSunkCount();
            board2.printFogOfWar();
            System.out.println("---------------------");
            board.printBoard();
            System.out.println();
            System.out.print("Player 1, it's your turn:");
            targetArray = askForTarget(scanner);
            convertedTargetArray = convertAskForTarget(targetArray);

            if (shipArmada2.checkForHit(convertedTargetArray)) {
                shipArmada2.setHit(convertedTargetArray);
                shipArmada2.setSunkShipCount();
                System.out.print("STOREDCURRENT " + currentSunkShipCountP2);
                System.out.println("SHIPSUNKCOUNTP2FROMMETHOD " + shipArmada2.getShipSunkCount());
                if(currentSunkShipCountP2<shipArmada2.getShipSunkCount()){
                    System.out.println("You sank a ship!");
                }
                board2.placeShipArmada(shipArmada2);
                System.out.println();
                if(shipArmada2.allShipsSunk()){
                    System.out.println("You sank the last ship. You won. Congratulations!");
                    break;
                }
                System.out.println("You hit a ship!");
                System.out.println("Press enter and pass the move to another player");
                pause = scanner.nextLine();

            } else {
                board2.placeMiss(targetArray);
                System.out.println("You missed!");
                System.out.println("Press enter and pass the move to another player");
                pause = scanner.nextLine();
            }

            board.printFogOfWar();
            System.out.println("---------------------");
            board2.printBoard();
            System.out.println();
            System.out.print("Player 2, it's your turn:");

            targetArray = askForTarget(scanner);
            convertedTargetArray = convertAskForTarget(targetArray);


            if (shipArmada.checkForHit(convertedTargetArray)) {
                int currentSunkShipCountP1 = shipArmada.getShipSunkCount();
                shipArmada.setHit(convertedTargetArray);
                shipArmada.setSunkShipCount();
                System.out.print("STOREDCURRENT " + currentSunkShipCountP1);
                System.out.println("SHIPSUNKCOUNTP1FROMMETHOD " + shipArmada.getShipSunkCount());
                if(currentSunkShipCountP1<shipArmada.getShipSunkCount()){
                    System.out.println("You sank a ship!");
                }

                board.placeShipArmada(shipArmada);
                System.out.println();
                if(shipArmada.allShipsSunk()){
                    System.out.println("You sank the last ship. You won. Congratulations!");
                    break;
                }
                System.out.println("You hit a ship!");
                System.out.println("Press enter and pass the move to another player");
                pause = scanner.nextLine();

            } else {
                board.placeMiss(targetArray);
                System.out.println("You missed!");
                System.out.println("Press enter and pass the move to another player");
                pause = scanner.nextLine();
            }

        }


        System.out.println("The game starts!");
        System.out.println();
        board.printFogOfWar();

        //shipArmada = new ShipArmada(Carrier, Battleship, Sub, Cruiser, Destroyer);
        //shipArmada.fillValidTargetArrays();
/*
        System.out.println();
        System.out.println("Take a shot!");
        System.out.println();
        String target = scanner.nextLine();
        askForTargetStringInput(target,scanner);
*/
        //old loop
        /*
        boolean firstShot = true;
        while (!shipArmada.allShipsSunk()) {
            if(firstShot) {
                System.out.println();
                System.out.println("Take a shot!");
                firstShot = false;
            }
            targetArray = askForTarget(scanner);

            convertedTargetArray = convertAskForTarget(targetArray);

            if (shipArmada.checkForHit(convertedTargetArray)) {
                //System.out.println("WE ARE HRE!");
                shipArmada.setHit(convertedTargetArray);
                //shipArmada.viewShipArrays();
                board.placeShipArmada(shipArmada);
                board.printFogOfWar();
                System.out.println();
                if(shipArmada.allShipsSunk()){
                    System.out.println("You sank the last ship. You won. Congratulations!");
                    break;
                }
                System.out.println("You hit a ship! Try again:");

            } else {
                board.placeMiss(targetArray);
                board.printFogOfWar();
                System.out.println("You missed! Try again:");
            }

        }

         */

    }

    public static int[] askForInput(int[] shipCoordinates, Scanner scanner, Ship ship, Boolean shipCoordinateCheck, Board board) {
        System.out.println();
        System.out.println("Enter the coordinates of the " + ship.getShipName() + "( " + ship.getSize() + " cells):");
        System.out.println();
        String userInput = "";

        while (shipCoordinateCheck || !validCheckOfCoordinates(shipCoordinates) || calculateShipSize(ship, shipCoordinates) != ship.getSize() || !board.checkEmpty(shipCoordinates)) {
            resetShipCoordinates(shipCoordinates);
            userInput = scanner.nextLine();
            shipCoordinates = convertCoordinatesToNumbers(shipCoordinates, userInput);

            shipCoordinateCheck = (shipCoordinates[0] == -1 || shipCoordinates[1] == -1 || shipCoordinates[2] == -1 || shipCoordinates[3] == -1);
        }
        return shipCoordinates;
    }

    public static int[] askForTarget(Scanner scanner) {
        //System.out.println("Take a shot!");
        System.out.println();
        String target = scanner.nextLine();
        int row = convertLetter(target);
        int col = getCoordinateNumber(target);

        while (row == -1 || col < 1 || col > 10) {
            System.out.println();
            System.out.println("Error you entered the wrong coordinates! Try again:");
            System.out.println();
            target = scanner.nextLine();
            row = convertLetter(target);
            col = getCoordinateNumber(target);

        }
        int[] targetArray = new int[2];
        targetArray[0] = row;
        targetArray[1] = col;

        return targetArray;
    }

    public static int[] askForTargetStringInput(String target,Scanner scanner) {
        //System.out.println("Take a shot!");
        System.out.println();
        //String target = scanner.nextLine();
        int row = convertLetter(target);
        int col = getCoordinateNumber(target);

        while (row == -1 || col < 1 || col > 10) {
            System.out.println();
            System.out.println("Error you entered the wrong coordinates! Try again:");
            System.out.println();
            target = scanner.nextLine();
            row = convertLetter(target);
            col = getCoordinateNumber(target);

        }
        int[] targetArray = new int[2];
        targetArray[0] = row;
        targetArray[1] = col;

        return targetArray;
    }

    public static int convertAskForTarget(int[] targetArray) {
        int i1 = targetArray[0];
        int i2 = targetArray[1];

        String s1 = Integer.toString(i1) + Integer.toString(i2);
        return Integer.parseInt(s1);
    }

    public static int[] convertCoordinatesToNumbers(int[] shipCoordinates, String input) {

        try {

            String[] splitString = splitInput(input);
            shipCoordinates[0] = convertLetter(splitString[0]);
            shipCoordinates[2] = convertLetter(splitString[1]);
            shipCoordinates[1] = getCoordinateNumber(splitString[0]);
            shipCoordinates[3] = getCoordinateNumber(splitString[1]);

            //swaps coordintes if value is of cordinate is larger smaller
            if (shipCoordinates[0] > shipCoordinates[2]) {
                int tempHold = shipCoordinates[2];
                shipCoordinates[2] = shipCoordinates[0];
                shipCoordinates[0] = tempHold;
            }
            if (shipCoordinates[1] > shipCoordinates[3]) {
                int tempHold = shipCoordinates[3];
                shipCoordinates[3] = shipCoordinates[1];
                shipCoordinates[1] = tempHold;
            }

            if (shipCoordinates[0] == -1 || shipCoordinates[1] == -1 || shipCoordinates[2] == -1 || shipCoordinates[3] == -1) {
                System.out.println();
                System.out.println("Error! Please enter coordinate sin a valid format!");
                System.out.println();
            }
        } catch(Exception e) {
            System.out.println("Error! Enter correct input1");
        }


        // }
        return shipCoordinates;
    }

    public static boolean validCheckOfCoordinates(int[] shipCoordinates) {
        if ((shipCoordinates[0] != shipCoordinates[2]) && (shipCoordinates[1] != shipCoordinates[3])) {
            System.out.println();
            System.out.println("Error! Wrong ship location! Try again:");
            System.out.println();
            return false;
        } else {
            return true;
        }
    }

    public static int[] resetShipCoordinates(int[] shipCoordinates) {
        shipCoordinates[0] = -1;
        shipCoordinates[1] = -1;
        shipCoordinates[2] = -1;
        shipCoordinates[3] = -1;
        return shipCoordinates;
    }

    public static String[] splitInput(String input) {
        String[] splitString = input.split(" ");
        return splitString;
    }

    public static int convertLetter(String input) {
        input = input.toUpperCase();
        int rowIndex = -1;

        try {
            String c1 = input.substring(0, 2);
            char rowChar = c1.charAt(0);

            switch (rowChar) {
                case 'A':
                    rowIndex = 1;
                    break;
                case 'B':
                    rowIndex = 2;
                    break;
                case 'C':
                    rowIndex = 3;
                    break;
                case 'D':
                    rowIndex = 4;
                    break;
                case 'E':
                    rowIndex = 5;
                    break;
                case 'F':
                    rowIndex = 6;
                    break;
                case 'G':
                    rowIndex = 7;
                    break;
                case 'H':
                    rowIndex = 8;
                    break;
                case 'I':
                    rowIndex = 9;
                    break;

                case 'J':
                    rowIndex = 10;
                    break;


            }
        }catch(Exception e) {
            System.out.println("Error! Enter input!");
            return -1;
        }
        return rowIndex;
    }

    public static int getCoordinateNumber(String input) {

        int CoordinateNumber;
        try {
            if (input.length() >= 3) {
                CoordinateNumber = Integer.parseInt(input.substring(1, 3));
            } else {
                CoordinateNumber = Integer.parseInt(input.substring(1));
            }
        } catch (Exception e) {
            return -1;
        }
        return CoordinateNumber;
    }

    public static int calculateShipSize(Ship ship, int[] shipCoordinates) {
        int i1 = shipCoordinates[0];
        int i2 = shipCoordinates[1];
        int i3 = shipCoordinates[2];
        int i4 = shipCoordinates[3];

        if (i1 == i3 && ((Math.abs(i4 - i2) + 1 == ship.getSize()))) {
            return Math.abs(i4 - i2) + 1;
        }
        if (i2 == i4 && ((Math.abs(i3 - i1) + 1 == ship.getSize()))) {
            return Math.abs(i3 - i1) + 1;
        }
        System.out.println();
        System.out.println("Error! Wrong length of the " + ship.getShipName() + "! Try again:");
        System.out.println();
        return -1;
    }
}




