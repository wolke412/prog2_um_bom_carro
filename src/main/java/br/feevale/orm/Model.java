package br.feevale.orm;

import java.util.ArrayList;

public interface Model<T extends Entity> {

    public String getTableName();

    public ArrayList<T> selectAll();

    public boolean insertOne(T t);

    public boolean updateOne(T t);

    public boolean deleteOne(T t);

}
