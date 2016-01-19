package com.eyzhou.zest;

/**
 * Created by eyzhou on 1/6/16.
 */
public class RowItemGrocery {
    private String groceryName;
    private String groceryAmount;
    private int groceryPrice;


    public RowItemGrocery(String groceryName, String groceryAmount, int groceryPrice) {
        this.groceryName = groceryName;
        this.groceryAmount = groceryAmount;
        this.groceryPrice = groceryPrice;
    }

    public String getName() {
        return groceryName;
    }

    public String getAmount() {
        return groceryAmount;
    }

    public String getPrice() {
        return "$ " + Integer.toString(groceryPrice);
    }


}
