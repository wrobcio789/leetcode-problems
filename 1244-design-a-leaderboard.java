import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Leaderboard {
    private final Map<Integer, Integer> playerToScoreMap = new HashMap<>();
    private final Map<Integer, Integer> scoreCountMap = new TreeMap<>(Comparator.reverseOrder());

    public Leaderboard() {

    }

    public void addScore(int playerId, int score) {
        if (!playerToScoreMap.containsKey(playerId)) {
            playerToScoreMap.put(playerId, score);
            scoreCountMap.merge(score, 1, Integer::sum);
        } else {
            final var oldScore = playerToScoreMap.get(playerId);
            final var newScore = oldScore + score;
            playerToScoreMap.put(playerId, newScore);
            scoreCountMap.merge(oldScore, -1, Integer::sum);
            scoreCountMap.merge(newScore, 1, Integer::sum);
        }
    }

    public int top(int K) {
        int sum = 0;
        for (final var entry : scoreCountMap.entrySet()) {
            int take = Math.min(K, entry.getValue());
            K -= take;

            sum += take * entry.getKey();
            if (K == 0) {
                break;
            }
        }

        return sum;
    }

    public void reset(int playerId) {
        final var oldScore = playerToScoreMap.get(playerId);
        playerToScoreMap.remove(playerId);
        scoreCountMap.merge(oldScore, -1, Integer::sum);
    }
}
