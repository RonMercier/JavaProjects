package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This model contains the Product class that has
 * associated parts.
 *
 * @author Ron Mercier
 * */

public class Product {



   /**
    * @param id Id of the product
    * */
   private int id;
   /**
    * @param name Name of the product
    * */
   private String name;
   /**
    * @param price Price of the product
    * */
   private double price;
   /**
    * @param stock Stock level of the product
    * */
   private int stock;
   /**
    * @param min Minimum level of the product
    * */
   private int min;
   /**
    * @param max Maximum level of the product
    * */
   private int max;

    /**
     * List of associated parts of the product
     * */
    public ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    /**Constructor for a new instance of a part
     *
     * @param id Id of the product
     * @param name Name of the product
     * @param price Price of the product
     * @param stock Stock level of the product
     * @param min Minimum level of the product
     * @param max Maximum level of the product
     * */
   public Product(int id, String name, double price, int stock, int min, int max) {
       this.id = id;
       this.name = name;
       this.price = price;
       this.stock = stock;
       this.min = min;
       this.max = max;
   }


    /**
    * Getters and Setters
    * */

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }
    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }
    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }
    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }
    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }
    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
    /**
     * Adds a part to the list of associated parts of the product
     *
     * @param part Adds the associated part*/
    public void addAssociatedPart(Part part) {
       associatedParts.add(part);
    }
    /**
     * Deletes a part from the list of associated parts of the product
     *
     * @param selectedAssociatedPart Selected part to delete
     * @return A boolean that indicates the status of the deletion
     * */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
       if (associatedParts.contains(selectedAssociatedPart)) {
           associatedParts.remove(selectedAssociatedPart);
           return true;
       }
       else
           return false;
    }
    /**
     * Gets all the associated parts of the product
     *
     * @return List of associated parts
     * */
    public ObservableList<Part> getAllAssociatedParts() {
       return associatedParts;
    }


}
