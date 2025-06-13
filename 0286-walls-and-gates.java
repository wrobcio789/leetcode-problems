import java.util.LinkedList;

class Solution {
    public void wallsAndGates(int[][] rooms) {
        final var rows = rooms.length;
        final var cols = rooms[0].length;


        final var queue = new LinkedList<Pos>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(rooms[i][j] == 0) {
                    queue.add(new Pos(i, j, 0));
                } else if (rooms[i][j] != -1) {
                    rooms[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        while(!queue.isEmpty()) {
            final var pos = queue.poll();
            final var row = pos.row;
            final var col = pos.col;
            final var distance = pos.distance;

            if(row < 0 || row >= rows || col < 0 || col >= cols || rooms[row][col] == -1) {
                continue;
            }

            if(distance == 0 || rooms[row][col] > distance) {
                rooms[row][col] = distance;

                final var nextDistance = distance + 1;
                queue.add(new Pos(row - 1, col, nextDistance));
                queue.add(new Pos(row + 1, col, nextDistance));
                queue.add(new Pos(row, col - 1, nextDistance));
                queue.add(new Pos(row, col + 1, nextDistance));
            }
        }

    }

    private record Pos(int row, int col, int distance) {}
}