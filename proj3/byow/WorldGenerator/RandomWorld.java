package byow.WorldGenerator;

import byow.Core.Engine;
import byow.TileEngine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWorld {
    private static final int WIDTH = Engine.WIDTH;
    private static final int HEIGHT = Engine.HEIGHT;

    public static long SEED;
    public static Random RANDOM;

    /**
     * Generates world given tiles
     * @param tiles
     */
    public static void generateWorld(TETile[][] tiles, long seed) {
        SEED = seed;
        RANDOM = new Random(seed);
        Draw.initializeTiles(tiles);
        List<Room> rooms = new ArrayList<>();
        Room room;

        // Sets up first room
        do {
        int width = RANDOM.nextInt(11 - 3) + 3;
        int height = RANDOM.nextInt(11 - 3) + 3;
        int x = RANDOM.nextInt(WIDTH);
        int y = RANDOM.nextInt(HEIGHT);

        Position startPos = new Position(x, y);
        room = new Room(width, height, startPos);

        }
        while (Catch.outOfBounds(room) || Catch.overlaps(room, rooms));

        rooms.add(room);
        Draw.drawRoom(room, tiles);

        // Generates rooms
        roomRecursion(tiles, room, rooms);

    }

    private static void roomRecursion(TETile[][] tiles, Room room, List<Room> rooms) {
        if (room == null) {
            return;
        }
        int numNeighbors = RANDOM.nextInt(3) + 1;
        for (int i = 0; i < numNeighbors; i += 1) {
            Room newRoom = generateRoom(tiles, room, rooms);
            roomRecursion(tiles, newRoom, rooms);
        }
    }

    /**
     * Takes a room and generates neighbor next to it
     * @param tiles
     * @param room
     * @return
     */
    private static Room generateRoom(TETile[][] tiles, Room room, List<Room> rooms) {
        Room newRoom;
        Opening opening;
        RoomOpening roomOpening;
        int newWidth;
        int newHeight;
        int tries = 0;

        do {
            // Min width and height of 3 tiles
            newWidth = RANDOM.nextInt(11 - 3) + 3;
            newHeight = RANDOM.nextInt(11 - 3) + 3;
            roomOpening = randomNeighbor(room, newWidth, newHeight);
            newRoom = roomOpening.room;
            opening = roomOpening.opening;

            // Limit number of tries
            tries += 1;
            if (tries > 30) {
                return null;
            }
        }
        while (Catch.outOfBounds(newRoom) || Catch.overlaps(newRoom, rooms));

        rooms.add(newRoom);
        Draw.drawRoom(newRoom, tiles);
        Draw.drawOpening(opening, tiles);

        return newRoom;
    }

    /**
     * Returns a new neighboring room on random side of the given room,
     * given new room's width and height
     * @param room
     * @param width
     * @param height
     * @return
     */
    private static RoomOpening randomNeighbor(Room room, int width, int height) {
        int x;
        int y;
        Position bottomLeft = room.getBottomLeftCorner();
        Position topRight = room.getTopRightCorner();
        Room newRoom;
        Opening opening;
        int direction = RANDOM.nextInt(4);

        switch (direction) {
            /**
             * -1s limit the range so floors across rooms can be accessible
             */
            // To left
            case 0:
                x = bottomLeft.getX() - width;
                y = RANDOM.nextInt(topRight.getY() - bottomLeft.getY() - 1) + bottomLeft.getY();
                newRoom =  new Room(width, height, new Position(x, y));
                opening = Opening.toRight(newRoom, room);
                return new RoomOpening(newRoom, opening);

            // To right
            case 1:
                x = topRight.getX() + 1;
                y = RANDOM.nextInt(topRight.getY() - bottomLeft.getY() - 1) + bottomLeft.getY();
                newRoom = new Room(width, height, new Position(x, y));
                opening = Opening.toLeft(newRoom, room);
                return new RoomOpening(newRoom, opening);

            // To top
            case 2:
                x = RANDOM.nextInt(topRight.getX() - bottomLeft.getX() - 1) + bottomLeft.getX();
                y = topRight.getY() + 1;
                newRoom = new Room(width, height, new Position(x, y));
                opening = Opening.toBottom(newRoom, room);
                return new RoomOpening(newRoom, opening);

            // To bottom
            case 3:
                x = RANDOM.nextInt(topRight.getX() - bottomLeft.getX() - 1) + bottomLeft.getX();
                y = bottomLeft.getY() - height;
                newRoom = new Room(width, height, new Position(x, y));
                opening = Opening.toTop(newRoom, room);
                return new RoomOpening(newRoom, opening);

            default:
                x = bottomLeft.getX() - width;
                y = RANDOM.nextInt(topRight.getY() - bottomLeft.getY() - 1) + bottomLeft.getY();
                newRoom =  new Room(width, height, new Position(x, y));
                opening = Opening.toRight(newRoom, room);
                return new RoomOpening(newRoom, opening);
        }
    }
}
