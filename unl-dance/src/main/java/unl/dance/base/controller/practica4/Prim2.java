package unl.dance.base.controller.practica4;
import unl.dance.base.controller.data_struct.list.LinkedList;
public class Prim2 {
    
    public String generar(int r, int c) throws Exception {
        // dimensions of generated maze
        //int r = 100, c = 100;

        // build maze and initialize with only walls
        StringBuilder s = new StringBuilder(c);
        for (int x = 0; x < c; x++) {
            s.append('0');
        }
        char[][] maz = new char[r][c];
        for (int x = 0; x < r; x++) {
            maz[x] = s.toString().toCharArray();
        }

        // select random point and open as start node
        Point st = new Point((int) (Math.random() * r), (int) (Math.random() * c), null);
        maz[st.r][st.c] = 'S';

        // iterate through direct neighbors of node
        LinkedList< Point> frontier = new LinkedList< Point>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0 || x != 0 && y != 0) {
                    continue;
                }
                try {
                    if (maz[st.r + x][st.c + y] == '1') {
                        continue;
                    }
                } catch (Exception e) { // ignore ArrayIndexOutOfBounds
                    continue;
                }
                // add eligible points to frontier
                frontier.add(new Point(st.r + x, st.c + y, st));
            }
        }

        Point last = null;
        while (!frontier.isEmpty()) {

            // pick current node at random
            Point cu = frontier.delete((int) (Math.random() * frontier.getLength()));
            Point op = cu.opposite();
            try {
                // if both node and its opposite are walls
                if (maz[cu.r][cu.c] == '0') {
                    if (maz[op.r][op.c] == '0') {

                        // open path between the nodes
                        maz[cu.r][cu.c] = '1';
                        maz[op.r][op.c] = '1';

                        // store last node in order to mark it later
                        last = op;

                        // iterate through direct neighbors of node, same as earlier
                        for (int x = -1; x <= 1; x++) {
                            for (int y = -1; y <= 1; y++) {
                                if (x == 0 && y == 0 || x != 0 && y != 0) {
                                    continue;
                                }
                                try {
                                    if (maz[op.r + x][op.c + y] == '1') {
                                        continue;
                                    }
                                } catch (Exception e) {
                                    continue;
                                }
                                frontier.add(new Point(op.r + x, op.c + y, op));
                            }
                        }
                    }
                }
            } catch (Exception e) { // ignore NullPointer and ArrayIndexOutOfBounds
            }

            // if algorithm has resolved, mark end node
            if (frontier.isEmpty()) {
                maz[last.r][last.c] = 'E';
            }
        }

        s = new StringBuilder();
        for (int i = 0; i < r; i++) {
            String aux = "";
            for (int j = 0; j < c; j++) {
                //System.out.print(maz[i][j]);
                 aux += maz[i][j]+",";
            }
            aux = aux.substring(0,aux.length()-1);
            s.append(aux).append("\n");
        }
        
       
        
      
        return s.toString();
    }
}
