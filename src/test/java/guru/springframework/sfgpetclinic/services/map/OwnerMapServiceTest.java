package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Owner Map Service Test - ")
class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    PetTypeService petTypeService;
    PetService petService;

    @BeforeEach
    void setUp() {
        petTypeService = new PetTypeMapService();
        petService = new PetMapService();
        ownerMapService = new OwnerMapService(petTypeService, petService);

        System.out.println("First Before Each");
    }

    @Test
    @DisplayName("Verify Zero Owners")
    void ownersAreZero() {
        int ownerCount = ownerMapService.findAll().size();

        assertThat(ownerCount).isZero();
    }

    @Nested
    @DisplayName("Pet Type - ")
    class TestCreatePetTypes {

        @BeforeEach
        void setUp() {
            PetType petType = new PetType(1L, "Dog");
            PetType petType1 = new PetType(2L, "Cat");
            petTypeService.save(petType);
            petTypeService.save(petType1);

            System.out.println("Nested Before Each");
        }

        @Test
        void testPetCount() {
            int petTypeCount = petTypeService.findAll().size();

            assertThat(petTypeCount).isNotZero().isEqualTo(2);
        }

        @Nested
        @DisplayName("Save Owners Test - ")
        class SaveOwnersTest {

            @BeforeEach
            void setUp() {
                ownerMapService.save(new Owner(1L, "Before", "Each"));

                System.out.println("Saved Owners Before Each");
            }


            @Nested
            @DisplayName("Find Owner Test - ")
            class FindOwnersTest {

                @Test
                void findOwner() {
                    Owner foundOwner = ownerMapService.findById(1L);

                    assertThat(foundOwner).isNotNull();
                }
            }

            @Test
            @DisplayName("Find Owner")
            void saveOwner() {
                Owner owner = new Owner(2L, "Joe", "Buck");

                Owner savedOwner = ownerMapService.save(owner);

                assertThat(savedOwner).isNotNull();
            }
        }
    }

    @Test
    @DisplayName("Verify Still Zero Owners")
    void ownersAreStillZero() {
        int ownerCount = ownerMapService.findAll().size();

        assertThat(ownerCount).isZero();
    }
}