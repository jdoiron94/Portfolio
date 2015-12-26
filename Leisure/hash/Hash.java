import java.io.File;
import java.net.URL;
import java.util.jar.JarInputStream;

/**
 * @author Jacob Doiron
 * @since 12/25/2015
 */
public class Test {

    /**
     * Gets the hash of the local jar file.
     *
     * @param path The path to the local jar.
     */
    public int getLocalHash(String path) {
        try (JarInputStream stream = new JarInputStream(new File(path).toURI().toURL().openStream())) {
            return stream.getManifest().hashCode();
        } catch (Exception e) {
            System.err.println("Could not get local hash: [" + e.getLocalizedMessage() + "]");
        }
        return -1;
    }

    /**
     * Gets the hash of the remote jar file.
     *
     * @param path The path to the remote jar.
     */
    public int getRemoteHash(String path) {
        try (JarInputStream stream = new JarInputStream(new URL(path).openStream())) {
            return stream.getManifest().hashCode();
        } catch (Exception e) {
            System.err.println("Could not get remote hash: [" + e.getLocalizedMessage() + "]");
        }
        return -1;
    }
}
