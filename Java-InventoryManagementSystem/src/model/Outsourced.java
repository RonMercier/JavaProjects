package model;

/**
 * Model of the Outsourced part
 *
 * @author Ron Mercier
 *
 * */
public class Outsourced extends Part {
    /**
     * Company Name for the part
     * */
    private String companyName;
    /**
     * Constructor for a new instance of the Outsourced part.
     *
     * @param id Id of the part
     * @param name Name of the part
     * @param price Price of the part
     * @param stock Stock level of the part
     * @param min Minimum level of the part
     * @param max Maximum level of the part
     * @param companyName Company Name of the Outsourced part
     * */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    /**
     * Getter of the Company Name
     *
     * @return Company Name for the part
     * */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * Setter of the Company Name
     *
     * @param companyName Sets the companyName
     * */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}