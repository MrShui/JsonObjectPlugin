package syw.jsonobj;

import syw.jsonobj.bean.FiledBean;
import syw.jsonobj.bean.ParseBean;

import java.util.List;

/**
 * 生成jsonObject转对象代码
 * Created by Shui on 2018/5/18.
 */
public class GenerateCode {
    public String generateCode(ParseBean parseBean) {
        List<FiledBean> filedBeans = parseBean.getFiledBeans();

        StringBuffer sb = new StringBuffer("Bean bean = new Bean();\n");
        appendHead(sb);
        appendParams(sb, filedBeans);
        appendFoot(sb);
        return sb.toString();
    }

    private void appendParams(StringBuffer sb, List<FiledBean> filedBeans) {
        for (FiledBean filedBean : filedBeans) {
            String name = filedBean.getName();
            String type = filedBean.getType();

            if (type == null || type.length() == 0) {
                continue;
            }

            if (type.contains("List")) {
                //集合

            } else {
                //基本类型
                sb.append("    bean.set")
                        .append(Utils.captureName(name))
                        .append("(jsonObject.opt")
                        .append(Utils.captureName(type))
                        .append("(\"")
                        .append(name)
                        .append("\"));")
                        .append("\n");
            }
        }
    }

    private void appendFoot(StringBuffer sb) {
        sb.append("} catch (JSONException e) {\n")
                .append("    e.printStackTrace();\n")
                .append("}\n")
                .append("return bean;\n");
    }

    private void appendHead(StringBuffer sb) {
        sb.append("try {\n")
                .append("    JSONObject jsonObject = new JSONObject(json);\n");
    }
}
