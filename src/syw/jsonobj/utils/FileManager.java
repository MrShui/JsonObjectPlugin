package syw.jsonobj.utils;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.lang.reflect.Field;

/**
 * Created by Shui on 2018/5/18.
 */
public class FileManager {
    private static FileManager ourInstance = new FileManager();
    private Project project;
    private String packageName;

    public static FileManager getInstance() {
        return ourInstance;
    }

    private FileManager() {
    }

    public void init(AnActionEvent event) {
        project = event.getData(PlatformDataKeys.PROJECT);
        String filePath = getFilePath(event);
        packageName = getPackageName(filePath);
    }

    private String getPackageName(String filePath) {
        return null;
    }

    private String getFilePath(AnActionEvent e) {
        String path = "";
        FileEditor fileEditor = e.getData(PlatformDataKeys.FILE_EDITOR);
        try {
            Class<?> superclass = fileEditor.getClass().getSuperclass();
            Field myFile = superclass.getDeclaredField("myFile");
            myFile.setAccessible(true);
            VirtualFile virtualFile = (VirtualFile) myFile.get(fileEditor);
            path = virtualFile.getPath();
        } catch (NoSuchFieldException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
        return path;
    }
}
