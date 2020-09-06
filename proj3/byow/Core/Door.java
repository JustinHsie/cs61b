package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import byow.WorldGenerator.RandomWorld;

import java.util.Random;

public class Door {
    Random RANDOM = RandomWorld.RANDOM;
    TETile[][] world;
    TERenderer ter;
    Coordinate loc;
    TETile icon = Tileset.LOCKED_DOOR;

    public Door(TETile[][] world, TERenderer ter) {
        this.world = world;
        this.ter = ter;

        while (true) {
            int x = RANDOM.nextInt(Engine.WIDTH - 2);
            int y = RANDOM.nextInt(Engine.HEIGHT - 2);
            if (world[x][y].equals(Tileset.WALL) &&
                    (world[x + 1][y].equals(Tileset.FLOOR) ||
                            world[x][y + 1].equals(Tileset.FLOOR) ||
                                    world[x - 1][y].equals(Tileset.FLOOR) ||
                                            world[x][y - 1].equals(Tileset.FLOOR))) {
                world[x][y] = icon;
                loc = new Coordinate(x, y);
                break;
            }
        }
        ter.renderFrame(world);
    }
}
