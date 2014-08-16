package volupnote.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class IOUtils {

    public static String read(final File file) {
        try (final FileInputStream stream = new FileInputStream(file)) {
            final StringBuilder builder = new StringBuilder();
            int content;
            while ((content = stream.read()) != -1) {
                builder.append((char) content);
            }
            stream.close();
            return builder.toString();
        } catch (final IOException ignored) {
            System.err.println("Could not read the file: ");
            return null;
        }
    }
}