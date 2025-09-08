import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

class Solution {

    private static final List<Point> MOVES = List.of(
            new Point(1, -2),
            new Point(2, -1),
            new Point(2, 1),
            new Point(1, 2),
            new Point(-1, 2),
            new Point(-2, 1),
            new Point(-2, -1),
            new Point(-1, -2)
    );

    public int minKnightMoves(int targetX, int targetY) {
        targetX = Math.abs(targetX);
        targetY = Math.abs(targetY);

        final var queue = new LinkedList<Vertex>();
        queue.add(new Vertex(new Point(0, 0), 0));

        final var visited = new HashSet<Point>();

        while (!queue.isEmpty()) {
            final var vertex = queue.poll();
            final var point = vertex.point();
            final var distance = vertex.distance();

            if (visited.contains(point)) {
                continue;
            }

            final var x = point.x();
            final var y = point.y();

            if (x == targetX && y == targetY) {
                return distance;
            }

            final var nextDistance = distance + 1;

            for (final var move : MOVES) {
                final var nextPoint = new Point(y + move.y(), x + move.x());

                if (nextPoint.x() <= -4 || nextPoint.y <= -4) {
                    continue;
                }
                queue.add(new Vertex(nextPoint, nextDistance));
            }

            visited.add(point);
        }

        return -1;
    }

    private record Vertex(Point point, int distance) {
    }

    private record Point(int y, int x) {
    }
}