
import java.io.Serializable;

public class NewConnectionRequest implements Serializable {

    private final String host;
    private final int port;

    public NewConnectionRequest(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
