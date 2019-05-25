package com.test.ch1;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

// 建造者模式 带有层级的构造
public abstract class Pizza {

    public enum Topping{
        HAM,MUSHROOM,ONION,PEPPER,SAUSAG
    }

    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping){

            toppings.add(Objects.requireNonNull(topping));
            return self();
        }


        abstract Pizza build();

        abstract T self();

    }



    Pizza(Builder<?> builder){
        toppings = builder.toppings.clone();
    }

}
