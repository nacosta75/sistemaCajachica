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
@Table(name = "CCH_TIPODOC", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipodoc.findAll", query = "SELECT c FROM Tipodoc c"),
    @NamedQuery(name = "Tipodoc.findByIdtipodoc", query = "SELECT c FROM Tipodoc c WHERE c.idtipodoc = :idtipodoc"),
    @NamedQuery(name = "Tipodoc.findByDescripcion", query = "SELECT c FROM Tipodoc c WHERE c.descripcion = :descripcion")})
public class Tipodoc implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idtipodoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DESCRIPCION", nullable = false, length = 20)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipodoc", fetch = FetchType.EAGER)
    private List<CchDetGastos> cchDetGastosList;
    @JoinColumn(name = "IDEMPRESA", referencedColumnName = "PLUEMPRESA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Empresas idempresa;
    
    private String iva;

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }
    

    public Tipodoc() {
    }

    public Tipodoc(Integer idtipodoc) {
        this.idtipodoc = idtipodoc;
    }

    public Tipodoc(Integer idtipodoc, String descripcion) {
        this.idtipodoc = idtipodoc;
        this.descripcion = descripcion;
    }

    public Integer getIdtipodoc() {
        return idtipodoc;
    }

    public void setIdtipodoc(Integer idtipodoc) {
        this.idtipodoc = idtipodoc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idtipodoc != null ? idtipodoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipodoc)) {
            return false;
        }
        Tipodoc other = (Tipodoc) object;
        if ((this.idtipodoc == null && other.idtipodoc != null) || (this.idtipodoc != null && !this.idtipodoc.equals(other.idtipodoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CchTipodoc[ idtipodoc=" + idtipodoc + " ]";
    }
    
}
