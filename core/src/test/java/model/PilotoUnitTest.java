package model;

import exception.PilotoIncorrectoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PilotoUnitTest {


    @Test
    public  void atributosCorrectos(){
        Piloto piloto = Piloto.factory("jonatan padilla","33376040",LocalDate.of(1988,5,2));
        Assertions.assertNotNull(piloto);
    }

    @Test
    public  void atributosIncorrectos(){
        Exception exceptionNull = Assertions.assertThrows(PilotoIncorrectoException.class,() -> Piloto.factory(null, "33376040", LocalDate.of(1988, 5, 2)));
        Exception exceptionVacio = Assertions.assertThrows(PilotoIncorrectoException.class,() -> Piloto.factory(null, "33376040", LocalDate.of(1988, 5, 2)));
        Assertions.assertEquals("El nombre es obligatorio", exceptionNull.getMessage());
        Assertions.assertEquals("El nombre es obligatorio", exceptionVacio.getMessage());
    }

    @Test
    public void instanciar_documentoIncorrecto(){
        Exception exceptionNulo = Assertions.assertThrows(PilotoIncorrectoException.class, () -> Piloto.factory("jonatan padilla", null, LocalDate.of(1988, 5, 2)));
        Exception exceptionVacio = Assertions.assertThrows(PilotoIncorrectoException.class,() -> Piloto.factory("jonatan padilla", "", LocalDate.of(1988, 5, 2)));
        Assertions.assertEquals("El documento es obligatorio", exceptionNulo.getMessage());
        Assertions.assertEquals("El documento es obligatorio", exceptionVacio.getMessage());
    }

    @Test
    public void instanciar_fechaNacimientoIncorrecto(){
        Exception exceptionNulo = Assertions.assertThrows(PilotoIncorrectoException.class, () -> Piloto.factory("jonatan padilla", "33376040", null));
        Exception exceptionMayor = Assertions.assertThrows(PilotoIncorrectoException.class, () -> Piloto.factory("jonatan padilla", "33376040", LocalDate.now().plusDays(1)));
        Assertions.assertEquals("La fecha de nacimiento es inválida", exceptionNulo.getMessage());
        Assertions.assertEquals("La fecha de nacimiento es inválida", exceptionMayor.getMessage());
    }

    @Test
    public void instanciar_fechaMenor18Anios(){
        Exception exception = Assertions.assertThrows(PilotoIncorrectoException.class, () -> Piloto.factory("jonatan padilla", "33376040", LocalDate.of(2022, 5, 2)));
        Assertions.assertEquals("El piloto no puede ser menor de 18 años", exception.getMessage());
    }




}
