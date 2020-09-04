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
        // OR statement in case room1's corners don't overlap with room2's
        // ie room2's corners are both within room1's
        if (cornersOverlap(room1, room2) || cornersOverlap(room2, room1)) {
            return true;
        }
        if (bodiesOverlap(room1, room2) || bodiesOverlap(room2, room1)) {
            return true;
        }
        return false;
    }

    // If corners don't overlap but bodies do
    private static boolean bodiesOverlap(Room room1, Room room2) {
        if (room2.getLeftX() <= room1.getLeftX() && room1.getRightX() <= room2.getRightX() &&
        room1.getBottomY() <= room2.getBottomY() && room2.getTopY() <= room1.getTopY()) {
            return true;
        }
        return false;
    }

    // If room1's corners are within room2 boundaries
    private static boolean cornersOverlap(Room room1, Room room2) {
        if (betweenX(room1, room2) && betweenY(room1, room2)) {
            return true;
        }
        return false;
    }

    // If room1's left or right X is between room2's Xs
    private static boolean betweenX(Room room1, Room room2) {
        if (room2.getLeftX() <= room1.getRightX() &&
                room1.getRightX() <= room2.getRightX()) {
            return true;
        } else if (room2.getLeftX() <= room1.getLeftX() &&
                room1.getLeftX() <= room2.getRightX()) {
            return true;
        }
        return false;
    }

    // If room1's top or bottom Y is between room2's Ys
    private static boolean betweenY(Room room1, Room room2) {
        if (room2.getBottomY() <= room1.getTopY() &&
                room1.getTopY() <= room2.getTopY()) {
            return true;
        } else if (room2.getBottomY() <= room1.getBottomY() &&
                room1.getBottomY() <= room2.getTopY()) {
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
