/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Salsa;
import Modelo.SalsaDAO;
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
public class ControladorSalsa {

    //instancias
    SalsaDAO salsadao;

    /**
     * Constructor de la controladora Tortilla
     */
    public ControladorSalsa() {
        salsadao = new SalsaDAO();

    }

    /**
     * Método para validar si el campo nombre y precio estan vacios
     *
     * @param nombre El parámetro nombre hace referencia al nombre ingresado
     * dentro del txtNombre del FrmSalsa
     * @param precio El parámetro precio hace referencia al precio ingresado
     * dentro del txtPrecio del FrmSalsa
     * @return Retorna false si algun campo esta vacio o retorna true si ambos
     * campos estan llenos, con contenido
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
     * Método para agregar Salsa, que utiliza el método agregar que se encuentra
     * en SalsaDAO, para agregar la salsa en la base de datos
     *
     * @param nombre El parámetro nombre hace referencia al nombre ingresado
     * dentro del txtNombre del FrmSalsa
     * @param precio El parámetro precio hace referencia al precio ingresado
     * dentro del txtPrecio del FrmSalsa
     * @return Retorna true si se agrego correctamente el alimento o retorna
     * false si hubo algun tipo de error, por tanto no se agrega la salsa
     */
    public boolean agregarSalsa(String nombre, String precio) {
        Double precioss;
        try {
            if (validarDatos(nombre, precio) == true) {
                precioss = Double.parseDouble(precio);
                Salsa salsa = new Salsa(nombre, precioss);
                //System.out.println(salsa);
                if (salsadao.agregar(salsa) == true) {
                    //System.out.println(salsa);
                    JOptionPane.showMessageDialog(null, "Salsa agregada exitosamente");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Esa salsa ya esta agregada", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            return false;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El campo precio debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE);
            //System.out.println("Error al agregar la salsa: " + e);
            return false;
        }

    }

    /**
     * Método que llena la tabla con la lista de salsas que hay en la base de
     * datos
     *
     * @param dtm El parámetro dtm hace referencia al DefaultTableModel que esta
     * en FrmSalsa
     * @param tblTabla El parámetro tblTabla hace referencia al JTable tblTabla
     * que esta en FrmSalsa
     */
    public void llenarTablaConListaSalsas(DefaultTableModel dtm, JTable tblTabla) {
        String[] titulos = new String[]{"Código", "Nombre - Salsa", "Precio $$$"}; //titulos de la tabla
        dtm.setColumnIdentifiers(titulos);
        tblTabla.setModel(dtm);
        List<Salsa> listaSalsas = listarSalsas();
        for (Salsa salsa : listaSalsas) {
            dtm.addRow(new Object[]{salsa.getId(), salsa.getNombre(), salsa.getPrecio()});
        }

        tblTabla.setModel(dtm);
        tblTabla.setPreferredSize(new Dimension(350, dtm.getRowCount() * 16));
    }

    /**
     * Método que crea una lista con las salsas que se encuentran en la base de
     * datos
     *
     * @return Retorna una lista (salsas), con las salsas que hay cargadas hasta
     * el momento
     */
    public List listarSalsas() {
        List<Salsa> salsas = salsadao.listar();
        return salsas;
    }

    /**
     * Método para actualizar Salsa, que utiliza el método actualizar que se
     * encuentra en SalsaDAO, para actualizar la salsa en la base de datos
     *
     * @param id El parámetro id hace referencia al código seleccionado de la
     * tabla
     * @param nombre El párametro nombre hace referencia al nombre seleccionado
     * de la tabla
     * @param precio El párametro precio hace referencia al precio seleccionado
     * de la tabla
     * @return Retorna true si se realizo correctamente la actualizacion o
     * retorna false si hubo algun error
     */
    public boolean actualizarSalsa(int id, String nombre, String precio) {
        double precioss;
        try {
            if (validarDatos(nombre, precio) == true) {
                int salida = JOptionPane.showConfirmDialog(null, "Esta seguro de guardar los cambios??", "Esta acción no se puede revertir..", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (salida == 0) {
                    precioss = Double.parseDouble(precio);
                    Salsa salsa = new Salsa(id, nombre, precioss); //uso el constructor actualizar que tiene como parametro el id
                    salsadao.actualizar(salsa);
                    JOptionPane.showMessageDialog(null, "Salsa actualizada");
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
     * Método para borrar Salsa, que utiliza el método borrar que se encuentra
     * en SalsaDAO, para borrar la salsa en la base de datos
     *
     * @param id El parámetro id hace referencia al código seleccionado de la
     * tabla
     * @return Retorna true si la salsa se elimino correctamente o retorna false
     * si hubo algun error
     */
    public boolean borrarSalsa(int id) {
        try {
            int salida = JOptionPane.showConfirmDialog(null, "Esta seguro de que desea borrar esta salsa??", "Esta acción no se puede revertir..", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (salida == 0) {
                salsadao.borrar(id);
                JOptionPane.showMessageDialog(null, "Salsa borrada");

            }
            //System.out.println("estoy por ir al return true");
            return true;

        } catch (HeadlessException e) {
            System.out.println("Error al borrar Salsa en la controladora: " + e);

        }

        return false;
    }

    /**
     * Método para obtener el id de la salsa más cara, que recorre todas las
     * salsas que estan cargadas en la base de datos para luego encontrarlo
     *
     * @return Retorna un entero que corresponde al número id de la salsa más
     * cara (idsalsa)
     */
    public int obtenerIdSalsaMasCara() {
        double comparador = 0;
        List<Salsa> listaSalsas = salsadao.listar();
        int idsalsa = 0;

        for (Salsa salsa : listaSalsas) {
            if (salsa.getPrecio() > comparador) {
                comparador = salsa.getPrecio();
                idsalsa = salsa.getId();

                //System.out.println("id tortilla mas barata: " + idsalsa);
            }

        }

        return idsalsa;

    }

    /**
     * Método que le pasa como parametro el id de la salsa más cara [obtenido
     * en el metodo obtenerIdSalsaMasCara()] al metodo
     * salsaCara(idsalsa) que se encuentra en AlimentoDAO
     *
     * @return Retorna una lista con la salsa más cara que se encuentre en
     * la base de datos (salsas)
     */
    public List listarSalsasCaras() {
        int idsalsa = obtenerIdSalsaMasCara();
        List<Salsa> salsas = salsadao.salsaCara(idsalsa);
        return salsas;
    }

    
     /**
     * Método que obtiene el promedio de la lista de salsas que se encuentra
     * cargada en la base de datos
     *
     * @return Retorna un numero tipo double (promedioSalsas), que
     * corresponde a la suma de todos los precios de las salsas existentes,
     * dividido por la cantidad de salsas que existen en la base de datos. 
     */
    public double obtenerPromedioSalsas() {
        List<Salsa> listaSalsas = salsadao.listar();
        double numeroSalsasCargadas = listaSalsas.size();
        double promedioSalsas = 0;
        for (Salsa salsa : listaSalsas) {
            promedioSalsas = promedioSalsas + salsa.getPrecio();

        }

        promedioSalsas = promedioSalsas / numeroSalsasCargadas;
        //System.out.println("promedio salsas: " + (promedioSalsas));

        return promedioSalsas;
    }
}
