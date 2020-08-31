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
        this.topRightCorner = new Position(bottomLeftPosition.getX() + width,
                                           bottomLeftPosition.getY() + height);
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
