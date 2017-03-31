/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entitats.Marca;
import entitats.Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
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

    private ClasseDAO<Marca> modelMarca;
    private ClasseDAO<Model> modelModel;
    private Vista vista;
    private TableColumn CarregaTaulaMarca;
    private TableColumn CarregaTaulaModel;
    private int filasel = -1;

    public Controlador(ClasseDAO<Marca> modelMarca, ClasseDAO<Model> modelModel, Vista vista) {
        this.vista = vista;
        this.modelMarca = modelMarca;
        this.modelModel = modelModel;
        Sortir();
        InsertarMarca();
        BorrarMarca();
        ModificarMarca();
        InsertarModel();
        BorrarModel();
        ModificarModel();
        SeleccionarTaulaMarca();
        SeleccionarTaulaModel();
        carregaCombo((ArrayList) modelMarca.obtenLlista(), vista.getjComboBoxCompeteixMarca());
        carregaCombo((ArrayList) modelMarca.obtenLlista(), vista.getjComboBoxFabricatModel());
        carregaLlista((ArrayList) modelModel.obtenLlista(), vista.getjListEsFabricatMarca());
        CarregaTaulaMarca = CarregaTaula.carregaTaula((ArrayList) modelMarca.obtenLlista(), vista.getjTableMarca(), Marca.class);
        CarregaTaulaModel = CarregaTaula.carregaTaula((ArrayList) modelModel.obtenLlista(), vista.getjTableModel(), Model.class);
        vista.setVisible(true);
    }

    /**
     * Boto Per Sortir de l'Aplicacio
     */
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
        combo.setModel(new DefaultComboBoxModel((resultSet != null ? resultSet.toArray() : new Object[]{})));
    }

    private void carregaLlista(Iterable<Model> modelList, JList jlist) {
        DefaultListModel<String> model = new DefaultListModel<String>();
        for (Model p : modelList) {
            model.addElement(p.toString());
        }
        jlist.setModel(model);
    }

    /**
     *
     * Per la Taula Marca
     *
     * Per Seleccionar la linia de la taula i escriure en els JtextF...
     * JCombo....
     */
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

    /**
     *
     * Per la Taula Model
     *
     * Per Seleccionar la linia de la taula i escriure en els JtextF...
     * JCombo....
     */
    private void SeleccionarTaulaModel() {
        MouseAdapter clickTable = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (vista.getjTableModel().getSelectedRow() != -1) {
                    super.mouseClicked(e);
                    DefaultTableModel model = (DefaultTableModel) vista.getjTableModel().getModel();
                    vista.getjTextFieldIdModel().setText(model.getValueAt(vista.getjTableModel().getSelectedRow(), 0).toString());
                    vista.getjTextFieldReferenciaModel().setText(model.getValueAt(vista.getjTableModel().getSelectedRow(), 1).toString());
                    vista.getjTextFieldNomModel().setText(model.getValueAt(vista.getjTableModel().getSelectedRow(), 2).toString());
                    vista.getjTextFieldTipusCarrosseriaModel().setText(model.getValueAt(vista.getjTableModel().getSelectedRow(), 3).toString());
                } else {
                    JOptionPane.showMessageDialog(null, "S'ha de seleccionar alguna línia de la taula", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        };
        vista.getjTableModel().addMouseListener(clickTable);
    }

    /**
     *
     * CRUD Marca
     *
     */
    private void InsertarMarca() {
        ActionListener accioInsertarMarca = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(vista.getjButtonMarcaInsertar())) {
                    if (!vista.getjTextFieldNomMarca().getText().trim().equals("") || !vista.getjTextFieldSeuCentralMarca().getText().trim().equals("")) {
                        modelMarca.obtenLlista();
                    }
                    carregaLlista((ArrayList) modelModel.obtenLlista(), vista.getjListEsFabricatMarca());
                    JList<Model> llista = vista.getjListEsFabricatMarca();
                    Marca m = new Marca(vista.getjTextFieldNomMarca().getText(), vista.getjTextFieldSeuCentralMarca().getText(), (Marca) vista.getjComboBoxCompeteixMarca().getSelectedItem(), (List<Model>) llista.getSelectedValuesList());
                    modelMarca.guarda(m);
                    CarregaTaulaMarca = CarregaTaula.carregaTaula((ArrayList) modelMarca.obtenLlista(), vista.getjTableMarca(), Marca.class);
                    carregaCombo((ArrayList) modelMarca.obtenLlista(), vista.getjComboBoxCompeteixMarca());
                    carregaCombo((ArrayList) modelMarca.obtenLlista(), vista.getjComboBoxFabricatModel());
                    carregaLlista((ArrayList) modelModel.obtenLlista(), vista.getjListEsFabricatMarca());

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
                if (vista.getjTableMarca().getSelectedRow() != -1) {
                    DefaultTableModel tmodel = (DefaultTableModel) vista.getjTableMarca().getModel();
                    Marca borrarM = (Marca) tmodel.getValueAt(vista.getjTableMarca().getSelectedRow(), tmodel.getColumnCount() - 1);
                    vista.getjTableMarca().removeColumn(CarregaTaulaMarca);
                    modelMarca.elimina(borrarM);
                    vista.getjTableMarca().addColumn(CarregaTaulaMarca);
                    carregaCombo((ArrayList) modelMarca.obtenLlista(), vista.getjComboBoxCompeteixMarca());
                    carregaCombo((ArrayList) modelMarca.obtenLlista(), vista.getjComboBoxFabricatModel());
                    CarregaTaulaMarca = CarregaTaula.carregaTaula((ArrayList) modelMarca.obtenLlista(), vista.getjTableMarca(), Marca.class);
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
                if (vista.getjTableMarca().getSelectedRow() != -1) {
                    vista.getjTableMarca().addColumn(CarregaTaulaMarca);
                    DefaultTableModel tmodel = (DefaultTableModel) vista.getjTableMarca().getModel();
                    Marca modificarM = (Marca) tmodel.getValueAt(vista.getjTableMarca().getSelectedRow(), tmodel.getColumnCount() - 1);
                    modificarM.set2_Nom(vista.getjTextFieldNomMarca().getText());
                    modificarM.set3_SeuCentral(vista.getjTextFieldSeuCentralMarca().getText());
                    modificarM.set4_Competeix((Marca) vista.getjComboBoxCompeteixMarca().getSelectedItem());

                    vista.getjTableMarca().removeColumn(CarregaTaulaMarca);
                    modelMarca.actualitza(modificarM);
                    vista.getjTableMarca().addColumn(CarregaTaulaMarca);
                    CarregaTaulaMarca = CarregaTaula.carregaTaula((ArrayList) modelMarca.obtenLlista(), vista.getjTableMarca(), Marca.class);
                    carregaCombo((ArrayList) modelMarca.obtenLlista(), vista.getjComboBoxCompeteixMarca());
                    carregaCombo((ArrayList) modelMarca.obtenLlista(), vista.getjComboBoxFabricatModel());
                } else {
                    JOptionPane.showMessageDialog(null, "S'ha de seleccionar alguna línia de la taula per poder Modificiar", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        vista.getjButtonMarcaModificar().addActionListener(accioModificarMarca);
    }

    /**
     *
     * CRUD Model
     *
     */
    private void InsertarModel() {
        ActionListener accioInsertarModel = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(vista.getjButtonModelInsertar())) {
                    if (!vista.getjTextFieldReferenciaModel().getText().trim().equals("") || !vista.getjTextFieldNomModel().getText().trim().equals("") || !vista.getjTextFieldTipusCarrosseriaModel().getText().trim().equals("")) {
                        modelModel.obtenLlista();
                    }
                    //String to Int
                    String Referencia = vista.getjTextFieldReferenciaModel().getText();
                    int mreferencia = Integer.parseInt(Referencia);
                    //
                    Model m = new Model(mreferencia, vista.getjTextFieldNomModel().getText(), vista.getjTextFieldTipusCarrosseriaModel().getText(), (Marca) vista.getjComboBoxFabricatModel().getSelectedItem());
                    modelModel.guarda(m);
                    CarregaTaulaModel = CarregaTaula.carregaTaula((ArrayList) modelModel.obtenLlista(), vista.getjTableModel(), Model.class);
                    carregaLlista(modelModel.obtenLlista(), vista.getjListEsFabricatMarca());
                } else {
                    JOptionPane.showMessageDialog(null, "Introdueix tots els valors", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        vista.getjButtonModelInsertar().addActionListener(accioInsertarModel);
    }

    private void BorrarModel() {
        ActionListener accioBorrarModel = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableColumnModel tc = (TableColumnModel) vista.getjTableModel().getColumnModel();
                if (vista.getjTableModel().getSelectedRow() != -1) {
                    DefaultTableModel tmodel = (DefaultTableModel) vista.getjTableModel().getModel();
                    Model borrarM = (Model) tmodel.getValueAt(vista.getjTableModel().getSelectedRow(), tmodel.getColumnCount() - 1);
                    vista.getjTableModel().removeColumn(CarregaTaulaModel);
                    modelModel.elimina(borrarM);
                    vista.getjTableModel().addColumn(CarregaTaulaModel);
                    CarregaTaulaModel = CarregaTaula.carregaTaula((ArrayList) modelModel.obtenLlista(), vista.getjTableModel(), Model.class);
                    carregaLlista(modelModel.obtenLlista(), vista.getjListEsFabricatMarca());
                } else {
                    JOptionPane.showMessageDialog(null, "S'ha de seleccionar alguna línia de la taula per poder borrar", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        vista.getjButtonModelBorrar().addActionListener(accioBorrarModel);
    }

    private void ModificarModel() {
        ActionListener accioModificarModel = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableColumnModel tc = (TableColumnModel) vista.getjTableMarca().getColumnModel();
                if (vista.getjTableModel().getSelectedRow() != -1) {
                    vista.getjTableModel().addColumn(CarregaTaulaModel);
                    DefaultTableModel tmodel = (DefaultTableModel) vista.getjTableModel().getModel();
                    Model modificarM = (Model) tmodel.getValueAt(vista.getjTableModel().getSelectedRow(), tmodel.getColumnCount() - 1);
                    //String to Int
                    String Referencia = vista.getjTextFieldReferenciaModel().getText();
                    int mreferencia = Integer.parseInt(Referencia);
                    //
                    modificarM.set2_Referencia(mreferencia);
                    modificarM.set3_Nom(vista.getjTextFieldNomModel().getText());
                    modificarM.set4_TipusCarrosseria(vista.getjTextFieldTipusCarrosseriaModel().getText());

                    vista.getjTableModel().removeColumn(CarregaTaulaModel);
                    modelModel.actualitza(modificarM);
                    vista.getjTableModel().addColumn(CarregaTaulaModel);
                    CarregaTaulaModel = CarregaTaula.carregaTaula((ArrayList) modelModel.obtenLlista(), vista.getjTableModel(), Model.class);
                    carregaLlista(modelModel.obtenLlista(), vista.getjListEsFabricatMarca());
                } else {
                    JOptionPane.showMessageDialog(null, "S'ha de seleccionar alguna línia de la taula per poder Modificiar", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        vista.getjButtonModelModificar().addActionListener(accioModificarModel);
    }
}
