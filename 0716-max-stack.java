import java.util.Comparator;
import java.util.TreeSet;

class MaxStack {

    private int id = 0;
    private final TreeSet<Item> maxStack = new TreeSet<>(Comparator.comparing(Item::value).thenComparing(Item::id).reversed());
    private final TreeSet<Item> recentStack = new TreeSet<>(Comparator.comparing(Item::id).thenComparing(Item::value).reversed());


    public void push(int x) {
        final var item = new Item(x, id++);
        maxStack.add(item);
        recentStack.add(item);
    }

    public int pop() {
        final var item = recentStack.first();
        recentStack.remove(item);
        maxStack.remove(item);
        return item.value();
    }

    public int top() {
        return recentStack.first().value();
    }

    public int peekMax() {
        return maxStack.first().value();
    }

    public int popMax() {
        final var item = maxStack.first();
        maxStack.remove(item);
        recentStack.remove(item);
        return item.value();
    }

    private record Item(int value, int id) {
    }
}