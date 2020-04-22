package com.tohsoft.airquality.forecast;

public class Pair<L, R> {
    private final L left;
    private final R right;

    public Pair(L left2, R right2) {
        this.left = left2;
        this.right = right2;
    }

    public L getLeft() {
        return this.left;
    }

    public R getRight() {
        return this.right;
    }

    public int hashCode() {
        return this.left.hashCode() ^ this.right.hashCode();
    }

    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair pairo = (Pair) o;
        if (!this.left.equals(pairo.getLeft()) || !this.right.equals(pairo.getRight())) {
            return false;
        }
        return true;
    }
}