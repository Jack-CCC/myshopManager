package manager.common;

import java.io.File;

public class DeleteFile {

    public static void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

}
