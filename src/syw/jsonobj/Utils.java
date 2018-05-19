package syw.jsonobj;

/**
 * Created by Shui on 2018/5/18.
 */
public class Utils {
    //首字母大写
    public static String upcaseFirseChar(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
//        char[] cs = name.toCharArray();
//        cs[0] -= 32;
//        return String.valueOf(cs);

    }

    /**
     * 首字母小写
     *
     * @param name
     * @return
     */
    public static String lowerFirstChar(String name) {
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return name;
    }

}
