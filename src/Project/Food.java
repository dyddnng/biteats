package Project;

import java.io.Serializable;

public class Food implements Serializable {
    //음식이름
    private String foodName;
    //음식가격
    private int price;
    //음식이 포함되있는 가게이름
    private String storeName;
    private static final long serialVersionUID = 3L;


    public Food(String foodName, int price) {
        this.foodName = foodName;
        this.price = price;
    }
    
    public int getPrice() {
        return price;
    }
    
    public String getfoodName() {
        return foodName;
    }
    
    public String getStoreName() {
        return storeName;
    }
    @Override
    public String toString() {
        return "[음식 = " + this.foodName + " 가격 = " + this.price + "]";
    }
}