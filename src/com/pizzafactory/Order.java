package com.pizzafactory;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Pizza> pizzas;
    private List<Side> sides;

    public Order() {
        pizzas = new ArrayList<>();
        sides = new ArrayList<>();
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public void addSide(Side side) {
        sides.add(side);
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public List<Side> getSides() {
        return sides;
    }
}