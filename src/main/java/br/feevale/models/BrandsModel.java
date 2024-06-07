package br.feevale.models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.feevale.entities.Brand;
import br.feevale.orm.Model;

public class BrandsModel implements Model<Brand>{

    Connection conn;

    public BrandsModel(Connection conn) {
        this.conn = conn;
    }

    @Override
    public String getTableName() {
        return "brands";
    }

    @Override
    public boolean updateOne(Brand t) {
        
        try {
            PreparedStatement s = this.conn.prepareStatement(
                "UPDATE " + this.getTableName()+" set name = ?, country = ? WHERE id  = ?"
            );
            
            s.setString(1, t.getName());
            s.setString(2, t.getCountry());
            s.setInt(3, t.getId());

            int affectedRows = s.executeUpdate();

            return affectedRows > 0;
        } 
        catch( Exception e ) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public ArrayList<Brand> selectAll() {
        
        // Statement
        try {
            PreparedStatement pr = this.conn.prepareStatement(
                "SELECT id, name, country from " + this.getTableName() + 
                " ORDER BY ID DESC, name ASC"
            );

            ResultSet r = pr.executeQuery();

            ArrayList<Brand> res = new ArrayList<>();

            while (r.next()) {
                res.add(
                    new Brand(r.getInt(1), r.getString(2), r.getString(3))
                );
            }

            return res;
        } 

        catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    @Override
    public boolean insertOne(Brand t) {
        
        try {
            PreparedStatement s = this.conn.prepareStatement(
                "INSERT into " + this.getTableName()+" (name, country) values(?, ?)");
            
            s.setString(1, t.getName());
            s.setString(2, t.getCountry());

            int affectedRows = s.executeUpdate();

            return affectedRows > 0;
        }

        catch( Exception e ) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteOne(Brand t) {
        // Statement
        try {
            PreparedStatement pr = this.conn.prepareStatement(
                "DELETE from " + this.getTableName() + " WHERE id = ?"
            );

            pr.setInt(1, t.getId());

            int modified = pr.executeUpdate();

            return modified > 0;
        } 

        catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

}
