import static java.lang.Math.random;

/**
 * Creates and random generates the bear
 */
public class Bear {

    int startX;
    int startY;

    public void createCoords() {

        startX = (int) (random() * 10) + 1;
        startY = (int) (random() * 10) + 1;
    }

}



