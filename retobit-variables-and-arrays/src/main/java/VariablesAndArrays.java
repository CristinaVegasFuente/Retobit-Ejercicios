public class VariablesAndArrays {
    /**
     * Reto 1: Declaración de una variable int.
     * Declara una variable de tipo int llamada 'age' y asígnale el valor 30
     *
     * @return La variable con el int.
     */
    public int declareInt() {

        // Tu código aquí
        int age = 30;
        // Sustituye el 0 por tu variable ¡pero no borres el return!
        return age;
    }

    /**
     * Reto 2: Declaración e inicialización de un booleano.
     * Declara una variable de tipo boolean llamada 'isActive' y asígnale el valor true.
     *
     * @return La variable con su boolean.
     */
    public boolean declareBoolean() {

        // Tu código aquí
        boolean isActive = true;
        // Sustituye el false por tu variable
        return isActive;
    }

    /**
     * Reto 3: Declaración e inicialización de un caracter.
     * Declara una variable de tipo char llamada 'initial' y asígnale el valor 'J'.
     *
     * @return La variable 'initial'.
     */
    public char declareChar() {

        // Tu código aquí
        char initial = 'J';
        // Sustituye el espacio por tu variable
        return initial;
    }

    /**
     * Reto 4: Declaración e inicialización de un array de Strings.
     * Declara un array de Strings llamado 'names' con los valores "Ana", "Luis", "Marta".
     *
     * @return El array 'names'.
     */
    public String[] declareStringArray() {

        // Tu código aquí
        String[] names = {"Ana","Luis","Marta"};
        // Sustituye new String[0] por la variable 'names'
        return names;
    }


    public static void main(String[] args) {
        // Puedes hacer pruebas por aquí 🤖
        VariablesAndArrays vars = new VariablesAndArrays();

        // Guardamos el valor devuelto en variables locales
        int age = vars.declareInt();
        boolean isActive = vars.declareBoolean();
        char initial = vars.declareChar();
        String[] names = vars.declareStringArray();

        // Mostramos los resultados
        System.out.println("Edad: " + age);
        System.out.println("Activo: " + isActive);
        System.out.println("Inicial: " + initial);

        System.out.println("Nombres:");
        for (String nombre : names) {
            System.out.println("- " + nombre);
        }
    }
}
