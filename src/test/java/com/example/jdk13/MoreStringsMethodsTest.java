package com.example.jdk13;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.jdk13.TestUtils.readFileOnClasspath;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("removal")
@DisplayName("New (and deprecated) JDK 13 String methods")
class MoreStringsMethodsTest {

    @SuppressWarnings("removal")
    @Test
    void shouldStripIndent() {
        var html = readFileOnClasspath("testIndent.html");
        var strippedHtml = readFileOnClasspath("testStrippedIndent.html");
        assertThat(html.stripIndent()).isEqualTo(strippedHtml);
    }

    @Test
    void shouldReturnFormattedStrings() {
        var template = "The %s %s %s jumped over %d %s %s %s";

        var formatted = template.formatted("quick", "red", "fox", 42, "lazy", "brown", "dogs");

        assertThat(formatted)
                .isEqualTo("The quick red fox jumped over 42 lazy brown dogs");
    }

    @SuppressWarnings("TextBlockMigration")
    @Test
    void shouldTranslateEscapesInStrings() {
        var text = "Hello\\nWorld!\\nThis is the first line.\\n\\nCan you believe it?";

        assertThat(text.translateEscapes())
                .isEqualTo("Hello\nWorld!\nThis is the first line.\n\nCan you believe it?");
    }

    @Test
    void shouldTranslateEscapesFromTextFiles() {
        var text = readFileOnClasspath("testTextFileWithEscapes.txt");

        var translatedText = readFileOnClasspath("testTextFileEscaped.txt");

        assertThat(text.translateEscapes()).isEqualTo(translatedText);
    }
}
