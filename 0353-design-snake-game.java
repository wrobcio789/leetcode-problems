import java.util.*;

class SnakeGame {

    private final List<Position> snake = new ArrayList<>(Collections.singleton(new Position(0, 0)));
    private final int width;
    private final int height;
    private final int[][] food;
    int foodIndex = 0;

    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
    }

    public int move(String direction) {
        final var head = snake.get(snake.size() - 1);
        final var nextPos = head.next(direction);

        if (nextPos.row < 0 || nextPos.col < 0 || nextPos.row >= height || nextPos.col >= width) {
            return -1;
        }

        for (int i = 1; i < snake.size() - 1; i++) {
            if (Objects.equals(snake.get(i), nextPos)) {
                return -1;
            }
        }

        if (foodIndex < food.length && (nextPos.row == food[foodIndex][0] && nextPos.col == food[foodIndex][1])) {
            foodIndex++;
            snake.add(nextPos);
        } else {
            for (int i = 0; i < snake.size() - 1; i++) {
                snake.set(i, snake.get(i + 1));
            }
            snake.set(snake.size() - 1, nextPos);
        }

        return snake.size() - 1;
    }

    private record Position(int row, int col) {

        private Position next(String dir) {
            return switch (dir) {
                case "R" -> new Position(row, col + 1);
                case "L" -> new Position(row, col - 1);
                case "U" -> new Position(row - 1, col);
                case "D" -> new Position(row + 1, col);
                default -> throw new IllegalStateException("Unexpected value: " + dir);
            };
        }
    }
}