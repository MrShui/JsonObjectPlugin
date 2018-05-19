package syw.jsonobj;

import syw.jsonobj.bean.FiledBean;

import java.util.List;

/**
 * Created by Shui on 2018/5/18.
 */
public class Test {
    public static void main(String[] arg) {
        ParseFile parseFile = new ParseFile();
        String text = "/**\n" +
                " * Created by Shui on 2018/5/18.\n" +
                " */\n" +
                "public class TestBean {\n" +
                "    private String name;\n" +
                "    private int age;\n" +
                "\n" +
                "    public String getName() {\n" +
                "        return name;\n" +
                "    }\n" +
                "\n" +
                "    public void setName(String name) {\n" +
                "        this.name = name;\n" +
                "    }\n" +
                "\n" +
                "    public int getAge() {\n" +
                "        return age;\n" +
                "    }\n" +
                "\n" +
                "    public void setAge(int age) {\n" +
                "        this.age = age;\n" +
                "    }\n" +
                "}";
//        List<FiledBean> parse = parseFile.parseFiled(text);
//        System.out.println(parse);
//        String str = new GenerateCode().generateCode(parse);
//        System.out.println(str);
        List<String> strings = parseFile.parseInnerClass(text);

    }
}
