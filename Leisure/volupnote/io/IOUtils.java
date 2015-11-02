package volupnote.io;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class IOUtils {

    public static String read(File file) {
        try {
            Path path = file.toPath();
            byte[] data = Files.readAllBytes(path);
            if (data != null) {
                return new String(data, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}