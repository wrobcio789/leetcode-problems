import java.util.ArrayList;
import java.util.List;

class Solution {
    public int diameter(Node root) {
        final var handler = new SolutionHandler();
        handler.process(root);
        return handler.getResult();
    }

    static class SolutionHandler {
        int result = 0;

        public int process(Node root) {

            var maxDepth = 0;
            var secondMaxDepth = 0;

            for(final var child : root.children) {
                final var depth = process(child);

                if (depth > maxDepth) {
                    secondMaxDepth = maxDepth;
                    maxDepth = depth;
                } else if (depth > secondMaxDepth) {
                    secondMaxDepth = depth;
                }
            }

            result = Math.max(result, maxDepth + secondMaxDepth);

            return maxDepth + 1;
        }

        public int getResult() {
            return result;
        }
    }
}