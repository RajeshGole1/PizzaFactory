package com.pizzafactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

//Main class
public class   PizzaDriver{
 private static Scanner scanner = new Scanner(System.in);
 private static PizzaFactory pizzaFactory = new PizzaFactory();
 private static Map<PizzaType, Integer> inventory = new HashMap<>();

 public static void main(String[] args) {
     System.out.println("Welcome to PizzaFactory!");

     // Initialize inventory
     initializeInventory();

     // Display menu
     displayMenu();

     // Take order
     Order order = takeOrder();

     // Validate and place order
     if (validateOrder(order)) {
         placeOrder(order);
     } else {
         System.out.println("Order validation failed. Please try again.");
     }
 }

 private static void initializeInventory() {
     // Initialize inventory with initial quantities
     for (PizzaType type : pizzaFactory.getMenu()) {
         inventory.put(type, 10); // Initial quantity of each pizza type is 10
     }
 }

 private static void displayMenu() {
     System.out.println("\nMenu:");
     for (PizzaType type : pizzaFactory.getMenu()) {
         System.out.println(type.toString());
     }
 }

 private static Order takeOrder() {
     Order order = new Order();

     System.out.print("\nEnter the number of pizzas you want to order: ");
     int numPizzas = scanner.nextInt();

     for (int i = 0; i < numPizzas; i++) {
         System.out.println("\nPizza " + (i + 1));
         System.out.println("---------------");

         System.out.println("Select a pizza type:");
         List<PizzaType> menu = pizzaFactory.getMenu();
         for (int j = 0; j < menu.size(); j++) {
             System.out.println((j + 1) + ". " + menu.get(j));
         }
         System.out.print("Enter your choice: ");
         int pizzaChoice = scanner.nextInt();
         PizzaType pizzaType = menu.get(pizzaChoice - 1);

         System.out.println("\nSelect a pizza size:");
         System.out.println("1. Regular");
         System.out.println("2. Medium");
         System.out.println("3. Large");
         System.out.print("Enter your choice: ");
         int sizeChoice = scanner.nextInt();
         PizzaSize pizzaSize = PizzaSize.values()[sizeChoice - 1];

         System.out.println("\nSelect a crust type:");
         Set<CrustType> crustTypes = pizzaFactory.getCrustTypes();
         int k = 1;
         for (CrustType crustType : crustTypes) {
             System.out.println(k + ". " + crustType);
             k++;
         }
         System.out.print("Enter your choice: ");
         int crustChoice = scanner.nextInt();
         CrustType crustType = crustTypes.toArray(new CrustType[0])[crustChoice - 1];

         Pizza pizza = new Pizza(pizzaType, pizzaSize, crustType);

         System.out.println("\nSelect toppings for your pizza:");
         Set<Topping> allowedToppings = pizzaFactory.getAllowedToppings(pizzaType);
         System.out.println("Available toppings: " + allowedToppings);
         System.out.println("Enter the toppings one by one (Enter -1 to finish):");
         int toppingChoice;
         do {
             System.out.print("Enter topping choice: ");
             toppingChoice = scanner.nextInt();
             if (toppingChoice > 0 && toppingChoice <= allowedToppings.size()) {
                 Topping topping = allowedToppings.toArray(new Topping[0])[toppingChoice - 1];
                 pizza.addTopping(topping);
             }
         } while (toppingChoice > 0 && toppingChoice <= allowedToppings.size());

         System.out.println("\nDo you want extra cheese? (yes/no)");
         String extraCheeseChoice = scanner.next();
         if (extraCheeseChoice.equalsIgnoreCase("yes")) {
             pizza.setExtraCheese(true);
         }

         order.addPizza(pizza);
     }

     System.out.print("\nEnter the number of sides you want to order: ");
     int numSides = scanner.nextInt();

     for (int i = 0; i < numSides; i++) {
         System.out.println("\nSide " + (i + 1));
         System.out.println("---------------");

         System.out.println("Select a side:");
         Set<Side> sides = pizzaFactory.getSides();
         int k = 1;
         for (Side side : sides) {
             System.out.println(k + ". " + side);
             k++;
         }
         System.out.print("Enter your choice: ");
         int sideChoice = scanner.nextInt();
         Side side = sides.toArray(new Side[0])[sideChoice - 1];

         order.addSide(side);
     }

     return order;
 }

 private static boolean validateOrder(Order order) {
     List<Pizza> pizzas = order.getPizzas();

     // Validate pizza inventory
     for (Pizza pizza : pizzas) {
         PizzaType type = pizza.getType();
         int quantity = inventory.getOrDefault(type, 0);
         if (quantity <= 0) {
             System.out.println("Sorry, the pizza '" + type + "' is out of stock.");
             return false;
         }
         inventory.put(type, quantity - 1);
     }

     return true;
 }

 private static void placeOrder(Order order) {
     // Process the order here
     System.out.println("\nOrder placed successfully!");

     // Display order summary
     System.out.println("\nOrder Summary:");
     List<Pizza> pizzas = order.getPizzas();
     System.out.println("Pizzas:");
     for (Pizza pizza : pizzas) {
         System.out.println(pizza.getType() + " - " + pizza.getSize() + " - " + pizza.getCrust());
         System.out.println("Toppings: " + pizza.getToppings());
         if (pizza.hasExtraCheese()) {
             System.out.println("Extra Cheese");
         }
         System.out.println("Price: $" + pizzaFactory.getPrice(pizza.getType(), pizza.getSize()));
         System.out.println();
     }

     List<Side> sides = order.getSides();
     System.out.println("Sides:");
     for (Side side : sides) {
         System.out.println(side);
     }

     System.out.println("\nThank you for ordering from PizzaFactory!");
 }
}