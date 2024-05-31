package br.feevale.models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import br.feevale.entities.Country;
import br.feevale.orm.Model;

public class CountriesModel implements Model<Country>{

    Connection conn;

    public CountriesModel(Connection conn) {
        this.conn = conn;
    }

    @Override
    public String getTableName() {
        return "contries";
    }

    @Override
    public boolean updateOne(Country t) {
        
        return false;
    }

    @Override
    public ArrayList<Country> selectAll() {
        // Statement
        try {
            PreparedStatement pr = this.conn.prepareStatement("SELECT * from " + this.getTableName());
            ResultSet r = pr.executeQuery();

            ArrayList<Country> res= new ArrayList<>();
            while (r.next()) {
                res.add(
                    new Country(r.getInt(0), r.getString(1), r.getString(2))
                );
            }

            return res;
        } 

        catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean insertOne(Country t) {
        
        try {
            PreparedStatement s = this.conn.prepareStatement("INSERT into " + this.getTableName()+" (name, sigla) values(?, ?)");
            
            s.setString(0, t.getName());
            s.setString(1, t.getSigla());

            int affectedRows = s.executeUpdate();

            return affectedRows > 0;
        } 
        catch( Exception e ) {
            return false;
        }
    }

    @Override
    public boolean deleteOne(Country t) {
        // TODO Auto-generated method stub
        return false;
    }

}
