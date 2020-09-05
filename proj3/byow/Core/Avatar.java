package byow.Core;

import byow.InputDemo.InputSource;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import byow.WorldGenerator.RandomWorld;

import java.util.Random;

public class Avatar {
    Random RANDOM = RandomWorld.RANDOM;
    TETile[][] world;
    TERenderer ter;
    Coordinate loc;

    public Avatar(TETile[][] world, TERenderer ter) {
        this.world = world;
        this.ter = ter;

        while (true) {
            int x = RANDOM.nextInt(Engine.WIDTH);
            int y = RANDOM.nextInt(Engine.HEIGHT);
            if (world[x][y].equals(Tileset.FLOOR)) {
                world[x][y] = Tileset.FLOWER;
                loc = new Coordinate(x, y);
                break;
            }
        }
        ter.renderFrame(world);
    }

    public void move(char c) {
        if (c == 'W') {
            getTile(loc.x, loc.y + 1);
        }
        if (c == 'A') {
            getTile(loc.x - 1, loc.y);
        }
        if (c == 'S') {
            getTile(loc.x, loc.y - 1);
        }
        if (c == 'D') {
            getTile(loc.x + 1, loc.y);
        }
    }

    private void getTile(int x, int y) {
        if (world[x][y].equals(Tileset.FLOOR)) {
            world[x][y] = Tileset.FLOWER;
            world[loc.x][loc.y] = Tileset.FLOOR;
            loc.x = x;
            loc.y = y;
            ter.renderFrame(world);
        }
    }
}
