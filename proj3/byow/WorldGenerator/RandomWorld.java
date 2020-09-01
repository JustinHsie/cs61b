package byow.WorldGenerator;

import byow.Core.Engine;
import byow.TileEngine.*;

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
        initializeTiles(tiles);
        //Room room = generateRoom(tiles);
        Room room = new Room(5, 6, new Position(50, 20));
        Draw.drawRoom(room, tiles);
        Room room2 = generateNeighbor(tiles, room.getBottomLeftCorner(), room.getTopRightCorner());
        Room room3 = generateNeighbor(tiles, room2.getBottomLeftCorner(), room2.getTopRightCorner());
        Room room4 = generateNeighbor(tiles, room3.getBottomLeftCorner(), room3.getTopRightCorner());


    }

    /**
     * Takes a room's corners and generates neighbor next to it
     * @param tiles
     * @param bottomLeft
     * @param topRight
     * @return
     */
    private static Room generateNeighbor(TETile[][] tiles, Position bottomLeft, Position topRight) {
        Room room;
        Position bottomLeftPosition;
        int width;
        int height;

        do {
            // Min width and height of 3 tiles
            width = RANDOM.nextInt(WIDTH - 3) + 3;
            height = RANDOM.nextInt(HEIGHT - 3) + 3;
            bottomLeftPosition = randomSide(bottomLeft, topRight, width, height);
        }
        while (outOfBounds(bottomLeftPosition, width, height));

        room = new Room(width, height, bottomLeftPosition);
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
     * Generates random room of random size and location, returns room
     * @param tiles
     * @return room
     */
    private static Room generateRoom(TETile[][] tiles) {
        Room room;
        Position bottomLeftPosition;
        int width;
        int height;

        do {
            bottomLeftPosition = randomPosition();
            // Min width and height of 3 tiles
            width = RANDOM.nextInt(WIDTH - 3) + 3;
            height = RANDOM.nextInt(HEIGHT - 3) + 3;
        }
        while (outOfBounds(bottomLeftPosition, width, height));

        room = new Room(width, height, bottomLeftPosition);
        Draw.drawRoom(room, tiles);
        return room;
    }

    /**
     * Checks if room is out of bounds of grid
     * @param bottomLeft
     * @param width
     * @param height
     * @return
     */
    private static boolean outOfBounds(Position bottomLeft, int width, int height) {
        int roomTop = bottomLeft.getY() + height;
        int roomRight = bottomLeft.getX() + width;

        if (roomTop >= HEIGHT || roomRight >= WIDTH) {
            return true;
        }
        if (bottomLeft.getX() < 0 || bottomLeft.getY() < 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns random position
     * @return position
     */
    private static Position randomPosition() {
        int x = RANDOM.nextInt(WIDTH);
        int y = RANDOM.nextInt(HEIGHT);
        Position position = new Position(x, y);

        return position;
    }


    /**
     * Fills grid with Tileset.Nothing
     * @param tiles
     */
    private static void initializeTiles(TETile[][] tiles) {
        for (int i = 0; i < WIDTH; i += 1) {
            for (int j = 0; j < HEIGHT; j += 1) {
                tiles[i][j] = Tileset.NOTHING;
            }
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
