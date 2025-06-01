import java.util.Stack;

class Solution {

    public int[] asteroidCollision(int[] asteroids) {
        final Stack<Integer> movingAsteroids = new Stack<>();
        for (final var asteroid : asteroids) {
            if (asteroid < 0) {
                final var danger = -asteroid;
                while (!movingAsteroids.empty() && movingAsteroids.peek() > 0 && movingAsteroids.peek() < danger) {
                    movingAsteroids.pop();
                }

                if (movingAsteroids.empty() || movingAsteroids.peek() < 0) {
                    movingAsteroids.push(asteroid);
                } else if (movingAsteroids.peek() == danger) {
                    movingAsteroids.pop();
                }

            } else {
                movingAsteroids.push(asteroid);
            }
        }

        return movingAsteroids.stream().mapToInt(e -> e).toArray();
    }
}