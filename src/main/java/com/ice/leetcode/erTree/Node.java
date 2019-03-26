package com.ice.leetcode.erTree;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author ice
 * @Date 2019/3/15 18:03
 */
public class Node {
    public int index;
    public String data;
    public Node leftNode;
    public Node rightNode;

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
