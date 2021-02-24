package Project;
import java.util.ArrayList;

public class Ordered {
    //구매자의 주문목록
    private ArrayList<Food> food;
    //가게이름
    private String storeName;
    //총 가격
    private int totalPrice;
    //구매자 아이디
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
