package br.feevale.views.components.form;

public class Pair<K, T> {
    public K left;
    public T right;

    public Pair(K l, T r) {
        this.left = l;
        this.right = r;
    }

    public K getL() {
        return this.left;
    }

    public T getR() {
        return this.right;
    }
}
