package com.pizzafactory;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private PizzaType type;
    private PizzaSize size;
    private CrustType crust;
    private List<Topping> toppings;
    private boolean extraCheese;

    public Pizza(PizzaType type, PizzaSize size, CrustType crust) {
        this.type = type;
        this.size = size;
        this.crust = crust;
        toppings = new ArrayList<>();
        extraCheese = false;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public PizzaType getType() {
        return type;
    }

    public PizzaSize getSize() {
        return size;
    }

    public CrustType getCrust() {
        return crust;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public boolean hasExtraCheese() {
        return extraCheese;
    }

    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }
}
