package org.streams.academy;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.streams.academy.StreamsAcademyHandler.StreamRequest;
import org.streams.academy.StreamsAcademyHandler.StreamResponse;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class StreamsAcademyHandler implements RequestHandler<StreamRequest, StreamResponse> {

  @SuppressWarnings("unchecked")
  @Override
  public StreamResponse handleRequest(final StreamRequest streamRequest, final Context context) {
    System.out.print(streamRequest);

    if (streamRequest == null) {
      return StreamResponse.fail("Request is null.");
    }

    if (streamRequest.getStreamType() == null) {
      return StreamResponse.fail("type is null.");
    }

    final StreamType streamType = StreamType.valueOf(streamRequest.getStreamType().toUpperCase());

    try {
      final List<String> result = (List<String>) Util.executeCode(streamType, streamRequest.getCode());
      return StreamResponse.success(Arrays.toString(result.toArray()));
    } catch (final Exception e) {
      return StreamResponse.fail(e.getMessage());
    }
  }

  // public static void main(final String[] args) {
  // final StreamRequest r = new StreamRequest();
  // r.setCode("map(pokemon -> pokemon.getName())");
  // r.setType("MAP");
  // System.out.println(new StreamsAcademyHandler().handleRequest(r,
  // null).getOutput());
  // }

  // public static void main(final String[] args) throws IOException, Exception {
  // final String templateFilename = "templates/map.tpl";
  // final ClassLoader classloader =
  // Thread.currentThread().getContextClassLoader();
  // final URL fileURL = classloader.getResource(templateFilename);
  // final Path templatePath = Paths.get(fileURL.toURI());
  //
  // final byte[] template = Files.readAllBytes(templatePath);
  //
  // final String templateString = new String(template).replace("[PLACEHOLDER]",
  // "map(pokemon -> pokemon.getName()).forEach(System.out::println);");
  // // "artists.stream().filter(a ->
  // // \"adele\".equalsIgnoreCase(a.getName())).forEach(System.out::println);");
  //
  // final File root = new File("sources");
  // final File sourceFile = new File(root, "org/streams/academy/StreamMap.java");
  // sourceFile.getParentFile().mkdirs();
  // Files.write(sourceFile.toPath(),
  // templateString.getBytes(StandardCharsets.UTF_8));
  //
  // final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
  // compiler.run(null, null, null, sourceFile.getPath());
  //
  // final URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] {
  // root.toURI().toURL() });
  // final Class<?> cls = Class.forName("org.streams.academy.StreamMap", true,
  // classLoader);
  // final Object result = cls.getMethod("main", String[].class).invoke(null,
  // (Object) new String[] {});
  // }

  public static class StreamRequest implements Serializable {

    private static final long serialVersionUID = 7870421219503646921L;

    String streamType;
    String code;

    public StreamRequest() {
    }

    public StreamRequest(final String streamType, final String code) {
      this.streamType = streamType;
      this.code = code;
    }

    public String getStreamType() {
      return streamType;
    }

    public void setStreamType(final String streamType) {
      this.streamType = streamType;
    }

    public String getCode() {
      return code;
    }

    public void setCode(final String code) {
      this.code = code;
    }

    @Override
    public String toString() {
      final StringBuilder builder = new StringBuilder();
      builder.append("StreamRequest [streamType=");
      builder.append(streamType);
      builder.append(", code=");
      builder.append(code);
      builder.append("]");
      return builder.toString();
    }

  }

  public static class StreamResponse implements Serializable {

    private static final long serialVersionUID = -5247282507175944492L;

    String status;
    String output;

    public StreamResponse() {
    }

    public StreamResponse(final String status, final String output) {
      this.status = status;
      this.output = output;
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(final String status) {
      this.status = status;
    }

    public String getOutput() {
      return output;
    }

    public void setOutput(final String output) {
      this.output = output;
    }

    public static StreamResponse success(final String output) {
      return new StreamResponse("SUCCESS", Optional.of(output).orElse(""));
    }

    public static StreamResponse fail(final String output) {
      return new StreamResponse("FAIL", Optional.of(output).orElse(""));
    }

  }

}
