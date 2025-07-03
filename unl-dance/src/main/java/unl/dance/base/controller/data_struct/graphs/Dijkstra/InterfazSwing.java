package unl.dance.base.controller.data_struct.graphs.Dijkstra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import unl.dance.base.controller.data_struct.list.LinkedList;

public class InterfazSwing extends JFrame {

    private char[][] matriz;
    private LinkedList<String> camino;
    private int cellSize = 20;
    private boolean mostrarCoordenadas = false;
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
        add(panel, BorderLayout.CENTER);

        // Panel de botones abajo
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));

        JButton zoomInButton = new JButton("Zoom +");
        zoomInButton.addActionListener((ActionEvent e) -> cambiarZoom(1.2));

        JButton zoomOutButton = new JButton("Zoom -");
        zoomOutButton.addActionListener((ActionEvent e) -> cambiarZoom(0.8));

        JCheckBox coordCheckBox = new JCheckBox("Mostrar coordenadas");
        coordCheckBox.addActionListener(e -> {
            mostrarCoordenadas = coordCheckBox.isSelected();
            panel.repaint();
        });

        controlPanel.add(zoomInButton);
        controlPanel.add(zoomOutButton);
        controlPanel.add(coordCheckBox);

        add(controlPanel, BorderLayout.SOUTH);

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
                    case '0' -> g.setColor(Color.GRAY);       // Pared
                    case '1' -> g.setColor(Color.WHITE);       // Camino libre
                    case 'S' -> g.setColor(Color.GREEN);       // Inicio
                    case 'E' -> g.setColor(Color.RED);         // Fin
                    case '*' -> g.setColor(Color.YELLOW);      // Camino de solución
                    default -> g.setColor(Color.LIGHT_GRAY);
                }

                g.fillRect(x, y, cellSize, cellSize);
                g.setColor(Color.GRAY);
                g.drawRect(x, y, cellSize, cellSize);

                if (mostrarCoordenadas && cellSize >= 15) {
                    g.setColor(Color.BLACK);
                    g.drawString(i + "," + j, x + 2, y + 12);
                }
            }
        }
    }

    private void cambiarZoom(double factor) {
        int nuevoTamaño = (int) (cellSize * factor);
        if (nuevoTamaño >= 5 && nuevoTamaño <= 50) {
            cellSize = nuevoTamaño;
            panel.setPreferredSize(new Dimension(matriz[0].length * cellSize, matriz.length * cellSize));
            pack();
            panel.repaint();
        }
    }

    public static void mostrar(char[][] matriz, LinkedList<String> camino) {
        SwingUtilities.invokeLater(() -> new InterfazSwing(matriz, camino));
    }
}
