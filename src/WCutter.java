import static java.lang.Math.random;
/**
 * Creates and random generates the wood cutter
 */
public class WCutter {

    int startX;
    int startY;

    public void createCoords() {
        startX = (int) (random() * 10) + 1;
        startY = (int) (random() * 10) + 1;
    }
}
