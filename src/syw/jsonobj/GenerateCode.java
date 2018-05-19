package syw.jsonobj;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiTypeElement;

import java.util.Arrays;
import java.util.List;

import static syw.jsonobj.Utils.lowerFirstChar;
import static syw.jsonobj.Utils.upcaseFirseChar;

/**
 * 生成jsonObject转对象代码
 * Created by Shui on 2018/5/18.
 */
public class GenerateCode {
    //一些基本的数据类型，加上String
    private static final List<String> BASIC_TYPE = Arrays.asList("int", "String", "boolean", "double", "long");

    public String generateCode(PsiClass psiClass) {
        PsiField[] fields = psiClass.getFields();
        String fileClassName = psiClass.getName();

        StringBuffer sb = new StringBuffer();
        appendHead(sb, fileClassName);
        appendParams(sb, fields, fileClassName, psiClass, false);
        appendFoot(sb);
        return sb.toString();

    }

    private void appendParams(StringBuffer sb, PsiField[] fields, String className, PsiClass psiClass, boolean isInnerClass) {
        for (PsiField field : fields) {
            String name = field.getName();
            PsiTypeElement typeElement = field.getTypeElement();
            if (typeElement == null) {
                continue;
            }
            String type = field.getTypeElement().getType().getPresentableText();

            if (type.length() == 0) {
                continue;
            }

            if (type.contains("List")) {
                //集合
                handList(sb, className, psiClass, name, type);
            } else if (BASIC_TYPE.contains(type)) {
                //基本类型
                handBasic(sb, className, isInnerClass, name, type);
            } else {
                //对象
//                JSONObject playlink2Obj = videosObj.optJSONObject("playlink2");
//                NewPageChannelInfos.Videos.Playlink2 playlink2 = new NewPageChannelInfos.Videos.Playlink2();
//                playlink2.setId(playlink2Obj.optInt("id"));
                sb.append("\n")
                        .append("    ")
                        .append("    ")
                        .append("JSONObject ")
                        .append(lowerFirstChar(type))
                        .append("Obj = ")
                        .append(lowerFirstChar(className))
                        .append("Obj.optJSONObject(\"")
                        .append(lowerFirstChar(className))
                        .append("\");\n");

                sb.append("    ")
                        .append("    ")
                        .append(upcaseFirseChar(className))
                        .append(".")
                        .append(type)
                        .append(" ")
                        .append(lowerFirstChar(type))
                        .append(" = new ")
                        .append(upcaseFirseChar(className))
                        .append(".")
                        .append(type)
                        .append("();\n");

                PsiClass innerClass = findInnerClass(psiClass, type);
                appendParams(sb, innerClass.getFields(), innerClass.getName(), innerClass, true);

                //                videos.setPlaylink2(playlink2);
                sb.append("    ")
                        .append("    ")
                        .append(lowerFirstChar(className))
                        .append(".")
                        .append("set")
                        .append(upcaseFirseChar(type))
                        .append("(")
                        .append(lowerFirstChar(type))
                        .append(");\n");
            }
        }
    }

    private void handBasic(StringBuffer sb, String className, boolean isInnerClass, String name, String type) {
        if (isInnerClass) {
            sb.append("    ");
        }
        sb.append("    ")
                .append(lowerFirstChar(className))
                .append(".set")
                .append(Utils.upcaseFirseChar(name))
                .append("(")
                .append(lowerFirstChar(className))
                .append("Obj.opt")
//                        .append("(jsonObject.opt")
                .append(Utils.upcaseFirseChar(type))
                .append("(\"")
                .append(name)
                .append("\"));")
                .append("\n");
    }

    private void handList(StringBuffer sb, String className, PsiClass psiClass, String name, String type) {
        //JSONArray videosArray = jsonObject.optJSONArray("videos");
        //List<NewPageChannelInfos.Videos> videosList = new ArrayList<NewPageChannelInfos.Videos>();
        sb.append("    ")
                .append("JSONArray ")
                .append(name)
                .append("Array = ")
                .append(lowerFirstChar(className))
                .append("Obj.optJSONArray(\"")
                .append(name)
                .append("\");")
                .append("\n");

        //List<Videos>
        PsiClass innerClass = findInnerClass(psiClass, type);
        if (innerClass == null) {
            return;
        }

        sb.append("    List<")
                .append(className)
                .append(".")
                .append(innerClass.getName())
                .append("> ")
                .append(lowerFirstChar(innerClass.getName()))
                .append("List")
                .append(" = new ArrayList<")
                .append(className)
                .append(".")
                .append(innerClass.getName())
                .append(">();\n");

        appendArrayParams(className, innerClass, sb);
    }

    private PsiClass findInnerClass(PsiClass psiClass, String type) {
        String innerClassName;
        if (type.contains("List")) {
            innerClassName = type.substring(5, type.length() - 1);
        } else {
            innerClassName = type;
        }
        return psiClass.findInnerClassByName(innerClassName, false);
    }

    private void appendArrayParams(String outsideClassName, PsiClass innerClass, StringBuffer sb) {
        //for (int i = 0; i < videosArray.length(); i++) {
        //    JSONObject jsonObj = videosArray.getJSONObject(i);
        //    NewPageChannelInfos.Videos videos = new NewPageChannelInfos.Videos();
        //}
        String className = innerClass.getName();
        sb.append("    for (int i = 0; i < ")
                .append(lowerFirstChar(className))
                .append("Array.length(); i++) {\n")
                .append("    ")
                .append("    JSONObject ")
                .append(lowerFirstChar(className))
                .append("Obj = ")
                .append(lowerFirstChar(className))
                .append("Array.getJSONObject(i);\n");

        sb.append("    ")
                .append("    ")
                .append(outsideClassName)
                .append(".")
                .append(className)
                .append(" ")
                .append(lowerFirstChar(className))
                .append(" = new ")
                .append(outsideClassName)
                .append(".")
                .append(className)
                .append("();\n");

        appendParams(sb, innerClass.getFields(), className, innerClass, true);

        //videosList.add(videos);
        sb.append("    ")
                .append("    ")
                .append(lowerFirstChar(className))
                .append("List")
                .append(".add(")
                .append(lowerFirstChar(className))
                .append(");\n");

        sb.append("    }\n");

        //bean.setVideos(videosList);
        sb.append("    ")
                .append(lowerFirstChar(outsideClassName))
                .append(".set")
                .append(upcaseFirseChar(className))
                .append("(")
                .append(lowerFirstChar(className))
                .append("List")
                .append(");\n\n");
    }

    private void appendFoot(StringBuffer sb) {
        sb.append("} catch (JSONException e) {\n")
                .append("    e.printStackTrace();\n")
                .append("}\n")
                .append("return bean;\n");
    }

    private void appendHead(StringBuffer sb, String className) {
        //"Bean bean = new Bean();\n"
        sb.append(className)
                .append(" ")
                .append(lowerFirstChar(className))
                .append(" = new ")
                .append(className)
                .append("();\n\n");

        sb.append("try {\n")
                .append("    JSONObject ")
                .append(lowerFirstChar(className))
                .append("Obj = new JSONObject(json);\n");
    }
}
