package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import byow.WorldGenerator.RandomWorld;

import java.util.Random;

public class Key {
    Random RANDOM = RandomWorld.RANDOM;
    TETile[][] world;
    TERenderer ter;
    Coordinate loc;
    TETile icon = Tileset.FLOWER;

    public Key(TETile[][] world, TERenderer ter) {
        this.world = world;
        this.ter = ter;

        while (true) {
            int x = RANDOM.nextInt(Engine.WIDTH);
            int y = RANDOM.nextInt(Engine.HEIGHT);
            if (world[x][y].equals(Tileset.FLOOR)) {
                world[x][y] = icon;
                loc = new Coordinate(x, y);
                break;
            }
        }
        ter.renderFrame(world);
    }
}
