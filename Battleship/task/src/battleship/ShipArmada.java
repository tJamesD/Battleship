package battleship;

import java.util.ArrayList;

public class ShipArmada {

    Ship Carrier;
    Ship BattleShip;
    Ship Submarine;
    Ship Cruiser;
    Ship Destroyer;
    Boolean newlySunk;

    int shipSunkCount;

    ArrayList<Ship> shipArmadaList;

    public ShipArmada(Ship Carrier, Ship Battleship, Ship Submarine, Ship Cruiser, Ship Destroyer) {
        this.Carrier = Carrier;
        this.BattleShip = Battleship;
        this.Submarine = Submarine;
        this.Cruiser = Cruiser;
        this.Destroyer = Destroyer;
        this.shipSunkCount = 0;
        this.newlySunk = false;

        shipArmadaList = new ArrayList<>();

        shipArmadaList.add(Carrier);
        shipArmadaList.add(Battleship);
        shipArmadaList.add(Submarine);
        shipArmadaList.add(Cruiser);
        shipArmadaList.add(Destroyer);

    }

    public ArrayList<Ship> getShipArmadaList() {
        return shipArmadaList;
    }

    public void getValidTargets() {
        for (Ship ship : shipArmadaList) {
            int[] tempArray = ship.getShipCoordinates();
            for (int i = 0; i < tempArray.length; i++) {
                System.out.println(ship.getShipName() + " " + tempArray[i]);
            }
        }
    }

    public void fillValidTargetArrays() {
        for (Ship ship : shipArmadaList) {
            ship.setValidTargetArray();
        }
    }

    public void getValidTargetArrays() {
        for (Ship ship : shipArmadaList) {
            int[] tempArray = ship.getShipTargetArray();
            for (int i = 0; i < tempArray.length; i++) {
                System.out.println(ship.getShipName() + " " + tempArray[i]);
            }
        }
    }

    public boolean checkForHit(int target) {

        for (Ship ship : shipArmadaList) {
            int[] tempArray = ship.getShipTargetArray();
            for (int i = 0; i < tempArray.length; i++) {
                //System.out.println("SHIP - " + ship.getShipName() + " target - " + target + " tempArrayI - " + tempArray[i]  );
                if (target == tempArray[i]) {
                    return true;
                }
            }

        }
        return false;
    }

    public boolean allShipsSunk() {
        for (Ship ship : shipArmadaList) {
            if (!ship.checkShipSunk()) {
                return false;
            }
        }
        return true;
    }

    public void setHit(int target) {
        for (Ship ship : shipArmadaList) {
            int[] tempArray = ship.getShipTargetArray();
            for (int i = 0; i < tempArray.length; i++) {
                if (target == tempArray[i]) {
                    ship.setHit(i);
                }
            }
        }
    }

    public void viewShipArrays() {
        for (Ship ship : shipArmadaList) {
            String[] tempArray = ship.getShipArray();
            for (int i = 0; i < tempArray.length; i++) {
                System.out.print(tempArray[i]);
            }
            System.out.println();
        }
    }

    public void setSunkShipCount() {

        int currentShipSunkCount = 0;

        for (Ship ship : shipArmadaList) {
            //System.out.println("SHIPSUNKCHECKSHIPARMADA " + ship.checkShipSunk());
            if(ship.checkShipSunk() == true) {
                currentShipSunkCount++;
            }
        }

        this.shipSunkCount = currentShipSunkCount;
    }

    public int getShipSunkCount() {
        return this.shipSunkCount;
    }




}
