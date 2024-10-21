import java.util.HashMap;
import java.util.Scanner;

public class Videojuego5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean jugarDeNuevo;

        do {
            int n = 10; 
            HashMap<String, Soldado> ejercito1 = new HashMap<>();
            HashMap<String, Soldado> ejercito2 = new HashMap<>();
            boolean[][] ocupadas = new boolean[10][10];

            generarSoldados(ejercito1, ocupadas, "Ejercito1", n);
            generarSoldados(ejercito2, ocupadas, "Ejercito2", n);

            System.out.println("\nTablero:");
            mostrarTablero(ejercito1, ejercito2);

            mostrarSoldadoConMayorVida(ejercito1, "Ejercito1");
            mostrarSoldadoConMayorVida(ejercito2, "Ejercito2");

            mostrarPromedioPuntosVida(ejercito1, "Ejercito1");
            mostrarPromedioPuntosVida(ejercito2, "Ejercito2");

            mostrarRanking(ejercito1, "Ejercito1");
            mostrarRanking(ejercito2, "Ejercito2");

            determinarGanador(ejercito1, ejercito2);

            // Preguntar si el usuario quiere jugar de nuevo
            System.out.print("¿Desea volver a jugar? (s/n): ");
            String respuesta = scanner.nextLine();
            jugarDeNuevo = respuesta.equalsIgnoreCase("s");

        } while (jugarDeNuevo);

        System.out.println("Gracias por jugar!");
        scanner.close();
    }

    public static void generarSoldados(HashMap<String, Soldado> ejercito, boolean[][] ocupadas, String nombreEjercito, int n) {
        for (int i = 0; i < n; i++) {
            String nombre = nombreEjercito + "Soldado" + (i + 1);
            int puntosVida = (int) (Math.random() * 5) + 1; 
            int fila, columna;
            do {
                fila = (int) (Math.random() * 10); 
                columna = (int) (Math.random() * 10); 
            } while (ocupadas[fila][columna]); 

            ocupadas[fila][columna] = true; 
            ejercito.put(nombre, new Soldado(nombre, puntosVida, fila, columna)); 
        }
    }

    public static void mostrarTablero(HashMap<String, Soldado> ejercito1, HashMap<String, Soldado> ejercito2) {
        String[][] espacios = new String[10][10];
        for (int i = 0; i < espacios.length; i++) {
            for (int j = 0; j < espacios[i].length; j++) {
                espacios[i][j] = " "; 
            }
        }
        
        for (Soldado soldado : ejercito1.values()) {
            int fila = soldado.getFila(); 
            int col = soldado.getColumna(); 
            if (fila >= 0 && fila < 10 && col >= 0 && col < 10) {
                espacios[fila][col] = "1"; 
            }
        }
        
        for (Soldado soldado : ejercito2.values()) {
            int fila = soldado.getFila(); 
            int col = soldado.getColumna(); 
            if (fila >= 0 && fila < 10 && col >= 0 && col < 10) {
                espacios[fila][col] = "2"; 
            }
        }

        
        System.out.print("\t");
        for (char col = 'A'; col <= 'J'; col++) {
            System.out.print(col + "\t");
        }
        System.out.println();

        
        for (int i = 0; i < espacios.length; i++) {
            System.out.print((i + 1) + "\t"); 
            for (int j = 0; j < espacios[i].length; j++) {
                System.out.print(espacios[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void mostrarSoldadoConMayorVida(HashMap<String, Soldado> ejercito, String nombreEjercito) {
        Soldado mayorVida = null;
        for (Soldado soldado : ejercito.values()) {
            if (mayorVida == null || soldado.getPuntosVida() > mayorVida.getPuntosVida()) {
                mayorVida = soldado;
            }
        }
        System.out.println(nombreEjercito + " - Soldado con mayor vida: " + mayorVida);
    }

    public static void mostrarPromedioPuntosVida(HashMap<String, Soldado> ejercito, String nombreEjercito) {
        int suma = 0;
        for (Soldado soldado : ejercito.values()) {
            suma += soldado.getPuntosVida();
        }
        double promedio = (double) suma / ejercito.size();
        System.out.printf("%s - Promedio de puntos de vida: %.2f\n", nombreEjercito, promedio);
    }

    public static void mostrarRanking(HashMap<String, Soldado> ejercito, String nombreEjercito) {
        System.out.println(nombreEjercito + " - Ranking de poder:");
        
        Soldado[] soldados = new Soldado[ejercito.size()];
        int index = 0;
        for (Soldado soldado : ejercito.values()) {
            soldados[index++] = soldado;
        }

        
        for (int i = 0; i < soldados.length - 1; i++) {
            for (int j = 0; j < soldados.length - i - 1; j++) {
                if (soldados[j].getPuntosVida() < soldados[j + 1].getPuntosVida()) {
                    
                    Soldado temp = soldados[j];
                    soldados[j] = soldados[j + 1];
                    soldados[j + 1] = temp;
                }
            }
        }

        for (Soldado soldado : soldados) {
            System.out.println(soldado);
        }
    }

    public static void determinarGanador(HashMap<String, Soldado> ejercito1, HashMap<String, Soldado> ejercito2) {
        int totalVidaEjercito1 = 0;
        int totalVidaEjercito2 = 0;

        for (Soldado soldado : ejercito1.values()) {
            totalVidaEjercito1 += soldado.getPuntosVida();
        }
        for (Soldado soldado : ejercito2.values()) {
            totalVidaEjercito2 += soldado.getPuntosVida();
        }

        System.out.println("\nTotal de puntos de vida - Ejercito1: " + totalVidaEjercito1 + ", Ejercito2: " + totalVidaEjercito2);
        String ganador = totalVidaEjercito1 > totalVidaEjercito2 ? "Ejercito1" : "Ejercito2";
        System.out.println("El ganador de la batalla es: " + ganador);
    }
}
