import java.util.*;

class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        final var graph = buildGraph(pid, ppid);
        final var processesToKill = new ArrayList<Integer>();
        dfs(graph, kill, processesToKill);

        return processesToKill;
    }

    private void dfs(Map<Integer, List<Integer>> graph, int pid, List<Integer> processes) {
        processes.add(pid);
        for(final var childProcess : graph.get(pid)) {
            dfs(graph, childProcess, processes);
        }
    }

    private Map<Integer, List<Integer>> buildGraph(List<Integer> pid, List<Integer> ppid) {
        final var n = pid.size();

        final Map<Integer, List<Integer>> graph = new HashMap<>(n);
        for (final var id : pid) {
            graph.put(id, new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            final var parent = ppid.get(i);
            final var process = pid.get(i);
            if (parent != 0) {
                graph.get(parent).add(process);
            }
        }

        return graph;
    }
}