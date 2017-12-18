package org.streams.academy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CompileUtilTest {

  @SuppressWarnings("unchecked")
  @Test
  public void shouldGenereateMapCode() throws Exception {
    final List<String> output = (List<String>) CompileUtil.executeCode(StreamType.MAP, "map(pk -> pk.getName())");
    assertThat(output, equalTo(Arrays.asList("Pikachu", "Charmander", "Snorlax")));
  }

}
