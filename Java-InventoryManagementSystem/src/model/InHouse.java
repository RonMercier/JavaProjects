package model;

/**
 * Model of the InHouse part.
 *
 * @author Ron Mercier
 *
 * */

public class InHouse extends Part {
    /**
     * Machine ID for the part
     * */
    private int machineId;
    /**
     * Constructor for a new instance of the InHouse part.
     *
     * @param id Id of the part
     * @param name Name of the part
     * @param price Price of the part
     * @param stock Stock level of the part
     * @param min Minimum level of the part
     * @param max Maximum level of the part
     * @param machineId Machine ID for the part
     * */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    /**
     * Getter for the machineId
     *
     * @return machineId for the part*/
    public int getMachineId() {
        return machineId;
    }
    /**
     * Setter for the machineId
     *
     * @param machineId Sets the machineId
     * */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}