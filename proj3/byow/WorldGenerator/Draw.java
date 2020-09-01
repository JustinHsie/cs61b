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

        for (int x = startX; x < endX; x += 1) {
            for (int y = startY; y < endY; y += 1) {
                if (x == startX || x == endX - 1 || y == startY || y == endY - 1) {
                    tiles[x][y] = Tileset.WALL;
                }
                else {
                    tiles[x][y] = Tileset.FLOOR;
                }
            }
        }
    }
}
