/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entitats.Marca;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.ClasseDAO;
import view.Vista;

/**
 *
 * @author roger
 */
public class Controlador {
    
    private ClasseDAO<Marca> model;
    private Vista vista;
    private TableColumn CarregaTaulaMarca;

    // Camps Taula Marca
    private int id = -1;
    private String Nom = "";
    private String SeuCentral = "";
    private Marca competeix;
    //private Marca esFabricat;
    private int filasel = -1;

    public Controlador(ClasseDAO<Marca> model, Vista vista) {
        this.vista = vista;
        this.model = model;
        Sortir();
        InsertarMarca();
        BorrarMarca();
        ModificarMarca();
        SeleccionarTaulaMarca();
        carregaCombo((ArrayList)model.obtenLlista(),vista.getjComboBoxCompeteixMarca());
        CarregaTaulaMarca = CarregaTaula.carregaTaula((ArrayList) model.obtenLlista(), vista.getjTableMarca(), Marca.class);
        vista.setVisible(true);
    }

    private void Sortir() {
        ActionListener accioSortir = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        vista.getjButtonSortir().addActionListener(accioSortir);
    }
    
    private void carregaCombo(ArrayList resultSet, JComboBox combo) {
        combo.setModel(new DefaultComboBoxModel((resultSet!=null?resultSet.toArray():new Object[]{})));
    }
    
    private void SeleccionarTaulaMarca() {
        MouseAdapter clickTable = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (vista.getjTableMarca().getSelectedRow() != -1) {
                    super.mouseClicked(e);
                    DefaultTableModel model = (DefaultTableModel) vista.getjTableMarca().getModel();
                    vista.getjTextFieldIdMarca().setText(model.getValueAt(vista.getjTableMarca().getSelectedRow(), 0).toString());
                    vista.getjTextFieldNomMarca().setText(model.getValueAt(vista.getjTableMarca().getSelectedRow(), 1).toString());
                    vista.getjTextFieldSeuCentralMarca().setText(model.getValueAt(vista.getjTableMarca().getSelectedRow(), 2).toString());
                    vista.getjComboBoxCompeteixMarca().setSelectedItem(model.getValueAt(vista.getjTableMarca().getSelectedRow(), 3).toString());
                } else {
                    JOptionPane.showMessageDialog(null, "S'ha de seleccionar alguna línia de la taula", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        };
        vista.getjTableMarca().addMouseListener(clickTable);
    }
    
    //CRUD Marca
    private void InsertarMarca() {
        ActionListener accioInsertarMarca = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(vista.getjButtonMarcaInsertar())) {
                    if(!vista.getjTextFieldNomMarca().getText().trim().equals("") || !vista.getjTextFieldSeuCentralMarca().getText().trim().equals("") || !vista.getjTextFieldEsFabricatMarca().getText().trim().equals(""))
                    model.obtenLlista();
                    Marca m = new Marca(vista.getjTextFieldNomMarca().getText(), vista.getjTextFieldSeuCentralMarca().getText(), (Marca) vista.getjComboBoxCompeteixMarca().getSelectedItem());
                    model.guarda(m);
                    CarregaTaulaMarca = CarregaTaula.carregaTaula((ArrayList) model.obtenLlista(), vista.getjTableMarca(), Marca.class);
                    carregaCombo((ArrayList)model.obtenLlista(),vista.getjComboBoxCompeteixMarca());
                } else {
                    JOptionPane.showMessageDialog(null, "Introdueix tots els valors", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        vista.getjButtonMarcaInsertar().addActionListener(accioInsertarMarca);
    }

    private void BorrarMarca() {
        ActionListener accioBorrarMarca = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableColumnModel tc = (TableColumnModel) vista.getjTableMarca().getColumnModel();
                if(vista.getjTableMarca().getSelectedRow() != -1) {
                    DefaultTableModel tmodel = (DefaultTableModel) vista.getjTableMarca().getModel();
                    Marca borrarM = (Marca) tmodel.getValueAt(vista.getjTableMarca().getSelectedRow(), tmodel.getColumnCount() -1);                    
                    vista.getjTableMarca().removeColumn(CarregaTaulaMarca);
                    model.elimina(borrarM);
                    vista.getjTableMarca().addColumn(CarregaTaulaMarca);
                    CarregaTaulaMarca = CarregaTaula.carregaTaula((ArrayList) model.obtenLlista(), vista.getjTableMarca(), Marca.class);
                    carregaCombo((ArrayList)model.obtenLlista(),vista.getjComboBoxCompeteixMarca());
                } else {
                    JOptionPane.showMessageDialog(null, "S'ha de seleccionar alguna línia de la taula per poder borrar", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        vista.getjButtonMarcaBorrar().addActionListener(accioBorrarMarca);
    }

    private void ModificarMarca() {
        ActionListener accioModificarMarca = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableColumnModel tc = (TableColumnModel) vista.getjTableMarca().getColumnModel();
                if(vista.getjTableMarca().getSelectedRow() != -1) {
                    vista.getjTableMarca().addColumn(CarregaTaulaMarca);
                    DefaultTableModel tmodel = (DefaultTableModel) vista.getjTableMarca().getModel();
                    Marca modificarM = (Marca) tmodel.getValueAt(vista.getjTableMarca().getSelectedRow(), tmodel.getColumnCount() -1);
                    modificarM.set2_Nom(vista.getjTextFieldNomMarca().getText());
                    modificarM.set3_SeuCentral(vista.getjTextFieldSeuCentralMarca().getText());
                    modificarM.set4_Competeix((Marca) vista.getjComboBoxCompeteixMarca().getSelectedItem());
                    
                    vista.getjTableMarca().removeColumn(CarregaTaulaMarca);
                    model.actualitza(modificarM);
                    vista.getjTableMarca().addColumn(CarregaTaulaMarca);
                    CarregaTaulaMarca = CarregaTaula.carregaTaula((ArrayList) model.obtenLlista(), vista.getjTableMarca(), Marca.class);
                    carregaCombo((ArrayList)model.obtenLlista(),vista.getjComboBoxCompeteixMarca());
                } else {
                    JOptionPane.showMessageDialog(null, "S'ha de seleccionar alguna línia de la taula per poder Modificiar", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        vista.getjButtonMarcaModificar().addActionListener(accioModificarMarca);
    }
}
