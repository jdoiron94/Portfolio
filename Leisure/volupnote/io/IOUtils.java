package volupnote.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class IOUtils {

    public static String read(File file) {
        try (FileInputStream stream = new FileInputStream(file)) {
            StringBuilder builder = new StringBuilder(100000);
            int content;
            while ((content = stream.read()) != -1) {
                builder.append((char) content);
            }
            return builder.toString();
        } catch (IOException ignored) {
            System.err.println("Could not read the file: ");
            return null;
        }
    }
}