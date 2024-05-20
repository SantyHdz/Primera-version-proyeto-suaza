/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;
import javax.swing.JOptionPane;

public class VistaJuego {
    private LogicaJuego logicaJuego; // Objeto para manejar la lógica del juego

    public VistaJuego() {
        logicaJuego = new LogicaJuego(); // Inicializa la lógica del juego
    }

    public void iniciarJuego() {
        String nombreJugador = JOptionPane.showInputDialog(null, "Introduce tu nombre:");
        boolean juegoGanado = false;
        int intentos = 0;

        while (intentos < 12 && !juegoGanado) {
            String entradaUsuario = JOptionPane.showInputDialog(null, "Introduce un número de 4 cifras:");
            if (entradaUsuario == null || entradaUsuario.length() != 4 || !entradaUsuario.matches("\\d{4}")) {
                JOptionPane.showMessageDialog(null, "Entrada no válida. Por favor, introduce un número de 4 cifras.");
                continue;
            }

            int[] resultado = logicaJuego.evaluarIntento(entradaUsuario);
            intentos++;

            // Mostramos las dos matrices después de cada propuesta del jugador
            JOptionPane.showMessageDialog(null, "Matriz de Códigos:\n" + obtenerMatrizComoString(logicaJuego.obtenerMatrizCodigo()) + "\nMatriz de Resultados:\n" + obtenerMatrizComoString(logicaJuego.obtenerMatrizResultado()));
            JOptionPane.showMessageDialog(null, "Aciertos: " + resultado[0] + " | Aproximaciones: " + resultado[1]);
            logicaJuego.actualizarMatrices(entradaUsuario, resultado);

            if (resultado[0] == 4) {
                juegoGanado = true;
                JOptionPane.showMessageDialog(null, "¡Felicidades, " + nombreJugador + "! Has adivinado el código en " + intentos + " intentos.");
            } else if (intentos == 12) {
                JOptionPane.showMessageDialog(null, "Has agotado todos los intentos. El código era: " + logicaJuego.obtenerCodigoSecreto());
            }
        }

        logicaJuego.registrarResultadoJuego(nombreJugador, juegoGanado, intentos);
        mostrarEstadisticas();
    }

    // Método para obtener las matrices como cadena para mostrar en el mensaje
    private String obtenerMatrizComoString(int[][] matriz) {
        StringBuilder sb = new StringBuilder();
        for (int[] fila : matriz) {
            for (int valor : fila) {
                sb.append(valor).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // Método para mostrar las estadísticas del juego
    private void mostrarEstadisticas() {
        String estadisticas = logicaJuego.obtenerEstadisticas();
        JOptionPane.showMessageDialog(null, estadisticas);
    }
}
