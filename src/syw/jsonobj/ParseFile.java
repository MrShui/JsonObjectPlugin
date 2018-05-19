package syw.jsonobj;

import syw.jsonobj.bean.FiledBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 把类文件解析成对应的实体类
 * Created by Shui on 2018/5/18.
 */
public class ParseFile {
    public List<FiledBean> parseFiled(String data) {
        List<FiledBean> beans = new ArrayList<>();

        String regex = "private (.*);";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(data);
        while (matcher.find()) {
            String group = matcher.group(0);
            if (group != null && group.length() > 0) {
                group = group.replace(";", "");
                String[] split = group.split(" ");
//                FiledBean filedBean = new FiledBean(split[2], split[1]);
//                beans.add(filedBean);
            }
        }
        return beans;
    }

    public List<String> parseInnerClass(String data) {
        List<String> clazzs = new ArrayList<>();

        String regex = "class (.*)";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(data);
        while (matcher.find()) {
            String group = matcher.group(0);
            if (group != null && group.length() > 0) {
                String[] split = group.split(" ");
                clazzs.add(split[1]);
            }
        }
        return clazzs;
    }
}
