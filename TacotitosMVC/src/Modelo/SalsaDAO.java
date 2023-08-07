/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ana
 */
public class SalsaDAO {
    Connection conn;
    ConexionBD conexionn = new ConexionBD();  //INSTANCIA DE CONEXION BD
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar() {
        String sql = "SELECT * from salsa";
        List<Salsa> lista = new ArrayList<>();
        try {
            conn = (Connection) conexionn.ConectarBaseDatos(); 
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Salsa salsa = new Salsa();
                salsa.setId(rs.getInt(1));
                salsa.setNombre(rs.getString(2));
                salsa.setPrecio(rs.getDouble(3));
                lista.add(salsa);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar DAO: " + e);
        }
        return lista;
    }
    
    //Metodo agregar 
    public boolean agregar(Salsa salsa){
        String  sql = "INSERT INTO salsa(nombre_salsa, precio_salsa) values(?, ?)"; 
        try {
            conn = conexionn.ConectarBaseDatos(); 
            ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS); 
            ps.setString(1, salsa.getNombre());
            ps.setDouble(2, salsa.getPrecio());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if ( rs.next() ) {
                salsa.setId(rs.getInt(1));
            }
            return true; 
            
        } catch (SQLException e) {
            
            System.out.println("Error en agregar DAO: "+ e);
            return false; 
        }
    }
    
    //metodo actualizar 
    public void actualizar(Salsa salsa){
        String sql = "UPDATE salsa SET nombre_salsa=?, precio_salsa=? WHERE idsalsa=? ";
        try {
            conn = conexionn.ConectarBaseDatos(); 
            ps = conn.prepareStatement(sql); 
            ps.setString(1, salsa.getNombre());
            ps.setDouble(2, salsa.getPrecio());
            ps.setInt(3, salsa.getId());
            ps.executeUpdate(); 
        } catch (SQLException e) {
            System.out.println("Error al actualizar DAO: "+e);
        }
    }
    
    //metodo borrar
    public void borrar(int id){
        String sql = "DELETE from salsa WHERE idsalsa ="+id; //le pasamos el id que viene por parametro
        try {
           conn = conexionn.ConectarBaseDatos();
           ps = conn.prepareStatement(sql); 
           ps.executeUpdate(); 
        } catch (SQLException e) {
            System.out.println("Error al borrar DAO: "+e);
        }
    }
    
    
    public List salsaCara(int idsalsa) {
        String sql = "SELECT * from salsa WHERE idsalsa =" + idsalsa;
        List<Salsa> lista = new ArrayList<>();
        try {
            conn = (Connection) conexionn.ConectarBaseDatos();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Salsa salsa = new Salsa();
                salsa.setId(rs.getInt(1));
                salsa.setNombre(rs.getString(2));
                salsa.setPrecio(rs.getDouble(3));
                lista.add(salsa);
            }

        } catch (SQLException e) {
            System.out.println("Error al listarTortillasBusquedaXtaco DAO: " + e);
        }
        return lista;
    }
}
