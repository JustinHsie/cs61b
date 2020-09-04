package byow.WorldGenerator;

import java.util.Random;

public class Opening {
    private Position opening;
    private Position neighborOpening;
    private static final Random RANDOM = RandomWorld.RANDOM;

    /**
     * Contains opening positions
     * @param opening
     * @param neighborOpening
     */
    public Opening(Position opening, Position neighborOpening) {
        this.opening = opening;
        this.neighborOpening = neighborOpening;
    }

    public Position getOpening() {
        return opening;
    }

    public Position getNeighborOpening() {
        return neighborOpening;
    }

    public static Opening toTop(Room current, Room neighbor) {
        // Bottom and top Y of opening
        int bottomY = current.getTopY();
        int topY = neighbor.getBottomY();
        int lowerX = current.getLeftX();
        int upperX = current.getRightX();
        int neighborLowerX = neighbor.getLeftX();
        int neighborUpperX = neighbor.getRightX();

        int point = getOpening(lowerX, upperX, neighborLowerX, neighborUpperX);
        Position top = new Position(point, topY);
        Position bottom = new Position(point, bottomY);
        return new Opening(top, bottom);
    }

    public static Opening toBottom(Room current, Room neighbor) {
        // Top and bottom Y of opening
        int topY = current.getBottomY();
        int bottomY = neighbor.getTopY();
        int lowerX = current.getLeftX();
        int upperX = current.getRightX();
        int neighborLowerX = neighbor.getLeftX();
        int neighborUpperX = neighbor.getRightX();

        int point = getOpening(lowerX, upperX, neighborLowerX, neighborUpperX);
        Position top = new Position(point, topY);
        Position bottom = new Position(point, bottomY);
        return new Opening(top, bottom);
    }

    public static Opening toLeft(Room current, Room neighbor) {
        // Left and right X of opening
        int leftX = current.getLeftX();
        int rightX = neighbor.getRightX();
        int lowerY = current.getBottomY();
        int upperY = current.getTopY();
        int neighborLowerY = neighbor.getBottomY();
        int neighborUpperY = neighbor.getTopY();

        int point = getOpening(lowerY, upperY, neighborLowerY, neighborUpperY);
        Position left = new Position(leftX, point);
        Position right = new Position(rightX, point);
        return new Opening(left, right);
    }

    public static Opening toRight(Room current, Room neighbor) {
        // Left and right X of opening
        int leftX = current.getRightX();
        int rightX = neighbor.getLeftX();
        int lowerY = current.getBottomY();
        int upperY = current.getTopY();
        int neighborLowerY = neighbor.getBottomY();
        int neighborUpperY = neighbor.getTopY();

        int point = getOpening(lowerY, upperY, neighborLowerY, neighborUpperY);
        Position left = new Position(leftX, point);
        Position right = new Position(rightX, point);
        return new Opening(left, right);
    }

    /**
     * Calculates opening positions given bordering wall corners
     * @return
     */
    private static int getOpening(int lower, int upper,
                               int neighborLower, int neighborUpper) {
        int low = Math.max(lower + 1, neighborLower + 1);
        int high = Math.min(upper - 1, neighborUpper - 1);
        if (high - low == 0) {
            return high;
        }
        else {
            int point = RANDOM.nextInt(high - low) + low;
            return point;
        }

    }

}
