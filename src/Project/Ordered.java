package Project;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ordered implements Serializable {
    //구매자의 주문목록
    private List<Food> food;
    //가게이름
    private String storeName;
    //총 가격
    private int totalPrice;
    //구매자 아이디
    private String customerId;
    private final String orderHistoryPath = "C:\\BitEats\\OrderHistory";
    private static final long serialVersionUID = 3L;



    public Ordered(List<Food> food, int totalPrice, String customerId) {  //구매내역 발생하면 바로 파일로 저장됩니다
        this.food = new ArrayList<Food>();
        this.food = food;
        this.totalPrice = totalPrice;
        this.customerId = customerId;

        String filename = orderHistoryPath + "\\" + customerId + ".txt"; // 객체를 직렬화해서 write

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream out = null;

        try {
            fos = new FileOutputStream(filename); //append
            bos = new BufferedOutputStream(fos);
            // 여기까지 하고 직렬화를 하기 위해 아래와 같이 한다
            out = new ObjectOutputStream(bos);

            //직렬화 대상 (객체)
            Ordered ordered = new Ordered(this.food, this.totalPrice, this.customerId);

            //이제 다른곳으로 보낼거임. filename으로
            out.writeObject(ordered); // 분해해서 Userdata.txt 에 쓴다
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                out.close();
                bos.close();
                fos.close();
            } catch (Exception e2) {

            }
        }
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
