/**
 * Creates the map
 */
public class Field {

    public enum Cell {
        START, FINISH, DANGER, SAFE
    }

    public char[][] symbs;
    //Constructor of map
    public Field() {
        symbs = new char[11][11];
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                symbs[i][j] = '_';
            }
        }
    }

    void updateField(int x, int y, char type) {
        if (x > 9 || y > 9 || x < 1 || y < 1) {
            return;
        }
        symbs[x][y] = type;
    // Range of bear
        if (type == 'B') {
            symbs[x - 1][y - 1] = 'B';
            symbs[x][y - 1] = 'B';
            symbs[x + 1][y - 1] = 'B';
            symbs[x - 1][y] = 'B';
            symbs[x - 1][y + 1] = 'B';
            symbs[x + 1][y] = 'B';
            symbs[x + 1][y + 1] = 'B';
            symbs[x][y + 1] = 'B';
        }
    // Range of wolf
        if (type == 'W') {
            symbs[x][y - 1] = 'W';
            symbs[x - 1][y] = 'W';
            symbs[x + 1][y] = 'W';
            symbs[x][y + 1] = 'W';

        }
    //Range of RRH
        if (type == 'R') {
            symbs[x][y + 1] = 'r';
            symbs[x + 1][y] = 'r';
            symbs[x + 1][y + 1] = 'r';
        }
    }

    //check if the specific cell is busy
    boolean isBusy(int x, int y) {
        return symbs[x][y] != '_';
    }

    //printing the map
    void printMap() {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                System.out.print(symbs[i][j]);
            }
            System.out.println();
        }
    }

    // hide the unnecessary range
    void hideR() {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                if (symbs[i][j] == 'r') {
                    symbs[i][j] = '_';
                }
            }
        }
    }
}
