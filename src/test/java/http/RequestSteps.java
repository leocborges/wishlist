package http;

import io.cucumber.java8.En;
import java.net.http.HttpResponse;
import net.javacrumbs.jsonunit.JsonMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

/**
 * Request step definitions.
 */
public final class RequestSteps implements En {

    /**
     * Ctor.
     * @param request Request instance.
     * @param headers Headers instance.
     */
    public RequestSteps(final RequestInstance request, final RequestHeaders headers) {
        And(
            "an authenticated user {string}",
            (String user) -> headers.headers().put("x-user", user)
        );
        Given(
            "I call POST {string} with request body",
            (String path, String body) -> {
                request.set(() -> request.post(path, body));
            }
        );
        Given(
            "response JSON body is",
            (String path, String body) -> {
                final HttpResponse<String> response = request.reference();
                MatcherAssert.assertThat(response.body(), JsonMatchers.jsonEquals(body));
            }
        );
        When(
            "I call PUT {string} it returns status {int}",
            (String path, Integer status) -> {
                HttpResponse<String> response = request.put(path);
                MatcherAssert.assertThat(response.statusCode(), Matchers.is(status));
            }
        );
        Then(
            "I call PUT {string} it returns status {int} with JSON body",
            (String path, Integer status, String body) -> {
                final HttpResponse<String> response = request.put(path);
                MatcherAssert.assertThat(response.statusCode(), Matchers.is(status));
                MatcherAssert.assertThat(response.body(), JsonMatchers.jsonEquals(body));
            }
        );
        Then(
            "I call GET {string} it returns status {int} with JSON body",
            (String path, Integer status, String body) -> {
                final HttpResponse<String> response = request.get(path);
                MatcherAssert.assertThat(response.statusCode(), Matchers.is(status));
                MatcherAssert.assertThat(response.body(), JsonMatchers.jsonEquals(body));
            }
        );
        Then(
            "I call DELETE {string} and it returns status {int}",
            (String path, Integer status) -> {
                final HttpResponse<String> response = request.delete(path);
                MatcherAssert.assertThat(response.statusCode(), Matchers.is(status));
            }
        );
        Then(
            "I call DELETE {string} and it returns status {int} with JSON body",
            (String path, Integer status, String body) -> {
                final HttpResponse<String> response = request.delete(path);
                MatcherAssert.assertThat(response.statusCode(), Matchers.is(status));
                MatcherAssert.assertThat(response.body(), JsonMatchers.jsonEquals(body));
            }
        );
    }

}
