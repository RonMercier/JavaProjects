package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is an inventory model for the parts and products.
 *
 * @author Ron Mercier
 *
 * */

public class Inventory {
    /**
     * Provides a list of all parts in the inventory.
     * */
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    /**
     * Provides a list of all products in the inventory.
     * */
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();
    /**
     * Variable used for part IDs.
     * */
    private static int partId = 0;
    /**
     * Variable used for product IDs.
     * */
    private static int productId = 0;
    /**
     * Generates a new part ID.
     *
     * @return Returns a new part ID.
     * */
    public static int generatePartId() {
        partId++;
        return partId;
    }
    /**
     * Generates a new product ID.
     *
     * @return Returns a new product ID.
     * */
    public static int generateProductId() {
        productId++;
        return productId;
    }
    /**
     * Adds a new part to the inventory.
     *
     * @param newPart The part object to add.
     *
     * */
    public static void addPart (Part newPart) {
        allParts.add(newPart);
    }
    /**
     * Adds a new product to the inventory
     *
     * @param newProduct The product object to add.
     *
     * */
    public static void addProduct (Product newProduct) {
        allProducts.add(newProduct);
    }
    /**
     * Searches for the part by the part ID.
     *
     * @param partId partID used in the search for the part.
     * @return Returns the part by the part ID.
     *
     * */
    public static int lookupPart(int partId) {
        return partId;
    }
    /**
     * Searches for the product by the product ID.
     *
     * @param productId productID used in the search for the product.
     * @return Returns the product by the product ID.
     *
     * */
    public static int lookupProduct(int productId) {
        return productId;
    }
    /**
     * Searches for the part by the part name.
     *
     * @param partName Name that is used to search for the part.
     * @return Returns the search result from the partName.
     *
     * */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> searchResult = FXCollections.observableArrayList();
        if (!allParts.isEmpty()) {
            for (Part allPart : allParts) {
                if (allPart.getName().toLowerCase().contains(partName.toLowerCase())) {
                    searchResult.add(allPart);
                }
            }
        } else {
            return searchResult;
        }
        return searchResult;
    }
    /**
     * Searches for the product by the product name.
     *
     * @param productName Name that is used to search for the product.
     * @return Returns the search result from the productName.
     *
     * */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> searchResult = FXCollections.observableArrayList();
        if (!allProducts.isEmpty()) {
            for (Product allProduct : allProducts) {
                if (allProduct.getName().toLowerCase().contains(productName.toLowerCase())) {
                    searchResult.add(allProduct);
                }
            }
        } else {
            return searchResult;
        }
        return searchResult;
    }
    /**
     * Updates a part in the list of parts.
     *
     * @param partIndex Index of the part that is being updated.
     * @param selectedPart The part that is being updated.
     *
     * */
    public static void updatePart(int partIndex, Part selectedPart) {
        allParts.set(partIndex, selectedPart);
    }
    /**
     * Updates a product in the list of products.
     *
     * @param productIndex Index of the product that is being updated.
     * @param selectedProduct The product that is being updated.
     *
     * */
    public static void updateProduct(int productIndex, Product selectedProduct) {
        allProducts.set(productIndex, selectedProduct);
    }
    /**
     * Deletes a part from the list of parts.
     *
     * @param selectedPart The part that is being removed.
     * @return A boolean that indicates the status of the part removal.
     *
     * */
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }
    }
    /**
     * Deletes a product from the list of products.
     *
     * @param selectedProduct The product that is being removed.
     * @return A boolean that indicates the status of the product removal.
     *
     * */
    public static boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }
    /**
     * Retrieves a list of all parts in the inventory.
     *
     * @return Returns a list of all parts.
     *
     * */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    /**
     * Retrieves a list of all products in the inventory.
     *
     * @return Returns a list of all products.
     *
     * */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }




}
