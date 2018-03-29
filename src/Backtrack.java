import java.util.Stack;
/**
 * Uses the Backtracking Algorithm to find the path from
 * an initial point to a final goal.
 *
 */
public class Backtrack {

    int[] start = null; // initial position of RRH.
    int[] finish = null; // location of granny.

    /**
     * Encoding the original map into int[][] array where wolf and bear are defined by 1
     * and wood cutter with empty spaces are defined by 0.
     *
     * @param field initial map
     * @return encoded map
     */
    public int[][] transformMap(Field field) {
        int maze[][] = new int[11][11];

        for (int row = 1; row < 11; row++) {
            for (int column = 1; column < 11; column++) {
                switch (field.symbs[row][column]) {
                    case 'W':
                        maze[row][column] = 1;
                        break;
                    case 'B':
                        maze[row][column] = 1;
                        break;
                    case 'C':
                        maze[row][column] = 0;
                        break;
                    case 'c':
                        maze[row][column] = 0;
                        break;
                    case '_':
                        maze[row][column] = 0;
                        break;
                    case 'R':
                        maze[row][column] = 2;
                        break;
                    case 'G':
                        maze[row][column] = 3;
                        break;
                }
            }
        }
        return maze;
    }

    /**
     * Compare the coordinates of one value to another one.
     *
     * @param a first value to compare
     * @param b second value to compare
     * @return they are the same or not
     */
    private static boolean compareCoordinates(int[] a, int[] b) {
        if (a[0] == b[0] && a[1] == b[1]) {
            return true;
        }
        return false;
    }

    /**
     * Find the coordinates of a value in maze.
     *
     * @param coord given value
     * @param maze  given map
     * @return null if doesn't exist
     */
    private static int[] findCoordinates(int[] coord, int[][] maze) {
        int x = coord[0], y = coord[1];
        if (x - 1 >= 0 && maze[x - 1][y] == 0) return new int[]{x - 1, y};
        if (x + 1 < maze.length && maze[x + 1][y] == 0) return new int[]{x + 1, y};
        if (y - 1 >= 0 && maze[x][y - 1] == 0) return new int[]{x, y - 1};
        if (y + 1 < maze.length && maze[x][y + 1] == 0) return new int[]{x, y + 1};
        return null;
    }

    Stack stack = new Stack();

    /**
     * This function solves the problem using Backtracking. It returns -1 if no
     * path is possible, otherwise return the number of steps that were forming the path till granny.
     *
     * @param maze the map
     * @return the number of steps or -1
     */
    public int findPath(int[][] maze) {
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if (maze[i][j] == 2) {
                    start = new int[]{i, j};
                    maze[i][j] = 0;
                } else {
                    if (maze[i][j] == 3) {
                        finish = new int[]{i, j};
                        maze[i][j] = 0;
                    } else {
                        maze[i][j] = maze[i][j];
                    }
                }
            }
        }
        stack.push(start);
        maze[start[0]][start[1]] = 2;
        while (!stack.isEmpty() && !compareCoordinates((int[]) stack.peek(), finish)) {
            if (findCoordinates((int[]) stack.peek(), maze) != null) {
                stack.push(findCoordinates((int[]) stack.peek(), maze));
                maze[((int[]) stack.peek())[0]][((int[]) stack.peek())[1]] = 2;
            } else stack.pop();
        }
        int count = 0;
        if (stack.isEmpty()) {
            return -1;
        } else {
            String[] answer = new String[stack.size()];
            int i = stack.size() - 1;
            while (!stack.isEmpty()) {
                int x = ((int[]) stack.peek())[0];
                int y = ((int[]) stack.peek())[1];
                String s = (char) ('A' + x) + Integer.toString(y + 1);
                answer[i] = s;
                i--;
                stack.pop();
            }
            for (int j = 0; j < answer.length; j++) {
                count++;
            }
        }
        return count;
    }

    /**
     * printing the map
     *
     * @param map
     */
    void printMap(int[][] map) {
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map.length - 1; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
