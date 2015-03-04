/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author trompudo
 */
@Entity
@Table(name = "CCH_CATALOGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CchCatalogo.findAll", query = "SELECT c FROM CchCatalogo c"),
    @NamedQuery(name = "CchCatalogo.findByIdgcatalogo", query = "SELECT c FROM CchCatalogo c WHERE c.idgcatalogo = :idgcatalogo"),
    @NamedQuery(name = "CchCatalogo.findByNombreGasto", query = "SELECT c FROM CchCatalogo c WHERE c.nombreGasto = :nombreGasto"),
    @NamedQuery(name = "CchCatalogo.findByFovial", query = "SELECT c FROM CchCatalogo c WHERE c.fovial = :fovial")})
public class CchCatalogo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idgcatalogo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE_GASTO")
    private String nombreGasto;
    @Size(max = 1)
    @Column(name = "FOVIAL")
    private String fovial;
    @JoinColumn(name = "IDEMPRESA", referencedColumnName = "PLUEMPRESA")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Empresas idempresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idgcatalogo", fetch = FetchType.EAGER)
    private List<CchDetCatalogo> cchDetCatalogoList;

    public CchCatalogo() {
    }

    public CchCatalogo(Integer idgcatalogo) {
        this.idgcatalogo = idgcatalogo;
    }

    public CchCatalogo(Integer idgcatalogo, String nombreGasto) {
        this.idgcatalogo = idgcatalogo;
        this.nombreGasto = nombreGasto;
    }

    public Integer getIdgcatalogo() {
        return idgcatalogo;
    }

    public void setIdgcatalogo(Integer idgcatalogo) {
        this.idgcatalogo = idgcatalogo;
    }

    public String getNombreGasto() {
        return nombreGasto;
    }

    public void setNombreGasto(String nombreGasto) {
        this.nombreGasto = nombreGasto;
    }

    public String getFovial() {
        return fovial;
    }

    public void setFovial(String fovial) {
        this.fovial = fovial;
    }

    public Empresas getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Empresas idempresa) {
        this.idempresa = idempresa;
    }

    @XmlTransient
    public List<CchDetCatalogo> getCchDetCatalogoList() {
        return cchDetCatalogoList;
    }

    public void setCchDetCatalogoList(List<CchDetCatalogo> cchDetCatalogoList) {
        this.cchDetCatalogoList = cchDetCatalogoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgcatalogo != null ? idgcatalogo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CchCatalogo)) {
            return false;
        }
        CchCatalogo other = (CchCatalogo) object;
        if ((this.idgcatalogo == null && other.idgcatalogo != null) || (this.idgcatalogo != null && !this.idgcatalogo.equals(other.idgcatalogo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CchCatalogo[ idgcatalogo=" + idgcatalogo + " ]";
    }
    
}
