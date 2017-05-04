/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitats;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author roger
 */
@Entity
@Table(name = "acabatcotxes")
public class AcabatCotxe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acabatcotxe_id")
    private Integer _1_Id;
    
    private String _2_Pack;
    private String _3_QualitatAcabament;

    public AcabatCotxe() {
    }

    public AcabatCotxe(String _2_Pack, String _3_QualitatAcabament) {
        this._2_Pack = _2_Pack;
        this._3_QualitatAcabament = _3_QualitatAcabament;
    }

    public Integer get1_Id() {
        return _1_Id;
    }

    public void set1_Id(Integer _1_Id) {
        this._1_Id = _1_Id;
    }

    public String get2_Pack() {
        return _2_Pack;
    }

    public void set2_Pack(String _2_Pack) {
        this._2_Pack = _2_Pack;
    }

    public String get3_QualitatAcabament() {
        return _3_QualitatAcabament;
    }

    public void set3_QualitatAcabament(String _3_QualitatAcabament) {
        this._3_QualitatAcabament = _3_QualitatAcabament;
    }

    @Override
    public String toString() {
        return _2_Pack;
    }
}
