package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
    }

    @Test
    public void whenPairWithCommentAndEmptyString() {
        String path = "./data/pair_with_comment_and_empty_strings.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Pavel Yurchenko"));
        assertThat(config.value("age"), is("5"));
        assertThat(config.value("id"), is("123kjh235"));
    }

    @Test
    public void whenPairWithCommentAndDoubleEqual() {
        String path = "./data/pair_with_comment_and_double_equal.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Pavel=Yurchenko"));
        assertThat(config.value("age"), is("5="));
        assertThat(config.value("id"), is("123kjh235"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenOnlyValue() {
        String path = "./data/pair_with_template_error_equal_first.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenOnlyKey() {
        String path = "./data/pair_with_template_error_equal_first.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenNoEqual() {
        String path = "./data/pair_with_template_error_equal_first.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenOnlyEqual() {
        String path = "./data/pair_with_template_error_only_equal.properties";
        Config config = new Config(path);
        config.load();
    }

}