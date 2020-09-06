package byow.Core;

import byow.InputDemo.InputSource;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import byow.WorldGenerator.RandomWorld;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Random;

public class Avatar {
    Random RANDOM = RandomWorld.RANDOM;
    TETile[][] world;
    TERenderer ter;
    Coordinate loc;
    TETile icon = Tileset.AVATAR;
    boolean hasKey;

    public Avatar(TETile[][] world, TERenderer ter) {
        this.world = world;
        this.ter = ter;
        hasKey = false;

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
            world[x][y] = icon;
            world[loc.x][loc.y] = Tileset.FLOOR;
            loc.x = x;
            loc.y = y;
            ter.renderFrame(world);
        }
        if (world[x][y].equals(Tileset.FLOWER)) {
            world[x][y] = icon;
            world[loc.x][loc.y] = Tileset.FLOOR;
            loc.x = x;
            loc.y = y;
            hasKey = true;
            ter.renderFrame(world);
        }
        if (world[x][y].equals(Tileset.LOCKED_DOOR) && hasKey) {
            StdDraw.clear();
            StdDraw.enableDoubleBuffering();
            StdDraw.clear(Color.black);
            StdDraw.setPenColor(Color.white);
            StdDraw.text(Engine.WIDTH / 2, Engine.HEIGHT / 2, "You win!!");
            StdDraw.show();
        }
    }
}
