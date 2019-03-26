package com.ice.leetcode.erTree;

/**
 * @author ice
 * @Date 2019/3/15 18:05
 */
public class TreeM {

    public Node root;


    public void insertNode(int index, String data) {
        Node node = new Node();
        node.index = index;
        node.data = data;

        if (root == null) {
            root = node;
            return;
        }
        Node currentNode = root;
        Node parent;
        while (true) {
            parent = currentNode;
            if (index == currentNode.index) {
                return;
            }
            if (index < currentNode.index) {
                currentNode = currentNode.leftNode;
                if (currentNode == null) {
                    parent.leftNode = node;
                    return;
                }
            } else {
                currentNode = currentNode.rightNode;
                if (currentNode == null) {
                    parent.rightNode = node;
                    return;
                }
            }
        }
    }

    public Node find(int key) {
        Node current = root;
        while (current.index != key) {
            if (key < current.leftNode.index) {
                current = current.leftNode;
            } else {
                current = current.rightNode;
            }
            if (current == null) {
                break;
            }
        }
        return current;
    }


    public boolean delete(int key) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;
        while (current.index != key) {
            parent = current;
            if (key < current.index) {
                isLeftChild = true;
                current = current.leftNode;
            } else {
                isLeftChild = false;
                current = current.rightNode;
            }
            if (current == null) {
                return false;
            }
        }
        //第一种情况，删除节点为子节点
        if (current.leftNode == null && current.rightNode == null) {
            if (current == root) {
                root = null;
            } else {
                if (isLeftChild) {
                    parent.leftNode = null;
                } else {
                    parent.rightNode = null;
                }
            }

        } else if ((current.leftNode != null && current.rightNode == null) || (current.leftNode == null && current.rightNode != null)) {
            //第二中情况，删除节点只包含一个子节点，则将子节点移动动当前节点中
            if (current.rightNode == null) {
                if (root == current) {
                    root = current.leftNode;
                } else {
                    if (isLeftChild) {
                        parent.leftNode = current.leftNode;
                    } else {
                        parent.rightNode = current.leftNode;
                    }
                }
            } else {
                if (root == current) {
                    root = current.rightNode;
                } else {
                    if (isLeftChild) {
                        parent.leftNode = current.rightNode;
                    } else {
                        parent.rightNode = current.rightNode;
                    }
                }
            }
        } else if (current.leftNode != null && current.rightNode != null) {
            //第三种情况，删除节点中有左右两个节点
            //找到后继节点
            Node processer = processer(current);
            if (current == root) {
                root = processer;
            } else {
                if (isLeftChild) {
                    parent.leftNode = processer;
                } else {
                    parent.rightNode = processer;
                }
            }
            //选中的节点的左节点与删除节点的左节点相连
            processer.leftNode = current.leftNode;
        }
        return true;
    }

    //找到后继节点
    private Node processer(Node delNode) {
        Node parent = delNode;
        Node success = delNode;
        Node current = delNode.rightNode;
        while (current != null) {
            parent = current;
            success = current;
            current = current.leftNode;
        }
        if (success != delNode.rightNode) {
            parent.leftNode = success.rightNode;
            success.rightNode = delNode.rightNode;
        }
        return success;
    }

    public void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftNode);
            System.out.println("索引：" + localRoot.index + ",值：" + localRoot.data);
            inOrder(localRoot.rightNode);
        }
    }

    public static void main(String[] args) {
        TreeM treeM = new TreeM();
        treeM.insertNode(2, "value:2");
        treeM.insertNode(3, "value:3");
        treeM.insertNode(10, "value:10");
        treeM.insertNode(11, "value:11");
        treeM.delete(3);
        treeM.inOrder(treeM.root);
    }
}
