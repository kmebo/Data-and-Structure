public class NodeF1 {
    public String data;
    public NodeF1 left;
    public NodeF1 right;

    public NodeF1() {
        this.data = "";
        this.left = null;
        this.right = null;
    }
    public NodeF1(String data, NodeF1 left, NodeF1 right){
        this.data = data;
        this.left = left;
        this.right = right;
    }
}