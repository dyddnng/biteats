package Project_KMH;

import Project.Food;

import java.util.ArrayList;

public class Ordered {
    private ArrayList<Project.Food> food;
    private String storeName;
    private int totalPrice;
    private String customerId;

    public Ordered(ArrayList<Food> food, int totalPrice, String customerId) {
        this.food = food;
        this.storeName = food.get(0).getStoreName();
        this.totalPrice = totalPrice;
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        String foodInfo =null;
        for(int i=0;i<food.size();i++) {
            foodInfo+=i+". "+"음식정보: " + this.food.get(i).getfoodName() + ", "+this.food.get(i).getPrice() + "\n";
        }
        return "*********************" + "\n" +
               "- 구매자 아이디: " + this.customerId + "\n"+
               "- 가게: " + this.storeName + "\n"+
               "- 주문음식: " + "\n" + foodInfo + "\n" +
               "- 총 가격: " + this.totalPrice +
               "*********************";
    }
}