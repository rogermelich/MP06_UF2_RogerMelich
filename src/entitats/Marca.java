/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitats;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author roger
 */
@Entity
@Table(name = "marques")
public class Marca {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer _1_id;
    private String _2_Nom;
    private String _3_SeuCentral;
    
    //@Transient
    @OneToOne(cascade=javax.persistence.CascadeType.ALL)
    private Marca _4_competeix;
    @Transient
    private List<Model> _5_esFabricat;

    public Marca() {
    }

    public Marca(String _2_Nom, String _3_SeuCentral, Marca _4_competeix) {
        this._2_Nom = _2_Nom;
        this._3_SeuCentral = _3_SeuCentral;
        this._4_competeix = _4_competeix;
    }

    public Integer get1_id() {
        return _1_id;
    }

    public void set1_id(Integer _1_id) {
        this._1_id = _1_id;
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

    public Marca get4_competeix() {
        return _4_competeix;
    }

    public void set4_competeix(Marca _4_competeix) {
        this._4_competeix = _4_competeix;
    }

    public List<Model> get5_esFabricat() {
        return _5_esFabricat;
    }

    public void set5_esFabricat(List<Model> _5_esFabricat) {
        this._5_esFabricat = _5_esFabricat;
    }

    @Override
    public String toString() {
        return _2_Nom;
    }
    
}
