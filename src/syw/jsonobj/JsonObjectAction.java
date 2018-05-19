package syw.jsonobj;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import syw.jsonobj.bean.FiledBean;
import syw.jsonobj.bean.ParseBean;
import syw.jsonobj.utils.FileManager;

import java.util.List;

/**
 * Created by Shui on 2018/5/18.
 */
public class JsonObjectAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        String data = e.getData(PlatformDataKeys.FILE_TEXT);
        FileManager.getInstance().init(e);
        ParseFile parseFile = new ParseFile();
        ParseBean parseBean = new ParseBean(parseFile.parseFiled(data), parseFile.parseInnerClass(data));

        new GenerateCode().generateCode(parseBean);

//        PsiFile[] psiFiles = FilenameIndex.getFilesByName(project, "Videos.java", GlobalSearchScope.projectScope(project));
//        if (psiFiles.length > 0) {
//            PsiJavaFile javaFile = (PsiJavaFile) psiFiles[0];
//        }
    }


}
