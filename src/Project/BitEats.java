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
    private final String storePath = "C:\\BitEats\\Store";
    private File f;
    private Store store;
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
        checkFileExists(f); //경로 체크 함수 실행
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

        File[] files = f.listFiles();

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
                showStore();

/////////////////////////////////////////////////////////////////////////////////////////////////////////
//                while (choice != 3) //3번이 결제버튼
//                    System.out.println("가게 목록을 불러옵니다!");
//                //showMenu()로 목록 불러옴
//                System.out.println("대충 가게 목록 불러왔다는 이야기");
//                System.out.println("대충 원하는 가게를 선택해달라는 이야기");
//                System.out.println("대충 원하는 메뉴를 선택하거나 결제해달라는 이야기");
//                System.out.println("대충 원하는 메뉴를 선택하거나 결제해달라는 이야기");
//                //while문 빠져나옴
//                System.out.println("대충 결제완료 후 돈뺏기고 거래내역 저장되었다는 이야기");

//////////////////////////////////////////////////////////////////////////////////////////////////////////

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
    public void showMenu() {

        String filename = "menulist.txt";

        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ObjectInputStream in = null;

        try {

            fis = new FileInputStream(filename);
            bis = new BufferedInputStream(fis);
            in = new ObjectInputStream(bis); // 역직렬화 코드

            Store menulist = (Store)in.readObject();

            System.out.println(menulist);


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

    public void storeMenu() {
        System.out.println("==========비트이츠 가게 목록==========");
        switch(1) {
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

            BitEats storelist01 = (BitEats) in.readObject();

            for (int i = 0; i < storelist01.getStoreList().size(); i++) {
                System.out.print(i+1 + " : ");
                System.out.println(storelist01.getStoreList().get(i));
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

        f = new File(this.loginInfoPath);
        //storeList = new ArrayList<String>();
        storeList = new ArrayList<Store>();
        //store = new Store();

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
}

/*    public void addStore(String name, String foodName, int price) {

        storeList.add(store = new Store(name,foodName,price));*/




/*    @Override
    public String toString() {
        return "BitEats [customerList=" + customerList + ", storeList=" + storeList + "]";
    }*/


