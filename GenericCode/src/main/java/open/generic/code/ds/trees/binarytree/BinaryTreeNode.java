package open.generic.code.ds.trees.binarytree;

public class BinaryTreeNode {

    private Integer value;

    private BinaryTreeNode parent;

    private BinaryTreeNode leftChild;

    private BinaryTreeNode rightChild;

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(Integer value) {
        this.value = value;
    }

    public BinaryTreeNode(Integer value, BinaryTreeNode parent, BinaryTreeNode leftChild, BinaryTreeNode rightChild) {
        this.value = value;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public BinaryTreeNode(BinaryTreeNode node) {
        this.value = node.getValue();
        this.parent = node.getParent();
        this.leftChild = node.getLeftChild();
        this.rightChild = node.getRightChild();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public BinaryTreeNode getParent() {
        return parent;
    }

    public void setParent(BinaryTreeNode parent) {
        this.parent = parent;
    }

    public BinaryTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return "\n\n"+
                "BinaryTreeNode{" +
                "value=" + value +
                ", parent=" + parent +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }
}
