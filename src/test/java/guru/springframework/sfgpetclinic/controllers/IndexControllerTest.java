package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@Tag("controllers")
class IndexControllerTest {

    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @Test
    @DisplayName("Test Proper View name is returned for index page")
    void index() {
        assertEquals("index", controller.index());
        assertEquals("index", controller.index(), "Wrong View Returned");
        assertEquals("index", controller.index(), () -> "Another Expensive Message " +
                "Make me only if you have to");

        assertThat(controller.index()).isEqualTo("index");
    }

    @Test
    @DisplayName("Test exception")
    void oopsHandler() {

        assertThrows(ValueNotFoundException.class, () -> {
            controller.oopsHandler();
        });
        //assertTrue("notimplemented".equals(controller.oopsHandler()), () -> "This is some expensive " +
        //          "Message to build " +
        //          "for my test");
    }

    @Test
    @Disabled("Demo of timeout")
    void testTimeout() {
        assertTimeout(Duration.ofMillis(100), () -> {
             Thread.sleep(5000);
            System.out.println("I got here");
        });
    }

    @Test
    @Disabled("Demo of timeout")
    void testTimeoutPrempt() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            Thread.sleep(5000);
            System.out.println("I got here 234234234");
        });
    }

    @Test
    void testAssumptionTrue() {
        assumeTrue("GURU".equalsIgnoreCase(System.getenv("GURU_RUNTIME")));
    }

    @Test
    void testAssumptionTrueAssumptionIsTrue() {
        assumeTrue("GURU".equalsIgnoreCase("GURU"));
    }

    @Test
    @EnabledOnOs(OS.MAC)
    void testMeOnMacOs() {
    }

    @Test
    @EnabledOnOs({OS.WINDOWS})
    void testMeOnWinnows() {
    }

    @Test
    @EnabledOnJre(JRE.OTHER)
    void testMeOnJava15() {
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void testMeOnJava8() {
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "USER", matches = "Lukasz Czajkowski")
    void testIfUserLukaszCzajkowski() {
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "USER", matches = "fred")
    void testIfUserFred() {
    }
}