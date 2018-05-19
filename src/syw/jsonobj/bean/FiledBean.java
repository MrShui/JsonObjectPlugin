package syw.jsonobj.bean;

/**
 * Created by Shui on 2018/5/18.
 */
public class FiledBean {
    private String name;
    private String type;
    private String className;

    public FiledBean(String name, String type,String className) {
        this.name = name;
        this.type = type;
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FiledBean{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
