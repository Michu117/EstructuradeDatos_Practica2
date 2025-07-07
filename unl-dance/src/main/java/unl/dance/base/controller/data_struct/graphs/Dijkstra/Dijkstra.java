package unl.dance.base.controller.data_struct.graphs.Dijkstra;

import unl.dance.base.controller.data_struct.graphs.UndirectedLabelGraph;
import unl.dance.base.controller.data_struct.list.LinkedList;

public class Dijkstra {

    public static String[] dijkstra(UndirectedLabelGraph<String> grafo, String inicio) {

        int n = grafo.nro_vertex();

        String[] etiquetas = new String[n];
        float[] distancias = new float[n];
        boolean[] visitados = new boolean[n];
        String[] predecesores = new String[n];

        for (int i = 0; i < n; i++) {
            etiquetas[i] = grafo.getLabel(i + 1);
            distancias[i] = Float.MAX_VALUE;
            visitados[i] = false;
            predecesores[i] = null;
        }

        Float[][] matriz = grafo.getMatrix();

        int indiceInicio = buscarIndice(etiquetas, inicio);
        distancias[indiceInicio] = 0;

        for (int i = 0; i < n; i++) {
            int actual = nodoMinDistancia(distancias, visitados);
            if (actual == -1) break;
            visitados[actual] = true;

            for (int j = 0; j < n; j++) {
                if (matriz[actual][j] != null && !matriz[actual][j].isNaN() && !visitados[j]) {
                    float peso = matriz[actual][j];
                    float nuevaDistancia = distancias[actual] + peso;
                    if (nuevaDistancia < distancias[j]) {
                        distancias[j] = nuevaDistancia;
                        predecesores[j] = etiquetas[actual];
                    }
                }
            }
        }

        return predecesores;
    }

    private static int nodoMinDistancia(float[] distancias, boolean[] visitados) {
        float min = Float.MAX_VALUE;
        int indiceMin = -1;
        for (int i = 0; i < distancias.length; i++) {
            if (!visitados[i] && distancias[i] < min) {
                min = distancias[i];
                indiceMin = i;
            }
        }
        return indiceMin;
    }

    private static int buscarIndice(String[] etiquetas, String etiqueta) {
        for (int i = 0; i < etiquetas.length; i++) {
            if (etiquetas[i].equals(etiqueta)) {
                return i;
            }
        }
        return -1;
    }

    public static LinkedList<String> reconstruirCamino(UndirectedLabelGraph<String> grafo, String inicio, String fin, String[] predecesores) throws Exception {
        
        String[] etiquetas = new String[grafo.nro_vertex()];
        for (int i = 0; i < etiquetas.length; i++) {
            etiquetas[i] = grafo.getLabel(i + 1);
        }

        LinkedList<String> camino = new LinkedList<>();
        String actual = fin;

        while (actual != null && !actual.equals(inicio)) {
            camino.add(actual, 0);
            int indiceActual = buscarIndice(etiquetas, actual);
            actual = predecesores[indiceActual];
        }

        if (actual != null && actual.equals(inicio)) {
            camino.add(inicio, 0);
        } else {
            camino.clear();
        }

        return camino;
    }

    public static char[][] textoAMatriz(String laberintoTexto) {
        String[] filas = laberintoTexto.split("\n");
        char[][] matriz = new char[filas.length][filas[0].split(",").length];

        for (int i = 0; i < filas.length; i++) {
            String[] elementos = filas[i].split(",");
            for (int j = 0; j < elementos.length; j++) {
                matriz[i][j] = elementos[j].charAt(0);
            }
        }
        
        return matriz;
    }
}
