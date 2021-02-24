package Project_KMH;

public class Food {
    private String foodName;
    private int price;
    private String storeName;
    
    public Food(String storeName, String foodName, int price) {
        this.foodName = foodName;
        this.price = price;
        this.storeName = storeName;
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
        return "Food [가게명=" + storeName + ", 음식=" + foodName + ", 가격=" + price + "]";
    }
}