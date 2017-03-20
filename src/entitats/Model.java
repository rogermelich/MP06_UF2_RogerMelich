/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitats;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author roger
 */
@Entity
@Table(name = "models")
public class Model {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer Referencia;
    private String Nom;
    private String TipusCarrosseria;
    
    @Transient
    private List<AcabatCotxe> conte;
    @Transient
    private Marca fabrica;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReferencia() {
        return Referencia;
    }

    public void setReferencia(Integer Referencia) {
        this.Referencia = Referencia;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getTipusCarrosseria() {
        return TipusCarrosseria;
    }

    public void setTipusCarrosseria(String TipusCarrosseria) {
        this.TipusCarrosseria = TipusCarrosseria;
    }

    public List<AcabatCotxe> getConte() {
        return conte;
    }

    public void setConte(List<AcabatCotxe> conte) {
        this.conte = conte;
    }

    public Marca getFabrica() {
        return fabrica;
    }

    public void setFabrica(Marca fabrica) {
        this.fabrica = fabrica;
    }
}
