package Project;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {
    //구매자 아이디
    private String id;
    //구매자 돈
    private int money;
    //주문목록 ( 결제전 )
    private ArrayList<Food> orderList;
    //주문내역 ( 결제후 )
    private ArrayList<Ordered> orderedList;
    //총 가격
    private int totalPrice;
    private static final long serialVersionUID = 3L;


    public Customer(String id) { //이 생성자는 회원가입시에 쓰입니다.
        this.id = id;
        this.money = 100000;
    }

    public Customer(String id, int money) { //이 생성자는 회원정보 갱신에 쓰입니다.
        this.id = id;
        this.money = money;
    }


    
    //주문하기전 주문목록확인
    public void printOrderList() {
        if(this.orderList.size() == 0) {
            System.out.println("주문목록에 메뉴가 없습니다.");
            return;
        }
        for(int i=0; i<this.orderList.size();i++) {
            System.out.println(this.orderList.get(i).toString());
        }
    }
    
    //주문목록에 음식추가
    public void plusOrder(Food food) {
        this.orderList.add(food);
        this.totalPrice += food.getPrice();
    }

    //주문목록에서 음식제거
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
    
    //주문목록 결제
    public void pay() {
        if(this.money < this.totalPrice) {
            System.out.println("잔액이 부족합니다.");
            System.out.println("현재금액 : " + this.money);
        } else if(this.orderList.size() == 0) {
            System.out.println("주문목록에 메뉴가 없습니다.");
            return;
        } else {
            this.money-=this.totalPrice;
            orderedList.add(new Ordered(this.orderList,this.totalPrice,this.id));
            orderList.removeAll(orderList);      
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Food> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Food> orderList) {
        this.orderList = orderList;
    }

    public ArrayList<Ordered> getOrderedList() {
        return orderedList;
    }

    public void setOrderedList(ArrayList<Ordered> orderedList) {
        this.orderedList = orderedList;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", money=" + money + ", orderList=" + orderList + "]";
    }
}