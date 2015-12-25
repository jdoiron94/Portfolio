import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;

/**
 * @author Jacob Doiron
 * @since 12/24/2015
 */
public class Checksum {

    /**
     * Calculates the local checksum of the client.
     *
     * @return The local checksum.
     */
    public static String getLocalChecksum(String path) {
        try (FileInputStream stream = new FileInputStream(path)) {
            return calculateChecksum(stream);
        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println("Could not get local checksum: [" + e.getLocalizedMessage() + "]");
        }
        return null;
    }

    /**
     * Calculates the remote checksum of the client.
     *
     * @return The remote checksum.
     */
    public static String getRemoteChecksum(String path) {
        try (InputStream stream = new URL(path).openStream()) {
            return calculateChecksum(stream);
        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println("Could not get remote checksum: [" + e.getLocalizedMessage() + "]");
        }
        return null;
    }

    /**
     * Calculates the checksum of the file at the input stream.
     *
     * @param stream The stream to check.
     * @return The checksum for the file.
     */
    public static String calculateChecksum(InputStream stream) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] buffer = new byte[4096];
            int read;
            while ((read = stream.read(buffer)) != -1) {
                sha256.update(buffer, 0, read);
            }
            byte[] hashBytes = sha256.digest();
            StringBuilder builder = new StringBuilder(hashBytes.length);
            for (byte b : hashBytes) {
                builder.append(Integer.toHexString(0xFF & b));
            }
            return builder.toString();
        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println("Could not get checksum: [" + e.getLocalizedMessage() + "]");
        }
        return null;
    }
}
