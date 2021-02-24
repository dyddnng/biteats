package Project;

import java.util.ArrayList;

public class Customer {
    private String id;
    private int money;
    private ArrayList<Food> orderList;
    private ArrayList<Ordered> orderedList;
    private int totalPrice;
    
    public Customer() {
        this.money = 100000;
        this.orderList = new ArrayList<Food>();
    }
    
    public void printOrderList() {
        for(int i=0; i<this.orderList.size();i++) {
            System.out.println(this.orderList.get(i).toString());
        }
    }
    
    public void plusOrder(Food food) {
        this.orderList.add(food);
        this.totalPrice += food.getPrice();
    }

    public void minusOrder(Food food) {
        for(int i=0; i<this.orderList.size();i++) {
            if(food.getfoodName().equals((orderList.get(i).getfoodName()))) {
                this.totalPrice -= this.orderList.get(i).getPrice();
                orderList.remove(i);
            } else {
                System.out.println("일치하는 메뉴를 찾지못했습니다.");
                return;
            }
        }
        
    }
    
    public void pay() {
        if(this.money < this.totalPrice) {
            System.out.println("잔액이 부족합니다.");
            System.out.println("현재금액 : " + this.money);
        } else {
            this.money-=this.totalPrice;
            orderedList.add(new Ordered(this.orderList,this.totalPrice,this.id));
            orderList.removeAll(orderList);      
        }
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", money=" + money + ", orderList=" + orderList + "]";
    }
}