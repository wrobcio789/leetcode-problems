import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public Node cloneTree(Node root) {
        if(root == null) {
            return null;
        }
        
        return deepCopy(root);
    }

    private Node deepCopy(Node root) {
        if (root.children.size() == 0) {
            return new Node(root.val);
        }
        
        final var children = root.children.stream()
                .map(this::deepCopy)
                .collect(Collectors.toCollection(ArrayList::new));
        return new Node(root.val, children);
    }
}