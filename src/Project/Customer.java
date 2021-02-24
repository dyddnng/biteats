package Project;

import java.util.ArrayList;

public class Customer {
    private String id;
    private int money;
    private ArrayList<Order> orderList;

    public Customer() {
        orderList = new ArrayList<Order>();
    }

    public void order() {

    }

    public void pay() {

    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", money=" + money + ", orderList=" + orderList + "]";
    }
}