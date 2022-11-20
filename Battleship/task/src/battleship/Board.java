package battleship;

import java.util.ArrayList;

public class Board {
    String[][] board;

    public Board() {
        this.board = new String[11][11];
    }

    public void createBoard() {
        char rowLetter = 'A';
        for (int rows = 0; rows < board.length; rows++) {

            for (int cols = 0; cols < board[rows].length; cols++) {
                if ((rows == 0 && cols == 0)) {
                    board[0][0] = " ";
                    continue;
                }
                if (rows == 0) {

                    Integer colInteger = cols;
                    board[rows][cols] = colInteger.toString();
                    continue;
                }

                if (cols == 0) {

                    int rowLetterAcsii = (int) rowLetter + rows - 1;
                    rowLetter = (char) rowLetterAcsii;
                    board[rows][cols] = String.valueOf(rowLetter);
                    rowLetter = 'A';
                    continue;
                }

                board[rows][cols] = "~";

            }
        }
    }

    public void printBoard() {
        for (int rows = 0; rows < board.length; rows++) {
            for (int cols = 0; cols < board[rows].length; cols++) {
                System.out.print(board[rows][cols] + " ");

            }
            System.out.println();
        }
    }



    public void printFogOfWar() {
        for (int rows = 0; rows < board.length; rows++) {
            for (int cols = 0; cols < board[rows].length; cols++) {
                if(board[rows][cols].equals("O")) {
                    System.out.print("~ ");

                }
                else {
                    System.out.print(board[rows][cols] + " ");
                }
            }
            System.out.println();
        }
    }

    public void placeShipArmada(ShipArmada shipArmada) {
        ArrayList<Ship> shipArmadaList = shipArmada.getShipArmadaList();

        for(Ship ship : shipArmadaList) {
            placeShip(ship);
        }

    }

    public void placeShip(Ship shipObject) {
        String[] shipArray = shipObject.getShipArray();
        int[] shipCoordinates = shipObject.getShipCoordinates();

            if (shipCoordinates[0] == shipCoordinates[2]) {
                int i = 0; //used to loop through ship array
                for (int col = shipCoordinates[1]; col <= shipCoordinates[3]; col++) {
                    board[shipCoordinates[0]][col] = shipArray[i];
                    i++;
                }
            }
            if (shipCoordinates[1] == shipCoordinates[3]) {
                int i = 0; //used to loop trhough ship array
                for (int row = shipCoordinates[0]; row <= shipCoordinates[2]; row++) {
                    board[row][shipCoordinates[1]] = shipArray[i];
                    i++;
                }
            }
        }


        //for (int rows = 0; rows < board.length;)


    public boolean checkEmpty(int[] shipCoordinates) {
        if(shipCoordinates[0] == shipCoordinates[2]) {
            int i = 0; //used to loop through ship array
            for (int col = shipCoordinates[1];col<=shipCoordinates[3];col++) {
                if(board[shipCoordinates[0]][col] == "O") {
                    System.out.println();
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    System.out.println();
                    return false;
                }
                if(!checkSurroundingArea(shipCoordinates[0],col)) {
                    return false;
                }
                i++;
            }
        }
        if(shipCoordinates[1] == shipCoordinates[3]) {
            int i = 0; //used to loop trhough ship array
            for(int row = shipCoordinates[0];row<=shipCoordinates[2];row++){
                if(board[row][shipCoordinates[1]] == "O") {
                    System.out.println();
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    System.out.println();
                    return false;
                }
                if(!checkSurroundingArea(row,shipCoordinates[1])) {
                    return false;
                }
                i++;
            }
        }

        return true;
    }

    public boolean checkSurroundingArea(int one, int two) {

        if(two+1==11) {
            two = two-1;
        }
        if(one+1==11) {
            one = one-1;
        }

        if(board[one-1][two].equals("O")) {
            System.out.println();
            System.out.println("Error! Ships cannot touch!");
            System.out.println();
            return false;
        }
        if(board[one][two+1].equals("O")) {
            System.out.println();
            System.out.println("Error! Ships cannot touch!");
            System.out.println();
            return false;
        }
        if(board[one+1][two].equals("O")) {
            System.out.println();
            System.out.println("Error! Ships cannot touch!");
            System.out.println();
            return false;
        }
        if(board[one][two-1].equals("O")) {
            System.out.println();
            System.out.println("Error! Ships cannot touch!");
            System.out.println();
            return false;
        }

        return true;
    }

    public boolean fireShip(int[] targetArray) {
        int row = targetArray[0];
        int col = targetArray[1];

        if (board[row][col].equals("O")) {
            board[row][col] = "X";
            return true;
        }
        else {
            board[row][col] = "M";
            return false;
        }

    }

    public boolean checkIfAllShipsSunk() {
        for(int rows = 0; rows<board.length; rows++) {
            for(int cols = 0; cols<board[rows].length; cols++) {
                if (board[rows][cols].equals("O")){
                    return false;
                }
            }
        }
        return true;
    }

    public String[][] getBoard() {
        return board;
    }

    public void placeMiss(int[] targetArray) {
        int row = targetArray[0];
        int col = targetArray[1];

        board[row][col] = "M";
    }

}
