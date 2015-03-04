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
@Table(name = "CLASIFICACIONES", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clasificaciones.findAll", query = "SELECT c FROM Clasificaciones c"),
    @NamedQuery(name = "Clasificaciones.findByPluclasificacion", query = "SELECT c FROM Clasificaciones c WHERE c.pluclasificacion = :pluclasificacion"),
    @NamedQuery(name = "Clasificaciones.findByCodigo", query = "SELECT c FROM Clasificaciones c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Clasificaciones.findByNombre", query = "SELECT c FROM Clasificaciones c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Clasificaciones.findByTiposaldo", query = "SELECT c FROM Clasificaciones c WHERE c.tiposaldo = :tiposaldo"),
    @NamedQuery(name = "Clasificaciones.findByResultado", query = "SELECT c FROM Clasificaciones c WHERE c.resultado = :resultado")})
public class Clasificaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLUCLASIFICACION", nullable = false)
    private Integer pluclasificacion;
    @Size(max = 5)
    @Column(name = "CODIGO", length = 5)
    private String codigo;
    @Size(max = 60)
    @Column(name = "NOMBRE", length = 60)
    private String nombre;
    @Column(name = "TIPOSALDO")
    private Character tiposaldo;
    @Column(name = "RESULTADO")
    private Character resultado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pluclasificaciones", fetch = FetchType.EAGER)
    private List<Crubros> crubrosList;
    @JoinColumn(name = "PLUEMPRESA", referencedColumnName = "PLUEMPRESA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Empresas pluempresa;

    public Clasificaciones() {
    }

    public Clasificaciones(Integer pluclasificacion) {
        this.pluclasificacion = pluclasificacion;
    }

    public Integer getPluclasificacion() {
        return pluclasificacion;
    }

    public void setPluclasificacion(Integer pluclasificacion) {
        this.pluclasificacion = pluclasificacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Character getTiposaldo() {
        return tiposaldo;
    }

    public void setTiposaldo(Character tiposaldo) {
        this.tiposaldo = tiposaldo;
    }

    public Character getResultado() {
        return resultado;
    }

    public void setResultado(Character resultado) {
        this.resultado = resultado;
    }

    @XmlTransient
    public List<Crubros> getCrubrosList() {
        return crubrosList;
    }

    public void setCrubrosList(List<Crubros> crubrosList) {
        this.crubrosList = crubrosList;
    }

    public Empresas getPluempresa() {
        return pluempresa;
    }

    public void setPluempresa(Empresas pluempresa) {
        this.pluempresa = pluempresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pluclasificacion != null ? pluclasificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clasificaciones)) {
            return false;
        }
        Clasificaciones other = (Clasificaciones) object;
        if ((this.pluclasificacion == null && other.pluclasificacion != null) || (this.pluclasificacion != null && !this.pluclasificacion.equals(other.pluclasificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Clasificaciones[ pluclasificacion=" + pluclasificacion + " ]";
    }
    
}
