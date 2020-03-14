package com.example.jdk13;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JDK 13 Switch - 2nd Preview")
@SuppressWarnings({"squid:S00100", "squid:S128", "squid:S1192", "ConstantConditions"})
class SwitchPreviewTest {

    @Test
    void shouldSupportSwitchExpression() {
        var day = Day.FRIDAY;

        var description = switch (day) {
            case MONDAY, TUESDAY -> "Back to work";
            case WEDNESDAY -> "Hump Day!";
            case THURSDAY, FRIDAY -> "Plan for the weekend!";
            case SATURDAY, SUNDAY -> "Enjoy!";
        };

        assertThat(description).isEqualTo("Plan for the weekend!");
    }

    @Test
    void shouldRequireDefault_OnlyWhenNonExhaustive() {
        var day = Day.WEDNESDAY;

        var description = switch (day) {
            case MONDAY, TUESDAY -> "Back to work";
            case WEDNESDAY -> "Hump Day!";
            case THURSDAY, FRIDAY -> "Plan for the weekend!";
            case SATURDAY -> "Enjoy!";
            default -> "I guess it is Sunday?";
        };

        assertThat(description).isEqualTo("Hump Day!");
    }

    @Test
    void shouldNotFallThrough_UsingNewSwitchForm() {
        var stringBuilder = new StringBuilder();

        var event = Event.PLAY;

        switch (event) {
            case PLAY -> stringBuilder.append("Play ");
            case STOP -> stringBuilder.append("Stop ");
            default -> stringBuilder.append("Unknown Event! ");
        }

        assertThat(stringBuilder).contains("Play ");
    }

    // NOTE:
    // This changed from JDK 12 preview which used 'break' instead of 'yield'.
    @Test
    void shouldSupportYield_ToReturnValueFromCaseBlack() {
        var event = Event.PAUSE;

        var eventLog = switch (event) {
            case PLAY -> "Play button pressed";
            case STOP -> "Stop button pressed";
            default -> {
                var now = ZonedDateTime.now();
                yield String.format("Unsupported Event %s at %s",
                        event, now.format(DateTimeFormatter.RFC_1123_DATE_TIME));
            }
        };

        assertThat(eventLog).startsWith("Unsupported Event PAUSE at");
    }

    @Test
    void shouldStillFallThrough_UsingLegacySwitchStatementForm_WhenNoBreaks() {
        var stringBuilder = new StringBuilder();

        var event = Event.PLAY;

        switch (event) {
            case PLAY:
                stringBuilder.append("Play ");
            case STOP:
                stringBuilder.append("Stop ");
            default:
                stringBuilder.append("Unknown Event! ");
        }

        assertThat(stringBuilder).contains("Play Stop Unknown Event! ");
    }
}
