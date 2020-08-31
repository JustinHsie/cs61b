package byow.WorldGenerator;

import byow.Core.Engine;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

public class RandomWorld {
    private static final int WIDTH = Engine.WIDTH;
    private static final int HEIGHT = Engine.HEIGHT;

    private static final long SEED = 123;
    private static final Random RANDOM = new Random(123);

    public static void generateWorld(TETile[][] tiles) {
        initializeTiles(tiles);
        generateRoom(tiles);


    }

    private static void generateRoom(TETile[][] tiles) {
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
        drawRoom(tiles, room);

    }

    private static boolean outOfBounds(Position bottomLeft, int width, int height) {
        int roomTop = bottomLeft.getY() + height;
        int roomRight = bottomLeft.getX() + width;

        if (roomTop >= HEIGHT || roomRight >= WIDTH) {
            return true;
        }
        return false;
    }

    private static void drawRoom(TETile[][] tiles, Room room) {
        int startY = room.getBottomLeftCorner().getY();
        int startX = room.getBottomLeftCorner().getX();
        int endY = room.getTopRightCorner().getY();
        int endX = room.getTopRightCorner().getX();

        for (int row = startX; row < endX; row += 1) {
            for (int col = startY; col < endY; col += 1) {
                if (row == startX || row == endX - 1 || col == startY || col == endY - 1) {
                    tiles[row][col] = Tileset.WALL;
                }
                else {
                    tiles[row][col] = Tileset.FLOOR;
                }
            }
        }

        System.out.println("(" + startX + ", " + startY + ")");
        System.out.println("(" + endX + ", " + endY + ")");
        System.out.println("Room width: " + room.getWidth());
        System.out.println("Room height: " + room.getHeight());
    }

    private static Position randomPosition() {
        int x = RANDOM.nextInt(WIDTH);
        int y = RANDOM.nextInt(HEIGHT);
        Position position = new Position(x, y);

        return position;
    }

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
