package unl.dance.base.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ListaEnlazada {

    static class Node {
        Integer data;
        Node next;

        public Node(Integer data) {
            this.data = data;
            this.next = null;
        }
    }

    static class LinkedList {
        Node head;

        public void add(Integer data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }

        public void printList() {
            Node current = head;
            while (current != null) {
                System.out.print(current.data);
                if (current.next != null) {
                    System.out.print(", ");
                }
                current = current.next;
            }
            System.out.println();
        }

        public int countRepeated() {
            HashMap<Integer, Integer> occurrences = new HashMap<>();
            Node current = head;
            while (current != null) {
                occurrences.put(current.data, occurrences.getOrDefault(current.data, 0) + 1);
                current = current.next;
            }

            int repeatedCount = 0;
            for (int count : occurrences.values()) {
                if (count > 1) {
                    repeatedCount++;
                }
            }

            return repeatedCount;
        }
    }

    public static void main(String[] args) {
        ListaEnlazada app = new ListaEnlazada();
        app.DataProcess();
    }

    public void PrintResult(LinkedList list, String titulo) {
        System.out.println(titulo + ":");
        list.printList();
    }

    private BufferedReader FileReadM(String fileName) throws Exception {
        InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
        if (input == null) {
            throw new Exception("Archivo '" + fileName + "' no encontrado en recursos");
        }
        return new BufferedReader(new InputStreamReader(input));
    }

    public void DataProcess() {
        long initialTime = System.nanoTime();

        LinkedList lista = new LinkedList();

        try (BufferedReader br = FileReadM("data.txt")) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lista.add(Integer.parseInt(linea.trim()));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo data: " + e.getMessage());
            return;
        }

        int numRepeated = lista.countRepeated();
        long finalTime = System.nanoTime();
        long duration = finalTime - initialTime;

        PrintResult(lista, "Elementos en la lista");
        System.out.println("Cantidad de elementos repetidos: " + numRepeated);
        System.out.println("Tiempo de ejecución: " + duration + " ns");
    }
}
