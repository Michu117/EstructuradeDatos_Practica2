package unl.dance.base.controller.data_struct.graphs;

import java.lang.reflect.Array;
import java.util.HashMap;

import unl.dance.base.controller.data_struct.list.LinkedList;

public class DirectLabelGraph<E> extends DirectedGraph {

    protected E labels[];
    protected HashMap<E, Integer> dictVertex; // a cada vertice le voy a asignar un objeto
    private Class clazz;

    public DirectLabelGraph(Integer nro_vertex, Class clazz) {
        super(nro_vertex);
        this.clazz = clazz;
        this.labels = (E[]) Array.newInstance(this.clazz, nro_vertex + 1);
        dictVertex = new HashMap<>(nro_vertex);
    }

    public Adjacency exist_edge_label(E o, E d){
        if(isLabelsGraph()){
            return exist_edge(getVertex(o), getVertex(d));
        }return null;
    }

    public void insert_label(E o, E d, Float weight){
        if(isLabelsGraph()){
            insert(getVertex(o), getVertex(d), weight); // retorno vertice asociado
        }
    }

    public void insert_label(E o, E d){
        if(isLabelsGraph()){
            insert(getVertex(o), getVertex(d), Float.NaN); // retorno vertice asociado
        }
    }

    public LinkedList<Adjacency> adjacencies_label(E o){
        if(isLabelsGraph()){
            return adjacencies(getVertex(o));
        } return new LinkedList<>();
    }

    public void label_vertex(Integer vertex, E data){
        labels[vertex] = data;
        dictVertex.put(data, vertex);
    }

    public Boolean isLabelsGraph() {
        Boolean band = true;
        for (int i = 1; i <= nro_vertex(); i++) {
            if (labels[i] == null) {
                band = false;
                break;
            }
        }
        return band;
    }

    public Integer getVertex(E label){
        return dictVertex.get(label);
    }

    public E getLabel(Integer i){
        //return dictVertex.get(i);
        return labels[i]; //obtener la etiqueta del vertice

    }

    @Override 
    public String toString(){
        StringBuilder sb= new StringBuilder();
        for(int i=1; i<= nro_vertex(); i++){
            sb.append("Vertex =").append(getLabel(i)).append("\n");
            LinkedList<Adjacency> list = adjacencies(i);
            if(!list.isEmpty()){
                Adjacency [] matrix = list.toArray();
                for(Adjacency ad: matrix){
                    sb.append("\tAdjacency ").append("\n").append("Vertex = ").append(String.valueOf(getLabel(ad.getDestiny())));
                    if(ad.getWeigth().isNaN()){
                        sb.append("weight = "+ad.getWeigth().toString()).append("\n");
                    }
                }
            }
        }
        return sb.toString();
    }



    public static void main(String[] args) {
        DirectLabelGraph<String> gd = new DirectLabelGraph<>(5, String.class);
        gd.label_vertex(1, "A");
        gd.label_vertex(2, "B");
        gd.label_vertex(3, "C");
        gd.label_vertex(4, "D");
        gd.label_vertex(5, "E");
        gd.insert_label("A", "B", 1.0f);
        gd.insert_label("A", "C", 2.0f);
        System.out.println(gd.toString());
    }


    
}
