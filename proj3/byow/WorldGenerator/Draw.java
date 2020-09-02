package byow.WorldGenerator;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class Draw {
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
    public static void initializeTiles(TETile[][] tiles, int width, int height) {
        for (int i = 0; i < width; i += 1) {
            for (int j = 0; j < height; j += 1) {
                tiles[i][j] = Tileset.NOTHING;
            }
        }
    }
}
