package Project;

public class Food {
    private String foodName;
    private int price;
    
    public Food(String foodName, int price) {
        foodName = this.foodName;
        price = this.price;
    }

    @Override
    public String toString() {
        return "Food [foodName=" + foodName + ", price=" + price + "]";
    }
}
