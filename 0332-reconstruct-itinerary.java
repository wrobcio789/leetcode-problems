import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

class Solution {
    private static final String START = "JFK";

    public List<String> findItinerary(List<List<String>> tickets) {
        final var graph = tickets.stream()
                .collect(Collectors.groupingBy(
                        ticket -> ticket.get(0),
                        Collectors.mapping(ticket -> ticket.get(1), Collectors.collectingAndThen(
                                Collectors.toList(),
                                neighbours -> neighbours.stream().sorted().collect(Collectors.toCollection(LinkedList::new))
                        ))));

        final var resultStack = new Stack<String>();

        final var stack = new Stack<String>();
        stack.push(START);
        while (!stack.isEmpty()) {
            final var v = stack.peek();

            final var neighbours = graph.getOrDefault(v, new LinkedList<>());
            if (neighbours.isEmpty()) {
                resultStack.push(v);
                stack.pop();
                continue;
            }

            final var neighbour = neighbours.poll();
            stack.push(neighbour);
        }

        return toResult(resultStack);
    }

    private List<String> toResult(Stack<String> resultStack) {
        final var reversedOrder = new ArrayList<>(resultStack);
        Collections.reverse(reversedOrder);
        return reversedOrder;
    }
}