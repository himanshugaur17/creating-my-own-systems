import java.util.ArrayList;
import java.util.List;

public class LoadBalancer {
    public void addNode(Node node) {

    }

    public void removeNode(Integer nodeId) {

    }

    public Node routeTraffic() {
        return null;
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
