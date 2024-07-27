import java.util.ArrayList;
import java.util.List;

public class LoadBalancer {
    private static final HashRing hashRing = new HashRing();

    public void addNode(Integer position, Node node) {
        hashRing.addNode(position, node);
    }

    public void removeNode(Integer position) {
        hashRing.removeNode(position);
    }

    public Node routeTraffic(Integer dataKey) {
        return hashRing.getNode(HashRing.hashFxn(dataKey));
    }
}

class Node {
    private final List<Data> nodeData = new ArrayList<>();

    public class Data {

        private Integer key;
        private String value;

        public Data(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    public List<Data> getNodeData() {
        return nodeData;
    }

    public void addData(Data data) {
        this.nodeData.add(data);
    }
}
