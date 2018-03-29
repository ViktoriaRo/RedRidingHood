import java.util.ArrayList;
/**
 * Uses the A* Algorithm to find the shortest path from
 * an initial to a goal node.
 *
 */
public class PathSeeker {

    //returns number of steps or -1 if there is no path
    public int findStepsNumber(Field.Cell[][] field) {
        // since we've got an empty open list or we've run out of search
       // there was no path. Just return -1
        Node end = findPath(field);
        if (end == null)
            return -1;
        int result = -1;
        while (end != null) {
            result++;
            end = end.parent;
        }
        return result;
    }

    /**
     *
     * @param field The map being searched
     * @return Returns the shortest Path from a start node to an end node according to
     * the A* heuristics.
     */
    private Node findPath(Field.Cell[][] field) {
        //The complete set of nodes across the map
        Node[][] nodes = new Node[field.length][field[0].length];
        Node start = null, goal = null;
        Node currentNode = null;
        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closedList = new ArrayList<>();


        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {

                if (field[i][j] == Field.Cell.DANGER) {
                    nodes[i][j] = null;
                    continue;
                }
                nodes[i][j] = new Node(i, j);
                if (field[i][j] == Field.Cell.START)
                    start = nodes[i][j];
                if (field[i][j] == Field.Cell.FINISH)
                    goal = nodes[i][j];
            }
        }
        if (start == null || goal == null) {
            return null;
        }

        start.fScore = 0;
        openList.add(start);
        ArrayList<Node> neighbors = new ArrayList<>();
        while (!openList.isEmpty()) {

            currentNode = openList.get(0);
            for (int i = 1; i < openList.size(); i++) {
                if (currentNode.fScore > openList.get(i).fScore)
                    currentNode = openList.get(i);
            }

            if (currentNode == goal)
                return goal;

            openList.remove(currentNode);
            closedList.add(currentNode);

            neighbors.clear();

            if (currentNode.x > 0 && nodes[currentNode.x - 1][currentNode.y] != null)
                neighbors.add(nodes[currentNode.x - 1][currentNode.y]);
            if (currentNode.x < nodes.length - 1 && nodes[currentNode.x + 1][currentNode.y] != null)
                neighbors.add(nodes[currentNode.x + 1][currentNode.y]);
            if (currentNode.y > 0 && nodes[currentNode.x][currentNode.y - 1] != null)
                neighbors.add(nodes[currentNode.x][currentNode.y - 1]);
            if (currentNode.y < nodes[0].length - 1 && nodes[currentNode.x][currentNode.y + 1] != null)
                neighbors.add(nodes[currentNode.x][currentNode.y + 1]);

            for (int i = 0; i < neighbors.size(); i++) {

                if (closedList.contains(neighbors.get(i)))
                    continue;

                int gScore = currentNode.gScore + 1;

                if (openList.contains(neighbors.get(i))) {

                    if (gScore < neighbors.get(i).gScore) {
                        neighbors.get(i).gScore = gScore;
                        neighbors.get(i).fScore = gScore + Math.abs(neighbors.get(i).x - currentNode.x) + Math.abs(neighbors.get(i).y - currentNode.y);
                        neighbors.get(i).parent = currentNode;
                    }
                } else {

                    neighbors.get(i).gScore = gScore;
                    neighbors.get(i).fScore = gScore + Math.abs(neighbors.get(i).x - currentNode.x) + Math.abs(neighbors.get(i).y - currentNode.y);
                    neighbors.get(i).parent = currentNode;
                    openList.add(neighbors.get(i));
                }

            }

        }

        return null;
    }

    class Node {

        private int x;
        private int y;
        private Node parent;
        private int gScore;
        private int fScore;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
