package org.streams.academy;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class StreamsAcademyHandlerTest {

  private static Object input;

  @BeforeClass
  public static void createInput() throws IOException {
    // TODO: set up your sample input object here.
    input = null;
  }

  private Context createContext() {
    final TestContext ctx = new TestContext();

    // TODO: customize your context here if needed.
    ctx.setFunctionName("Your Function Name");

    return ctx;
  }

  @Test
  public void testStreamsAcademyHandler() {
    final StreamsAcademyHandler handler = new StreamsAcademyHandler();
    final Context ctx = createContext();

    // String output = handler.handleRequest(input, ctx);

    // TODO: validate output here if needed.
    // Assert.assertEquals("Hello from Lambda!", output);
  }
}
