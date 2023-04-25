package Utils;





import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;


public class MaHoa {
    public static String toSHA1(String str) {
        String salt = "asjrlkmcoewj@tjle;oxqskjhdjksjf1jurVn";// Làm cho mật khẩu phức tap
        String result = null;

        str = str + salt;
        try {
            byte[] dataBytes = str.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(dataBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
//    public static void main(String[] args) {
//        System.out.println(toSHA1("123456"));
//    }

}
