import java.util.Scanner;

public class JuegoDeLaVida {

    public static void main(String[] args) {


        boolean[][] tablero = new boolean[5][5];

        tablero[2][3] = true;
        tablero[1][2] = true;
        tablero[1][3] = true;

        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Cuántas generaciones quieres simular?");
        int numeroGeneraciones = 0;

        while (true) {
            if (!scanner.hasNextInt()) {
                System.out.println("Debes introducir un número entero.");
                scanner.next();
                continue;
            }

            numeroGeneraciones = scanner.nextInt();

            if (numeroGeneraciones < 0) {
                System.out.println("El número de generaciones no puede ser negativo.");
                continue;
            }

            break;
        }


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

}
