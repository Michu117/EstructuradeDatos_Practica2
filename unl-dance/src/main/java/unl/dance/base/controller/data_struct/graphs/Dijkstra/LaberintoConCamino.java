package unl.dance.base.controller.data_struct.graphs.Dijkstra;

import unl.dance.base.controller.data_struct.list.LinkedList;
import unl.dance.base.controller.practica4.Prim2;

public class LaberintoConCamino {

    public static void imprimirLaberintoConCamino(char[][] matriz, LinkedList<String> camino) {

        String[] pasos = camino.toArray();

        for (String paso : pasos) {
            String[] partes = paso.split(",");
            int fila = Integer.parseInt(partes[0]);
            int columna = Integer.parseInt(partes[1]);

            if (matriz[fila][columna] != 'S' && matriz[fila][columna] != 'E') {
                matriz[fila][columna] = '*';
            }
        }

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        Prim2 generador = new Prim2();
        String laberintoTexto = generador.generar(100, 100);

        LaberintoResultado resultado = LaberintoAGrafo.transformar(laberintoTexto);

        String[] predecesores = Dijkstra.dijkstra(resultado.grafo, resultado.inicio);

        LinkedList<String> camino = Dijkstra.reconstruirCamino(resultado.grafo, resultado.inicio, resultado.fin, predecesores);

        System.out.println("Camino encontrado:");
        System.out.println(camino.print());

        char[][] matriz = Dijkstra.textoAMatriz(laberintoTexto);

        LaberintoConCamino.imprimirLaberintoConCamino(matriz, camino);
        
        InterfazSwing.mostrar(matriz, camino);

    }
}
