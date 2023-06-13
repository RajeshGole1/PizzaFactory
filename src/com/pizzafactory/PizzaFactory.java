package com.pizzafactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PizzaFactory {
    private Map<PizzaType, Map<PizzaSize, Double>> menu;
    private Set<Topping> vegToppings;
    private Set<Topping> nonVegToppings;
    private Set<CrustType> crustTypes;
    private Set<Side> sides;
    private Map<PizzaType, Set<Topping>> allowedToppings;

    public PizzaFactory() {
        menu = new HashMap<>();
        vegToppings = new HashSet<>();
        nonVegToppings = new HashSet<>();
        crustTypes = new HashSet<>();
        sides = new HashSet<>();
        allowedToppings = new HashMap<>();
        initializeMenu();
        initializeToppings();
        initializeCrustTypes();
        initializeSides();
        initializeAllowedToppings();
    }

    private void initializeMenu() {
        Map<PizzaSize, Double> deluxeVeggiePrices = new HashMap<>();
        deluxeVeggiePrices.put(PizzaSize.REGULAR, 150.0);
        deluxeVeggiePrices.put(PizzaSize.MEDIUM, 200.0);
        deluxeVeggiePrices.put(PizzaSize.LARGE, 325.0);
        menu.put(PizzaType.DELUXE_VEGGIE, deluxeVeggiePrices);

        Map<PizzaSize, Double> cheeseAndCornPrices = new HashMap<>();
        cheeseAndCornPrices.put(PizzaSize.REGULAR, 175.0);
        cheeseAndCornPrices.put(PizzaSize.MEDIUM, 375.0);
        cheeseAndCornPrices.put(PizzaSize.LARGE, 475.0);
        menu.put(PizzaType.CHEESE_AND_CORN, cheeseAndCornPrices);

        Map<PizzaSize, Double> paneerTikkaPrices = new HashMap<>();
        paneerTikkaPrices.put(PizzaSize.REGULAR, 160.0);
        paneerTikkaPrices.put(PizzaSize.MEDIUM, 290.0);
        paneerTikkaPrices.put(PizzaSize.LARGE, 340.0);
        menu.put(PizzaType.PANEER_TIKKA, paneerTikkaPrices);

        Map<PizzaSize, Double> nonVegSupremePrices = new HashMap<>();
        nonVegSupremePrices.put(PizzaSize.REGULAR, 190.0);
        nonVegSupremePrices.put(PizzaSize.MEDIUM, 325.0);
        nonVegSupremePrices.put(PizzaSize.LARGE, 425.0);
        menu.put(PizzaType.NON_VEG_SUPREME, nonVegSupremePrices);

        Map<PizzaSize, Double> chickenTikkaPrices = new HashMap<>();
        chickenTikkaPrices.put(PizzaSize.REGULAR, 210.0);
        chickenTikkaPrices.put(PizzaSize.MEDIUM, 370.0);
        chickenTikkaPrices.put(PizzaSize.LARGE, 500.0);
        menu.put(PizzaType.CHICKEN_TIKKA, chickenTikkaPrices);

        Map<PizzaSize, Double> pepperBarbecueChickenPrices = new HashMap<>();
        pepperBarbecueChickenPrices.put(PizzaSize.REGULAR, 220.0);
        pepperBarbecueChickenPrices.put(PizzaSize.MEDIUM, 380.0);
        pepperBarbecueChickenPrices.put(PizzaSize.LARGE, 525.0);
        menu.put(PizzaType.PEPPER_BARBECUE_CHICKEN, pepperBarbecueChickenPrices);
    }

    private void initializeToppings() {
        vegToppings.add(Topping.BLACK_OLIVE);
        vegToppings.add(Topping.CAPSICUM);
        vegToppings.add(Topping.PANEER);
        vegToppings.add(Topping.MUSHROOM);
        vegToppings.add(Topping.FRESH_TOMATO);

        nonVegToppings.add(Topping.CHICKEN_TIKKA);
        nonVegToppings.add(Topping.BARBECUE_CHICKEN);
        nonVegToppings.add(Topping.GRILLED_CHICKEN);
    }

    private void initializeCrustTypes() {
        crustTypes.add(CrustType.HAND_TOSSED);
        crustTypes.add(CrustType.WHEAT_THIN_CRUST);
        crustTypes.add(CrustType.CHEESE_BURST);
        crustTypes.add(CrustType.FRESH_PAN_PIZZA);
    }

    private void initializeSides() {
        sides.add(Side.COLD_DRINK);
        sides.add(Side.MOUSSE_CAKE);
    }

    private void initializeAllowedToppings() {
        Set<Topping> vegAllowedToppings = new HashSet<>(vegToppings);
        allowedToppings.put(PizzaType.DELUXE_VEGGIE, vegAllowedToppings);
        allowedToppings.put(PizzaType.CHEESE_AND_CORN, vegAllowedToppings);
        allowedToppings.put(PizzaType.PANEER_TIKKA, vegAllowedToppings);

        Set<Topping> nonVegAllowedToppings = new HashSet<>(nonVegToppings);
        allowedToppings.put(PizzaType.NON_VEG_SUPREME, nonVegAllowedToppings);
        allowedToppings.put(PizzaType.CHICKEN_TIKKA, nonVegAllowedToppings);
        allowedToppings.put(PizzaType.PEPPER_BARBECUE_CHICKEN, nonVegAllowedToppings);
    }

    public List<PizzaType> getMenu() {
        return new ArrayList<>(menu.keySet());
    }

    public double getPrice(PizzaType type, PizzaSize size) {
        return menu.get(type).get(size);
    }

    public Set<CrustType> getCrustTypes() {
        return new HashSet<>(crustTypes);
    }

    public Set<Topping> getAllowedToppings(PizzaType type) {
        return new HashSet<>(allowedToppings.get(type));
    }

    public Set<Topping> getVegToppings() {
        return new HashSet<>(vegToppings);
    }

    public Set<Topping> getNonVegToppings() {
        return new HashSet<>(nonVegToppings);
    }

    public Set<Side> getSides() {
        return new HashSet<>(sides);
    }
}
