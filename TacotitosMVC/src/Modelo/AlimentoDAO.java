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
public class AlimentoDAO {
    Connection conn;
    ConexionBD conexionn = new ConexionBD();  //INSTANCIA DE CONEXION BD
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar() {
        String sql = "SELECT * from alimento";
        List<Alimento> lista = new ArrayList<>();
        try {
            conn = (Connection) conexionn.ConectarBaseDatos(); 
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Alimento alimento = new Alimento();
                alimento.setId(rs.getInt(1));
                alimento.setNombre(rs.getString(2));
                alimento.setPrecio(rs.getDouble(3));
                lista.add(alimento);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar DAO: " + e);
        }
        return lista;
    }
    
    //Metodo agregar 
    public boolean agregar(Alimento alimento){
        String  sql = "INSERT INTO alimento(nombre_alimento, precio_alimento) values(?, ?)"; 
        try {
            conn = conexionn.ConectarBaseDatos(); 
            ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS); 
            ps.setString(1, alimento.getNombre());
            ps.setDouble(2, alimento.getPrecio());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if ( rs.next() ) {
                alimento.setId(rs.getInt(1));
            }
            return true; 
            
        } catch (SQLException e) {
            
            System.out.println("Error en agregar DAO: "+ e);
            return false; 
        }
    }
    
    
    //metodo actualizar 
    public void actualizar(Alimento alimento){
        String sql = "UPDATE alimento SET nombre_alimento=?, precio_alimento=? WHERE idalimento=? ";
        try {
            conn = conexionn.ConectarBaseDatos(); 
            ps = conn.prepareStatement(sql); 
            ps.setString(1, alimento.getNombre());
            ps.setDouble(2, alimento.getPrecio());
            ps.setInt(3, alimento.getId());
            ps.executeUpdate(); 
        } catch (SQLException e) {
            System.out.println("Error al actualizar DAO: "+e);
        }
    }
    
    //metodo borrar
    public void borrar(int id){
        String sql = "DELETE from alimento WHERE idalimento ="+id; //le pasamos el id que viene por parametro
        try {
           conn = conexionn.ConectarBaseDatos();
           ps = conn.prepareStatement(sql); 
           ps.executeUpdate(); 
        } catch (SQLException e) {
            System.out.println("Error al borrar DAO: "+e);
        }
    }
    
    public List alimentoBarato(int idalimento) {
        String sql = "SELECT * from alimento WHERE idalimento =" + idalimento;
        List<Alimento> lista = new ArrayList<>();
        try {
            conn = (Connection) conexionn.ConectarBaseDatos();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Alimento alimento = new Alimento();
                alimento.setId(rs.getInt(1));
                alimento.setNombre(rs.getString(2));
                alimento.setPrecio(rs.getDouble(3));
                lista.add(alimento);
            }

        } catch (SQLException e) {
            System.out.println("Error al listarTortillasBusquedaXtaco DAO: " + e);
        }
        return lista;
    }
    
    public List alimentoCaro(int idalimento) {
        String sql = "SELECT * from alimento WHERE idalimento =" + idalimento;
        List<Alimento> lista = new ArrayList<>();
        try {
            conn = (Connection) conexionn.ConectarBaseDatos();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Alimento alimento = new Alimento();
                alimento.setId(rs.getInt(1));
                alimento.setNombre(rs.getString(2));
                alimento.setPrecio(rs.getDouble(3));
                lista.add(alimento);
            }

        } catch (SQLException e) {
            System.out.println("Error al listarTortillasBusquedaXtaco DAO: " + e);
        }
        return lista;
    }
    
    
}
