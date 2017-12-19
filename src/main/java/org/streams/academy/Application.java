package org.streams.academy;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Application {

  public static void main(final String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @CrossOrigin(origins = "*")
  @RestController
  public class SubmitController {

    @SuppressWarnings("unchecked")
    @PostMapping(value = "/submit/{type}", consumes = "text/plain", produces = "application/json")
    public SubmitResponse map(@PathVariable final String type, @RequestBody final String requestBody) {

      try {

        final StreamType streamType = StreamType.valueOf(type.trim().toUpperCase());
        final List<String> result = (List<String>) CompileUtil.executeCode(streamType, requestBody);

        return SubmitResponse.success(Arrays.toString(result.toArray()));

      } catch (final Exception e) {

        final StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));

        return SubmitResponse.fail(sw.toString());

      }

    }

  }

  static class SubmitResponse {

    final String status;
    final String result;

    SubmitResponse(final String status, final String result) {
      this.status = status;
      this.result = result;
    }

    public String getStatus() {
      return status;
    }

    public String getResult() {
      return result;
    }

    static SubmitResponse fail(final String result) {
      return new SubmitResponse("FAIL", result);
    }

    static SubmitResponse success(final String result) {
      return new SubmitResponse("SUCCESS", result);
    }

  }

}