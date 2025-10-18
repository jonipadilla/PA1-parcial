package usecase;


import exception.PilotoIncorrectoException;
import input.CrearPilotoInput;
import model.Piloto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import output.CrearPilotoRepository;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class CrearPilotoUseCaseTest {

    CrearPilotoInput crearPilotoInput;
    UUID id = UUID.randomUUID();

    @Mock
    CrearPilotoRepository crearPilotoRepository;

    @BeforeEach
    void setUp() {crearPilotoInput = new CrearPilotoUseCase(crearPilotoRepository);}

    @Test
    void crearPiloto_pilotoNoExiste_crearPiloto() {
        Piloto piloto = Piloto.factory("jonatan padilla", "33376040", LocalDate.of(1988,5,2));
        when(crearPilotoRepository.buscarPiloto("jonatan padilla")).thenReturn(false);
        when(crearPilotoRepository.crearPiloto(any(Piloto.class))).thenReturn(id);

        Assertions.assertEquals(id, crearPilotoInput.crearPiloto(piloto));
    }

    @Test
    void crearPiloto_pilotoExiste() {
        Piloto piloto = Piloto.factory("jonatan padilla", "33376040", LocalDate.of(1988,5,2));
        when(crearPilotoRepository.buscarPiloto("jonatan padilla")).thenReturn(true);

        Assertions.assertThrows(PilotoIncorrectoException.class, () -> crearPilotoInput.crearPiloto(piloto));
    }

}
