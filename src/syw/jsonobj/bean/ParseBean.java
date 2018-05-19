package syw.jsonobj.bean;

import java.util.List;

/**
 * Created by Shui on 2018/5/18.
 */
public class ParseBean {
    private List<FiledBean> filedBeans;
    private List<String> className;

    public ParseBean(List<FiledBean> filedBeans) {
        this.filedBeans = filedBeans;
    }

    public ParseBean(List<FiledBean> filedBeans, List<String> className) {
        this.filedBeans = filedBeans;
        this.className = className;
    }

    public List<FiledBean> getFiledBeans() {
        return filedBeans;
    }

    public void setFiledBeans(List<FiledBean> filedBeans) {
        this.filedBeans = filedBeans;
    }

    public List<String> getClassName() {
        return className;
    }

    public void setClassName(List<String> className) {
        this.className = className;
    }
}
