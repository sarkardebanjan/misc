package open.generic.code.ds.trees.binarytree;

public class BinaryTreeImpl {

    public enum TRAVERSE_ORDER { PREORDER, INORDER, POSTORDER; }

    private void insertNodeIntoTree(BinaryTreeNode currentNode, BinaryTreeNode node) {
        if (currentNode.getValue() > node.getValue()) {
            if (null == currentNode.getLeftChild())
                currentNode.setLeftChild(node);
            else
                insertNodeIntoTree(currentNode.getLeftChild(), node);
        } else { //currentNode.getValue() <= node.getValue()
            if (null == currentNode.getRightChild())
                currentNode.setRightChild(node);
            else
                insertNodeIntoTree(currentNode.getRightChild(), node);
        }
    }

    private boolean isLeafNode(BinaryTreeNode node) {
        return null == node.getLeftChild() && null == node.getRightChild();
    }

    private StringBuilder traverseTree(BinaryTreeNode currentNode, final TRAVERSE_ORDER traverseOrder, StringBuilder traversalResult) {
        traversalResult.append(currentNode.getValue()).append(", ");
        if (TRAVERSE_ORDER.PREORDER == traverseOrder) {
            if (null != currentNode.getLeftChild())
                traverseTree(currentNode.getLeftChild(), traverseOrder, traversalResult);
            if (null != currentNode.getRightChild())
                traverseTree(currentNode.getRightChild(), traverseOrder, traversalResult);
        } else if (TRAVERSE_ORDER.INORDER == traverseOrder) {
            if (null != currentNode.getRightChild())
                traverseTree(currentNode.getRightChild(), traverseOrder, traversalResult);
            if (null != currentNode.getLeftChild())
                traverseTree(currentNode.getLeftChild(), traverseOrder, traversalResult);
        } else if (TRAVERSE_ORDER.POSTORDER == traverseOrder) {
        }
        return traversalResult;
    }

    private void doWork() {
        //Integer[] input = {25, 10, 11, 99, 12, 44, 7, 22, 14, 150};
        //Integer[] input = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100}; //sorted input
        Integer[] input = { 7, 1, 9, 0, 3, 8, 10, 2, 5, 4, 6 };
        /*
        Expected Pre-order traversal: 7, 1, 0, 3, 2, 5, 4, 6, 9, 8, 10
        Expected In-order traversal: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        Expected Post-order traversal: 0, 2, 4, 6, 5, 3, 1, 8, 10, 9, 7
        */

        BinaryTreeNode rootNode = new BinaryTreeNode(input[0]);

        for (int i = 1; i < input.length; i++) {
            Integer value = input[i];
            BinaryTreeNode node = new BinaryTreeNode(value);
            insertNodeIntoTree(rootNode, node);
        }

        StringBuilder preOrderTraversalResult = traverseTree(rootNode, TRAVERSE_ORDER.PREORDER, new StringBuilder());
        StringBuilder inOrderTraversalResult = traverseTree(rootNode, TRAVERSE_ORDER.INORDER, new StringBuilder());
        StringBuilder postOrderTraversalResult = traverseTree(rootNode, TRAVERSE_ORDER.POSTORDER, new StringBuilder());

        System.out.println(preOrderTraversalResult.toString());
        System.out.println(inOrderTraversalResult.toString());
    }

    public static void main(String[] args) {
        new BinaryTreeImpl().doWork();
    }
}