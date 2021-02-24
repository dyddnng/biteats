package Project;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BitEats {
    private HashMap<String, String> users;
    private ArrayList<Customer> customerList;
    private ArrayList<Store> storeList;

    public BitEats() {
        customerList = new ArrayList<Customer>();
        storeList = new ArrayList<Store>();
    }

    public void join() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("비트이츠 회원가입 시스템입니다!!");

        System.out.println("아이디를 입력해주세요");
        String id = scanner.nextLine();
        System.out.println("비밀번호를 입력해주세요");
        String password = scanner.nextLine();

        this.users = new HashMap<>();
        this.users.put(id, password);
        LoginInfo temp = new LoginInfo(id, users);
        temp = null; //다음 회원가입을 위해 객체 null화

    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("비트이츠 로그인 시스템입니다!!");



        System.out.println("아이디를 입력해주세요");
        String id = scanner.nextLine();
        System.out.println("비밀번호를 입력해주세요");
        String password = scanner.nextLine();

        String filename = "C:\\BitEats\\LoginInfo\\" + id + ".txt";

        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ObjectInputStream in = null;

        try {
            fis = new FileInputStream(filename); //파일이름불러오고
            bis = new BufferedInputStream(fis); //불러온걸 버퍼로옮기고
            in = new ObjectInputStream(bis); //버퍼로 옮긴걸 조립한다

//          UserInfo r1 = (UserInfo) in.readObject(); //다운캐스팅을 해줘야함

            LoginInfo loginInfo = (LoginInfo) in.readObject();
            HashMap map = loginInfo.getLogin();

            if (map.get(id).equals(password)) { //아이디와 키값이 같다면
                System.out.println("로그인성공 ㅎ");
            } else {
                System.out.println("비밀번호가 틀립니다!!");
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

    @Override
    public String toString() {
        return "BitEats [customerList=" + customerList + ", storeList=" + storeList + "]";
    }
}
