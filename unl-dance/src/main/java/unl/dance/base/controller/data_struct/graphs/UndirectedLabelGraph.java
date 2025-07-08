package unl.dance.base.controller.data_struct.graphs;

import unl.dance.base.controller.data_struct.list.LinkedList;

public class UndirectedLabelGraph<E> extends DirectLabelGraph<E> {

	private Float[][] graph;

    public UndirectedLabelGraph(Integer size, Class<E> clazz) {
		super(size, clazz);
	}
    
    @Override
    public void insert_label(E o, E d, Float weight) {
        if (isLabelsGraph()) {
            Integer vertexO = getVertex(o);
            Integer vertexD = getVertex(d);
            
            insert(vertexO, vertexD, weight);
            
            if (!vertexO.equals(vertexD)) { 
                insert(vertexD, vertexO, weight);
            }
        }
    }

    public Float[][] getMatrix() {
        if (this.graph == null) {
            int n = nro_vertex();
            this.graph = new Float[n][n];
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    this.graph[i][j] = null;
                }
            }
            
            for (int i = 1; i <= n; i++) {
                LinkedList<Adjacency> adjacencies = adjacencies(i);
                if (!adjacencies.isEmpty()) {
                    Adjacency[] adjArray = adjacencies.toArray();
                    for (Adjacency adj : adjArray) {
                        int sourceIndex = i - 1; 
                        int destIndex = adj.getDestiny() - 1; 
                        this.graph[sourceIndex][destIndex] = adj.getWeigth();
                    }
                }
            }
        }
        return this.graph;
    }

    public static void main(String[] args) {
        UndirectedLabelGraph<String> gd = new UndirectedLabelGraph<>(5, String.class);
        gd.label_vertex(1, "A");
        gd.label_vertex(2, "B");
        gd.label_vertex(3, "C");
        gd.label_vertex(4, "D");
        gd.label_vertex(5, "E");
        gd.insert_label("A", "B", 1.0f);
        gd.insert_label("A", "C", 2.0f);
        
        System.out.println("=== GRAFO NO DIRIGIDO ===");
        System.out.println(gd.toString());
        
        System.out.println("=== VERIFICACIÓN DE BIDIRECCIONALIDAD ===");
        System.out.println("¿Existe A -> B? " + (gd.exist_edge_label("A", "B") != null));
        System.out.println("¿Existe B -> A? " + (gd.exist_edge_label("B", "A") != null));
        System.out.println("¿Existe A -> C? " + (gd.exist_edge_label("A", "C") != null));
        System.out.println("¿Existe C -> A? " + (gd.exist_edge_label("C", "A") != null));
    }

}
