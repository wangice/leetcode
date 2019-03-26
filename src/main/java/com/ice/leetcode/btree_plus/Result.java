package com.ice.leetcode.btree_plus;

/**
 * @author ice
 * @Date 2019/3/20 09:40
 */
public class Result extends SearchResult {

    public BTreeNode node;

    public int parentIndex;

    public Result(BTreeNode node, int index, int parentIndex, boolean found) {
        super(index, found);
        this.node = node;
        this.parentIndex = parentIndex;
    }

    public Result(BTreeNode node, int parentIndex, SearchResult searchResult) {
        super(searchResult.index, searchResult.found);
        this.node = node;
        this.parentIndex = parentIndex;
    }
}
