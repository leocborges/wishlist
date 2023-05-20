package http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * Request instance used in ITs.
 */
public final class RequestInstance {

    private static final Duration TIMEOUT = Duration.ofSeconds(0);

    private final String api;
    private final RequestHeaders headers;
    private final AtomicReference<Supplier<HttpResponse<String>>> request;
    private final HttpClient client;

    /**
     * Ctor.
     * @param headers Headers instance.
     */
    public RequestInstance(final RequestHeaders headers) {
        this.api = "http://localhost:8080/api/v1";
        this.headers = headers;
        this.request = new AtomicReference<>();
        this.client = HttpClient.newBuilder().build();
    }

    /**
     * Calls a GET operation.
     * @param path Path.
     * @return Response.
     */
    public HttpResponse<String> get(final String path) {
        final HttpRequest.Builder builder = this.defaultPart(path);
        final HttpRequest request = builder.GET().build();
        try {
            return this.client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Calls a POST operation.
     * @param path Path.
     * @param body Body.
     * @return Response.
     */
    public HttpResponse<String> post(final String path, final String body) {
        final HttpRequest.Builder builder = this.defaultPart(path);
        final HttpRequest request = builder.POST(
            HttpRequest.BodyPublishers.ofString(body)
        ).build();
        try {
            return this.client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Calls a PUT operation.
     * @param path Path.
     * @return Response.
     */
    public HttpResponse<String> put(final String path) {
        final HttpRequest.Builder builder = this.defaultPart(path);
        final HttpRequest request = builder.PUT(HttpRequest.BodyPublishers.noBody()).build();
        try {
            return this.client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Calls a DELETE operation.
     * @param path Path.
     * @return Response.
     */
    public HttpResponse<String> delete(final String path) {
        final HttpRequest.Builder builder = this.defaultPart(path);
        final HttpRequest request = builder.DELETE().build();
        try {
            return this.client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Get a reference of the current response.
     * @return Response.
     */
    public HttpResponse<String> reference() {
        return this.request.get().get();
    }

    /**
     * Sets a response supplier.
     * @param res Response.
     */
    public void set(final Supplier<HttpResponse<String>> res) {
        this.request.set(res);
    }

    private HttpRequest.Builder defaultPart(final String path) {
        final HttpRequest.Builder builder = HttpRequest.newBuilder()
            .uri(URI.create(this.api.concat(path)))
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        this.headers.headers().forEach(builder::header);
        return builder;
    }

}
