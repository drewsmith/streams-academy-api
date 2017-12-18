package org.streams.academy;

import java.io.IOException;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

public class SourceCode extends SimpleJavaFileObject {

  private String contents = null;
  private final String className;

  public SourceCode(final String className, final String contents) throws Exception {
    super(URI.create("string:///" + className.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
    this.contents = contents;
    this.className = className;
  }

  public String getClassName() {
    return className;
  }

  @Override
  public CharSequence getCharContent(final boolean ignoreEncodingErrors) throws IOException {
    return contents;
  }

}