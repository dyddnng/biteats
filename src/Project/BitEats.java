package Project;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class BitEats implements Serializable {
    private HashMap<String, String> users;
    private ArrayList<Store> storeList;
    private final String loginInfoPath = "C:\\BitEats\\LoginInfo";
    private final String customersPath = "C:\\BitEats\\Customers";
    private final String storePath = "C:\\BitEats\\Store";
    private File loginPathToFile;
    private File customerPathToFile;
    private File storePathToFile;
    private Store store;
    private BitEats bitstores;
    private List<Food> oredrList;
    private Customer currentCustomer; // 현재 로그인되어있는 사용자
    private static final long serialVersionUID = 3L;



    public void boot() {
        int choice = 9;
        while (choice != 0) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("안녕하세요 비트이츠 서비스 입니다. 명령어를 입력해주세요");
            System.out.println("1. 회원가입 2. 로그인 0. 나가기");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 :
                    System.out.println("회원가입을 진행합니다.");
                    join();
                    break;
                case 2 :
                    System.out.println("로그인을 진행합니다.");
                    login();
                    break;
                case 0 :
                    System.out.println("이용해주셔서 감사합니다.");
                    break;
                default :
                    System.out.println("유효하지 않은 명령어입니다. 다시 확인해주세요");
                    break;
            }
        }
        System.out.println("end line------------------------------------");


    }

    //프로그램 사용에 필요한 경로가 있는지 확인 후 없으면 만들어주는 함수
    public void checkFileExists(File f) {

        if(!f.exists() || !f.isDirectory()) {
            System.out.println("디렉토리가 없는것을 발견했습니다. 디렉토리를 생성합니다.");
            if(f.mkdirs()) {
                System.out.println("디렉토리를 생성했습니다.");
            } else {
                System.out.println("디렉토리 생성 실패! 관리자에게 문의해주세요.");
            }
        } else {
            return;
        }
    }

    public void join() {
        checkFileExists(loginPathToFile); //경로 체크 함수 실행
        checkFileExists(customerPathToFile); //경로 체크 함수 실행
        Scanner scanner = new Scanner(System.in);

        System.out.println("비트이츠 회원가입 시스템입니다!!");

        System.out.println("아이디를 입력해주세요");
        String id = scanner.nextLine();
        System.out.println("비밀번호를 입력해주세요");
        String password = scanner.nextLine();

        /*
            "C:\\BitEats\\LoginInfo" 안의 File 객체의 list를 불러오는 함수
             거기 있는 파일들이 모두 file 객체다
             이 객체들을 files[i]에 하나씩 넣자
             지금 LoginInfo 폴더 안에 있는 파일들은 가입된 아이디가 파일명이니
             이 리스트와 중복하는 아이디가 들어오면 중복체크를 할 수 있다      */

        File[] files = loginPathToFile.listFiles();

        // 불러온 파일 리스트를 ArrayList에 넣어서 for문으로 중복 ID 확인과정을 거친다
        List<String> ids = new ArrayList<String>();

        for(int i = 0; i < files.length; i++) {
            ids.add(files[i].getName()); // 파일명들을 ids배열의 인덱스에 넣는다
            if( ids.get(i).equals(id+".txt") ) {  // id와 일치하는 값이 있을 경우
                System.out.println("이미 가입된 아이디입니다.\n메인으로 돌아갑니다.\n");
            }
        }

        // 중복이 아니라면 정상가입 진행
        // 아이디와 비밀번호를 각각 키와 밸류로 지정해 해쉬맵에 넣는다
        this.users = new HashMap<>();
        this.users.put(id, password);

        // 회원 정보 저장해야 하니까 객체를 temp에 넣는다
        // Loginfo 생정자가 이 객체의 정보를 직렬화해서 파일로 내보내는 것까지 한다
        LoginInfo temp = new LoginInfo(id, users);

        //Customer 파일에 회원정보 저장
        String filename = customersPath + "\\" + id + ".txt"; // 객체를 직렬화해서 write

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream out = null;

        try {
            fos = new FileOutputStream(filename); //append
            bos = new BufferedOutputStream(fos);
            // 여기까지 하고 직렬화를 하기 위해 아래와 같이 한다
            out = new ObjectOutputStream(bos);

            //직렬화 대상 (객체)
            Customer customer = new Customer(id);

            //이제 다른곳으로 보낼거임. filename으로
            out.writeObject(customer); // 분해해서 Userdata.txt 에 쓴다
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

        // 직렬화가 끝난 후 다음을 회원가입을 위해 temp를 비운다
        temp = null;


    }
    //로그인 기능
    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("비트이츠 로그인 시스템입니다!!");



        System.out.println("아이디를 입력해주세요");
        String id = scanner.nextLine();
        System.out.println("비밀번호를 입력해주세요");
        String password = scanner.nextLine();

        String filename = loginInfoPath + "\\" + id + ".txt";

        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ObjectInputStream in = null;

        try {
            fis = new FileInputStream(filename); //파일이름불러오고
            bis = new BufferedInputStream(fis); //불러온걸 버퍼로옮기고
            in = new ObjectInputStream(bis); //버퍼로 옮긴걸 조립한다

            //UserInfo r1 = (UserInfo) in.readObject(); //다운캐스팅을 해줘야함

            LoginInfo loginInfo = (LoginInfo) in.readObject();

            if (loginInfo.getLogin().get(id).equals(password)) { //아이디와 키값이 같다면
                System.out.println("로그인을 성공했습니다!");
                setCurrentCustomer(id);
                showMeTheMoney();       //로그인 성공시 인사와 잔액을 보여줍니다.
                showStore();
                storeMenu();
                System.out.println("아디오스");
                System.exit(0);
            } else {
                System.out.println("비밀번호가 틀립니다!!\n메인으로 돌아갑니다.");
            }

        } catch (FileNotFoundException fn) {
            System.out.println("해당 아이디가 존재하지 않습니다.\n메인으로 돌아갑니다.");
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        } finally {
            try {
                in.close();
                bis.close();
                fis.close();
            } catch (Exception e3) {

            }
        }
    }
    public void showMeTheMoney() {
        System.out.printf("환영합니다 %s님 , 잔액 : %d 원을 가지고 계십니다.\n", this.currentCustomer.getId(), this.currentCustomer.getMoney());
    }

    public void setCurrentCustomer(String id) {

        String path = "C:\\BitEats\\Customers\\" + id + ".txt";

        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ObjectInputStream in = null;

        try {
            fis = new FileInputStream(path); //파일이름불러오고
            bis = new BufferedInputStream(fis); //불러온걸 버퍼로옮기고
            in = new ObjectInputStream(bis); //버퍼로 옮긴걸 조립한다

            Customer customer = (Customer) in.readObject();
            this.currentCustomer = customer; // 로그인 성공이후부터 비트이츠 이용자

        } catch (FileNotFoundException fn) {
            System.out.println("해당 아이디가 존재하지 않습니다.\n메인으로 돌아갑니다.");
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        } finally {
            try {
                in.close();
                bis.close();
                fis.close();
            } catch (Exception e3) {

            }
        }
    }

    public void showMenu(Store s) {
        s.getMenu();
    }

    public void storeMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("가게를 선택해주세요");
        int choice = scanner.nextInt();
        switch(choice) {
            case 1: System.out.println("아빠곰돈까스에 오신 것을 환영합니다.");
                //showMenu(storeList.get(choice-1));
                ordering(choice);
                return;
            case 2: System.out.println("덕자네방앗간에 오신 것을 환영합니다.\n원하시는 메뉴를 선택해주세요.");
                //showMenu(storeList.get(choice-1));
                ordering(choice);
                return;

            case 3: System.out.println("꿀맛김밥에 오신 것을 환영합니다.\n원하시는 메뉴를 선택해주세요.");
                //showMenu(storeList.get(choice-1));
                ordering(choice);
                return;

            case 4: System.out.println("피자나라치킨공주에 오신 것을 환영합니다.\n원하시는 메뉴를 선택해주세요.");
                //showMenu(storeList.get(choice-1));
                ordering(choice);
                return;

            default : System.out.println("잘못 입력하셨습니다.");
                return;

        }
    }

    public void ordering(int choice) {
        Scanner scanner = new Scanner(System.in);
        int foodNumber = -1;
        int totalPrice = 0;
        while(foodNumber != 0 ) {
            showMenu(storeList.get(choice-1));  //목록 보여줌(처음)
            System.out.println("원하시는 메뉴를 선택해주세요.\n선택한 메뉴를 결제하시려면 0을 입력해주세요.");
            foodNumber = scanner.nextInt();
            if (foodNumber == 0) {  //foodNumber가 0이면 결제
                if(currentCustomer.getMoney() < totalPrice) {
                    System.out.println("가지고 있는 돈이 결제금액 보다 적습니다. 메뉴를 초기화합니다.");
                    this.oredrList.clear(); //잔액부족으로 결제실패시 담은음식 초기화
                    ordering(choice); //다시 음식을 고른다
                } else { //결제금액이 충분할경우
                    this.currentCustomer.setMoney(this.currentCustomer.getMoney() - totalPrice);
                    System.out.printf("결제가 완료되었습니다! 결제금액 %d원, 잔액 %d원\n" , totalPrice ,this.currentCustomer.getMoney());
                    saveLoginInfo();
                    break;
                }
            }
            selectMenu(choice, foodNumber);
            totalPrice = getOrderList();
        }

    }

    public void saveLoginInfo () { //로그아웃시 회원정보 갱신하는 함수
        String filename = customersPath + "\\" + this.currentCustomer.getId() + ".txt"; // 객체를 직렬화해서 write

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream out = null;

        try {
            fos = new FileOutputStream(filename); //append
            bos = new BufferedOutputStream(fos);
            // 여기까지 하고 직렬화를 하기 위해 아래와 같이 한다
            out = new ObjectOutputStream(bos);

            //직렬화 대상 (객체)
            Customer customer = new Customer(currentCustomer.getId(), currentCustomer.getMoney());

            //이제 다른곳으로 보낼거임. filename으로
            out.writeObject(customer); // 분해해서 Userdata.txt 에 쓴다
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

    public void selectMenu(int choice, int foodNumber) {
        this.oredrList.add(getStoreList().get(choice - 1).getFood(foodNumber - 1));
    }

    // 가게 목록 불러오기 함수
    public void showStore() {

        String filename0 = storePath + "\\storelist.txt";

        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ObjectInputStream in = null;

        try {

            fis = new FileInputStream(filename0);
            bis = new BufferedInputStream(fis);
            in = new ObjectInputStream(bis); // 역직렬화 코드

            BitEats storelist = (BitEats) in.readObject();
            this.bitstores = storelist;

            for (int i = 0; i < storelist.getStoreList().size(); i++) {
                System.out.print(i + 1 + " : ");
                System.out.println(storelist.getStoreList().get(i));
            }


        } catch (FileNotFoundException fe) {
            System.out.println("파일이 존재하지 않습니다.");
        } catch (EOFException eofe) { // End Of File의 약자
            System.out.println("끝 " + eofe.getMessage());

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());

        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());

        } finally{

            try {
                in.close();
                bis.close();
                fis.close();
            } catch (Exception e) {
                // TODO: handle exception
            }

        }
    }

    public BitEats() {

        loginPathToFile = new File(this.loginInfoPath);
        customerPathToFile = new File(this.customersPath);
        storePathToFile = new File(this.storePath);

        checkFileExists(storePathToFile);

        //storeList = new ArrayList<String>();
        storeList = new ArrayList<Store>();
        oredrList = new ArrayList<Food>();

        //BitEats가 생성되면 가게들 정보가 자동으로 생성됨

        Store store001 = new Store("아빠곰돈까스");
        Store store002 = new Store("덕자네 방앗간");
        Store store003 = new Store("꿀맛 김밥");
        Store store004 = new Store("피자나라 치킨공주");

        storeList.add(store001);
        storeList.add(store002);
        storeList.add(store003);
        storeList.add(store004);

        Food food000 = new Food("로스안심까스", 8500);
        Food food001 = new Food("치즈돈까스", 9000);
        Food food002 = new Food("떡볶이", 4000);
        Food food003 = new Food("왕돈까스", 6000);
        Food food004 = new Food("새우김밥", 4500);
        Food food005 = new Food("참치김밥", 4000);
        Food food006 = new Food("더블포테이토피자", 15000);
        Food food007 = new Food("콤비네이션피자", 12000);

        store001.addMenu(food000);
        store001.addMenu(food001);
        store002.addMenu(food002);
        store002.addMenu(food003);
        store003.addMenu(food004);
        store003.addMenu(food005);
        store004.addMenu(food006);
        store004.addMenu(food007);
    }
    public void write() {
        // storelist 직렬화해서 내보내기
        String path = this.storePath + "\\" + "storelist.txt";

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(path);
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);
            //직렬화 대상 bit
            BitEats bit = new BitEats();

            oos.writeObject(bit);

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

    public ArrayList<Store> getStoreList() {
        return storeList;
    }
    public int getOrderList() {
        int totalprice = 0;
        System.out.println("=======현재까지 담긴 음식=======");
        for(int i = 0; i < this.oredrList.size(); i++) {
            System.out.println(this.oredrList.get(i));
            totalprice += this.oredrList.get(i).getPrice();
        }
        System.out.println("총 결제금액 : " + totalprice);
        System.out.println("==============================");
        return totalprice;
    }
}

/*    public void addStore(String name, String foodName, int price) {

        storeList.add(store = new Store(name,foodName,price));*/




/*    @Override
    public String toString() {
        return "BitEats [customerList=" + customerList + ", storeList=" + storeList + "]";
    }*/


