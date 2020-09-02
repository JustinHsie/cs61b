package byow.WorldGenerator;

import java.util.ArrayList;
import java.util.List;

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
