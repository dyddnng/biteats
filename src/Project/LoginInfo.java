package Project;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class LoginInfo implements Serializable {
    private HashMap<String, String> login ;
    private static final long serialVersionUID = 3L;

    LoginInfo(HashMap<String, String> hash) {
        this.login = hash;
    }

    public HashMap<String, String> getLogin() {
        return login;
    }

    public LoginInfo(String id, HashMap<String, String> login) {

        String filename = "C:\\BitEats\\LoginInfo\\" +id + ".txt"; // 객체를 직렬화해서 write

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream out = null;

        try {
            fos = new FileOutputStream(filename); //append
            bos = new BufferedOutputStream(fos);
            // 여기까지 하고 직렬화를 하기 위해 아래와 같이 한다
            out = new ObjectOutputStream(bos);

            //직렬화 대상 (객체)
            LoginInfo loginInfo = new LoginInfo(login);

            //이제 다른곳으로 보낼거임. filename으로
            out.writeObject(loginInfo); // 분해해서 Userdata.txt 에 쓴다
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
}

