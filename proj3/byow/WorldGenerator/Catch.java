package byow.WorldGenerator;

import java.util.List;

public class Overlap {
    public static boolean overlaps(Room current, List<Room> rooms) {

        for (Room room: rooms) {
            if (between(current.getBottomLeftCorner(), room.getBottomLeftCorner(),
                    room.getTopRightCorner()) ||
                between(current.getTopRightCorner(), room.getBottomLeftCorner(),
                    room.getTopRightCorner())) {
                return true;
            }
        }
        return false;
    }

    private static boolean between(Position p,
                                          Position bottomLeftCorner, Position topRightCorner) {
        if (bottomLeftCorner.getX() <= p.getX() && p.getX() <= topRightCorner.getX() &&
                bottomLeftCorner.getY() <= p.getY() && p.getY() <= topRightCorner.getY()) {
            return true;
        }
        return false;
    }
}
