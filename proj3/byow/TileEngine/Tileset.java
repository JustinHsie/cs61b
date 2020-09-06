package byow.TileEngine;

import java.awt.Color;

/**
 * Contains constant tile objects, to avoid having to remake the same tiles in different parts of
 * the code.
 *
 * You are free to (and encouraged to) create and add your own tiles to this file. This file will
 * be turned in with the rest of your code.
 *
 * Ex:
 *      world[x][y] = Tileset.FLOOR;
 *
 * The style checker may crash when you try to style check this file due to use of unicode
 * characters. This is OK.
 */

public class Tileset {
    public static final TETile AVATAR = new TETile('♠', new Color(255, 51, 51), new Color(255, 229, 204), "you");
    public static final TETile WALL = new TETile('▲', new Color(255, 153, 153), new Color(255, 178, 102),
            "wall");
    public static final TETile FLOOR = new TETile('❀', new Color(76, 153, 0), new Color(178, 255, 102),
            "floor");
    public static final TETile NOTHING = new TETile('≈', new Color(51, 153, 255), new Color(153, 204, 255), "nothing");
    public static final TETile GRASS = new TETile('♠', Color.green, Color.black, "grass");
    public static final TETile WATER = new TETile('≈', Color.blue, Color.black, "water");
    public static final TETile FLOWER = new TETile('▢', new Color(64, 64, 64), new Color(255, 255, 51), "flower");
    public static final TETile LOCKED_DOOR = new TETile('█', new Color(255, 255, 51), new Color(64, 64, 64),
            "locked door");
    public static final TETile UNLOCKED_DOOR = new TETile('▢', Color.orange, Color.black,
            "unlocked door");
    public static final TETile SAND = new TETile('▒', Color.yellow, Color.black, "sand");
    public static final TETile MOUNTAIN = new TETile('▲', Color.gray, Color.black, "mountain");
    public static final TETile TREE = new TETile('♠', Color.green, Color.black, "tree");
}


