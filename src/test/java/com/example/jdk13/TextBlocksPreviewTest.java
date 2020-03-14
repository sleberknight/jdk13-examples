package com.example.jdk13;

import org.junit.jupiter.api.Test;

import static java.util.stream.Collectors.toUnmodifiableList;
import static org.assertj.core.api.Assertions.assertThat;

class TextBlocksPreviewTest {

    @Test
    void shouldStillBeAString() {
        var text = """
                Hello text blocks!""";

        assertThat(text.getClass()).isEqualTo(String.class);
    }

    @Test
    void shouldBeEquivalentToLines() {
        var html = """
                <html>
                    <body>
                        <p>Hello, world</p>
                    </body>
                </html>
                """;

        assertThat(html.lines()).containsExactly(
                "<html>",
                "    <body>",
                "        <p>Hello, world</p>",
                "    </body>",
                "</html>"
        );
    }

    @Test
    void shouldMakeJsonMorePleasant() {
        var json = """
                {
                    "userName": "bobsmith42",
                    "firstName": "Bob",
                    "lastName": "Smith",
                    "age": 42
                }
                """;

        assertThat(json).contains("\"firstName\": \"Bob\"");
    }

    @Test
    void shouldAlsoMakeSQLQueriesNicer() {
        var sql = """
                SELECT user_name, first_name, last_name, age
                FROM users
                WHERE last_name = 'Smith' AND age < 42
                """;

        var lines = sql.lines().collect(toUnmodifiableList());
        assertThat(String.join(" ", lines))
                .isEqualTo("SELECT user_name, first_name, last_name, age FROM users WHERE last_name = 'Smith' AND age < 42");
    }
}
