package byow.World;

import byow.Core.Engine;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class RandomWorld {
    private static final int WIDTH = Engine.WIDTH;
    private static final int HEIGHT = Engine.HEIGHT;

    public RandomWorld() {
        
    }


    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] randomWorld = new TETile[WIDTH][HEIGHT];

    }
}
