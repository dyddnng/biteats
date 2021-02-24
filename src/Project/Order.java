package Project;

public class Order {
    private Food food;
    private int totalPrice;
    private String storeName;
    private String customerId;
    private int orderNum;

    @Override
    public String toString() {
        return "Order [food=" + food + ", totalPrice=" + totalPrice + ", storeName=" + storeName + ", customerId="
                + customerId + ", orderNum=" + orderNum + "]";
    }
}