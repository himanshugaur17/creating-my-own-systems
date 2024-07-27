import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HashRing {
    public static final Integer MAX_NODE_POSITION_AVAILABLE = 100;
    private final TreeMap<Integer, Node> nodePositionTreeMap = new TreeMap<>();

    public void addNode(Integer position, Node node) {
        if (position > MAX_NODE_POSITION_AVAILABLE)
            System.out.println("position must be less than max");
        if (nodePositionTreeMap.isEmpty()) {
            nodePositionTreeMap.put(position, node);
            return;
        }
        Node leftNode = nodePositionTreeMap.floorEntry(position).getValue();
        nodePositionTreeMap.put(position, node);
        Predicate<Node.Data> addNode = (nodeData) -> hashFxn(nodeData.getKey()) <= position;
        copyKeys(leftNode, node, addNode);
    }

    public Node removeNode(Integer position) {
        Node node = nodePositionTreeMap.remove(position);
        if (nodePositionTreeMap.isEmpty()) {
            System.out.println("no node is alive");
            return null;
        }
        Entry<Integer, Node> rightNodeEntry = nodePositionTreeMap.ceilingEntry(position);
        Node toNode = rightNodeEntry == null ? nodePositionTreeMap.firstEntry().getValue() : rightNodeEntry.getValue();
        Predicate<Node.Data> removeNode = nodeData -> true;
        copyKeys(node, toNode, removeNode);
        return node;
    }

    public Node getNode(Integer hashValue) {
        Entry<Integer, Node> nodeEntry = nodePositionTreeMap.floorEntry(hashValue);

        return nodeEntry == null ? nodePositionTreeMap.firstEntry().getValue() : nodeEntry.getValue();
    }

    public <T> void copyKeys(Node fromNode, Node toNode, Predicate<? super Node.Data> p) {
        List<Node.Data> fromNodeDataList = fromNode.getNodeData();
        List<Node.Data> dataToBeMoved = fromNodeDataList.stream()
                .filter(p).collect(Collectors.toList());
        fromNodeDataList.removeAll(dataToBeMoved);
        toNode.getNodeData().addAll(dataToBeMoved);
    }

    public static Integer hashFxn(Node.Data nodeData) {
        return 1;
    }
}
