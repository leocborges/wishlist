package http;

import java.util.HashMap;
import java.util.Map;
import org.picocontainer.Disposable;

/**
 * Headers configured in IT steps.
 */
public class RequestHeaders implements Disposable {

    private final Map<String, String> headers;

    public RequestHeaders() {
        this.headers = new HashMap<>();
    }

    public Map<String, String> headers() {
        return this.headers;
    }

    @Override
    public void dispose() {
        this.headers.clear();
    }

}
