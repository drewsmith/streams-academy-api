package org.streams.academy;

public enum StreamType {

  MAP("org/streams/academy/StreamMap.java", "org.streams.academy.StreamMap", "templates/map.tpl");

  final String sourceFilename;
  final String className;
  final String templateName;

  StreamType(final String sourceFilename, final String className, final String templateName) {
    this.sourceFilename = sourceFilename;
    this.className = className;
    this.templateName = templateName;
  }

  public String getSourceFilename() {
    return sourceFilename;
  }

  public String getClassName() {
    return className;
  }

  public String getTemplateName() {
    return templateName;
  }

}
