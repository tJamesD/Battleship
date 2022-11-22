package battleship;

//import static java.lang.Integer.parseInt;

public class Ship {
    int size;
    String shipName;
    String[] shipArray;
    int[] shipCoordinates;
    int[] shipTargetArray;

    boolean shipSunk;




    public Ship(String shipName, int size) {
        //use as a switch case variable to loop name in user prompt
        //consider just suing ship size
        this.shipName = shipName;
        this.size = size;
        this.shipArray = new String[size];
        this.shipCoordinates = new int[4];
        this.shipTargetArray = new int[size];
        this.shipSunk = false;
        fillShipArray();
        fillCoordinateArray();


    }
    public void buildShip(int[] shipCoordinates) {
        int[] copyOfShipCoordinates = new int[4];
        copyOfShipCoordinates[0] = shipCoordinates[0];
        copyOfShipCoordinates[1] = shipCoordinates[1];
        copyOfShipCoordinates[2] = shipCoordinates[2];
        copyOfShipCoordinates[3] = shipCoordinates[3];

        this.shipCoordinates = copyOfShipCoordinates;

    }

    public void fillShipArray() {
        for(int i = 0; i<size; i++ ) {
            shipArray[i] = "O";
        }
    }

    public void setValidTargetArray() {
        if(shipCoordinates[0] == shipCoordinates[2]) { //HORIZONTAL

            int arrayIndex = 0;
            for (int i = shipCoordinates[1]; i<= shipCoordinates[3];i++) {
                String s1 = Integer.toString(shipCoordinates[0])+Integer.toString(i);
                int i1 = Integer.parseInt(s1);
                shipTargetArray[arrayIndex] = i1;
                arrayIndex++;
            }

        }

        if(shipCoordinates[1] == shipCoordinates[3]) { //VERTICAL
            int arrayIndex = 0;
            for (int i = shipCoordinates[0]; i<= shipCoordinates[2];i++) {
                String s1 = Integer.toString(i) + Integer.toString(shipCoordinates[3]);
                int i1 = Integer.parseInt(s1);
                shipTargetArray[arrayIndex] = i1;
                arrayIndex++;
            }
        }
    }

    public int[] getShipTargetArray() {
        return shipTargetArray;
    }

    public void fillCoordinateArray() {
        for (int i = 0; i<shipCoordinates.length; i++) {
            shipCoordinates[i] = -2;
        }
    }

    public void setShipCoordinates(int[] shipCoordinates) {
        this.shipCoordinates = shipCoordinates;
    }

    public void printArray() {
        for(String x : shipArray) {
            System.out.print(x);
        }
    }

    public void setHit(int arraySlot) {
        shipArray[arraySlot] = "X";
    }

    public String[] getShipArray() {

        return shipArray;
    }


    public void loopCoordinates() {
        for (int i = 0;i<shipCoordinates.length; i++) {
            System.out.print(shipCoordinates[i]);
        }
    }

    public int[] getShipCoordinates() {
        return shipCoordinates;
    }

    public String getShipName() {
        return this.shipName;
    }

    public int getSize() {
        return size;
    }

    public boolean shipSunk(Board board) {
       String[][] boardArray = board.getBoard();
        if(shipCoordinates[0] == shipCoordinates[2]) {
            for (int col = shipCoordinates[1]; col<=shipCoordinates[3];col++) {
                if(boardArray[shipCoordinates[0]][col].equals("X")) {
                    return false;
                }
            }
        }

        if(shipCoordinates[1] == shipCoordinates[3]) {
            for (int row = shipCoordinates[0]; row<=shipCoordinates[2];row++) {
                if(boardArray[row][shipCoordinates[1]].equals("X")) {
                    return false;
                }
            }
        }
        shipSunk = true;
        return true;
    }

    public boolean checkShipSunk() {
        shipSunk = true;
        for(int i = 0; i<shipArray.length;i++){
            if(shipArray[i].equals("O")) {
                shipSunk = false;
            }
        }
        return shipSunk;
    }

    public boolean getShipSunk() {
        return shipSunk;
    }

}
