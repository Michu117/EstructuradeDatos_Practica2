package unl.dance.base.controller.data_struct.graphs;

public class UndirectedLabelGraph<E> extends DirectLabelGraph<E> {

	public UndirectedLabelGraph(Integer size, Class<E> clazz) {
		super(size, clazz);
	}

    // Sobrescribir insert_label para hacer el grafo no dirigido
    @Override
    public void insert_label(E o, E d, Float weight) {
        if (isLabelsGraph()) {
            Integer vertexO = getVertex(o);
            Integer vertexD = getVertex(d);
            
            // Insertar arista de o hacia d
            insert(vertexO, vertexD, weight);
            
            // Insertar arista de d hacia o (para hacer no dirigido)
            if (!vertexO.equals(vertexD)) { // Evitar duplicar bucles
                insert(vertexD, vertexO, weight);
            }
        }
    }

    @Override
    public void insert_label(E o, E d) {
        insert_label(o, d, Float.NaN);
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
