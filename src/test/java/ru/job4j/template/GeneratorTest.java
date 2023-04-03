package ru.job4j.template;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class GeneratorTest {
    @Test
    public void whenProduceString() {
        String template = """
                        This ${stringDef1} string is just for ${target1}.
                        And that ${stringDef2} string is just for ${target2} too.
                        """;
        Map<String, String> map = new HashMap<>();
        map.put("stringDef1", "first");
        map.put("target1", "test");
        map.put("stringDef2", "second");
        map.put("target2", "another test");
        String expected = """
                        This first string is just for test.
                        And that second string is just for another test too.
                        """;
        assertThat(new SimpleGenerator().produce(template, map))
                .isEqualTo(expected);
    }

    @Test
    public void whenMapContainsRedundantKeysThenIllegalArgumentException() {
        String template = """
                        This ${stringDef1} string is just for ${target1}.
                        And that ${stringDef2} string is just for ${target2} too.
                        """;
        Map<String, String> map = new HashMap<>();
        map.put("stringDef1", "first");
        map.put("target1", "test");
        map.put("stringDef2", "second");
        map.put("target2", "another test");
        map.put("redundant key", "redundant value");
        assertThatThrownBy(() -> new SimpleGenerator().produce(template, map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Map contains redundant keys");
    }

    @Test
    public void whenTemplateWithRedundantParametersThenIllegalArgumentException() {
        String template = """
                        This ${stringDef1} string is just for ${target1}.
                        And that ${stringDef2} string is just for ${target2} too.
                        """;
        Map<String, String> map = new HashMap<>();
        map.put("stringDef1", "first");
        assertThatThrownBy(() -> new SimpleGenerator().produce(template, map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Template contains redundant keys");
    }

}