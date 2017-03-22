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
    @Column(name="id")
    private Integer id;
    @Column(name="pack")
    private String Pack;
    @Column(name="qualitatacabament")
    private String QualitatAcabament;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPack() {
        return Pack;
    }

    public void setPack(String Pack) {
        this.Pack = Pack;
    }

    public String getQualitatAcabament() {
        return QualitatAcabament;
    }

    public void setQualitatAcabament(String QualitatAcabament) {
        this.QualitatAcabament = QualitatAcabament;
    }
}
