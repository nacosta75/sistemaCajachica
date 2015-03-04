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
@Table(name = "CCH_CANAL", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CchCanal.findAll", query = "SELECT c FROM CchCanal c"),
    @NamedQuery(name = "CchCanal.findByIdcanal", query = "SELECT c FROM CchCanal c WHERE c.idcanal = :idcanal"),
    @NamedQuery(name = "CchCanal.findByNombreCanal", query = "SELECT c FROM CchCanal c WHERE c.nombreCanal = :nombreCanal")})
public class CchCanal implements Serializable {
    
  
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcanal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE_CANAL", nullable = false, length = 60)
    private String nombreCanal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcanal", fetch = FetchType.EAGER)
    private List<CchDetGastos> cchDetGastosList;

    @JoinColumn(name = "IDEMPRESA", referencedColumnName = "PLUEMPRESA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Empresas idempresa;

    public CchCanal() {
    }

    public CchCanal(Integer idcanal) {
        this.idcanal = idcanal;
    }

    public CchCanal(Integer idcanal, String nombreCanal) {
        this.idcanal = idcanal;
        this.nombreCanal = nombreCanal;
    }

    public Integer getIdcanal() {
        return idcanal;
    }

    public void setIdcanal(Integer idcanal) {
        this.idcanal = idcanal;
    }

    public String getNombreCanal() {
        return nombreCanal;
    }

    public void setNombreCanal(String nombreCanal) {
        this.nombreCanal = nombreCanal;
    }

    @XmlTransient
    public List<CchDetGastos> getCchDetGastosList() {
        return cchDetGastosList;
    }

    public void setCchDetGastosList(List<CchDetGastos> cchDetGastosList) {
        this.cchDetGastosList = cchDetGastosList;
    }


    public Empresas getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Empresas idempresa) {
        this.idempresa = idempresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcanal != null ? idcanal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CchCanal)) {
            return false;
        }
        CchCanal other = (CchCanal) object;
        if ((this.idcanal == null && other.idcanal != null) || (this.idcanal != null && !this.idcanal.equals(other.idcanal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CchCanal[ idcanal=" + idcanal + " ]";
    }

    

}
