package Project;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class BitEats {
    private HashMap<String, String> users;
    //private ArrayList<Project_KMH.Customer> customerList;
    private ArrayList<Store> storeList;
    private final String loginInfoPath = "C:\\BitEats\\LoginInfo";
    private File f = new File(loginInfoPath);

    Scanner scanner = new Scanner(System.in);


    public BitEats() {
        //customerList = new ArrayList<Customer>();
        storeList = new ArrayList<Store>();
    }
    //실행
    public void boot() {
        int choice = 9;
        while (choice != 0) {
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
    public void checkFileExists() {
        File f = new File(this.loginInfoPath);
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
        checkFileExists(); //경로 체크 함수 실행
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

            // 파일명들을 ids배열의 인덱스에 넣는다
            ids.add(files[i].getName());

            // id와 일치하는 값이 있을 경우
            if( ids.get(i).equals(id+".txt") ) {

                System.out.println("사용할 수 없는 아이디입니다.\n다시 입력해주세요.\n");
                join();
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

        // main에서 join()만 호출하면 되게 여기서 login() 호출하려 했는데
        // 이러니까 로그인 성공하고 나서 또 로그인하라고 뜬다
        // login();

    }





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

//          UserInfo r1 = (UserInfo) in.readObject(); //다운캐스팅을 해줘야함

            LoginInfo loginInfo = (LoginInfo) in.readObject();

            if (loginInfo.getLogin().get(id).equals(password)) { //아이디와 키값이 같다면
                System.out.println("로그인성공 ㅎ");
                int choice = scanner.nextInt();
                while (choice != 3) //3번이 결제버튼
                System.out.println("가게 목록을 불러옵니다!");
                //showMenu()로 목록 불러옴
                System.out.println("대충 가게 목록 불러왔다는 이야기");
                System.out.println("대충 원하는 가게를 선택해달라는 이야기");
                System.out.println("대충 원하는 메뉴를 선택하거나 결제해달라는 이야기");
                System.out.println("대충 원하는 메뉴를 선택하거나 결제해달라는 이야기");
                //while문 빠져나옴
                System.out.println("대충 결제완료 후 돈뺏기고 거래내역 저장되었다는 이야기");

            } else {
                System.out.println("비밀번호가 틀립니다!!\n다시 메인으로 돌아갑니다");
            }

        } catch (FileNotFoundException fn) {
            System.out.println("해당 아이디가 존재하지 않습니다");
        } catch (EOFException eofe) {
            System.out.println("파일이 끝났어오");
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

/*    @Override
    public String toString() {
        return "BitEats [customerList=" + customerList + ", storeList=" + storeList + "]";
    }*/
}
