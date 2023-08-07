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
public class TacoDAO {

    Connection conn;
    ConexionBD conexionn = new ConexionBD();  //INSTANCIA DE CONEXION BD
    PreparedStatement ps;
    ResultSet rs;
    

    public int numeroTacos() {
        
        String sql = "select count(*) from taco";
        int cantidad = 0;
        try {
            conn = conexionn.ConectarBaseDatos();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                cantidad = rs.getInt(1);
            
            }
            return cantidad; 
        } catch (SQLException e) {
            System.out.println("dsdfdsf");

        }
        return 0;
    }
    
    
    
    //Metodo agregar 

    public boolean agregarTaco(Taco taco, double importe, int[] ARRid) {
        String sql = "INSERT INTO taco(preciotaco) values(?)";
        try {
            conn = conexionn.ConectarBaseDatos();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //System.out.println("importe en dao: " + importe); //no me lo imprime el to string pero si lo guarda en la base de datos
            taco.setPrecio(importe); 
            ps.setDouble(1, taco.getPrecio());
            //ps.setDouble(1, importe);
            //System.out.println(taco.getPrecio()); //no me trae el precio de la controladora aca :(

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                taco.setId(rs.getInt(1));
            }
            ARRid[0] = taco.getId();
            //System.out.println("VALOR DEL ID TACO GENERADO: " + ARRid[0]);
            return true;

        } catch (SQLException e) {

            System.out.println("Error en agregar DAO: " + e);
            return false;
        }
    }

    public boolean agregarUnionTortillaTaco(int idtaco, int idtortilla) {
        String sql = "INSERT INTO union_tortilla_taco(idtortilla,idtaco) values(?,?)";
        try {
            conn = conexionn.ConectarBaseDatos();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idtortilla);
            ps.setInt(2, idtaco);

            ps.executeUpdate();

            //System.out.println("VALOR DEL ID TACO en UNION: " + idtaco);
            //System.out.println("VALOR DEL ID Tortilla en UNION: " + idtortilla);
            return true;

        } catch (SQLException e) {

            System.out.println("Error en agregar UnionTacoTortilla DAO: " + e);
            return false;
        }
    }

    public boolean agregarUnionSalsaTaco(int idtaco, int idsalsa) {
        String sql = "INSERT INTO union_salsa_taco(idsalsa,idtaco) values(?,?)";
        try {
            conn = conexionn.ConectarBaseDatos();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idsalsa);
            ps.setInt(2, idtaco);

            ps.executeUpdate();

            //System.out.println("VALOR DEL ID TACO en UNION: " + idtaco);
            //System.out.println("VALOR DEL ID SALSA en UNION: " + idsalsa);
            return true;

        } catch (SQLException e) {

            System.out.println("Error en agregar UnionTacoSalsa DAO: " + e);
            return false;
        }
    }

    public boolean agregarUnionAlimentoTaco(int idtaco, int idalimento) {
        String sql = "INSERT INTO union_alimento_taco(idalimento,idtaco) values(?,?)";
        try {
            conn = conexionn.ConectarBaseDatos();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idalimento);
            ps.setInt(2, idtaco);

            ps.executeUpdate();

            //System.out.println("VALOR DEL ID TACO en UNION: " + idtaco);
            //System.out.println("VALOR DEL ID ALIMENTO en UNION: " + idalimento);
            return true;

        } catch (SQLException e) {

            System.out.println("Error en agregar UnionTacoAlimento DAO: " + e);
            return false;
        }
    }

    public List listarTortillasXidTacoBuscado(int idtacobuscado) {
        String sql = "SELECT tortilla.idtortilla,tortilla.nombre_tortilla,tortilla.precio_tortilla FROM union_tortilla_taco LEFT JOIN tortilla ON union_tortilla_taco.idtortilla = tortilla.idtortilla WHERE idtaco =" + idtacobuscado;
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

    public List listarAlimentosXidTacoBuscado(int idtacobuscado) {
        String sql = "SELECT alimento.idalimento,alimento.nombre_alimento,alimento.precio_alimento FROM union_alimento_taco LEFT JOIN alimento ON union_alimento_taco.idalimento = alimento.idalimento WHERE idtaco =" + idtacobuscado;
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
            System.out.println("Error al listarAlimentosBusquedaXtaco DAO: " + e);
        }
        return lista;
    }

    public List listarSalsaXidTacoBuscado(int idtacobuscado) {
        String sql = "SELECT salsa.idsalsa,salsa.nombre_salsa,salsa.precio_salsa FROM union_salsa_taco LEFT JOIN salsa ON union_salsa_taco.idsalsa = salsa.idsalsa WHERE idtaco =" + idtacobuscado;
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
            System.out.println("Error al listarSalsaBusquedaXtaco DAO: " + e);
        }
        return lista;
    }

    public boolean modificarUnionTortillaTaco(int idtaco, int idtortilla) {
        String sql = "UPDATE union_tortilla_taco SET idtortilla=? WHERE idtaco =" + idtaco;
        try {
            conn = conexionn.ConectarBaseDatos();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idtortilla);

            ps.executeUpdate();

            //System.out.println("VALOR DEL ID Tortilla en UNION A EDITAR: " + idtortilla);
            return true;

        } catch (SQLException e) {

            System.out.println("Error en editar UnionTacoTortilla DAO: " + e);
            return false;
        }
    }

    public boolean editar(double importe, int idtaco) {
        String sql = "UPDATE taco set preciotaco=? where idtaco=" + idtaco;
        try {
            conn = conexionn.ConectarBaseDatos();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //System.out.println("importe en dao: " + importe); //no me lo imprime el to string pero si lo guarda en la base de datos
            ps.setDouble(1, importe);
            //System.out.println(taco.getPrecio()); //no me trae el precio de la controladora aca :(

            ps.executeUpdate();
            //ResultSet rs = ps.getGeneratedKeys();
            //if ( rs.next() ) {
            //taco.setId(rs.getInt(1));
            //}
            //ARRid[0] = taco.getId(); 
            //System.out.println("VALOR DEL ID TACO a EDITAR: " + idtaco);
            return true;

        } catch (SQLException e) {

            System.out.println("Error en EDITAR DAO: " + e);
            return false;
        }
    }
  
    public void borrarTaco(int idtacobuscado){
        String sql = "DELETE taco, union_alimento_taco, union_salsa_taco, union_tortilla_taco FROM taco LEFT JOIN  union_alimento_taco ON (taco.idtaco=union_alimento_taco.idtaco) LEFT JOIN  union_salsa_taco ON (taco.idtaco=union_salsa_taco.idtaco) LEFT JOIN  union_tortilla_taco ON (taco.idtaco=union_tortilla_taco.idtaco) WHERE taco.idtaco="+idtacobuscado; //le pasamos el id que viene por parametro
        try {
           conn = conexionn.ConectarBaseDatos();
           ps = conn.prepareStatement(sql); 
           ps.executeUpdate(); 
        } catch (SQLException e) {
            System.out.println("Error al borrar TaacoDAO: "+e);
        }
    } 
    
    
    public void eliminarUnionTacoTortillaParaModificar(int idtacobuscado){
        String sql = "DELETE union_tortilla_taco from union_tortilla_taco WHERE idtaco="+idtacobuscado;
        try {
           conn = conexionn.ConectarBaseDatos();
           ps = conn.prepareStatement(sql); 
           ps.executeUpdate(); 
        } catch (SQLException e) {
            System.out.println("Error al borrar union taco tortilla: "+e);
        }
    }
    
    public void eliminarUnionTacoAlimentoParaModificar(int idtacobuscado){
        String sql = "DELETE union_alimento_taco from union_alimento_taco WHERE idtaco="+idtacobuscado;
        try {
           conn = conexionn.ConectarBaseDatos();
           ps = conn.prepareStatement(sql); 
           ps.executeUpdate(); 
        } catch (SQLException e) {
            System.out.println("Error al borrar union taco alimento: "+e);
        }
    }
    
    public void eliminarUnionTacoSalsaParaModificar(int idtacobuscado){
        String sql = "DELETE union_salsa_taco from union_salsa_taco WHERE idtaco="+idtacobuscado;
        try {
           conn = conexionn.ConectarBaseDatos();
           ps = conn.prepareStatement(sql); 
           ps.executeUpdate(); 
        } catch (SQLException e) {
            System.out.println("Error al borrar union taco salsa: "+e);
        }
    }
    

}
