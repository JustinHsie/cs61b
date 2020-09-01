package byow.WorldGenerator;

import byow.TileEngine.Tileset;

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

    public List<Position> getWallTiles() {
        wallTiles = new ArrayList<>();
        int startX = bottomLeftCorner.getX();
        int startY = bottomLeftCorner.getY();
        int endX = topRightCorner.getX();
        int endY = topRightCorner.getY();

        for (int x = startX; x < endX; x += 1) {
            for (int y = startY; y < endY; y += 1) {
                if (x == startX || x == endX - 1 || y == startY || y == endY - 1) {
                    wallTiles.add(new Position(x, y));
                }
            }
        }
        return wallTiles;
    }
}
