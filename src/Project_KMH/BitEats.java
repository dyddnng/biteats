package Project_KMH;

import Project.Customer;
import Project.Store;

import java.util.ArrayList;

public class BitEats {
    ArrayList<Project.Customer> customerList;
    ArrayList<Project.Store> storeList;

    public BitEats() {
        customerList = new ArrayList<Customer>();
        storeList = new ArrayList<Store>();
    }

    public void join() {
        
    }

    public void login() {
        
    }

    @Override
    public String toString() {
        return "BitEats [customerList=" + customerList + ", storeList=" + storeList + "]";
    }
}
