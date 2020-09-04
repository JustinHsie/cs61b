package byow.WorldGenerator;

import byow.Core.Engine;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class Draw {
    private static final int WIDTH = Engine.WIDTH;
    private static final int HEIGHT = Engine.HEIGHT;

    public static void drawOpening(Opening opening, TETile[][] tiles) {
        Position current = opening.getOpening();
        Position neighbor = opening.getNeighborOpening();

        drawOpening(current, tiles);
        drawOpening(neighbor, tiles);
    }

    private static void drawOpening(Position opening, TETile[][] tiles) {
        int x = opening.getX();
        int y = opening.getY();
        tiles[x][y] = Tileset.FLOOR;
    }

    /**
     * Draws given room onto tiles grid
     * @param room
     * @param tiles
     */
    public static void drawRoom(Room room, TETile[][] tiles) {
        int startX = room.getBottomLeftCorner().getX();
        int startY = room.getBottomLeftCorner().getY();
        int endX = room.getTopRightCorner().getX();
        int endY = room.getTopRightCorner().getY();

        for (int x = startX; x <= endX; x += 1) {
            for (int y = startY; y <= endY; y += 1) {
                if (x == startX || x == endX || y == startY || y == endY) {
                    tiles[x][y] = Tileset.WALL;
                }
                else {
                    tiles[x][y] = Tileset.FLOOR;
                }
            }
        }
    }

    /**
     * Fills grid with Tileset.Nothing
     * @param tiles
     */
    public static void initializeTiles(TETile[][] tiles) {
        for (int i = 0; i < WIDTH; i += 1) {
            for (int j = 0; j < HEIGHT; j += 1) {
                tiles[i][j] = Tileset.NOTHING;
            }
        }
    }
}
