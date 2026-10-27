import java.util.Scanner;

public class JuegoDeLaVida {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int filasMinimas = 1;
        int columnasMinimas = 1;


        int inicioFila = leerEnteroPositivo(scanner, "¿Cuántas filas quieres?", filasMinimas);

        int inicioColumna = leerEnteroPositivo(scanner, "¿Cuántas columnas quieres?", columnasMinimas);


        boolean[][] tablero = new boolean[inicioFila][inicioColumna];

        int numeroCelulas = leerEnteroNoNegativo(scanner, "¿Cuántas células vivas quieres colocar?");
        int maximoCelulas = inicioFila * inicioColumna;

        while (numeroCelulas > maximoCelulas) {
            System.out.println("No caben tantas células en el tablero.");
            numeroCelulas = leerEnteroNoNegativo(
                    scanner, "¿Cuántas células vivas quieres colocar?"
            );
        }

        int celulasColocadas = 0;

        while (celulasColocadas < numeroCelulas) {
            int fila = leerEnteroPositivo(
                    scanner, "Introduce la fila de la célula " + (celulasColocadas + 1) + ":", 0
            );

            while (fila >= inicioFila) {
                System.out.println("Fila no válida. Introduce un valor entre 0 y " + (inicioFila - 1));

                fila = leerEnteroPositivo(
                        scanner, "Introduce la fila de la célula " + (celulasColocadas + 1) + ":", 0
                );
            }

            int columna = leerEnteroPositivo(
                    scanner, "Introduce la columna de la célula " + (celulasColocadas + 1) + ":", 0
            );
            while (columna >= inicioColumna) {
                System.out.println("Columna no válida. Introduce un valor entre 0 y " + (inicioColumna - 1));

                columna = leerEnteroPositivo(
                        scanner, "Introduce la columna de la célula " + (celulasColocadas + 1) + ":", 0
                );
            }

            if (tablero[fila][columna]) {
                System.out.println("Esa célula ya está viva. Elige otra posición.");
            } else {
                tablero[fila][columna] = true;
                celulasColocadas++;
            }
        }


        int numeroGeneraciones = leerEnteroNoNegativo(scanner, "¿Cuántas generaciones quieres simular?");


        System.out.println("Generación inicial:");
        imprimirTablero(tablero);

        for (int generacion = 0; generacion < numeroGeneraciones; generacion++) {

            tablero = siguienteGeneracion(tablero);

            System.out.println("Generación: " + (generacion + 1));
            imprimirTablero(tablero);

        }



    }


    static int contarVecinos(boolean[][] tablero, int fila, int columna) {
        int total = 0;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {

                int nuevaFila = fila + i;
                int nuevaColumna = columna + j;

                if (i == 0 && j == 0) {
                    continue;
                }
                if (nuevaFila < 0 || nuevaFila >= tablero.length){
                    continue;
                }
                if (nuevaColumna < 0 || nuevaColumna >= tablero[0].length){
                    continue;
                }
                if (tablero[nuevaFila][nuevaColumna]){
                    total++;
                }

            }

        }
        return total;
    }
    static boolean[][] siguienteGeneracion(boolean[][] tablero) {
        boolean[][] nuevo = new boolean[tablero.length][tablero[0].length];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {

                int vecinos = contarVecinos(tablero, i, j);

                if (tablero[i][j]) {
                    nuevo[i][j] = vecinos == 2 || vecinos == 3;
                }
                else {
                    nuevo[i][j] = vecinos == 3;
                }

            }
        }
        return nuevo;


    }
    static void imprimirTablero(boolean[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j]) {
                    System.out.print("0 ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();

    }

    static int leerEnteroPositivo(Scanner scanner, String mensaje, int minimo) {
        System.out.println(mensaje);
        while (true) {
            if (!scanner.hasNextInt()) {
                System.out.println("Debes introducir un número entero.");
                scanner.next();
                continue;
            }
            int numero = scanner.nextInt();

            if (numero < minimo) {
                System.out.println("Debes introducir un número igual o superior a " + minimo);
                continue;
            }
            return numero;

        }

    }

    static int leerEnteroNoNegativo(Scanner scanner, String mensaje) {
        System.out.println(mensaje);

        while (true) {
            if (!scanner.hasNextInt()) {
                System.out.println("Debes introducir un número entero.");
                scanner.next();
                continue;
            }

            int numero = scanner.nextInt();

            if (numero < 0) {
                System.out.println("El número no puede ser negativo.");
                continue;
            }

            return numero;
        }
    }

}
