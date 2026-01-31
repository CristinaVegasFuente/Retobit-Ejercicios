import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VariablesAndArraysTest {

    private VariablesAndArrays challenges = new VariablesAndArrays();

    @Test
    @DisplayName("Reto 1, declarar un int")
    @Order(1)
    void testDeclareInt() {
        assertEquals(30, challenges.declareInt(), "Reto 1: La edad declarada no es correcta.");
    }

    @Test
    @Order(2)
    @DisplayName("Reto 2, declarar un boolean")
    void testDeclareBoolean() {
        assertTrue(challenges.declareBoolean(), "Reto 2: La variable booleana no es correcta.");
    }

    @Test
    @DisplayName("Reto 3, declarar un char")
    @Order(3)
    void testDeclareChar() {
        assertEquals('J', challenges.declareChar(), "Reto 3: El caracter inicial no es correcto.");
    }

    @Test
    @DisplayName("Reto 4, declarar un array de Strings")
    @Order(4)
    void testDeclareStringArray() {
        String[] expectedArray = {"Ana", "Luis", "Marta"};
        assertArrayEquals(expectedArray, challenges.declareStringArray(), "Reto 4: El array de Strings no se declara correctamente.");
    }
}
