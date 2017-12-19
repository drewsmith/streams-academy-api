package org.streams.academy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

@SuppressWarnings("unchecked")
public class CompileUtilTest {

  @Test
  public void shouldGenereateMapCode() throws Exception {
    final String code = "map(pk -> pk.getName())";
    final List<String> output = (List<String>) CompileUtil.executeCode(StreamType.MAP, code);
    assertThat(output, equalTo(Arrays.asList("Pikachu", "Charmander", "Snorlax")));
  }

  @Test
  public void shouldFilterCode() throws Exception {
    final String code = "map(pk -> pk.getName()).filter(n -> n.equals(\"Pikachu\"))";
    final List<String> output = (List<String>) CompileUtil.executeCode(StreamType.MAP, code);
    assertThat(output, equalTo(Arrays.asList("Pikachu")));
  }

}
