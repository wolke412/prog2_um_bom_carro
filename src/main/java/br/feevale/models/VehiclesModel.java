package br.feevale.models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.feevale.entities.Vehicle;
import br.feevale.orm.Model;

public class VehiclesModel implements Model<Vehicle>{

    Connection conn;

    public VehiclesModel(Connection conn) {
        this.conn = conn;
    }

    @Override
    public String getTableName() {
        return "vehicles";
    }

    @Override
    public boolean updateOne(Vehicle t) {
        
        try {
            PreparedStatement s = this.conn.prepareStatement(
                "UPDATE " + this.getTableName() + " " +
                "SET name=?, year=?, km=?, value_in_cents=?, id_brand=? "+ 
                "WHERE id = ?"
            );
            
            s.setString(1, t.getName());
            s.setInt(2, t.getYear());
            s.setInt(3, t.getKm());
            s.setInt(4, t.getValue_in_cents());
            s.setInt(5, t.getId_brand());

            s.setInt(6, t.getId());

            int affectedRows = s.executeUpdate();

            return affectedRows > 0;
        } 
        catch( Exception e ) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public ArrayList<Vehicle> selectAll() {
        
        // Statement
        try {
            PreparedStatement pr = this.conn.prepareStatement(
                // Distrubuido pra garantir a ordem dos campos aqui. ->[id, name, year ....]
                "SELECT v.id, v.name, v.year, v.km, v.value_in_cents, v.id_brand, b.name as bname from " 
                + this.getTableName() + " v" 
                +" "+
                "LEFT JOIN brands b on b.id = v.id_brand " + 
                "ORDER BY ID DESC, name ASC"
            );

            ResultSet r = pr.executeQuery();

            ArrayList<Vehicle> res = new ArrayList<>();

            while (r.next()) {
                Vehicle v = new Vehicle(
                    r.getInt(1),    // id
                    r.getString(2), // name
                    r.getInt(3),    // year
                    r.getInt(4),    // km
                    r.getInt(5),    // val
                    r.getInt(6)     // idbrand
                );
                v.setJoinNameMarca(r.getString(7));

                res.add( v );
            }

            return res;
        } 

        catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    @Override
    public boolean insertOne(Vehicle t) {
        
        try {
            PreparedStatement s = this.conn.prepareStatement(
                "INSERT into " + this.getTableName()+
                " (name, year, km, value_in_cents, id_brand)"
                + " values(?, ?, ?, ?, ?)");
            
            s.setString(1, t.getName());
            s.setInt(2, t.getYear());
            s.setInt(3, t.getKm());
            s.setInt(4, t.getValue_in_cents());
            s.setInt(5, t.getId_brand());

            int affectedRows = s.executeUpdate();

            return affectedRows > 0;
        }

        catch( Exception e ) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteOne(Vehicle t) {
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
