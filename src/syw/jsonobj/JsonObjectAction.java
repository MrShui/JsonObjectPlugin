package syw.jsonobj;

import com.intellij.codeInsight.CodeInsightActionHandler;
import com.intellij.codeInsight.generation.actions.BaseGenerateAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/**
 * Created by Shui on 2018/5/18.
 */
public class JsonObjectAction extends BaseGenerateAction {
    public JsonObjectAction() {
        super(null);
    }

    public JsonObjectAction(CodeInsightActionHandler handler) {
        super(handler);
    }

    @Override
    protected boolean isValidForClass(final PsiClass targetClass) {
        return super.isValidForClass(targetClass);
    }

    @Override
    public boolean isValidForFile(Project project, Editor editor, PsiFile file) {
        return super.isValidForFile(project, editor, file);
    }

    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        PsiFile mFile = PsiUtilBase.getPsiFileInEditor(editor, project);
        PsiClass psiClass = getTargetClass(editor, mFile);

        String str = new GenerateCode().generateCode(psiClass);
        System.out.println(str);
        try {
            copyToClipboard(str);
        } catch (Exception e) {
            return;
        }

        new ShowMessageFrame("复制成功！");
    }

    private void copyToClipboard(String str) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 封装文本内容
        Transferable trans = new StringSelection(str);
        // 把文本内容设置到系统剪贴板
        clipboard.setContents(trans, null);
    }
}
