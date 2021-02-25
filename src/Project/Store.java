package Project;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Store implements Serializable{
    private String name;
    private List<Food> menu;
    private Ordered ordered;
    //메뉴 txt를 저장할 경로
    private final String storePath = "C:\\BitEats\\Store";
    /*
    Store(String name){
    	this.name = name;
    }
    */
    Store(String name, String FoodName,int price){
        this.name = name;
        this.menu = new ArrayList<>();
        Food food = new Food(this.name,FoodName,price);
        this.menu.add(food);

        //File f = new File(storePath);
        // menu 직렬화해서 내보내기
        String menulist = "menu.txt";
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(menulist);
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);

            oos.writeObject(menulist);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            try {
                oos.close();
                bos.close();
                fos.close();
            } catch (Exception e2) {
                // TODO: handle exception
            }

        }


    }

    public void StoreMenu(int storenumber) {

        switch(storenumber) {
            case 1: System.out.println("아빠곰돈까스에 오신 것을 환영합니다.\n원하시는 메뉴를 선택해주세요.");
                break;
            case 2: System.out.println("덕자네방앗간에 오신 것을 환영합니다.\n원하시는 메뉴를 선택해주세요.");
                break;
            case 3: System.out.println("꿀맛김밥에 오신 것을 환영합니다.\n원하시는 메뉴를 선택해주세요.");
                break;
            case 4: System.out.println("피자나라치킨공주에 오신 것을 환영합니다.\n원하시는 메뉴를 선택해주세요.");
                break;
            default : System.out.println("잘못 입력하셨습니다.");
                break;
        }
    }




    /*
    public void addMenu(String FoodName, int price) {

    	// 음식은 가게에 포함되므로 가게 생성자에서 음식 객체 생성한다
    	Food food = new Food(this.name,FoodName,price);
    	this.menu.add(food);
    }
    */
}
