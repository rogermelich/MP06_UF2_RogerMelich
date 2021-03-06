/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entitats.AcabatCotxe;
import entitats.Marca;
import entitats.Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
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
    private ClasseDAO<AcabatCotxe> modelAcabatCotxe;
    private Vista vista;
    private TableColumn CarregaTaulaMarca;
    private TableColumn CarregaTaulaModel;
    private TableColumn CarregaTaulaAcabatCotxe;
    private int filasel = -1;

    public Controlador(ClasseDAO<Marca> modelMarca, ClasseDAO<Model> modelModel, ClasseDAO<AcabatCotxe> modelAcabatCotxe, Vista vista) {
        this.vista = vista;
        this.modelMarca = modelMarca;
        this.modelModel = modelModel;
        this.modelAcabatCotxe = modelAcabatCotxe;
        Sortir();
        InsertarMarca();
        BorrarMarca();
        ModificarMarca();
        InsertarModel();
        BorrarModel();
        ModificarModel();
        InsertarAcabatCotxe();
        BorrarAcabatCotxe();
        ModificarAcabatCotxe();
        SeleccionarTaulaMarca();
        SeleccionarTaulaModel();
        SeleccionarTaulaAcabatCotxe();
        carregaCombo((ArrayList) modelMarca.obtenLlista(), vista.getjComboBoxCompeteixMarca());
        carregaCombo((ArrayList) modelMarca.obtenLlista(), vista.getjComboBoxFabricatModel());
        carregaLlista(modelModel.obtenLlista(), vista.getjListEsFabricatMarca());
        carregaLlista(modelAcabatCotxe.obtenLlista(), vista.getjListConteModel());
        CarregaTaulaMarca = CarregaTaula.carregaTaula((ArrayList) modelMarca.obtenLlista(), vista.getjTableMarca(), Marca.class);
        CarregaTaulaModel = CarregaTaula.carregaTaula((ArrayList) modelModel.obtenLlista(), vista.getjTableModel(), Model.class);
        CarregaTaulaAcabatCotxe = CarregaTaula.carregaTaula((ArrayList) modelAcabatCotxe.obtenLlista(), vista.getjTableAcabatCotxe(), AcabatCotxe.class);
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

    private void carregaLlista(List list, JList jlist) {
        ArrayList arraylist = new ArrayList(list);
        DefaultListModel dlmodel = new DefaultListModel();
        for (Object p : arraylist) {
            dlmodel.addElement(p.toString());
        }
        jlist.setModel(dlmodel);
    }

    //Comprova si Es un Número
    public static boolean esNumero(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
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
                    if (model.getValueAt(vista.getjTableMarca().getSelectedRow(), 3) == null) {
                        // Si es 'BUIT' no fem res
                    } else {
                        vista.getjComboBoxCompeteixMarca().setSelectedItem(model.getValueAt(vista.getjTableMarca().getSelectedRow(), 3).toString());
                    }
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
                    // Ho comento pel problema de 'java.lang.ArrayIndexOutOfBoundsException: -1'
                    // vista.getjListConteModel().setSelectedValue(model.getValueAt(vista.getjTableMarca().getSelectedRow(), 4), false);
                    if (model.getValueAt(vista.getjTableModel().getSelectedRow(), 5) == null) {
                        // Si es 'BUIT' no fem res
                    } else {
                        vista.getjComboBoxFabricatModel().setSelectedItem(model.getValueAt(vista.getjTableModel().getSelectedRow(), 5).toString());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "S'ha de seleccionar alguna línia de la taula", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        };
        vista.getjTableModel().addMouseListener(clickTable);
    }

    /**
     *
     * Per la Taula Model
     *
     * Per Seleccionar la linia de la taula i escriure en els JtextF...
     *
     */
    private void SeleccionarTaulaAcabatCotxe() {
        MouseAdapter clickTable = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (vista.getjTableAcabatCotxe().getSelectedRow() != -1) {
                    super.mouseClicked(e);
                    DefaultTableModel model = (DefaultTableModel) vista.getjTableAcabatCotxe().getModel();
                    vista.getjTextFieldIdAcabatCotxe().setText(model.getValueAt(vista.getjTableAcabatCotxe().getSelectedRow(), 0).toString());
                    vista.getjTextFieldPackAcabatCotxe().setText(model.getValueAt(vista.getjTableAcabatCotxe().getSelectedRow(), 1).toString());
                    vista.getjTextFieldQualitatAcabatCotxe().setText(model.getValueAt(vista.getjTableAcabatCotxe().getSelectedRow(), 2).toString());
                } else {
                    JOptionPane.showMessageDialog(null, "S'ha de seleccionar alguna línia de la taula", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        };
        vista.getjTableAcabatCotxe().addMouseListener(clickTable);
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
                        carregaLlista(modelModel.obtenLlista(), vista.getjListEsFabricatMarca());
                        Marca m = new Marca(vista.getjTextFieldNomMarca().getText(), vista.getjTextFieldSeuCentralMarca().getText(), (Marca) vista.getjComboBoxCompeteixMarca().getSelectedItem(), vista.getjListEsFabricatMarca().getSelectedValuesList());
                        modelMarca.guarda(m);
                        CarregaTaulaMarca = CarregaTaula.carregaTaula((ArrayList) modelMarca.obtenLlista(), vista.getjTableMarca(), Marca.class);
                        CarregaTaulaModel = CarregaTaula.carregaTaula((ArrayList) modelModel.obtenLlista(), vista.getjTableModel(), Model.class);
                        carregaCombo((ArrayList) modelMarca.obtenLlista(), vista.getjComboBoxCompeteixMarca());
                        carregaCombo((ArrayList) modelMarca.obtenLlista(), vista.getjComboBoxFabricatModel());
                        vista.NetejarCampsMarca("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Introdueix tots els valors", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
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
                    if (borrarM.get5_EsFabricat().isEmpty()) {
                        modelMarca.elimina(borrarM);
                    } else {
                        JOptionPane.showMessageDialog(null, "Avanç de borrar Elimina la relació: " + borrarM.get5_EsFabricat() + " de la línia Seleccionada", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    vista.getjTableMarca().addColumn(CarregaTaulaMarca);
                    CarregaTaulaMarca = CarregaTaula.carregaTaula((ArrayList) modelMarca.obtenLlista(), vista.getjTableMarca(), Marca.class);
                    CarregaTaulaModel = CarregaTaula.carregaTaula((ArrayList) modelModel.obtenLlista(), vista.getjTableModel(), Model.class);
                    carregaCombo((ArrayList) modelMarca.obtenLlista(), vista.getjComboBoxCompeteixMarca());
                    carregaCombo((ArrayList) modelMarca.obtenLlista(), vista.getjComboBoxFabricatModel());
                    vista.NetejarCampsMarca("");
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
                    Object marcaexception = tmodel.getValueAt(vista.getjTableMarca().getSelectedRow(), 4);
                    Marca modificarM = (Marca) tmodel.getValueAt(vista.getjTableMarca().getSelectedRow(), tmodel.getColumnCount() - 1);
                    modificarM.set2_Nom(vista.getjTextFieldNomMarca().getText());
                    modificarM.set3_SeuCentral(vista.getjTextFieldSeuCentralMarca().getText());
                    modificarM.set4_Competeix((Marca) vista.getjComboBoxCompeteixMarca().getSelectedItem());

                    vista.getjTableMarca().removeColumn(CarregaTaulaMarca);
                    modelMarca.actualitza(modificarM);
                    vista.getjTableMarca().addColumn(CarregaTaulaMarca);
                    CarregaTaulaMarca = CarregaTaula.carregaTaula((ArrayList) modelMarca.obtenLlista(), vista.getjTableMarca(), Marca.class);
                    CarregaTaulaModel = CarregaTaula.carregaTaula((ArrayList) modelModel.obtenLlista(), vista.getjTableModel(), Model.class);
                    carregaCombo((ArrayList) modelMarca.obtenLlista(), vista.getjComboBoxCompeteixMarca());
                    carregaCombo((ArrayList) modelMarca.obtenLlista(), vista.getjComboBoxFabricatModel());
                    vista.NetejarCampsMarca("");
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
                    if (esNumero(vista.getjTextFieldReferenciaModel().getText())) {
                        if (!vista.getjTextFieldNomModel().getText().trim().equals("") || !vista.getjTextFieldTipusCarrosseriaModel().getText().trim().equals("")) {
                            modelModel.obtenLlista();
                            //String to Int
                            int referencia = Integer.parseInt(vista.getjTextFieldReferenciaModel().getText());
                            //
                            if (vista.getjListConteModel().getSelectedValuesList().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Crea un Acabat de Cotxe o Seleccional", "ERROR", JOptionPane.ERROR_MESSAGE);
                            } else {
                                ArrayList ConteModelList = (ArrayList) vista.getjListConteModel().getSelectedValuesList();
                                Model m = new Model(referencia, vista.getjTextFieldNomModel().getText(), vista.getjTextFieldTipusCarrosseriaModel().getText(), ConteModelList, (Marca) vista.getjComboBoxCompeteixMarca().getSelectedItem());
                                modelModel.guarda(m);
                                CarregaTaulaModel = CarregaTaula.carregaTaula((ArrayList) modelModel.obtenLlista(), vista.getjTableModel(), Model.class);
                                CarregaTaulaMarca = CarregaTaula.carregaTaula((ArrayList) modelMarca.obtenLlista(), vista.getjTableMarca(), Marca.class);
                                carregaLlista(modelModel.obtenLlista(), vista.getjListEsFabricatMarca());
                                carregaCombo((ArrayList) modelMarca.obtenLlista(), vista.getjComboBoxFabricatModel());
                                vista.NetejarCampsModel("");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Introdueix tots els valors", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Referencia te que ser Enter", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
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
                    CarregaTaulaMarca = CarregaTaula.carregaTaula((ArrayList) modelMarca.obtenLlista(), vista.getjTableMarca(), Marca.class);
                    carregaCombo((ArrayList) modelMarca.obtenLlista(), vista.getjComboBoxFabricatModel());
                    carregaLlista(modelModel.obtenLlista(), vista.getjListEsFabricatMarca());
                    vista.NetejarCampsModel("");
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
                    if (esNumero(vista.getjTextFieldReferenciaModel().getText())) {
                        //String to Int
                        int referencia = Integer.parseInt(vista.getjTextFieldReferenciaModel().getText());
                        //
                        if (vista.getjListConteModel().getSelectedValuesList().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Selecciona un Acabat de Cotxe", "ERROR", JOptionPane.ERROR_MESSAGE);
                        } else {
                            // Jlits to ArrayList
                            ArrayList ConteModelList = (ArrayList) vista.getjListConteModel().getSelectedValuesList();
                            modificarM.set2_Referencia(referencia);
                            modificarM.set3_Nom(vista.getjTextFieldNomModel().getText());
                            modificarM.set4_TipusCarrosseria(vista.getjTextFieldTipusCarrosseriaModel().getText());
                            modificarM.set5_Conte(ConteModelList);
                            modificarM.set6_Fabrica((Marca) vista.getjComboBoxCompeteixMarca().getSelectedItem());

                            vista.getjTableModel().removeColumn(CarregaTaulaModel);                            
                            modelModel.actualitza(modificarM);
                            vista.getjTableModel().addColumn(CarregaTaulaModel);
                            CarregaTaulaModel = CarregaTaula.carregaTaula((ArrayList) modelModel.obtenLlista(), vista.getjTableModel(), Model.class);
                            CarregaTaulaMarca = CarregaTaula.carregaTaula((ArrayList) modelMarca.obtenLlista(), vista.getjTableMarca(), Marca.class);
                            carregaCombo((ArrayList) modelMarca.obtenLlista(), vista.getjComboBoxFabricatModel());
                            carregaLlista(modelModel.obtenLlista(), vista.getjListEsFabricatMarca());
                            vista.NetejarCampsModel("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Referencia te que ser Enter", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "S'ha de seleccionar alguna línia de la taula per poder Modificiar", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        vista.getjButtonModelModificar().addActionListener(accioModificarModel);
    }

    /**
     *
     * CRUD AcabatCotxe
     *
     */
    private void InsertarAcabatCotxe() {
        ActionListener accioInsertarAcabatCotxe = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(vista.getjButtonAcabatCotxeInsertar())) {
                    if (!vista.getjTextFieldPackAcabatCotxe().getText().trim().equals("") || !vista.getjTextFieldQualitatAcabatCotxe().getText().trim().equals("")) {
                        modelAcabatCotxe.obtenLlista();
                        AcabatCotxe ac = new AcabatCotxe(vista.getjTextFieldPackAcabatCotxe().getText(), vista.getjTextFieldQualitatAcabatCotxe().getText());

                        modelAcabatCotxe.guarda(ac);
                        CarregaTaulaModel = CarregaTaula.carregaTaula((ArrayList) modelModel.obtenLlista(), vista.getjTableModel(), Model.class);
                        CarregaTaulaAcabatCotxe = CarregaTaula.carregaTaula((ArrayList) modelAcabatCotxe.obtenLlista(), vista.getjTableAcabatCotxe(), AcabatCotxe.class);
                        carregaLlista(modelAcabatCotxe.obtenLlista(), vista.getjListConteModel());
                        vista.NetejarCampsAcabatCotxe("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Introdueix tots els valors", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Introdueix tots els valors", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        vista.getjButtonAcabatCotxeInsertar().addActionListener(accioInsertarAcabatCotxe);
    }

    private void BorrarAcabatCotxe() {
        ActionListener accioBorrarAcabatCotxe = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableColumnModel tc = (TableColumnModel) vista.getjTableAcabatCotxe().getColumnModel();
                if (vista.getjTableAcabatCotxe().getSelectedRow() != -1) {
                    DefaultTableModel tmodel = (DefaultTableModel) vista.getjTableAcabatCotxe().getModel();
                    AcabatCotxe borrarAC = (AcabatCotxe) tmodel.getValueAt(vista.getjTableAcabatCotxe().getSelectedRow(), tmodel.getColumnCount() - 1);
                    vista.getjTableAcabatCotxe().removeColumn(CarregaTaulaAcabatCotxe);
                    modelAcabatCotxe.elimina(borrarAC);
                    vista.getjTableAcabatCotxe().addColumn(CarregaTaulaAcabatCotxe);
                    CarregaTaulaModel = CarregaTaula.carregaTaula((ArrayList) modelModel.obtenLlista(), vista.getjTableModel(), Model.class);
                    CarregaTaulaAcabatCotxe = CarregaTaula.carregaTaula((ArrayList) modelAcabatCotxe.obtenLlista(), vista.getjTableAcabatCotxe(), AcabatCotxe.class);
                    carregaLlista(modelAcabatCotxe.obtenLlista(), vista.getjListConteModel());
                    vista.NetejarCampsAcabatCotxe("");
                } else {
                    JOptionPane.showMessageDialog(null, "S'ha de seleccionar alguna línia de la taula per poder borrar", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        vista.getjButtonAcabatCotxeBorrar().addActionListener(accioBorrarAcabatCotxe);
    }

    private void ModificarAcabatCotxe() {
        ActionListener accioModificarAcabatCotxe = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableColumnModel tc = (TableColumnModel) vista.getjTableMarca().getColumnModel();
                if (vista.getjTableAcabatCotxe().getSelectedRow() != -1) {
                    vista.getjTableAcabatCotxe().addColumn(CarregaTaulaAcabatCotxe);
                    DefaultTableModel tmodel = (DefaultTableModel) vista.getjTableAcabatCotxe().getModel();
                    AcabatCotxe modificarAC = (AcabatCotxe) tmodel.getValueAt(vista.getjTableAcabatCotxe().getSelectedRow(), tmodel.getColumnCount() - 1);
                    
                    modificarAC.set2_Pack(vista.getjTextFieldPackAcabatCotxe().getText());
                    modificarAC.set3_QualitatAcabament(vista.getjTextFieldQualitatAcabatCotxe().getText());
                    
                    vista.getjTableAcabatCotxe().removeColumn(CarregaTaulaAcabatCotxe);
                    modelAcabatCotxe.actualitza(modificarAC);
                    vista.getjTableAcabatCotxe().addColumn(CarregaTaulaAcabatCotxe);
                    CarregaTaulaModel = CarregaTaula.carregaTaula((ArrayList) modelModel.obtenLlista(), vista.getjTableModel(), Model.class);
                    CarregaTaulaAcabatCotxe = CarregaTaula.carregaTaula((ArrayList) modelAcabatCotxe.obtenLlista(), vista.getjTableAcabatCotxe(), AcabatCotxe.class);
                    carregaLlista(modelAcabatCotxe.obtenLlista(), vista.getjListConteModel());
                    vista.NetejarCampsAcabatCotxe("");
                } else {
                    JOptionPane.showMessageDialog(null, "S'ha de seleccionar alguna línia de la taula per poder Modificiar", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        vista.getjButtonAcabatCotxeModificar().addActionListener(accioModificarAcabatCotxe);
    }
}