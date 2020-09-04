package byow.WorldGenerator;

import byow.Core.Engine;
import java.util.List;

public class Catch {
    private static final int WIDTH = Engine.WIDTH;
    private static final int HEIGHT = Engine.HEIGHT;

    public static boolean overlaps(Room test, List<Room> rooms) {
        for (Room room: rooms) {
            if (overlaps(test, room)) {
                return true;
            }
        }
        return false;
    }

    private static boolean overlaps(Room room1, Room room2) {
        if (between(room1, room2) || between(room2, room1)) {
            return true;
        }
        return false;
    }

    private static boolean between(Room room1, Room room2) {
        if (betweenX(room1, room2) && betweenY(room1, room2)) {
            return true;
        }
        return false;
    }

    private static boolean betweenX(Room room1, Room room2) {
        if (room2.getBottomLeftCorner().getX() <= room1.getTopRightCorner().getX() &&
                room1.getTopRightCorner().getX() <= room2.getTopRightCorner().getX()) {
            return true;
        } else if (room2.getBottomLeftCorner().getX() <= room1.getBottomLeftCorner().getX() &&
                room1.getBottomLeftCorner().getX() <= room2.getTopRightCorner().getX()) {
            return true;
        }
        return false;
    }

    private static boolean betweenY(Room room1, Room room2) {
        if (room2.getBottomLeftCorner().getY() <= room1.getTopRightCorner().getY() &&
                room1.getTopRightCorner().getY() <= room2.getTopRightCorner().getY()) {
            return true;
        } else if (room2.getBottomLeftCorner().getY() <= room1.getBottomLeftCorner().getY() &&
                room1.getBottomLeftCorner().getY() <= room2.getTopRightCorner().getY()) {
            return true;
        }
        return false;
    }

    /**
     * Checks if room is out of bounds of grid
     * @param room
     * @return
     */
    public static boolean outOfBounds(Room room) {
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
}
