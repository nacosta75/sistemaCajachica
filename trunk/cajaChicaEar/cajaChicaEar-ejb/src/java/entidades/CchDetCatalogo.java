/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author trompudo
 */
@Entity
@Table(name = "CCH_DET_CATALOGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CchDetCatalogo.findAll", query = "SELECT c FROM CchDetCatalogo c"),
    @NamedQuery(name = "CchDetCatalogo.findById", query = "SELECT c FROM CchDetCatalogo c WHERE c.id = :id"),
    @NamedQuery(name = "CchDetCatalogo.findByCtaContab", query = "SELECT c FROM CchDetCatalogo c WHERE c.ctaContab = :ctaContab")})
public class CchDetCatalogo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Size(max = 30)
    @Column(name = "CTA_CONTAB")
    private String ctaContab;
    
    @JoinColumn(name = "IDGCATALOGO", referencedColumnName = "IDGCATALOGO")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CchCatalogo idgcatalogo;
    
    @JoinColumn(name = "IDCANAL", referencedColumnName = "IDCANAL")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CchCanal idcanal;
    @JoinColumn(name = "IDSUCURSAL", referencedColumnName = "PLUBODEGA")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Bodegas idsucursal;

    public CchDetCatalogo() {
    }

    public CchDetCatalogo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCtaContab() {
        return ctaContab;
    }

    public void setCtaContab(String ctaContab) {
        this.ctaContab = ctaContab;
    }

    public CchCatalogo getIdgcatalogo() {
        return idgcatalogo;
    }

    public void setIdgcatalogo(CchCatalogo idgcatalogo) {
        this.idgcatalogo = idgcatalogo;
    }

    public CchCanal getIdcanal() {
        return idcanal;
    }

    public void setIdcanal(CchCanal idcanal) {
        this.idcanal = idcanal;
    }

    public Bodegas getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Bodegas idsucursal) {
        this.idsucursal = idsucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CchDetCatalogo)) {
            return false;
        }
        CchDetCatalogo other = (CchDetCatalogo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CchDetCatalogo[ id=" + id + " ]";
    }
    
}
