# Java Inventory Management System

## UML Diagram
![Screenshot](https://github.com/RonMercier/Java_InventoryManagementSystem/blob/master/Java-InventoryManagementSystem/UML%20Diagram%20for%20Inventory%20System.png)

## Screenshots


### Main Screen

![Screenshot](https://github.com/RonMercier/Java_InventoryManagementSystem/blob/master/Java-InventoryManagementSystem/C482%20Screenshots/MainScreen.PNG)

The main screen contains the Parts and Products tables.  On this screen the user can add, modify and delete the parts on the table.  They also have the ability to add, modify and delete products from the table.  The set of parts shown on the left, belong to the product table on the right.  Since the parts are associated with the product, all parts must be removed from the product in order for the product to be deleted.  



### Add Part Screen (In-House and Outsourced)

![Screenshot](https://github.com/RonMercier/Java_InventoryManagementSystem/blob/master/Java-InventoryManagementSystem/C482%20Screenshots/AddPart_InHouse_Outsourced.PNG)

In the app parts screen, if the user selects In-House, the lavel on the bottom left displays Machine ID.  If the user selects the Outsourced button, the label displays Company name.



### Add Product Screen

![Screenshot](https://github.com/RonMercier/Java_InventoryManagementSystem/blob/master/Java-InventoryManagementSystem/C482%20Screenshots/AddProduct.PNG)

On this screen the user can add a product and select the parts that they want to associate with the product.  The user can select a part from the table on the top right and click on "add" to add it to the product.  They can also remove the part from the table on the bottom right by selecting it and clicking on "Remove Associated Part".  The user also has the ability to search for a part by using the search bar on the top right corner. 


### Modify Product Screen

![Screenshot](https://github.com/RonMercier/Java_InventoryManagementSystem/blob/master/Java-InventoryManagementSystem/C482%20Screenshots/ModifyProduct.PNG)

On this screen, the user can modify an existing product.  They can also modify the parts that are associated with the product. 


## Application details

IDE: Intellij<br>
Java version: Java SE 11.0.9<br>
JavaFX: JavaFX-SDK-11.0.2<br>

## Description

This is a multiple screen JavaFX application for WGU course C482 Software I.  It is an inventory management system for Parts and Products.  The application will allow users to enter, edit, and delete Parts (In-House or Outsourced) and Products.  It also validates the data that is entered by the user. 

## How to run the application

  Start by downloading JavaFX 11.0.2. (No longer packaged with JDK11+) - https://gluonhq.com/products/javafx/

  Once this has downloaded, add the lib folder to your project by following the steps below
  
    1.  File -> Project Structure -> libraries
  
    2.  Add the lib folder of your downloaded JavaFX library.
 
  PATH variable for the lib folder needs to be added to IntelliJ. This will make the step of adding VM options easier.<br>
  
  Follow the steps below
  
    3.  File -> Settings -> Path Variables (Under Appearance & Behavior sub-menu)
    
    4.  Create a new variable named 'PATH_TO_FX' and point it to the same lib folder from the last step.

  Finally, We need to add VM options, so the application uses the JavaFX library.<br>

  Go to:<br>

    5.  Run -> Edit Configurations and in the top-right 'Modify Options' dropdown, select 'Add VM Options'.
    
  This will add a new field in the Configuration Panel for VM Options.
  
  Paste this into the field:<br>

      --module-path ${PATH_TO_FX} --add-modules javafx.fxml,javafx.controls,javafx.graphics<br>
