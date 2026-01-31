import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NumbersAndOperationsTest {
    private NumbersAndOperations exercises = new NumbersAndOperations();

    @Test
    @Order(1)
    @DisplayName("Reto 1: Suma de dos números")
    void testSum() {
        assertEquals(63, exercises.sum(), "Reto 1: La suma de numberA y numberB no es correcta.");
    }

    @Test
    @Order(2)
    @DisplayName("Reto 2: División de dos números (float)")
    void testDivision() {
        assertEquals(10f / 3f, exercises.division(), 0.0001f, "Reto 2: La división no es correcta. Recuerda que el resultado debe ser float.");
    }

    @Test
    @Order(3)
    @DisplayName("Reto 3: Módulo de una división")
    void testModulo() {
        assertEquals(1, exercises.modulo(), "Reto 3: El resultado del módulo no es 1. Revisa los valores asignados a numberA y numberB.");
    }

    @Test
    @Order(4)
    @DisplayName("Reto 4: Generar número aleatorio entre 0 y 100")
    void testRandomizer() {
        int randomNumber = exercises.randomizer();
        assertTrue(randomNumber >= 0 && randomNumber <= 100,
                "Reto 4: El número aleatorio no está entre 0 y 100 (inclusive). Valor obtenido: " + randomNumber);

        boolean hasVaried = false;
        int firstResult = exercises.randomizer();
        for (int i = 0; i < 10; i++) {
            if (exercises.randomizer() != firstResult) {
                hasVaried = true;
                break;
            }
        }
        assertTrue(hasVaried, "Reto 4: Parece que el número aleatorio siempre devuelve el mismo valor. Asegúrate de usar una función realmente aleatoria.");
    }

    @Test
    @Order(5)
    @DisplayName("Reto 5: Calcular potencia")
    void testPower() {
        assertEquals(19683, exercises.power(), "Reto 5: El cálculo de la potencia no es correcto.");
    }

    @Test
    @Order(6)
    @DisplayName("Reto 6: Redondear un número decimal")
    void testRoundNumber() {
        assertEquals(10L, exercises.roundNumber(), "Reto 6: El redondeo no es correcto. Asegúrate de asignar un valor que al redondear dé 10.");
    }

    @Test
    @Order(7)
    @DisplayName("Reto 7: Operaciones combinadas")
    void testCombinedOperations() {
        assertEquals(156, exercises.combinedOperations(), "Reto 7: La combinación de operaciones no es correcta.");
    }
}
