/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "CCH_USUARIO", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT c FROM Usuarios c"),
    @NamedQuery(name = "Usuarios.findByIdusuario", query = "SELECT c FROM Usuarios c WHERE c.idusuario = :idusuario"),
    @NamedQuery(name = "Usuarios.findByNombre", query = "SELECT c FROM Usuarios c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Usuarios.findByUsuario", query = "SELECT c FROM Usuarios c WHERE c.usuario = :usuario"),
    @NamedQuery(name = "Usuarios.findByClave", query = "SELECT c FROM Usuarios c WHERE c.clave = :clave"),
    @NamedQuery(name = "Usuarios.findByActivo", query = "SELECT c FROM Usuarios c WHERE c.activo = :activo")})
public class Usuarios implements Serializable {
    
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUSUARIO")
    private Integer idusuario;
   
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVO")
    private String activo;
    
    @OneToMany(mappedBy = "idusuario")
    private Collection<CchLiquidacion> cchLiquidacionCollection;
    private static final long serialVersionUID = 1L;
    
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE", nullable = false, length = 60)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USUARIO", nullable = false, length = 30)
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "CLAVE", nullable = false, length = 60)
    private String clave;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario", fetch = FetchType.EAGER)
    private List<CchDetGastos> cchDetGastosList;

    private Integer idbodega;
    
    private Integer nivel;
    
    private String general;

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }
    
    public Integer getIdbodega() {
        return idbodega;
    }

    public void setIdbodega(Integer idbodega) {
        this.idbodega = idbodega;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
    
    
    
    public Usuarios() {
    }

    public Usuarios(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuarios(Integer idusuario, String nombre, String usuario, String clave, String activo) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.clave = clave;
        this.activo = activo;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @XmlTransient
    public List<CchDetGastos> getCchDetGastosList() {
        return cchDetGastosList;
    }

    public void setCchDetGastosList(List<CchDetGastos> cchDetGastosList) {
        this.cchDetGastosList = cchDetGastosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CchUsuario[ idusuario=" + idusuario + " ]";
    }

    
    public Usuarios(Integer idusuario, String activo) {
        this.idusuario = idusuario;
        this.activo = activo;
    }

    

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @XmlTransient
    public Collection<CchLiquidacion> getCchLiquidacionCollection() {
        return cchLiquidacionCollection;
    }

    public void setCchLiquidacionCollection(Collection<CchLiquidacion> cchLiquidacionCollection) {
        this.cchLiquidacionCollection = cchLiquidacionCollection;
    }

    
}
