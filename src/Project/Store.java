package Project;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store implements Serializable{
    private int storeNumber;
    private String name;
    private List<Food> menu;
    private Ordered ordered;
    //메뉴 txt를 저장할 경로
    private final String storePath = "C:\\BitEats\\Store";
    private static final long serialVersionUID = 3L;

    public Store(String storeName) {
        this.name = storeName;
        this.menu = new ArrayList<Food>();
    }

    public void addMenu(Food food) {
        this.menu.add(food);
    }
    public void getMenu() {
        for(int i = 0; i < menu.size(); i++) {
            System.out.print( i + 1 + "번 메뉴 : ");
            System.out.println(menu.get(i));
        }
    }



    @Override
    public String toString() {
        return name;
    }


}
