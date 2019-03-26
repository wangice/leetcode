package com.ice.leetcode.b_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author:ice
 * @Date: 2019/3/19 0019
 */
public class BTreeNode {

    public BTreeNode parent;

    /*以升序方式存储.*/
    public List<Integer> keys;
    /*子节点*/
    public List<BTreeNode> children;

    public boolean leaf;


    public BTreeNode() {
        keys = new ArrayList<>();
        children = new ArrayList<>();
        leaf = false;
    }

    /*返回关键字个数*/
    public int size() {
        return keys.size();
    }

    public SearchResult searchKey(Integer key) {
        int index = Collections.binarySearch(keys, key);
        if (index >= 0) {
            return new SearchResult(index, true);
        } else {
            return new SearchResult(Math.abs(index + 1), false);
        }
    }

    public void addKey(Integer key) {
        SearchResult searchResult = searchKey(key);
        if (!searchResult.found) {
            List<Integer> list = new ArrayList<>(size() + 1);
            for (int i = 0; i < searchResult.index; i++) {
                list.add(keys.get(i));
            }
            list.add(key);
            for (int i = searchResult.index; i < keys.size(); i++) {
                list.add(keys.get(i));
            }
            keys = list;
        }
    }

    public void removeKey(Integer key) {
        keys.remove(key);
    }

    public BTreeNode childAt(int index) {
        if (leaf) {
            throw new UnsupportedOperationException("Leaf node doesn't have children.");
        } else {
            return children.get(index);
        }
    }


    public void addChild(BTreeNode node) {
        children.add(node);
    }

    public void removeChild(int index) {
        children.remove(index);
    }

    public void removeChild(BTreeNode child) {
        children.remove(child);
    }

    public void addChild(BTreeNode child, int index) {
        List<BTreeNode> newChildren = new ArrayList<>();
        int i = 0;
        for (; i < index; ++i) {
            newChildren.add(children.get(i));
        }
        newChildren.add(child);
        for (; i < children.size(); ++i) {
            newChildren.add(children.get(i));
        }
        children = newChildren;
    }

}
