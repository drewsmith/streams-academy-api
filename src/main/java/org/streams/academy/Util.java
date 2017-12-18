package org.streams.academy;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

class Util {

  static final String PLACEHOLDER = "[PLACEHOLDER]";
  static final String SOURCES_FOLDER = "src/main/resources/sources";
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

    final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    compiler.run(null, null, null, sourceFile.getPath());

    final URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
    final Class<?> cls = Class.forName(type.getClassName(), true, classLoader);

    try {
      return cls.getMethod("execute", (Class<?>[]) null).invoke(null, (Object[]) null);
    } finally {
      Files.delete(sourceFile.toPath());

      final File parentDir = new File(sourceFile.getParent());
      Arrays.stream(parentDir.list(CLASS_FILTER)).map(File::new).forEach(File::delete);
    }
  }

}
