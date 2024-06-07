package br.feevale.views.components.form;

/**
 * Form
 */
public interface IForm<T> {

    public void setValues(T t);
    public T getValues();

    public void clear();
}