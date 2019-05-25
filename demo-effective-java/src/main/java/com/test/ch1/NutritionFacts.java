package com.test.ch1;

/**
 * 建造者模式
 *
 */
public class NutritionFacts {
    private final int servingSize;

    private final int servings;

    private final int calories;

    private final int fat;

    private final int sodium;


    public static class Builder{

        private final int servingSize;

        private final int servings;


        private int calories;

        private int fat;

        private int sodium;


        public Builder(int servingSize, int servings){
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val){
            this.calories = val;
            return this;
        }

        public Builder fat(int val){
            this.fat = val;
            return this;
        }

        public Builder sodium(int val){
            this.sodium = val;
            return this;
        }

        public NutritionFacts build(){
            return new NutritionFacts(this);
        }


    }

    private NutritionFacts(Builder builder){
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
    }

    public static void main(String[] args){
        NutritionFacts cocaCola = new NutritionFacts.Builder(11,22).calories(1).sodium(2).build();
    }
}
