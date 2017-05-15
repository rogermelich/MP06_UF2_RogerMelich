/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitats;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author roger
 */
@Entity
@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer _1_Id;
    
    private Integer _2_Referencia;
    private String _3_Nom;
    private String _4_TipusCarrosseria;

    // @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // Ja es guarda Correctament a la Base de Dades
    private ArrayList<AcabatCotxe> _5_Conte;

    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = true)
    private Marca _6_Fabrica;

    public Model() {
    }

    public Model(Integer _2_Referencia, String _3_Nom, String _4_TipusCarrosseria, ArrayList<AcabatCotxe> _5_Conte, Marca _6_Fabrica) {
        this._2_Referencia = _2_Referencia;
        this._3_Nom = _3_Nom;
        this._4_TipusCarrosseria = _4_TipusCarrosseria;
        this._5_Conte = _5_Conte;
        this._6_Fabrica = _6_Fabrica;
    }


    public Integer get1_Id() {
        return _1_Id;
    }

    public void set1_Id(Integer _1_Id) {
        this._1_Id = _1_Id;
    }

    public Integer get2_Referencia() {
        return _2_Referencia;
    }

    public void set2_Referencia(Integer _2_Referencia) {
        this._2_Referencia = _2_Referencia;
    }

    public String get3_Nom() {
        return _3_Nom;
    }

    public void set3_Nom(String _3_Nom) {
        this._3_Nom = _3_Nom;
    }

    public String get4_TipusCarrosseria() {
        return _4_TipusCarrosseria;
    }

    public void set4_TipusCarrosseria(String _4_TipusCarrosseria) {
        this._4_TipusCarrosseria = _4_TipusCarrosseria;
    }

    public ArrayList<AcabatCotxe> get5_Conte() {
        return _5_Conte;
    }

    public void set5_Conte(ArrayList<AcabatCotxe> _5_Conte) {
        this._5_Conte = _5_Conte;
    }

    public Marca get6_Fabrica() {
        return _6_Fabrica;
    }

    public void set6_Fabrica(Marca _6_Fabrica) {
        this._6_Fabrica = _6_Fabrica;
    }

    @Override
    public String toString() {
        return _3_Nom;
    }
}
