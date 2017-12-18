package org.streams.academy;

public enum StreamType {

  MAP("org/streams/academy/StreamMap.java", "org.streams.academy.StreamMap", "templates/map.tpl");

  final String sourceFilename;
  final String className;
  final byte[] template;

  StreamType(final String sourceFilename, final String className, final String templateName) {
    this.sourceFilename = sourceFilename;
    this.className = className;

    try {
      this.template = Util.getTemplate(templateName);
    } catch (final Exception e) {
      throw new IllegalStateException(e);
    }

  }

  public String getSourceFilename() {
    return sourceFilename;
  }

  public String getClassName() {
    return className;
  }

  public byte[] getTemplate() {
    return template;
  }

}
