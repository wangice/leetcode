package com.ice.leetcode.b_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:ice
 * @Date: 2019/3/19 0019
 */
public class BTree {

    private static final int DEFAULT_T = 2;
    public BTreeNode root;
    /* 根据B树的定义，B树的每个非根节点的关键字数n满足(t - 1) <= n <= (2t - 1) */
    private int t = DEFAULT_T;
    /* 非根节点中最小的键值数 */
    private int minKeySize;
    /* 非根节点中最大的键值数 */
    private int maxKeySize;

    public BTree() {
        root = new BTreeNode();
        root.leaf = true;
    }

    public BTree(int t) {
        this();
        this.t = t;
        minKeySize = (int) (Math.ceil(t / 2) - 1);
        maxKeySize = t - 1;
    }

    public int search(Integer key) {
        return searchKey(root, key);
    }

    public int searchKey(BTreeNode node, Integer key) {
        SearchResult searchResult = node.searchKey(key);
        if (searchResult.found) {
            return searchResult.index;
        } else {
            if (node.leaf) {
                return -1;
            } else {
                return searchKey(node.childAt(searchResult.index), key);
            }
        }
    }

    public void insert(Integer key) {
        //找到叶子节点
        Result result = searchLeftNode(root, key);
        if (result.found) {
            return;
        }
        BTreeNode leafNode = result.node;
        if (leafNode.leaf) {
            leafNode.addKey(key);//添加到子节点中
            if (isFull(leafNode)) {//叶子节点已经大于了最大数,则进行分裂
                split(leafNode.parent, leafNode);
            }
        }
    }

    //找不到则返回叶子节点，插入的数据都会先插入到叶子节点中
    public Result searchLeftNode(BTreeNode node, Integer key) {
        SearchResult searchResult = node.searchKey(key);
        if (searchResult.found) {//找到该节点
            return new Result(node, searchResult);
        }
        if (node.leaf) {
            return new Result(node, searchResult);//找到叶子节点
        }
        return searchLeftNode(node.children.get(searchResult.index), key);//向下遍历
    }

    //1. m/2节点移动到父节点中，
    private void split(BTreeNode parentNode, BTreeNode childNode) {
        int mid = childNode.size() / 2;
        Integer key = childNode.keys.remove(mid);
        //分裂出左节点形成新的节点
        List<Integer> keys = new ArrayList<>();
        for (int i = 0; i < mid; i++) {
            Integer k = childNode.keys.remove(0);
            keys.add(k);
        }
        BTreeNode node = new BTreeNode();
        node.parent = parentNode;
        node.leaf = true;
        node.keys.addAll(keys);

        if (parentNode == null) {//分裂的上级节点为空，则需要生成一个
            root = new BTreeNode();
            root.leaf = false;
            parentNode = root;
            childNode.parent = parentNode;
            node.parent = parentNode;
            parentNode.children.add(childNode);
        }

        parentNode.addKey(key);
        SearchResult searchResult = parentNode.searchKey(key);
        parentNode.addChild(node, searchResult.index);
        if (isFull(parentNode)) {//如果父级节点也满了，则继续分裂
            split(parentNode.parent, parentNode);
        }
    }

    public int delete(Integer key) {
        Result result = searchLeftNode(root, key);
        if (!result.found) {
            return -1;
        }
        BTreeNode node = result.node;
        if (node.leaf) {
            node.removeKey(key);
            if (node.size() > maxKeySize) {
                return 1;
            } else {
                //合并左节点
                union(node);
                return 1;
            }
        } else {//非叶子节点，则找后继节点替换
            BTreeNode successorNode = null;
            Integer replace = null;
            Integer index = null;
            for (int i = 0; i < node.children.size(); i++) {//找到后继节点
                BTreeNode child = node.children.get(i);
                if (child.keys.get(child.size() - 1) > key) {
                    replace = child.keys.get(0);
                    successorNode = child;
                    index = i;
                    break;
                } else {
                    continue;
                }
            }
            node.keys.add(result.index, replace);//替换
            if (successorNode.size() > minKeySize) {
                return 1;
            }
            //判断左右兄弟节点是否大于最小键子树
            BTreeNode left = node.children.get(index - 1);
            if (left.keys.size() != minKeySize) {//
                Integer leftKey = left.keys.remove(left.size() - 1);
                node.removeKey(replace);
                successorNode.addKey(replace);
                node.addKey(leftKey);
                return 1;
            }
            union(node);
            return 1;
        }
    }

    /**
     * 合并左节点
     */
    public void union(BTreeNode node) {
        //合并左节点
        Integer min = node.keys.get(0);
        BTreeNode parent = node.parent;
        //选取刚小于节点
        Integer k = null;
        Integer in = null;
        for (int i = parent.keys.size() - 1; i >= 0; i--) {
            if (min > parent.keys.get(i)) {
                in = i;
                k = parent.keys.remove(i);
                break;
            }
        }
        //找到当前k的最大值
        BTreeNode kNode = null;
        for (int i = parent.children.size() - 1; i >= 0; i--) {
            BTreeNode child = parent.children.get(i);
            if (k > child.keys.get(child.size() - 1)) {
                kNode = child;
            }
        }
        //
        kNode.addKey(k);
        for (int i = 0; i < node.size(); i++) {
            kNode.addKey(node.keys.get(i));
        }
        parent.removeChild(in + 1);//移除节点
    }

    private boolean isFull(BTreeNode node) {
        return node.size() > maxKeySize;
    }

    public void outPut() {
        outPut(root, 0);
    }

    private void outPut(BTreeNode node, int index) {
        if (node.leaf) {
            List<Integer> kes = node.keys;
            System.out.println("层级：" + index + ",keys:" + kes);
        } else {
            List<Integer> kes = node.keys;
            System.out.println("层级：" + index + ",keys:" + kes);
            for (int i = 0; i < node.children.size(); i++) {
                outPut(node.children.get(i), index + 1);
            }
        }
    }

    public static void main(String[] args) {
        BTree tree = new BTree(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(15);
        tree.insert(18);
        tree.insert(20);
        tree.insert(8);
        tree.insert(9);
        tree.insert(10);
        tree.insert(30);
        tree.insert(31);
        tree.insert(32);
        tree.insert(33);
        tree.insert(34);
        tree.insert(35);
        tree.insert(36);
        tree.insert(37);
        tree.insert(38);
        tree.outPut();
        int search = tree.search(10);
        System.out.println(search);
    }
}
