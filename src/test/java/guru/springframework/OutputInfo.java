package guru.springframework;

import org.junit.jupiter.api.*;

@Tag("repeated")
public interface OutputInfo {

    @BeforeEach
    default void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + ": " + repetitionInfo.getCurrentRepetition());
    }
}
