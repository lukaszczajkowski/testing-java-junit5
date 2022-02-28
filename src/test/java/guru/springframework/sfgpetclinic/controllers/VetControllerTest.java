package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.ControllerTests;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.fauxspring.ModelImpl;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.SpecialityMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class VetControllerTest implements ControllerTests {

    VetController vetController;
    VetService vetService;
    SpecialityMapService specialityMapService;
    Model model;

    @BeforeEach
    void setUp() {
        specialityMapService = new SpecialityMapService();
        vetService = new VetMapService(specialityMapService);
        vetController = new VetController(vetService);

        model = new ModelImpl();

        vetService.save(new Vet(1L, "Anna", "Malecka", new HashSet<>(Set.of(new Speciality("dogs")))));
        vetService.save(new Vet(2L, "Lukasz", "Czajkowski", new HashSet<>(Set.of(new Speciality("possums")))));
    }

    @Test
    void shouldListVets() {
        String view = vetController.listVets(model);

        assertThat(view).isEqualTo("vets/index");

        Set<Vet> modelAttribute = (Set<Vet>) ((ModelImpl) model).getMap().get("vets");
        assertThat(modelAttribute.size()).isEqualTo(2);
    }
}