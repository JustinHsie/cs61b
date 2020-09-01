package byow.WorldGenerator;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int width;
    private int height;
    private Position bottomLeftCorner;
    private Position topRightCorner;
    private List<Position> wallTiles;

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

    public List<Position> getBottomWallTiles() {
        return getWallTiles('b');
    }
    public List<Position> getTopWallTiles() {
        return getWallTiles('t');
    }
    public List<Position> getLeftWallTiles() {
        return getWallTiles('l');
    }
    public List<Position> getRightWallTiles() {
        return getWallTiles('r');
    }

    /**
     * Gets wall positions
     * @return
     */
    private List<Position> getWallTiles(char side) {
        wallTiles = new ArrayList<>();
        int startX = bottomLeftCorner.getX();
        int endX = topRightCorner.getX();
        int startY = bottomLeftCorner.getY();
        int endY = topRightCorner.getY();

        // Bottom wall
        if (side == 'b') {
            endY = startY;
        }
        // Top wall
        if (side == 't') {
            startY = endY;
        }
        // Left wall
        if (side == 'l') {
            endX = startX;
        }
        // Right wall
        if (side == 'r') {
            startX = endX;
        }

        for (int x = startX; x <= endX; x += 1) {
            for(int y = startY; y <= endY; y += 1) {
                wallTiles.add(new Position(x, y));
                System.out.print("(" + x + ", " + y + ")");
            }
        }
        System.out.println();
        return wallTiles;
    }
}
