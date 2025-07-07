package unl.dance.base.controller.data_struct.graphs.Dijkstra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import unl.dance.base.controller.data_struct.list.LinkedList;

public class InterfazSwing extends JFrame {

    private char[][] matriz;
    private LinkedList<String> camino;
    private int cellSize = 20;
    private JPanel panel;

    public InterfazSwing(char[][] matriz, LinkedList<String> camino) {
        this.matriz = matriz;
        this.camino = camino;

        marcarCamino();

        setTitle("Laberinto - Dijkstra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarLaberinto(g);
            }
        };

        panel.setPreferredSize(new Dimension(matriz[0].length * cellSize, matriz.length * cellSize));

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void marcarCamino() {
        if (camino != null) {
            String[] pasos = camino.toArray();
            for (String paso : pasos) {
                String[] partes = paso.split(",");
                int fila = Integer.parseInt(partes[0]);
                int columna = Integer.parseInt(partes[1]);
                if (matriz[fila][columna] != 'S' && matriz[fila][columna] != 'E') {
                    matriz[fila][columna] = '*';
                }
            }
        }
    }

    private void dibujarLaberinto(Graphics g) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                int x = j * cellSize;
                int y = i * cellSize;

                switch (matriz[i][j]) {
                    case '0' ->
                        g.setColor(Color.DARK_GRAY);   // Pared
                    case '1' ->
                        g.setColor(Color.WHITE);       // Camino libre
                    case 'S' ->
                        g.setColor(Color.GREEN);       // Inicio
                    case 'E' ->
                        g.setColor(Color.RED);         // Fin
                    case '*' ->
                        g.setColor(Color.CYAN);      // Camino Correcto
                    default ->
                        g.setColor(Color.LIGHT_GRAY);
                }

                g.fillRect(x, y, cellSize, cellSize);
                g.setColor(Color.GRAY);
                g.drawRect(x, y, cellSize, cellSize);

                
            }
        }
    }

    public static void mostrar(char[][] matriz, LinkedList<String> camino) {
        SwingUtilities.invokeLater(() -> new InterfazSwing(matriz, camino));
    }
}
