package unl.dance.base.controller.dao.dao_models;

import java.util.HashMap;

import unl.dance.base.controller.Utiles;
import unl.dance.base.controller.dao.AdapterDao;
import unl.dance.base.controller.data_struct.list.LinkedList;
import unl.dance.base.models.Cancion;

public class DaoCancion extends AdapterDao<Cancion> {

    private Cancion obj;

    public DaoCancion() {
        super(Cancion.class);
        // TODO Auto-generated constructor stub
    }

    public Cancion getObj() {
        if (obj == null) {
            this.obj = new Cancion();
        }
        return this.obj;
    }

    public void setObj(Cancion obj) {
        this.obj = obj;
    }

    public Boolean save() {
        try {
            obj.setId(listAll().getLength() + 1);
            this.persist(obj);
            return true;
        } catch (Exception e) {
            //TODO
            return false;
            // TODO: handle exception
        }
    }

    public Boolean update(Integer pos) {
        try {
            this.update(obj, pos);
            return true;
        } catch (Exception e) {
            //TODO
            return false;
            // TODO: handle exception
        }
    }

    public void quickSort(Cancion arr[], int low, int high, Integer type) {
        if (low < high) {
            int pi = partition(arr, low, high, type);
            quickSort(arr, low, pi - 1, type);
            quickSort(arr, pi + 1, high, type);
        }
    }

    private int partition(Cancion[] arr, int low, int high, Integer type) {
        Cancion pivot = arr[high];
        int i = (low - 1);
        if (type == Utiles.ASCENDENTE) {
            for (int j = low; j < high; j++) {
                if (arr[j].getNombre().toLowerCase().compareTo(pivot.getNombre().toLowerCase()) < 0) {
                    i++;
                    Cancion temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        } else {
            for (int j = low; j < high; j++) {
                if (arr[j].getNombre().toLowerCase().compareTo(pivot.getNombre().toLowerCase()) > 0) {
                    i++;
                    Cancion temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        Cancion temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;

    }

    public LinkedList<HashMap<String, String>> all() throws Exception {
        LinkedList<HashMap<String, String>> lista = new LinkedList<>();
        if (!this.listAll().isEmpty()) {
            Cancion[] arreglo = this.listAll().toArray();
            for (int i = 0; i < arreglo.length; i++) {
                lista.add(toDict(arreglo[i], i));
            }
        }
        return lista;
    }

    private HashMap<String, String> toDict(Cancion arreglo, Integer i) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        DaoAlbum db = new DaoAlbum();
        DaoGenero dc = new DaoGenero();
        HashMap<String, String> aux = new HashMap<>();

        aux.put("id", arreglo.getId().toString());
        aux.put("nombre", arreglo.getNombre());
        aux.put("genero", dc.get(arreglo.getId_genero()).getNombre());
        aux.put("album", db.get(arreglo.getId_album()).getNombre());
        aux.put("duracion", arreglo.getDuracion().toString());
        aux.put("url", arreglo.getUrl());
        aux.put("tipo", arreglo.getTipo().toString());
        return aux;
    }

    public LinkedList<HashMap<String, String>> orderbyCancion(Integer type, String attribute) throws Exception {
        LinkedList<HashMap<String, String>> lista = all();
        if (!lista.isEmpty()) {
            HashMap arr[] = lista.toArray();
            // Convert HashMap array back to Cancion array for sorting
            Cancion[] canciones = this.listAll().toArray();
            quickSort(canciones, 0, canciones.length - 1, type);
            // Update lista with sorted canciones
            lista = new LinkedList<>();
            for (Cancion cancion : canciones) {
                lista.add(toDict(cancion, type));
            }
        }
        return lista;
    }

    public LinkedList<HashMap<String, String>> search(String attribute, String text, Integer type) throws Exception {
        LinkedList<HashMap<String, String>> lista = all();
        LinkedList<HashMap<String, String>> resp = new LinkedList<>();
        if (!lista.isEmpty()) {
            HashMap<String, String>[] arr = lista.toArray();
            switch (type) {
                case 1:
                    for (HashMap m : arr) {
                        if (m.get(attribute).toString().toLowerCase().startsWith(text.toLowerCase())) {
                            resp.add(m);
                        }
                    }
                    break;
                case 2:
                    for (HashMap m : arr) {
                        if (m.get(attribute).toString().toLowerCase().endsWith(text.toLowerCase())) {
                            resp.add(m);
                        }
                    }
                    break;
                default:
                    for (HashMap m : arr) {
                        if (m.get(attribute).toString().toLowerCase().contains(text.toLowerCase())) {
                            resp.add(m);
                        }
                    }
                    break;
            }
        }
        return resp;
    }
    /*public static void main(String[] args) {
        DaoCancion dc = new DaoCancion();
       try {
           dc.setObj(dc.get(132));
            if (dc.getObj() != null && dc.getObj().getId() != null) {
            System.out.println("Cancion found: " + dc.getObj().getNombre());
            } else {
                System.out.println("Cancion not found.");
            }
           
       } catch (Exception ex) {
       }
    }*/
}
