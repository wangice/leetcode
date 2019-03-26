package com.ice.leetcode.b_tree;

/**
 * @author ice
 * @Date 2019/3/20 09:40
 */
public class Result extends SearchResult {

    public BTreeNode node;

    public Result(BTreeNode node, int index, boolean found) {
        super(index, found);
        this.node = node;
    }

    public Result(BTreeNode node, SearchResult searchResult) {
        super(searchResult.index, searchResult.found);
        this.node = node;
    }
}
