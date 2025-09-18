class Codec {
    private static final char STOP = 10_000 + 2;

    public String serialize(Node root) {
        if (root == null) {
            return "";
        }

        final var builder = new StringBuilder();
        serialize(root, builder);
        return builder.toString();
    }

    private void serialize(Node root, StringBuilder builder) {
        final var value = root.val;
        builder.append((char) value);

        for (final var child : root.children) {
            serialize(child, builder);
        }
        builder.append(STOP);
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }

        return deserialize(data, 0).node();
    }

    private DeserializationResult deserialize(String data, int index) {
        final var value = data.charAt(index);
        final var node = new Node(value, new ArrayList<>());

        int operatingIndex = index + 1;
        while (data.charAt(operatingIndex) != STOP) {
            final var result = deserialize(data, operatingIndex);
            operatingIndex = result.index() + 1;
            node.children.add(result.node());
        }

        return new DeserializationResult(node, operatingIndex);

    }

    private record DeserializationResult(Node node, int index) {
    }
}
