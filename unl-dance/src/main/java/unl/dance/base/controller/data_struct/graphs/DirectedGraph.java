package unl.dance.base.controller.data_struct.graphs;

import unl.dance.base.controller.data_struct.list.LinkedList;

public class DirectedGraph extends Graph {
    private Integer nro_vertex;
    private Integer nro_edge;
    private LinkedList<Adjacency> list_adjacencies[];

    public DirectedGraph(Integer nro_vertex) {
        this.nro_vertex = nro_vertex;
        this.nro_edge = 0;
        list_adjacencies = new LinkedList[nro_vertex + 1];
        for (int i = 1; i <= nro_vertex; i++) {
            list_adjacencies[i] = new LinkedList<>(); // por cada vertice se crea una lista de adyacencias
        }
    }

   
    public void setNro_edge(Integer nro_edge){
        this.nro_edge = nro_edge;
    }


    public LinkedList<Adjacency> [] getList_adjacencies() {
        return this.list_adjacencies;
    }

    public void setList_adjacencies(LinkedList<Adjacency> [] list_adjacencies) {
        this.list_adjacencies = list_adjacencies;
    }

    @Override
    public Integer nro_vertex() {
        return this.nro_vertex;
    }

    @Override
    public Integer nro_edge() {
        return this.nro_edge;
    }

    @Override
    public Adjacency exist_edge(Integer o, Integer d) {
        Adjacency band = null;
        if (o.intValue() <= nro_vertex.intValue() && d.intValue() <= nro_vertex.intValue()) { // que los vertives estan
                                                                                              // bien
            LinkedList<Adjacency> list = list_adjacencies[o];
            if (!list.isEmpty()) {
                Adjacency[] matrix = list.toArray(); //// recorre las adyacencias
                for (Adjacency adj : matrix) {
                    if (adj.getDestiny().intValue() == d.intValue()) { // si el detino es igual ql destino que estoy
                                                                       // buscando
                        band = adj;
                        break;
                    }
                }
            }
        }
        return band;
    }

    @Override
    public Float wight_edge(Integer o, Integer d) {
        Adjacency adj = exist_edge(o, d);
        if (adj != null) {
            return adj.getWeigth();
        }
        return Float.NaN;
    }

    @Override
    public void insert(Integer o, Integer d) {
        insert(o, d, Float.NaN);
    }

    @Override
    public void insert(Integer o, Integer d, Float weight) {
        if (o.intValue() <= nro_vertex.intValue() && d.intValue() <= nro_vertex.intValue()){
            if(exist_edge(o, d) == null){
                nro_edge++;
                Adjacency aux = new Adjacency();
                aux.setWeigth(weight);
                aux.setDestiny(d);
                list_adjacencies[o].add(aux); //al vertice origen le voy a fijar la nueva adyacencia que estoy creando
            }
        }else{
            throw new ArrayIndexOutOfBoundsException("Vertex origin o destiny index out ");
        }
    }

    @Override
    public LinkedList<Adjacency> adjacencies(Integer o) {
        return list_adjacencies[o];
    }

    public static void main(String[] args) {
        // Crear un grafo dirigido con 3 vértices
        DirectedGraph graph = new DirectedGraph(3);

        // Insertar algunas aristas
        graph.insert(1, 2, 1.5f);
        graph.insert(1, 3, 2.0f);
        graph.insert(2, 3, 3.5f);

        // Mostrar las adyacencias de cada vértice
        for (int i = 1; i <= graph.nro_vertex(); i++) {
            System.out.print("Adyacencias de " + i + ": ");
            LinkedList<Adjacency> adjList = graph.adjacencies(i);
            for (Adjacency adj : adjList.toArray()) {
                System.out.print("-> " + adj.getDestiny() + " (peso: " + adj.getWeigth() + ") ");
            }
            System.out.println();
        }

        // Probar exist_edge y wight_edge
        System.out.println("¿Existe arista de 1 a 2? " + (graph.exist_edge(1, 2) != null));
        System.out.println("Peso de la arista de 2 a 3: " + graph.wight_edge(2, 3));
    }

}
    
    