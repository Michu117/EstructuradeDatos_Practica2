package unl.dance.base.controller.data_struct.graphs.Dijkstra;

import unl.dance.base.controller.data_struct.graphs.UndirectedLabelGraph;

public class LaberintoResultado {

    public UndirectedLabelGraph<String> grafo;
    public String inicio;
    public String fin;

    public LaberintoResultado(UndirectedLabelGraph<String> grafo, String inicio, String fin) {
        this.grafo = grafo;
        this.inicio = inicio;
        this.fin = fin;
    }
}
