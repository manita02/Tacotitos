/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.ConexionBD;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ana
 */
public class TortillaDAO{
    Connection conn;
    ConexionBD conexionn = new ConexionBD();  //INSTANCIA DE CONEXION BD
    PreparedStatement ps;
    ResultSet rs;

    public List listar() {
        String sql = "SELECT * from tortilla";
        List<Tortilla> lista = new ArrayList<>();
        try {
            conn = (Connection) conexionn.ConectarBaseDatos(); 
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Tortilla tipoTortilla = new Tortilla();
                tipoTortilla.setId(rs.getInt(1));
                tipoTortilla.setNombre(rs.getString(2));
                tipoTortilla.setPrecio(rs.getDouble(3));
                lista.add(tipoTortilla);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar DAO: " + e);
        }
        return lista;
    }
    
    
    //Metodo agregar 
    public boolean agregar(Tortilla tipotortilla){
        String  sql = "INSERT INTO tortilla(nombre_tortilla, precio_tortilla) values(?, ?)"; 
        try {
            conn = conexionn.ConectarBaseDatos(); 
            ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS); 
            ps.setString(1, tipotortilla.getNombre());
            ps.setDouble(2, tipotortilla.getPrecio());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if ( rs.next() ) {
                tipotortilla.setId(rs.getInt(1));
            }
            return true; 
            
        } catch (SQLException e) {
            
            System.out.println("Error en agregar DAO: "+ e);
            return false; 
        }
    }
    
    //metodo actualizar 
    public void actualizar(Tortilla tipotortilla){
        String sql = "UPDATE tortilla SET nombre_tortilla=?, precio_tortilla=? WHERE idtortilla=? ";
        try {
            conn = conexionn.ConectarBaseDatos(); 
            ps = conn.prepareStatement(sql); 
            ps.setString(1, tipotortilla.getNombre());
            ps.setDouble(2, tipotortilla.getPrecio());
            ps.setInt(3, tipotortilla.getId());
            ps.executeUpdate(); 
        } catch (SQLException e) {
            System.out.println("Error al actualizar DAO: "+e);
        }
    }
    
    
    //metodo borrar
    public void borrar(int id){
        String sql = "DELETE from tortilla WHERE idtortilla ="+id; //le pasamos el id que viene por parametro
        try {
           conn = conexionn.ConectarBaseDatos();
           ps = conn.prepareStatement(sql); 
           ps.executeUpdate(); 
        } catch (SQLException e) {
            System.out.println("Error al borrar DAO: "+e);
        }
    }
    
    public List tortillaBarata(int idtortilla) {
        String sql = "SELECT * from tortilla WHERE idtortilla =" + idtortilla;
        List<Tortilla> lista = new ArrayList<>();
        try {
            conn = (Connection) conexionn.ConectarBaseDatos();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Tortilla tipoTortilla = new Tortilla();
                tipoTortilla.setId(rs.getInt(1));
                tipoTortilla.setNombre(rs.getString(2));
                tipoTortilla.setPrecio(rs.getDouble(3));
                lista.add(tipoTortilla);
            }

        } catch (SQLException e) {
            System.out.println("Error al listarTortillasBusquedaXtaco DAO: " + e);
        }
        return lista;
    }
      
    public List tortillaCara(int idtortilla) {
        String sql = "SELECT * from tortilla WHERE idtortilla =" + idtortilla;
        List<Tortilla> lista = new ArrayList<>();
        try {
            conn = (Connection) conexionn.ConectarBaseDatos();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Tortilla tipoTortilla = new Tortilla();
                tipoTortilla.setId(rs.getInt(1));
                tipoTortilla.setNombre(rs.getString(2));
                tipoTortilla.setPrecio(rs.getDouble(3));
                lista.add(tipoTortilla);
            }

        } catch (SQLException e) {
            System.out.println("Error al listarTortillasBusquedaXtaco DAO: " + e);
        }
        return lista;
    }

}









