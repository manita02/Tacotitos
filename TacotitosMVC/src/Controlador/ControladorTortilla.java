/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Tortilla;
import Modelo.TortillaDAO;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ana
 */
public class ControladorTortilla {

    //instancias
    TortillaDAO tortilladao;

    /**
     * Constructor de la controladora Tortilla
     */
    public ControladorTortilla() {
        tortilladao = new TortillaDAO();

    }

    
    
    /**
     * Método para validar si el campo nombre y precio estan vacios
     * @param nombre El parámetro nombre hace referencia al nombre ingresado dentro del txtNombre del FrmTortilla
     * @param precio El parámetro precio hace referencia al precio ingresado dentro del txtPrecio del FrmTortilla
     * @return Retorna false si algun campo esta vacio o retorna true si ambos campos estan llenos, con contenido
     */
    private boolean validarDatos(String nombre, String precio) {
        if ("".equals(nombre) || "".equals(precio)) { //valor del campo nombre o precio esta vacio?
            //System.out.println("comparando nombre y precio vacios");
            JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    
    
    /**
     * Método para agregar Tortilla, que utiliza el método agregar que se encuentra en TortillaDAO, para agregar la tortilla en la base de datos
     * @param nombre El parámetro nombre hace referencia al nombre ingresado dentro del txtNombre del FrmTortilla
     * @param precio El parámetro precio hace referencia al precio ingresado dentro del txtPrecio del FrmTortilla
     * @return Retorna true si se agrego correctamente la tortilla o retorna false si hubo algun tipo de error, por tanto no se agrega la tortilla
     */
    public boolean agregarTortilla(String nombre, String precio) {
        Double precioss;
        try {
            if (validarDatos(nombre, precio) == true) {
                precioss = Double.parseDouble(precio);
                Tortilla tortilla = new Tortilla(nombre, precioss);
                //System.out.println(tortilla);
                if (tortilladao.agregar(tortilla) == true) {
                    //System.out.println(tortilla);
                    JOptionPane.showMessageDialog(null, "Tortilla agregada exitosamente");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Esa tortilla ya esta agregada", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            return false;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El campo precio debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al agregar tortilla: " + e);
            return false;
        }

    }

    
    
    /**
     * Método que llena la tabla con la lista de tortillas que hay en la base de datos
     * @param dtm El parámetro dtm hace referencia al DefaultTableModel que esta en FrmTortilla
     * @param tblTabla El parámetro tblTabla hace referencia al JTable tblTabla que esta en FrmTortilla
     */
    public void llenarTablaConListaTortillas(DefaultTableModel dtm, JTable tblTabla) {
        String[] titulos = new String[]{"Código", "Nombre - Tortilla", "Precio $$$"}; //titulos de la tabla
        dtm.setColumnIdentifiers(titulos);
        tblTabla.setModel(dtm);
        List<Tortilla> listaTortillas = listarTortillas();
        for (Tortilla tortilla : listaTortillas) {
            dtm.addRow(new Object[]{tortilla.getId(), tortilla.getNombre(), tortilla.getPrecio()});
        }

        tblTabla.setModel(dtm);
        tblTabla.setPreferredSize(new Dimension(350, dtm.getRowCount() * 16));
    }

    
    
    /**
     * Método que crea una lista con las tortillas que se encuentran en la base de datos
     * @return Retorna una lista (tortillas), con las tortillas que hay cargadas hasta el momento 
     */
    public List listarTortillas() {
        List<Tortilla> tortillas = tortilladao.listar();
        return tortillas;
    }
    
    
   

    
    
    /**
     * Método para actualizar Tortilla, que utiliza el método actualizar que se encuentra en TortillaDAO, para actualizar la tortilla en la base de datos
     * @param id El parámetro id hace referencia al código seleccionado de la tabla
     * @param nombre El párametro nombre hace referencia al nombre seleccionado de la tabla
     * @param precio El párametro precio hace referencia al precio seleccionado de la tabla
     * @return Retorna true si se realizo correctamente la actualizacion o retorna false si hubo algun error
     */
    public boolean actualizarTortilla(int id, String nombre, String precio) {
        double precioss;
        try {
            if (validarDatos(nombre, precio) == true) {
                int salida = JOptionPane.showConfirmDialog(null, "Esta seguro de guardar los cambios??", "Esta acción no se puede revertir..", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (salida == 0) {
                    precioss = Double.parseDouble(precio);
                    Tortilla tortilla = new Tortilla(id, nombre, precioss); //uso el constructor actualizar que tiene como parametro el id
                    tortilladao.actualizar(tortilla);
                    JOptionPane.showMessageDialog(null, "Tortilla actualizada");
                    return true;

                }
                return false;
            }
            return false;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El campo precio debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    
    
    /**
     * Método para borrar Tortilla, que utiliza el método borrar que se encuentra en TortillaDAO, para borrar la tortilla en la base de datos
     * @param id El parámetro id hace referencia al código seleccionado de la tabla
     * @return Retorna true si la tortilla se elimino correctamente o retorna false si hubo algun error
     */
    public boolean borrarTortilla(int id) {
        try {
            int salida = JOptionPane.showConfirmDialog(null, "Esta seguro de que desea borrar esta tortilla??", "Esta acción no se puede revertir..", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (salida == 0) {
                tortilladao.borrar(id);
                JOptionPane.showMessageDialog(null, "Tortilla borrada");

            }
            //System.out.println("estoy por ir al return true");
            return true;

        } catch (HeadlessException e) {
            System.out.println("Error al borrar Tortilla controladora: " + e);

        }

        return false;
    }
    
    
    
    /**
     * Método para obtener el id de la tortilla más económica que recorre la lista
     * de tortillas que hay actualmente en la base de datos para luego
     * encontrarlo
     *
     * @return Retorna un entero que corresponde al número id de la tortilla más
     * económica (idtortilla)
     */
    public int obtenerIdTortillaMasEconomica() {
        double comparadorTortilla = 1000000;
        List<Tortilla> listaTortillas = tortilladao.listar();
        int idtortilla = 0;

        for (Tortilla tortilla : listaTortillas) {
            if (tortilla.getPrecio() < comparadorTortilla) {
                comparadorTortilla = tortilla.getPrecio();
                idtortilla = tortilla.getId();

                //System.out.println("id tortilla mas barata: " + idtortilla);
            }

        }

        return idtortilla;

    }
    
    
    
    /**
     * Método que le pasa como parametro el id de la tortilla más económica
     * [obtenido en el metodo obtenerIdTortillaMasEconomica()] al metodo
     * tortillaBarata(idtortilla) que se encuentra en TortillaDAO
     *
     * @return Retorna una lista con la tortilla más económica que se encuentre
     * en la base de datos (tortillas)
     */
    public List listarTortillasEconomicas() {
        int idtortilla = obtenerIdTortillaMasEconomica();
        List<Tortilla> tortillas = tortilladao.tortillaBarata(idtortilla);
        return tortillas;
    }
    
    
    
    /**
     * Método para obtener el id de la tortilla más cara que recorre la lista de
     * tortillas que hay actualmente en la base de datos para luego encontrarlo
     *
     * @return Retorna un entero que corresponde al número id de la tortilla más
     * cara (idtortilla)
     */
    public int obtenerIdTortillaMasCara() {
        double comparador = 0;
        List<Tortilla> listaTortillas = tortilladao.listar();
        int idtortilla = 0;

        for (Tortilla tortilla : listaTortillas) {
            if (tortilla.getPrecio() > comparador) {
                comparador = tortilla.getPrecio();
                idtortilla = tortilla.getId();

                //System.out.println("id tortilla mas barata: " + idtortilla);
            }

        }

        return idtortilla;

    }

  
    /**
     * Método que le pasa como parametro el id de la tortilla más cara [obtenido
     * en el metodo obtenerIdTortillaMasCara()] al metodo
     * tortillaCara(idtortilla) que se encuentra en TortillaDAO
     *
     * @return Retorna una lista con la tortilla más cara que se encuentre en
     * la base de datos (tortillas)
     */
    public List listarTortillasCaras() {
        int idtortilla = obtenerIdTortillaMasCara();
        List<Tortilla> tortillas = tortilladao.tortillaCara(idtortilla);
        return tortillas;
    }
    
    
    /**
     * Método que obtiene el promedio de la lista de tortillas que se encuentra
     * cargada en la base de datos
     *
     * @return Retorna un numero tipo double (promedioTortillas * 2), que
     * corresponde a la suma de todos los precios de las tortillas existentes,
     * dividido por la cantidad de tortillas que hay, y luego multiplicado X 2,
     * ya que posteriormente se desea obtener el valor promedio de un taco
     * completo y este tiene la capacidad máxima de contener 2 tortillas
     */
    public double obtenerPromedioTortillas() {
        List<Tortilla> listaTortillas = listarTortillas();
        double numeroTortillasCargadas = listaTortillas.size();
        double promedioTortillas = 0;
        for (Tortilla tortilla : listaTortillas) {
            promedioTortillas = promedioTortillas + tortilla.getPrecio();

        }

        promedioTortillas = promedioTortillas / numeroTortillasCargadas;
        //System.out.println("promedio tortillas: " + (promedioTortillas));

        return promedioTortillas * 2;
    }

}
