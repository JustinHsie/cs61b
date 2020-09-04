package byow.WorldGenerator;

import byow.Core.Engine;
import byow.TileEngine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWorld {
    private static final int WIDTH = Engine.WIDTH;
    private static final int HEIGHT = Engine.HEIGHT;

    public static final long SEED = 123;
    public static final Random RANDOM = new Random(SEED);

    /**
     * Generates world given tiles
     * @param tiles
     */
    public static void generateWorld(TETile[][] tiles) {
        Draw.initializeTiles(tiles);
        List<Room> rooms = new ArrayList<>();

        Room room = new Room(5, 6, new Position(50, 20));
        rooms.add(room);
        Draw.drawRoom(room, tiles);
        System.out.println(
                "(" + room.getBottomLeftCorner().getX() + ", " + room.getBottomLeftCorner().getY());
        Room room2 = generateRoom(tiles, room, rooms);
        generateRoom(tiles, room, rooms);
        generateRoom(tiles, room, rooms);
        generateRoom(tiles, room, rooms);

        generateRoom(tiles, room2, rooms);
        Room hm = generateRoom(tiles, room2, rooms);
        Room room3 = generateRoom(tiles, room2, rooms);

        Room room4 = generateRoom(tiles, room3, rooms);
        generateRoom(tiles, room3, rooms);

        generateRoom(tiles, hm, rooms);
        generateRoom(tiles, hm, rooms);
        generateRoom(tiles, hm, rooms);


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

        do {
            // Min width and height of 3 tiles
            newWidth = RANDOM.nextInt(10 - 3) + 3;
            newHeight = RANDOM.nextInt(10 - 3) + 3;
            roomOpening = randomNeighbor(room, newWidth, newHeight);
            newRoom = roomOpening.room;
            opening = roomOpening.opening;
        }
        while (Catch.outOfBounds(newRoom) || Catch.overlaps(newRoom, rooms));

        rooms.add(newRoom);
        Draw.drawRoom(newRoom, tiles);
        Draw.drawOpening(opening, tiles);
        System.out.println(
                "(" + newRoom.getBottomLeftCorner().getX() + ", " +
                        newRoom.getBottomLeftCorner().getY() + ")");

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

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        generateWorld(world);

        ter.renderFrame(world);
    }
}
