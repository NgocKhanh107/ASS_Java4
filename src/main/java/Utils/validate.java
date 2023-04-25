package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validate {
    public static boolean chekcKhoangTrang(String text) {
        int temp = text.indexOf(" ");
        if (temp > -1) {
            return true;
        }
        return false;
    }
    public static boolean isEmpty(String text) {
        if (text.trim().isEmpty()) {
            return true;
        }
        return false;
    }
    public static boolean checkso(String text) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

}
