package byow.WorldGenerator;

public class Room {
    private int width;
    private int height;
    private Position bottomLeftCorner;
    private Position topRightCorner;

    public Room(int width, int height, Position bottomLeftPosition) {
        this.width = width;
        this.height = height;
        this.bottomLeftCorner = bottomLeftPosition;
        this.topRightCorner = new Position(bottomLeftPosition.getX() + width - 1,
                                           bottomLeftPosition.getY() + height - 1);
    }

    public int getLeftX() {
        return bottomLeftCorner.getX();
    }

    public int getRightX() {
        return topRightCorner.getX();
    }

    public int getBottomY() {
        return bottomLeftCorner.getY();
    }

    public int getTopY() {
        return topRightCorner.getY();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Position getBottomLeftCorner() {
        return bottomLeftCorner;
    }

    public Position getTopRightCorner() {
        return topRightCorner;
    }
}
