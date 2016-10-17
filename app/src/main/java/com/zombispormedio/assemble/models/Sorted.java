package com.zombispormedio.assemble.models;

/**
 * Created by Xavier Serrano on 17/10/2016.
 */

public interface Sorted<T> extends Comparable<T> {

    boolean areTheSame(T o);

}
