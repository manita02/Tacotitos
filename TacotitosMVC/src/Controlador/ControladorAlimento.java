/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Alimento;
import Modelo.AlimentoDAO;
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
public class ControladorAlimento {

    //instancias
    AlimentoDAO alimentodao;

    /**
     * Constructor de la controladora Tortilla
     */
    public ControladorAlimento() {
        alimentodao = new AlimentoDAO();

    }

    /**
     * Método para validar si el campo nombre y precio estan vacios
     *
     * @param nombre El parámetro nombre hace referencia al nombre ingresado
     * dentro del txtNombre del FrmAlimento
     * @param precio El parámetro precio hace referencia al precio ingresado
     * dentro del txtPrecio del FrmAlimento
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
     * Método para agregar Alimento, que utiliza el método agregar que se
     * encuentra en AlimentoDAO, para agregar el alimento en la base de datos
     *
     * @param nombre El parámetro nombre hace referencia al nombre ingresado
     * dentro del txtNombre del FrmAlimento
     * @param precio El parámetro precio hace referencia al precio ingresado
     * dentro del txtPrecio del FrmAlimento
     * @return Retorna true si se agrego correctamente el alimento o retorna
     * false si hubo algun tipo de error, por tanto no se agrega el alimento
     */
    public boolean agregarAlimento(String nombre, String precio) {
        Double precioss;
        try {
            if (validarDatos(nombre, precio) == true) {
                precioss = Double.parseDouble(precio);
                Alimento alimento = new Alimento(nombre, precioss);
                //System.out.println(alimento);
                if (alimentodao.agregar(alimento) == true) {
                    //System.out.println(alimento);
                    JOptionPane.showMessageDialog(null, "Alimento agregado exitosamente");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Ese alimento ya esta agregado", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            return false;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El campo precio debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al agregar alimento: " + e);
            return false;
        }

    }

    /**
     * Método que llena la tabla con la lista de alimentos que hay en la base de
     * datos
     *
     * @param dtm El parámetro dtm hace referencia al DefaultTableModel que esta
     * en FrmAlimento
     * @param tblTabla El parámetro tblTabla hace referencia al JTable tblTabla
     * que esta en FrmAlimento
     */
    public void llenarTablaConListaAlimentos(DefaultTableModel dtm, JTable tblTabla) {
        String[] titulos = new String[]{"Código", "Nombre - Alimento", "Precio $$$"}; //titulos de la tabla
        dtm.setColumnIdentifiers(titulos);
        tblTabla.setModel(dtm);
        List<Alimento> listaAlimentos = listarAlimentos();
        for (Alimento alimento : listaAlimentos) {
            dtm.addRow(new Object[]{alimento.getId(), alimento.getNombre(), alimento.getPrecio()});
        }

        tblTabla.setModel(dtm);
        tblTabla.setPreferredSize(new Dimension(350, dtm.getRowCount() * 16));
    }

    /**
     * Método que crea una lista con los alimentos que se encuentran en la base
     * de datos
     *
     * @return Retorna una lista (alimentos), con los alimentos que hay cargados
     * hasta el momento
     */
    public List listarAlimentos() {
        List<Alimento> alimentos = alimentodao.listar();
        return alimentos;
    }

    /**
     * Método para actualizar Alimento, que utiliza el método actualizar que se
     * encuentra en AlimentoDAO, para actualizar el alimento en la base de datos
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
    public boolean actualizarAlimento(int id, String nombre, String precio) {
        double precioss;
        try {
            if (validarDatos(nombre, precio) == true) {
                int salida = JOptionPane.showConfirmDialog(null, "Esta seguro de guardar los cambios??", "Esta acción no se puede revertir..", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (salida == 0) {
                    precioss = Double.parseDouble(precio);
                    Alimento alimento = new Alimento(id, nombre, precioss); //uso el constructor actualizar que tiene como parametro el id
                    alimentodao.actualizar(alimento);
                    JOptionPane.showMessageDialog(null, "Alimento actualizado");
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
     * Método para borrar Alimento, que utiliza el método borrar que se
     * encuentra en AlimentoDAO, para borrar el alimento en la base de datos
     *
     * @param id El parámetro id hace referencia al código seleccionado de la
     * tabla
     * @return Retorna true si el alimento se elimino correctamente o retorna
     * false si hubo algun error
     */
    public boolean borrarAlimento(int id) {
        try {
            int salida = JOptionPane.showConfirmDialog(null, "Esta seguro de que desea borrar este alimento??", "Esta acción no se puede revertir..", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (salida == 0) {
                alimentodao.borrar(id);
                JOptionPane.showMessageDialog(null, "Alimento borrado");

            }
            //System.out.println("estoy por ir al return true");
            return true;

        } catch (HeadlessException e) {
            System.out.println("Error al borrar Alimento en la controladora: " + e);

        }

        return false;
    }

    /**
     * Método para obtener el id del alimento más económico que recorre la lista
     * de alimentos que hay actualmente en la base de datos para luego
     * encontrarlo
     *
     * @return Retorna un entero que corresponde al número id del alimento más
     * económico (idalimento)
     */
    public int obtenerIdAlimentoMasEconomico() {
        double comparadorAlimento = 1000000;
        int idalimento = 0;
        List<Alimento> listaAlimentos = alimentodao.listar();
        for (Alimento alimento : listaAlimentos) {
            if (alimento.getPrecio() < comparadorAlimento) {
                comparadorAlimento = alimento.getPrecio();
                idalimento = alimento.getId();
                //System.out.println("id alimento mas barato: " + idalimento);
            }
        }

        return idalimento;

    }

    /**
     * Método que le pasa como parametro el id del alimento más económico
     * [obtenido en el metodo obtenerIdAlimentoMasEconomico()] al metodo
     * alimentoBarato(idalimento) que se encuentra en AlimentoDAO
     *
     * @return Retorna una lista con el alimento más económico que se encuentre
     * en la base de datos (alimentos)
     */
    public List listarAlimentoEconomico() {
        int idalimento = obtenerIdAlimentoMasEconomico();
        List<Alimento> alimentos = alimentodao.alimentoBarato(idalimento);
        return alimentos;
    }

    /**
     * Método para obtener el id del alimento más caro que recorre la lista de
     * alimentos que hay actualmente en la base de datos para luego encontrarlo
     *
     * @return Retorna un entero que corresponde al número id del alimento más
     * caro (idalimento)
     */
    public int obtenerIdAlimentoMasCaro() {
        double comparador = 0;
        List<Alimento> listaAlimentos = alimentodao.listar();
        int idalimento = 0;

        for (Alimento alimento : listaAlimentos) {
            if (alimento.getPrecio() > comparador) {
                comparador = alimento.getPrecio();
                idalimento = alimento.getId();

                //System.out.println("id tortilla mas caro: " + idalimento);
            }

        }

        return idalimento;

    }

    /**
     * Método que le pasa como parametro el id del alimento más caro [obtenido
     * en el metodo obtenerIdAlimentoMasCaro()] al metodo
     * alimentoCaro(idalimento) que se encuentra en AlimentoDAO
     *
     * @return Retorna una lista con el alimento más caro que se encuentre en
     * la base de datos (alimentos)
     */
    public List listarAlimentosCaros() {
        int idalimento = obtenerIdAlimentoMasCaro();
        List<Alimento> alimentos = alimentodao.alimentoCaro(idalimento);
        return alimentos;
    }

    /**
     * Método que obtiene el promedio de la lista de alimentos que se encuentra
     * cargada en la base de datos
     *
     * @return Retorna un numero tipo double (promedioAlimentos * 5), que
     * corresponde a la suma de todos los precios de los alimentos existentes,
     * dividido por la cantidad de alimentos que hay, y luego multiplicado X 5,
     * ya que posteriormente se desea obtener el valor promedio de un taco
     * completo y este tiene la capacidad máxima de contener 5 alimentos
     */
    public double obtenerPromedioAlimentos() {
        List<Alimento> listaAlimentos = listarAlimentos();
        double numeroAlimentosCargados = listaAlimentos.size();
        double promedioAlimentos = 0;
        for (Alimento alimento : listaAlimentos) {
            promedioAlimentos = promedioAlimentos + alimento.getPrecio();

        }

        promedioAlimentos = promedioAlimentos / numeroAlimentosCargados;
        //System.out.println("promedio alimentos: " + (promedioAlimentos));

        return promedioAlimentos * 5;
    }

}
