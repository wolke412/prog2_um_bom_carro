package br.feevale.views.components.form;

/**
 * FieldInterface
 */
public interface FieldInterface<T> {
    T getValue();
    void setValue(T value);
}