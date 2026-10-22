public class JuegoDeLaVida {

    public static void main(String[] args) {

        boolean[][] tablero = new boolean[5][5];
        tablero[2][3] = true;


        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j]) {
                    System.out.print("0 ");
                }
                else {
                    System.out.print(". ");
                }
            }
            System.out.println();

        }
        System.out.println(contarVecinos(tablero, 2, 3));

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

}
