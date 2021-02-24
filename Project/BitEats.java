package Project;

import java.util.ArrayList;

public class BitEats {
    ArrayList<Customer> customerList;
    ArrayList<Store> storeList;
    
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
