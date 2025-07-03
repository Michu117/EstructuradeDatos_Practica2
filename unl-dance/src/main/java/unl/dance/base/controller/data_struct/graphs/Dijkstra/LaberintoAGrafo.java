package unl.dance.base.controller.data_struct.graphs.Dijkstra;

import unl.dance.base.controller.data_struct.graphs.UndirectedLabelGraph;

public class LaberintoAGrafo {

    public static LaberintoResultado transformar(String laberintoTexto) {
        
        String[] filas = laberintoTexto.split("\n");
        int r = filas.length;
        int c = filas[0].split(",").length;

        char[][] matriz = new char[r][c];
        
        // Se carga el laberinto a una matriz bidimensional
        for (int i = 0; i < r; i++) {
            String[] elementos = filas[i].split(",");
            for (int j = 0; j < c; j++) {
                matriz[i][j] = elementos[j].charAt(0);
            }
        }

        // Contar celdas transitables para determinar tamaño del grafo
        int contadorNodos = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (esTransitable(matriz[i][j])) {
                    contadorNodos++;
                }
            }
        }

        UndirectedLabelGraph<String> grafo = new UndirectedLabelGraph<>(contadorNodos, String.class);

        // Mapeo de etiquetas a índice en el grafo
        int indice = 1;
        String[][] etiquetas = new String[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (esTransitable(matriz[i][j])) {
                    String etiqueta = i + "," + j;
                    etiquetas[i][j] = etiqueta;
                    grafo.label_vertex(indice, etiqueta);
                    indice++;
                }
            }
        }

        // Insertar aristas no dirigidas entre vecinos transitables
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (esTransitable(matriz[i][j])) {
                    String etiquetaActual = etiquetas[i][j];

                    // Vecino arriba
                    if (i > 0 && esTransitable(matriz[i - 1][j])) {
                        grafo.insert_label(etiquetaActual, etiquetas[i - 1][j], 1.0f);
                    }
                    // Vecino abajo
                    if (i < r - 1 && esTransitable(matriz[i + 1][j])) {
                        grafo.insert_label(etiquetaActual, etiquetas[i + 1][j], 1.0f);
                    }
                    // Vecino izquierda
                    if (j > 0 && esTransitable(matriz[i][j - 1])) {
                        grafo.insert_label(etiquetaActual, etiquetas[i][j - 1], 1.0f);
                    }
                    // Vecino derecha
                    if (j < c - 1 && esTransitable(matriz[i][j + 1])) {
                        grafo.insert_label(etiquetaActual, etiquetas[i][j + 1], 1.0f);
                    }
                }
            }
        }

        // Buscar etiquetas de 'S' y 'E'
        String inicio = null;
        String fin = null;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matriz[i][j] == 'S') {
                    inicio = i + "," + j;
                }
                if (matriz[i][j] == 'E') {
                    fin = i + "," + j;
                }
            }
        }

        return new LaberintoResultado(grafo, inicio, fin);
    }

    private static boolean esTransitable(char c) {
        return c == '1' || c == 'S' || c == 'E';
    }
}

