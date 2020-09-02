package byow.WorldGenerator;

import byow.Core.Engine;
import byow.TileEngine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWorld {
    private static final int WIDTH = Engine.WIDTH;
    private static final int HEIGHT = Engine.HEIGHT;

    private static final long SEED = 123;
    private static final Random RANDOM = new Random(SEED);

    /**
     * Generates world given tiles
     * @param tiles
     */
    public static void generateWorld(TETile[][] tiles) {
        Draw.initializeTiles(tiles, WIDTH, HEIGHT);
        List<Room> rooms = new ArrayList<>();

        Room room = new Room(5, 6, new Position(50, 20));
        Draw.drawRoom(room, tiles);
        Room room2 = generateRoom(tiles, room.getBottomLeftCorner(), room.getTopRightCorner(), rooms);
        System.out.println(
                "(" + room2.getBottomLeftCorner().getX() + ", " + room2.getBottomLeftCorner().getY());
        Room room3 = generateRoom(tiles, room2.getBottomLeftCorner(), room2.getTopRightCorner(), rooms);
        System.out.println(
                "(" + room3.getBottomLeftCorner().getX() + ", " + room3.getBottomLeftCorner().getY());
        Room room4 = generateRoom(tiles, room3.getBottomLeftCorner(), room3.getTopRightCorner(), rooms);
        System.out.println(
                "(" + room4.getBottomLeftCorner().getX() + ", " + room4.getBottomLeftCorner().getY());


    }

    /**
     * Takes a room's corners and generates neighbor next to it
     * @param tiles
     * @param bottomLeft
     * @param topRight
     * @return
     */
    private static Room generateRoom(TETile[][] tiles, Position bottomLeft,
                                     Position topRight, List<Room> rooms) {
        Room room;
        Position bottomLeftPosition;
        int width;
        int height;

        do {
            // Min width and height of 3 tiles
            width = RANDOM.nextInt(10 - 3) + 3;
            height = RANDOM.nextInt(10 - 3) + 3;
            bottomLeftPosition = randomSide(bottomLeft, topRight, width, height);
            room = new Room(width, height, bottomLeftPosition);
        }
        while (outOfBounds(room, width, height) || Overlap.overlaps(room, rooms));

        rooms.add(room);
        Draw.drawRoom(room, tiles);
        return room;
    }

    private static Position randomSide(Position bottomLeft, Position topRight, int width, int height) {
        int x;
        int y;
        int direction = RANDOM.nextInt(4);

        switch (direction) {
            // To left
            case 0:
                x = bottomLeft.getX() - width;
                y = RANDOM.nextInt(topRight.getY() - bottomLeft.getY()) + bottomLeft.getY();
                return new Position(x, y);

            // To right
            case 1:
                x = topRight.getX() + 1;
                y = RANDOM.nextInt(topRight.getY() - bottomLeft.getY()) + bottomLeft.getY();
                return new Position(x, y);

            // To top
            case 2:
                x = RANDOM.nextInt(topRight.getX() - bottomLeft.getX()) + bottomLeft.getX();
                y = topRight.getY() + 1;
                return new Position(x, y);

            // To bottom
            case 3:
                x = RANDOM.nextInt(topRight.getX() - bottomLeft.getX()) + bottomLeft.getX();
                y = bottomLeft.getY() - height;
                return new Position(x, y);

            default:
                x = topRight.getX() + 1;
                y = RANDOM.nextInt(topRight.getY() - bottomLeft.getY()) + bottomLeft.getY();
                return new Position(x, y);
        }
    }

    /**
     * Checks if room is out of bounds of grid
     * @param room
     * @param width
     * @param height
     * @return
     */
    private static boolean outOfBounds(Room room, int width, int height) {
        int roomTop = room.getTopRightCorner().getY();
        int roomRight = room.getTopRightCorner().getX();
        int roomLeft = room.getBottomLeftCorner().getX();
        int roomBot = room.getBottomLeftCorner().getY();

        if (roomTop >= HEIGHT || roomRight >= WIDTH) {
            return true;
        }
        if (roomLeft < 0 || roomBot < 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        generateWorld(world);

        ter.renderFrame(world);
    }
}
