/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Alimento;
import Modelo.AlimentoDAO;
import Modelo.Salsa;
import Modelo.SalsaDAO;
import Modelo.Taco;
import Modelo.TacoDAO;
import Modelo.Tortilla;
import Modelo.TortillaDAO;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ana
 */
public class ControladorTaco {

    TortillaDAO tortilladao;
    AlimentoDAO alimentodao;
    SalsaDAO salsadao;
    TacoDAO tacodao;

    /**
     * Constructor de la controladora Taco
     */
    public ControladorTaco() {
        tortilladao = new TortillaDAO();
        alimentodao = new AlimentoDAO();
        salsadao = new SalsaDAO();
        tacodao = new TacoDAO();
    }

    /**
     * ************************* COMBOBOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOX ***************************
     */
    /**
     * Método que carga los tres comboboxs que estan en FrmTaco, con todas las
     * tortillas, alimentos y salsas que se encuentren en la base de datos
     *
     * @param combobox_Tortilla hace referencia al combobox de tortillas que
     * esta en FrmTaco
     * @param combobox_Alimento hace referencia al combobox de alimentos que
     * esta en FrmTaco
     * @param combobox_Salsa hace referencia al combobox de salsas que esta en
     * FrmTaco
     */
    public void cargarComboboxss(JComboBox combobox_Tortilla, JComboBox combobox_Alimento, JComboBox combobox_Salsa) {
        combobox_Tortilla.removeAllItems();
        combobox_Tortilla.addItem("<Seleccione>");
        List<Tortilla> listaTortillas = tortilladao.listar();
        for (int i = 0; i < listaTortillas.size(); i++) {
            combobox_Tortilla.addItem(listaTortillas.get(i).getId() + "_"
                    + listaTortillas.get(i).getNombre() + "_"
                    + listaTortillas.get(i).getPrecio());
        }

        combobox_Alimento.removeAllItems();
        combobox_Alimento.addItem("<Seleccione>");
        List<Alimento> listaAlimentos = alimentodao.listar();
        for (int i = 0; i < listaAlimentos.size(); i++) {
            combobox_Alimento.addItem(listaAlimentos.get(i).getId() + "_"
                    + listaAlimentos.get(i).getNombre() + "_"
                    + listaAlimentos.get(i).getPrecio());
        }

        combobox_Salsa.removeAllItems();
        combobox_Salsa.addItem("<Seleccione>");
        List<Salsa> listaSalsas = salsadao.listar();
        for (int i = 0; i < listaSalsas.size(); i++) {
            combobox_Salsa.addItem(listaSalsas.get(i).getId() + "_"
                    + listaSalsas.get(i).getNombre() + "_"
                    + listaSalsas.get(i).getPrecio());
        }

        if (listaAlimentos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tiene alimentos cargados!!\nAbra el menu principal y cargue los alimentos...", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } else {
            if (listaTortillas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No tiene tortillas cargadas!!\nAbra el al menu principal y cargue las tortillas...", "ERROR", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            } else {
                if (listaSalsas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No tiene salsas cargadas!!\nAbra el menu principal y cargue las salsas...", "ERROR", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }
        }

    }

    /**
     * *********** FIIIIIIIIIIIIIIIIIINNNNNNNN
     * COMBOBOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOX **************
     */
    /**
     * Método que verifica si la tabla tortilla y la tabla alimento, que estan
     * en FrmTaco, estan vacías, es decir no tienen ninguna fila cargada, por
     * tanto ningun elemento (tortilla o alimento)
     *
     * @param dtmTortilla hace referencia al DefaultTableModel de la tabla
     * tortilla que esta en FrmTaco
     * @param dtmAlimento hace referencia al DefaultTableModel de la tabla
     * alimento que esta en FrmTaco
     * @return Retorna true si es que la tabla tortilla y alimento tienen
     * contenido, es decir tienen por lo menos una fila insertada (alimento o
     * tortilla seleccionados), o retorna false si es que ambas tablas no tienen
     * ninguna fila insertada
     */
    public boolean validarTablas(DefaultTableModel dtmTortilla, DefaultTableModel dtmAlimento) {
        if ((dtmTortilla.getRowCount() > 0) && (dtmAlimento.getRowCount() > 0)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que obtiene la cantidad de tacos generados que hay actualmente en
     * la base de datos
     *
     * @return Retorna un número tipo int (cantidad) que corresponde a la
     * cantidad de tacos que existen
     */
    public int obtenerCantidadTacosGenerados() {
        int cantidad = tacodao.numeroTacos();
        return cantidad;
    }

    /**
     * ******* SE LLENAN LAS TABLAS UNIOOOOOOONEEEEEEEES DE LA BD CON LOS ID
     * CORRESPONDIENTES *****
     */
    /**
     * Método que obtiene el id de la tortilla seleccionada en la tabla tortilla
     * de FrmTaco, luego se comunica con el método agregarUnionTortillaTaco()
     * que se encuentra en TacoDAO, para guardar el id de la tortilla y el id
     * del taco en la base de datos
     *
     * @param idtaco hace referencia al id del taco obtenido en el método
     * agregarTaco() de esta controladora
     * @param dtmTortilla hace referencia al DefaultTableModel de la tabla
     * tortilla que esta en FrmTaco
     * @return Retorna true si se pudo realizar todo de manera correcta,
     * encontrando el id de la tortilla seleccionada y cargando el id del taco y
     * el id de la tortilla en la base de datos o retorna false si hubo algun
     * error
     */
    public boolean obtenerIdTortillaParaTablaUnionTortillaTaco(int idtaco, DefaultTableModel dtmTortilla) {

        try {
            int rows = dtmTortilla.getRowCount();
            for (int i = 0; i < rows; i++) {
                //System.out.println("por castear...");
                int idtortilla = Integer.parseInt(dtmTortilla.getValueAt(i, 0).toString());
                //System.out.println("id tortilla: " + idtortilla);
                tacodao.agregarUnionTortillaTaco(idtaco, idtortilla);

            }
            return true;
        } catch (Exception e) {
            System.out.println("Error al llenar union taco - tortilla: " + e);
            return false;
        }

    }

    /**
     * Método que obtiene el id de la salsa seleccionada en la tabla salsa de
     * FrmTaco, luego se comunica con el método agregarUnionSalsaTaco() que se
     * encuentra en TacoDAO, para guardar el id de la salsa y el id del taco en
     * la base de datos
     *
     * @param idtaco hace referencia al id del taco obtenido en el método
     * agregarTaco() de esta controladora
     * @param dtmSalsa hace referencia al DefaultTableModel de la tabla salsa
     * que esta en FrmTaco
     * @return Retorna true si se pudo realizar todo de manera correcta,
     * encontrando el id de la salsa seleccionada y cargando el id del taco y el
     * id de la salsa en la base de datos o retorna false si hubo algun error
     */
    public boolean obtenerIdSalsaParaTablaUnionSalsaTaco(int idtaco, DefaultTableModel dtmSalsa) {

        try {
            int rows = dtmSalsa.getRowCount();
            for (int i = 0; i < rows; i++) {
                //System.out.println("por castear...");
                int idsalsa = Integer.parseInt(dtmSalsa.getValueAt(i, 0).toString());
                //System.out.println("id salsa: " + idsalsa);
                tacodao.agregarUnionSalsaTaco(idtaco, idsalsa);

            }
            return true;
        } catch (Exception e) {
            System.out.println("Error al llenar union taco - salsa: " + e);
            return false;
        }

    }

    /**
     * Método que obtiene el id del alimento seleccionado en la tabla alimento
     * de FrmTaco, luego se comunica con el método agregarUnionAlimentoTaco()
     * que se encuentra en TacoDAO, para guardar el id del alimento y el id del
     * taco en la base de datos
     *
     * @param idtaco hace referencia al id del taco obtenido en el método
     * agregarTaco() de esta controladora
     * @param dtmAlimento hace referencia al DefaultTableModel de la tabla
     * alimento que esta en FrmTaco
     * @return Retorna true si se pudo realizar todo de manera correcta,
     * encontrando el id del alimento seleccionado y cargando el id del taco y
     * el id del alimento en la base de datos o retorna false si hubo algun
     * error
     */
    public boolean obtenerIdAlimentoParaTablaUnionAlimentoTaco(int idtaco, DefaultTableModel dtmAlimento) {

        try {
            int rows = dtmAlimento.getRowCount();
            for (int i = 0; i < rows; i++) {
                //System.out.println("por castear...");
                int idalimento = Integer.parseInt(dtmAlimento.getValueAt(i, 0).toString());

                //System.out.println("id alimento: " + idalimento);
                tacodao.agregarUnionAlimentoTaco(idtaco, idalimento);

            }
            return true;
        } catch (Exception e) {
            System.out.println("Error al llenar union taco - alimento: " + e);
            return false;
        }

    }

    /**
     * ************** FIIN LLEEEENAAAAAAAAAADOOOOO DEE TABLA DE
     * UNIOOOOOOONEEEEEEEES ************
     */
    /**
     * Método que agrega un nuevo taco en la base de datos
     *
     * @param importe hace referencia al numero que contiene el lbTotalTaco de
     * FrmTaco
     * @param dtmTortilla hace referencia al DefaultTableModel de la tabla
     * tortilla que esta en FrmTaco
     * @param dtmSalsa hace referencia al DefaultTableModel de la tabla salsa
     * que esta en FrmTaco
     * @param dtmAlimento hace referencia al DefaultTableModel de la tabla
     * alimento que esta en FrmTaco
     * @return Retorna true si se realizo todo OK, si el importe y los id de las
     * tortillas, alimentos o salsas seleccionados fueron cargados correctamente
     * en las correspondientes tablas de la base de datos o retorna false si
     * hubo algun error
     */
    public boolean agregarTaco(double importe, DefaultTableModel dtmTortilla, DefaultTableModel dtmSalsa, DefaultTableModel dtmAlimento) {
        int[] ARRid = new int[1];
        
        try {
            if (validarTablas(dtmTortilla, dtmAlimento) == true) {
                Taco taco = new Taco(importe);
                if (tacodao.agregarTaco(taco, importe, ARRid) == true) {

                    if (obtenerIdTortillaParaTablaUnionTortillaTaco(ARRid[0], dtmTortilla) == true && obtenerIdSalsaParaTablaUnionSalsaTaco(ARRid[0], dtmSalsa) == true && obtenerIdAlimentoParaTablaUnionAlimentoTaco(ARRid[0], dtmAlimento)) {
                        String mensaje = "Taco generado con éxito!!\nTaco N°" + taco.getId() + "\nImporte a pagar: $" + taco.getPrecio();
                        JOptionPane.showMessageDialog(null, mensaje);

                    }

                    return true;
                }
                return false;

            } else {
                JOptionPane.showMessageDialog(null, "Tiene que seleccionar por lo menos una tortilla y un alimento para generar un taco...", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (NumberFormatException e) {
            System.out.println("Error al agregar taco: " + e);
            return false;
        }

    }

    /**
     * Método que modifica la información de un taco en particular que es
     * buscado por su número ID
     *
     * @param idtacobuscado hace referencia al numero de taco buscado ingresado
     * en txtIdTaco del FrmTaco
     * @param dtmTortilla hace referencia al DefaultTableModel de la tabla
     * tortilla que esta en FrmTaco
     * @param importe hace referencia al numero que contiene el lbTotalTaco de
     * FrmTaco
     * @param dtmAlimento hace referencia al DefaultTableModel de la tabla
     * alimento que esta en FrmTaco
     * @param dtmSalsa hace referencia al DefaultTableModel de la tabla salsa
     * que esta en FrmTaco
     * @return Retorna true si se pudo modificar el taco y false si es que hubo algun error
     */
    public boolean modificarTaco(int idtacobuscado, DefaultTableModel dtmTortilla, double importe, DefaultTableModel dtmAlimento, DefaultTableModel dtmSalsa) {
        try {
            if (validarTablas(dtmTortilla, dtmAlimento) == true) {
                String mensaje = "Esta seguro de modificar el taco N° " + idtacobuscado + " ??";
                int salida = JOptionPane.showConfirmDialog(null, mensaje, "Esta acción no se puede revertir..", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (salida == 0) {
                    tacodao.eliminarUnionTacoTortillaParaModificar(idtacobuscado);
                    tacodao.eliminarUnionTacoAlimentoParaModificar(idtacobuscado);
                    tacodao.eliminarUnionTacoSalsaParaModificar(idtacobuscado);
                    tacodao.editar(importe, idtacobuscado);
                    obtenerIdTortillaParaTablaUnionTortillaTaco(idtacobuscado, dtmTortilla);
                    obtenerIdAlimentoParaTablaUnionAlimentoTaco(idtacobuscado, dtmAlimento);
                    obtenerIdSalsaParaTablaUnionSalsaTaco(idtacobuscado, dtmSalsa);
                    JOptionPane.showMessageDialog(null, "El taco N° " + idtacobuscado + " ha sido modificado manera exitosa...");
                    return true;
                }
            } else{
                JOptionPane.showMessageDialog(null, "Tiene que seleccionar por lo menos una tortilla y un alimento para modificar este taco...", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (HeadlessException e) {
            System.out.println("Error al modificar Taco controladora: " + e);
        }
        return false;
        //}
    }

    /**
     * ******************************* BUSQUEDA DE TACO ******************************************
     */
    /**
     * Método que obtiene el importe cuando se realiza la búsqueda de un taco
     * específico
     *
     * @param idtacobuscado hace referencia al numero de taco buscado ingresado
     * en txtIdTaco del FrmTaco
     * @return Retorna el total (suma) de los precios que contiene el taco
     */
    public double obtenerImporteAlRealizarBusqueda(int idtacobuscado) {
        double suma = 0;
        List<Salsa> listaSalsa = tacodao.listarSalsaXidTacoBuscado(idtacobuscado);
        List<Tortilla> listaTortillas = tacodao.listarTortillasXidTacoBuscado(idtacobuscado);
        List<Alimento> listaAlimentos = tacodao.listarAlimentosXidTacoBuscado(idtacobuscado);
        for (Tortilla tortilla : listaTortillas) {
            suma = suma + tortilla.getPrecio();

        }

        for (Alimento alimento : listaAlimentos) {
            suma = suma + alimento.getPrecio();

        }

        for (Salsa salsa : listaSalsa) {
            suma = suma + salsa.getPrecio();

        }

        return suma;
    }

    /**
     * Método que busca un taco por su número ID
     *
     * @param idtacobuscado hace referencia al numero de taco buscado ingresado
     * en txtIdTaco del FrmTaco
     * @param dtmTortilla hace referencia al DefaultTableModel de la tabla
     * tortilla que esta en FrmTaco
     * @param dtmSalsa hace referencia al DefaultTableModel de la tabla salsa
     * que esta en FrmTaco
     * @param dtmAlimento hace referencia al DefaultTableModel de la tabla
     * alimento que esta en FrmTaco
     * @param tblTortilla hace referencia al JTable tblTortilla que esta en
     * FrmTaco
     * @param tblAlimento hace referencia al JTable tblAlimento que esta en
     * FrmTaco
     * @param tblSalsa hace referencia al JTable tblSalsa que esta en FrmTaco
     */
    public void buscarTaco(int idtacobuscado, DefaultTableModel dtmTortilla, DefaultTableModel dtmSalsa, DefaultTableModel dtmAlimento, JTable tblTortilla, JTable tblAlimento, JTable tblSalsa) {
        List<Salsa> listaSalsa = tacodao.listarSalsaXidTacoBuscado(idtacobuscado);
        List<Tortilla> listaTortillas = tacodao.listarTortillasXidTacoBuscado(idtacobuscado);
        List<Alimento> listaAlimentos = tacodao.listarAlimentosXidTacoBuscado(idtacobuscado);

        dtmTortilla.setRowCount(0);

        for (Tortilla tortilla : listaTortillas) {
            dtmTortilla.addRow(new Object[]{tortilla.getId(), tortilla.getNombre(), tortilla.getPrecio()});

        }
        tblTortilla.setModel(dtmTortilla);

        dtmAlimento.setRowCount(0);
        for (Alimento alimento : listaAlimentos) {
            dtmAlimento.addRow(new Object[]{alimento.getId(), alimento.getNombre(), alimento.getPrecio()});
        }
        tblAlimento.setModel(dtmAlimento);

        dtmSalsa.setRowCount(0);
        for (Salsa salsa : listaSalsa) {
            dtmSalsa.addRow(new Object[]{salsa.getId(), salsa.getNombre(), salsa.getPrecio()});
        }
        tblSalsa.setModel(dtmSalsa);

    }

    /**
     * ******************************* FIN BUSQUEDA DE TACO ******************************************
     */
    /**
     * Método que lleva la información, a las tablas que estan en FrmTaco, de
     * los elementos que contiene el taco más económico
     *
     * @param dtmTortilla hace referencia al DefaultTableModel de la tabla
     * tortilla que esta en FrmTaco
     * @param tblTortilla hace referencia al JTable tblTortilla que esta en
     * FrmTaco
     * @param dtmAlimento hace referencia al DefaultTableModel de la tabla
     * alimento que esta en FrmTaco
     * @param tblAlimento hace referencia al JTable tblAlimento que esta en
     * FrmTaco
     */
    public void generarTacoMasEconomico(DefaultTableModel dtmTortilla, JTable tblTortilla, DefaultTableModel dtmAlimento, JTable tblAlimento) {
        ControladorTortilla controladortortilla = new ControladorTortilla();
        ControladorAlimento controladoralimento = new ControladorAlimento();

        dtmTortilla.setRowCount(0); //limpia la tabla borra todas las filas, las deja en 0

        List<Tortilla> listaTortillas = controladortortilla.listarTortillasEconomicas();
        for (Tortilla tortilla : listaTortillas) {
            dtmTortilla.addRow(new Object[]{tortilla.getId(), tortilla.getNombre(), tortilla.getPrecio()});
        }
        tblTortilla.setModel(dtmTortilla);

        dtmAlimento.setRowCount(0); //limpia la tabla borra todas las filas, las deja en 0

        List<Alimento> listaAlimentos = controladoralimento.listarAlimentoEconomico();
        for (Alimento alimento : listaAlimentos) {
            dtmAlimento.addRow(new Object[]{alimento.getId(), alimento.getNombre(), alimento.getPrecio()});
        }
        tblAlimento.setModel(dtmAlimento);

    }

    /**
     * Método que lleva la información, a las tablas que estan en FrmTaco, de
     * los elementos que contiene el taco más caro
     *
     * @param dtmTortilla hace referencia al DefaultTableModel de la tabla
     * tortilla que esta en FrmTaco
     * @param tblTortilla hace referencia al JTable tblTortilla que esta en
     * FrmTaco
     * @param dtmAlimento hace referencia al DefaultTableModel de la tabla
     * alimento que esta en FrmTaco
     * @param tblAlimento hace referencia al JTable tblAlimento que esta en
     * FrmTaco
     * @param dtmSalsa hace referencia al DefaultTableModel de la tabla salsa
     * que esta en FrmTaco
     * @param tblSalsa hace referencia al JTable tblSalsa que esta en FrmTaco
     */
    public void generarTacoMasCaro(DefaultTableModel dtmTortilla, JTable tblTortilla, DefaultTableModel dtmAlimento, JTable tblAlimento, DefaultTableModel dtmSalsa, JTable tblSalsa) {
        ControladorTortilla controladortortilla = new ControladorTortilla();
        ControladorAlimento controladoralimento = new ControladorAlimento();
        ControladorSalsa controladorsalsa = new ControladorSalsa();

        dtmTortilla.setRowCount(0); //limpia la tabla borra todas las filas, las deja en 0

        List<Tortilla> listaTortillas = controladortortilla.listarTortillasCaras();
        for (Tortilla tortilla : listaTortillas) {
            dtmTortilla.addRow(new Object[]{tortilla.getId(), tortilla.getNombre(), tortilla.getPrecio()});
            dtmTortilla.addRow(new Object[]{tortilla.getId(), tortilla.getNombre(), tortilla.getPrecio()});
        }
        tblTortilla.setModel(dtmTortilla);

        dtmAlimento.setRowCount(0); //limpia la tabla borra todas las filas, las deja en 0

        List<Alimento> listaAlimentos = controladoralimento.listarAlimentosCaros();
        for (Alimento alimento : listaAlimentos) {
            dtmAlimento.addRow(new Object[]{alimento.getId(), alimento.getNombre(), alimento.getPrecio()});
            dtmAlimento.addRow(new Object[]{alimento.getId(), alimento.getNombre(), alimento.getPrecio()});
            dtmAlimento.addRow(new Object[]{alimento.getId(), alimento.getNombre(), alimento.getPrecio()});
            dtmAlimento.addRow(new Object[]{alimento.getId(), alimento.getNombre(), alimento.getPrecio()});
            dtmAlimento.addRow(new Object[]{alimento.getId(), alimento.getNombre(), alimento.getPrecio()});
        }
        tblAlimento.setModel(dtmAlimento);

        dtmSalsa.setRowCount(0); //limpia la tabla borra todas las filas, las deja en 0

        List<Salsa> listaSalsas = controladorsalsa.listarSalsasCaras();
        for (Salsa salsa : listaSalsas) {
            dtmSalsa.addRow(new Object[]{salsa.getId(), salsa.getNombre(), salsa.getPrecio()});

        }

        tblSalsa.setModel(dtmSalsa);
    }

    /**
     * Método que obtiene el valor promedio de un taco completo, este se
     * comunica con el resto de las controladoras para obtener el promedio de
     * las tortillas, salsas y alimentos, hacer una suma y asi obtener el valor
     * final
     *
     * @return Retorna un número tipo double que corresponde al
     * promedioTacoCompleto
     */
    public double obtenerValorPromedioTacoCompleto() {
        ControladorTortilla controladortortilla = new ControladorTortilla();
        ControladorAlimento controladoralimento = new ControladorAlimento();
        ControladorSalsa controladorsalsa = new ControladorSalsa();

        double promedioTacoCompleto = controladortortilla.obtenerPromedioTortillas() + controladoralimento.obtenerPromedioAlimentos() + controladorsalsa.obtenerPromedioSalsas();

        return promedioTacoCompleto;

    }

    /**
     * Método que elimina un taco por completo de la base de datos
     *
     * @param idtacobuscado hace referencia al numero de taco buscado ingresado
     * en txtIdTaco del FrmTaco
     * @return Retorna true si se pudo eliminar el taco de manera correcta o
     * retorna false si hubo algun error
     */
    public boolean eliminarTaco(int idtacobuscado) {
        try {
            String mensaje = "Esta seguro de eliminar el taco N° " + idtacobuscado + " ??";
            int salida = JOptionPane.showConfirmDialog(null, mensaje, "Esta acción no se puede revertir..", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (salida == 0) {
                tacodao.borrarTaco(idtacobuscado);
                String mensaje2 = "Taco N° " + idtacobuscado + " eliminado con éxito";
                JOptionPane.showMessageDialog(null, mensaje2);

            }
            //System.out.println("estoy por ir al return true");
            return true;

        } catch (HeadlessException e) {
            System.out.println("Error al borrar Taco controladora: " + e);

        }

        return false;
    }

}
