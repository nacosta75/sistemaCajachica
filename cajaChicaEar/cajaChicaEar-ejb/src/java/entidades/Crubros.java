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
@Table(name = "CRUBROS", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Crubros.findAll", query = "SELECT c FROM Crubros c"),
    @NamedQuery(name = "Crubros.findByPlucrubro", query = "SELECT c FROM Crubros c WHERE c.plucrubro = :plucrubro"),
    @NamedQuery(name = "Crubros.findByCodigo", query = "SELECT c FROM Crubros c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Crubros.findByNombre", query = "SELECT c FROM Crubros c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Crubros.findByTiposaldo", query = "SELECT c FROM Crubros c WHERE c.tiposaldo = :tiposaldo"),
    @NamedQuery(name = "Crubros.findByResultado", query = "SELECT c FROM Crubros c WHERE c.resultado = :resultado")})
public class Crubros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLUCRUBRO", nullable = false)
    private Integer plucrubro;
    @Size(max = 10)
    @Column(name = "CODIGO", length = 10)
    private String codigo;
    @Size(max = 60)
    @Column(name = "NOMBRE", length = 60)
    private String nombre;
    @Column(name = "TIPOSALDO")
    private Character tiposaldo;
    @Column(name = "RESULTADO")
    private Character resultado;
    @JoinColumn(name = "PLUEMPRESA", referencedColumnName = "PLUEMPRESA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Empresas pluempresa;
    @JoinColumn(name = "PLUCLASIFICACIONES", referencedColumnName = "PLUCLASIFICACION", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Clasificaciones pluclasificaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plucrubro", fetch = FetchType.EAGER)
    private List<Cuentas> cuentasList;

    public Crubros() {
    }

    public Crubros(Integer plucrubro) {
        this.plucrubro = plucrubro;
    }

    public Integer getPlucrubro() {
        return plucrubro;
    }

    public void setPlucrubro(Integer plucrubro) {
        this.plucrubro = plucrubro;
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

    public Empresas getPluempresa() {
        return pluempresa;
    }

    public void setPluempresa(Empresas pluempresa) {
        this.pluempresa = pluempresa;
    }

    public Clasificaciones getPluclasificaciones() {
        return pluclasificaciones;
    }

    public void setPluclasificaciones(Clasificaciones pluclasificaciones) {
        this.pluclasificaciones = pluclasificaciones;
    }

    @XmlTransient
    public List<Cuentas> getCuentasList() {
        return cuentasList;
    }

    public void setCuentasList(List<Cuentas> cuentasList) {
        this.cuentasList = cuentasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plucrubro != null ? plucrubro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crubros)) {
            return false;
        }
        Crubros other = (Crubros) object;
        if ((this.plucrubro == null && other.plucrubro != null) || (this.plucrubro != null && !this.plucrubro.equals(other.plucrubro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Crubros[ plucrubro=" + plucrubro + " ]";
    }
    
}
