package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("model")
class OwnerTest {

    @Test
    void dependentAssertions() {
        Owner owner = new Owner(1L, "Joe", "Buck");
        owner.setCity("Key West");
        owner.setTelephone("1231231234");

        assertAll("Properties test",
                () -> assertAll("Person Properties",
                            () -> assertEquals("Joe", owner.getFirstName()),
                            () -> assertEquals("Buck", owner.getLastName())),
                () -> assertAll("Owner Properties",
                        () -> assertEquals("Key West",owner.getCity()),
                        () -> assertEquals("1231231234", owner.getTelephone())
                ));

        assertThat(owner.getCity(), is("Key West"));
    }
}