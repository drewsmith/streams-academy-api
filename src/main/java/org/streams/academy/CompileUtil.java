package org.streams.academy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.tools.ToolProvider;

class CompileUtil {

  static final String PLACEHOLDER = "[PLACEHOLDER]";
  static final String SOURCES_FOLDER = "/tmp";
  static final String MAIN_CLASS = "main";

  static final FilenameFilter CLASS_FILTER = (dir, name) -> name != null && name.endsWith(".class");

  static byte[] getTemplate(final String templateFilename) throws Exception {
    final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    final URL fileURL = classloader.getResource(templateFilename);
    final Path templatePath = Paths.get(fileURL.toURI());
    return Files.readAllBytes(templatePath);
  }

  static Object executeCode(final StreamType type, final String code) throws Exception {

    final String templateString = new String(type.getTemplate()).replace(PLACEHOLDER, code);

    final File root = new File(SOURCES_FOLDER);
    final File sourceFile = new File(root, type.getSourceFilename());

    sourceFile.getParentFile().mkdirs();
    Files.write(sourceFile.toPath(), templateString.getBytes(StandardCharsets.UTF_8));

    final Class<?> cls;
    final ByteArrayOutputStream errors = new ByteArrayOutputStream();

    try {
      ToolProvider.getSystemJavaCompiler().run(System.in, System.out, errors, sourceFile.getPath());
      final URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
      cls = Class.forName(type.getClassName(), true, classLoader);
    } catch (final Exception e) {
      throw new IllegalStateException(errors.toString());
    }

    try {
      return cls.getMethod("execute", (Class<?>[]) null).invoke(null, (Object[]) null);
    } finally {
      Arrays.stream(sourceFile.getParentFile().listFiles()).forEach(File::delete);
    }
  }

}
