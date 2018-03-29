import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        double finalTimeA = 0, finalTimeB = 0;
        // Create the agents
        WCutter wCutterReal = new WCutter();
        WCutter wCutterFake = new WCutter();
        Bear bear = new Bear();
        Granny granny = new Granny();
        RRH rrh = new RRH();
        Wolf wolf = new Wolf();

        int count = 0, count1 = 0;
        double average = 0, avg = 0;
        ArrayList<Field> fields = new ArrayList<>();
        //Generate 100 maps
        for (int i = 0; i < 100; i++) {
            Field newField = new Field();
            fields.add(newField);

            rrh.createCoords();
            fields.get(i).updateField(rrh.startX, rrh.startY, 'R');

            bear.createCoords();
            while (fields.get(i).isBusy(bear.startX, bear.startY)) {
                bear.createCoords();
            }
            fields.get(i).updateField(bear.startX, bear.startY, 'B');

            wolf.createCoords();
            while (fields.get(i).isBusy(wolf.startX, wolf.startY)) {
                wolf.createCoords();
            }
            fields.get(i).updateField(wolf.startX, wolf.startY, 'W');

            wCutterReal.createCoords();
            while (fields.get(i).isBusy(wCutterReal.startX, wCutterReal.startY)) {
                wCutterReal.createCoords();
            }
            fields.get(i).updateField(wCutterReal.startX, wCutterReal.startY, 'C');

            wCutterFake.createCoords();
            while (fields.get(i).isBusy(wCutterFake.startX, wCutterFake.startY)) {
                wCutterFake.createCoords();
            }
            fields.get(i).updateField(wCutterFake.startX, wCutterFake.startY, 'c');

            granny.createCoords();
            while (fields.get(i).isBusy(granny.startX, granny.startY)) {
                granny.createCoords();
            }
            fields.get(i).updateField(granny.startX, granny.startY, 'G');
            fields.get(i).hideR();
            //analyzing the map
            Field.Cell[][] field1 = new Field.Cell[11][11];
            for (int row = 1; row < 11; row++) {
                for (int column = 1; column < 11; column++) {
                    switch (fields.get(i).symbs[row][column]) {
                        case 'W':
                            field1[row][column] = Field.Cell.DANGER;
                            break;
                        case 'B':
                            field1[row][column] = Field.Cell.DANGER;
                            break;
                        case '_':
                            field1[row][column] = Field.Cell.SAFE;
                            break;
                        case 'c':
                            field1[row][column] = Field.Cell.SAFE;
                            break;
                        case 'C':
                            field1[row][column] = Field.Cell.SAFE;
                            break;
                        case 'R':
                            field1[row][column] = Field.Cell.START;
                            break;
                        case 'G':
                            field1[row][column] = Field.Cell.FINISH;
                            break;
                    }
                }
            }
//            fields.get(i).printMap();
//            System.out.println();
            double startTimeA, startTimeB, endTimeA, endTimeB, totalTimeA, totalTimeB;
            PathSeeker path = new PathSeeker();
            startTimeA = System.currentTimeMillis();
            int steps = path.findStepsNumber(field1); // solve with A* algorithm
            endTimeA = System.currentTimeMillis();
            totalTimeA = endTimeA - startTimeA;
            finalTimeA += totalTimeA;
            average += steps;
            if (steps != -1) {
                count++;
            }

            Backtrack back = new Backtrack();
            int[][] maze;
            startTimeB = System.currentTimeMillis();
            maze = back.transformMap(fields.get(i));
            int st = back.findPath(maze); //solve with backtracking algorithm
            endTimeB = System.currentTimeMillis();
            totalTimeB = endTimeB - startTimeB;
            finalTimeB += totalTimeB;
            avg += st;
            if (st != 0) {
                count1++;
            }
        }
        // statistics
        System.out.println("A* algorithm ");
        System.out.print(" Games won: " + count);
        System.out.print(";  average steps: " + average / count);
        System.out.println("; average time: " + (finalTimeA / 100) + " milliseconds");
        System.out.println(" Games lost: " + (100 - count));
        System.out.println("Backtracking algorithm ");
        System.out.print(" Games won: " + count1);
        System.out.print("; average steps: " + (avg / count));
        System.out.println("; average time: " + (finalTimeB / 100) + " milliseconds");
        System.out.println(" Games lost: " + (100 - count1));
    }
}


