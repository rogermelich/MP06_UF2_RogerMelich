/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitats;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.swing.ListModel;

/**
 *
 * @author roger
 */
@Entity
@Table(name = "marques")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer _1_Id;
    private String _2_Nom;
    private String _3_SeuCentral;

    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    private Marca _4_Competeix;
    
    @OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "_6_Fabrica", fetch = FetchType.EAGER)
    private List<Model> _5_EsFabricat;

    public Marca() {
    }

    public Marca(String _2_Nom, String _3_SeuCentral, Marca _4_Competeix, List<Model> _5_EsFabricat) {
        this._2_Nom = _2_Nom;
        this._3_SeuCentral = _3_SeuCentral;
        this._4_Competeix = _4_Competeix;
        this._5_EsFabricat = _5_EsFabricat;
    }

    public Integer get1_Id() {
        return _1_Id;
    }

    public void set1_Id(Integer _1_Id) {
        this._1_Id = _1_Id;
    }

    public String get2_Nom() {
        return _2_Nom;
    }

    public void set2_Nom(String _2_Nom) {
        this._2_Nom = _2_Nom;
    }

    public String get3_SeuCentral() {
        return _3_SeuCentral;
    }

    public void set3_SeuCentral(String _3_SeuCentral) {
        this._3_SeuCentral = _3_SeuCentral;
    }

    public Marca get4_Competeix() {
        return _4_Competeix;
    }

    public void set4_Competeix(Marca _4_Competeix) {
        this._4_Competeix = _4_Competeix;
    }

    public List<Model> get5_EsFabricat() {
        return _5_EsFabricat;
    }

    public void set5_EsFabricat(List<Model> _5_EsFabricat) {
        this._5_EsFabricat = _5_EsFabricat;
    }
    
    @Override
    public String toString() {
        return _2_Nom;
    }
}
