package com.ice.leetcode.btree_plus;

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
        minKeySize = t / 2;
        maxKeySize = t - 1;
    }

    public Result searchLeafNode(BTreeNode node, int parentIndex, Integer key) {
        SearchResult searchResult = node.searchKey(key);
        if (node.leaf) {//子节点
            return new Result(node, parentIndex, searchResult);
        } else {
            if (searchResult.found) {
                searchResult.index++;
            }
            return searchLeafNode(node.children.get(searchResult.index), searchResult.index, key);
        }
    }


    public boolean insert(Integer key) {
        // 找到子节点
        Result result = searchLeafNode(root, 0, key);
        if (result.found) {//找到该节点,不操作
            return false;
        }
        BTreeNode node = result.node;
        node.addKey(key);
        //判断节点是否满了，满了则进行分割
        if (isFull(node)) {
            split(node.parent, result.parentIndex, node);
        }
        return true;
    }


    //进行分割
    public void split(BTreeNode parentNode, int parentIndex, BTreeNode childNode) {
        int mid = childNode.size() / 2;
        Integer key = null;
        boolean unLeaf = childNode.children.isEmpty();
        if (unLeaf) {
            key = childNode.keys.get(mid);
        } else {
            key = childNode.keys.remove(mid);
        }
        //分裂出左节点形成新的节点
        List<Integer> keys = new ArrayList<>();
        for (int i = 0; i < mid; i++) {
            Integer k = childNode.keys.remove(0);
            keys.add(k);
        }
        BTreeNode node = new BTreeNode();
        node.parent = parentNode;
        node.leaf = childNode.children.isEmpty();
        node.keys.addAll(keys);
        node.next = childNode;//节点下一个
        if (!unLeaf) {
            mid = childNode.children.size() / 2;
            for (int i = 0; i < mid; i++) {
                BTreeNode bTreeNode = childNode.children.remove(0);
                bTreeNode.parent = node;
                node.addChild(bTreeNode);
            }
        }

        if (parentNode == null) {
            root = new BTreeNode();
            root.leaf = false;
            parentNode = root;
            childNode.parent = parentNode;
            node.parent = parentNode;
            parentNode.children.add(childNode);
        }

        int index = parentNode.addKey(key);
        //前一个指针的下一个指针重新定向
        BTreeNode preNode = parentNode.children.get(parentIndex);
        preNode.next = node;
        //将节点添加到列表中
        parentNode.addChild(node, index);
        if (isFull(parentNode)) {
            split(parentNode.parent, 0, parentNode);
        }
    }


    //删除节点
    public boolean delete(Integer key) {
        //找到该节点
        Result result = searchLeafNode(root, 0, key);
        if (!result.found) {//未找到
            return false;
        }
        //删除的节点数量大于
        BTreeNode node = result.node;
        node.removeKey(key);
        if (node.keys.size() >= minKeySize) {
            if (node.parent.keys.contains(key)) {//父节点中包含该节点
                Integer min = node.keys.get(0);
                node.parent.removeKey(key);
                node.parent.addKey(min);
            }
            return true;
        }
        //删除节点后，不满足情况，则找兄弟节点借
        BTreeNode parent = node.parent;
        if (result.parentIndex != 0 && parent.children.get(result.parentIndex - 1).keys.size() > minKeySize) {//左节点有富余可以借
            BTreeNode left = parent.children.get(result.parentIndex - 1);
            Integer max = left.keys.remove(left.keys.size() - 1);
            //替换节点
            if (parent.keys.contains(key)) {
                parent.removeKey(key);
                parent.addKey(max);
                node.addKey(max);
            } else {
                Integer min = node.keys.get(0);
                parent.removeKey(min);
                parent.addKey(max);
                node.addKey(max);
            }
        } else if (result.parentIndex != parent.children.size() && parent.children.get(result.parentIndex - 1).keys.size() > minKeySize) {//右节点有富余可以借
            BTreeNode right = parent.children.get(result.parentIndex + 1);
            Integer min = right.keys.remove(0);
            //替换节点
            if (parent.keys.contains(key)) {
                parent.removeKey(key);
                parent.addKey(min);
                node.addKey(min);
            } else {
                Integer max = node.keys.get(node.keys.size() - 1);
                parent.removeKey(max);
                parent.addKey(min);
                node.addKey(min);
            }
        } else {
            //兄弟节点也没有，则进行合并
            node.parent.removeKey(key);
            node.parent.removeKey(node.keys.get(0));
            union(node, result.parentIndex);
        }
        return true;
    }

    public void union(BTreeNode node, int parentIndex) {
        //合并左节点
        int ch = 0;
        if (parentIndex == 0) {
            ch = 1;
        } else {
            ch = parentIndex - 1;
        }
        BTreeNode parent = node.parent;
        if (parent == null) {
            return;
        }
        //找到当前k的最大值
        BTreeNode kNode = parent.children.get(ch);
        for (int i = 0; i < node.size(); i++) {
            kNode.addKey(node.keys.get(i));
        }
        parent.removeChild(parentIndex);//移除节点
    }


    public void union(BTreeNode node) {

    }

    private boolean isFull(BTreeNode node) {
        return node.size() > maxKeySize;
    }


    private void outPut(BTreeNode node, int index) {
        if (node.leaf) {
            List<Integer> kes = node.keys;
            System.out.println("叶子节点，层级：" + index + ",keys:" + kes);
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
        tree.insert(5);
        tree.insert(8);
        tree.insert(10);
        tree.insert(15);
        tree.insert(16);
        tree.insert(17);
        tree.insert(6);
        tree.insert(9);
        tree.insert(18);
        tree.insert(19);
        tree.insert(20);
        tree.insert(21);
        tree.insert(22);
        tree.insert(7);
        tree.outPut(tree.root, 0);

        System.out.println("---------------------------------------------");

        tree.delete(22);
        tree.delete(15);
        tree.delete(7);
        tree.outPut(tree.root, 0);
        System.out.println("---------------------------------------------");
    }

}
