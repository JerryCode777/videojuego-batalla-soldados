class Soldado {
    private String nombre;
    private int puntosVida;
    private int fila;
    private int columna;

    public Soldado(String nombre, int puntosVida, int fila, int columna) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.fila = fila;
        this.columna = columna;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public String toString() {
        return nombre + " (Vida: " + puntosVida + ", Fila: " + fila + ", Columna: " + columna + ")";
    }
}
