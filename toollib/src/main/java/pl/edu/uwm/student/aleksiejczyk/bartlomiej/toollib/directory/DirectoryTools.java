package pl.edu.uwm.student.aleksiejczyk.bartlomiej.toollib.directory;

import java.io.File;

public class DirectoryTools {
    public static String getParentDirectory(){
        File currentDirectory = new File("").getAbsoluteFile();
        File parentDirectory = currentDirectory.getParentFile();
        return parentDirectory.toString();
    }
}
