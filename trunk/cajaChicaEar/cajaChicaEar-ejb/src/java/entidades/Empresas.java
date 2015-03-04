/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author trompudo
 */
@Entity
@Table(name = "EMPRESAS", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresas.findAll", query = "SELECT e FROM Empresas e"),
    @NamedQuery(name = "Empresas.findByPluempresa", query = "SELECT e FROM Empresas e WHERE e.pluempresa = :pluempresa"),
    @NamedQuery(name = "Empresas.findByCodigo", query = "SELECT e FROM Empresas e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "Empresas.findByNombre", query = "SELECT e FROM Empresas e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Empresas.findByDireccion1", query = "SELECT e FROM Empresas e WHERE e.direccion1 = :direccion1"),
    @NamedQuery(name = "Empresas.findByDireccion2", query = "SELECT e FROM Empresas e WHERE e.direccion2 = :direccion2"),
    @NamedQuery(name = "Empresas.findByCiudad", query = "SELECT e FROM Empresas e WHERE e.ciudad = :ciudad"),
    @NamedQuery(name = "Empresas.findByPais", query = "SELECT e FROM Empresas e WHERE e.pais = :pais"),
    @NamedQuery(name = "Empresas.findByTelefono", query = "SELECT e FROM Empresas e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "Empresas.findByFax", query = "SELECT e FROM Empresas e WHERE e.fax = :fax"),
    @NamedQuery(name = "Empresas.findByNoregistro", query = "SELECT e FROM Empresas e WHERE e.noregistro = :noregistro"),
    @NamedQuery(name = "Empresas.findByNit", query = "SELECT e FROM Empresas e WHERE e.nit = :nit"),
    @NamedQuery(name = "Empresas.findByDepartamento", query = "SELECT e FROM Empresas e WHERE e.departamento = :departamento"),
    @NamedQuery(name = "Empresas.findByTipoempresa", query = "SELECT e FROM Empresas e WHERE e.tipoempresa = :tipoempresa"),
    @NamedQuery(name = "Empresas.findByEmail", query = "SELECT e FROM Empresas e WHERE e.email = :email"),
    @NamedQuery(name = "Empresas.findByWeb", query = "SELECT e FROM Empresas e WHERE e.web = :web"),
    @NamedQuery(name = "Empresas.findByGiro", query = "SELECT e FROM Empresas e WHERE e.giro = :giro"),
    @NamedQuery(name = "Empresas.findByActiva", query = "SELECT e FROM Empresas e WHERE e.activa = :activa")})
public class Empresas implements Serializable {
   
    @Column(name = "FECHAINICIAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainicial;
    @Column(name = "FECHAFINAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafinal;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLUEMPRESA", nullable = false)
    private Integer pluempresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODIGO", nullable = false, length = 10)
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE", nullable = false, length = 60)
    private String nombre;
    @Size(max = 60)
    @Column(name = "DIRECCION1", length = 60)
    private String direccion1;
    @Size(max = 60)
    @Column(name = "DIRECCION2", length = 60)
    private String direccion2;
    @Size(max = 20)
    @Column(name = "CIUDAD", length = 20)
    private String ciudad;
    @Size(max = 20)
    @Column(name = "PAIS", length = 20)
    private String pais;
    @Size(max = 20)
    @Column(name = "TELEFONO", length = 20)
    private String telefono;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Formato tel./fax. invalido, debe ser xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "FAX", length = 20)
    private String fax;
    @Size(max = 10)
    @Column(name = "NOREGISTRO", length = 10)
    private String noregistro;
    @Size(max = 20)
    @Column(name = "NIT", length = 20)
    private String nit;
    @Size(max = 20)
    @Column(name = "DEPARTAMENTO", length = 20)
    private String departamento;
   
    @Column(name = "TIPOEMPRESA")
    private Character tipoempresa;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Correo electrónico no válido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 60)
    @Column(name = "EMAIL", length = 60)
    private String email;
    @Size(max = 60)
    @Column(name = "WEB", length = 60)
    private String web;
    @Size(max = 100)
    @Column(name = "GIRO", length = 100)
    private String giro;
    @Column(name = "ACTIVA")
    private Character activa;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pluempresa", fetch = FetchType.EAGER)
    private List<Crubros> crubrosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idempresa", fetch = FetchType.EAGER)
    private List<Tipodoc> cchTipodocList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pluempresa", fetch = FetchType.EAGER)
    private List<Cuentas> cuentasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idempresa", fetch = FetchType.EAGER)
    private List<CchCanal> cchCanalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pluempresa", fetch = FetchType.EAGER)
    private List<Clasificaciones> clasificacionesList;
    
    public Empresas() {
    }

    public Empresas(Integer pluempresa) {
        this.pluempresa = pluempresa;
    }

    public Empresas(Integer pluempresa, String codigo, String nombre) {
        this.pluempresa = pluempresa;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getPluempresa() {
        return pluempresa;
    }

    public void setPluempresa(Integer pluempresa) {
        this.pluempresa = pluempresa;
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

    public String getDireccion1() {
        return direccion1;
    }

    public void setDireccion1(String direccion1) {
        this.direccion1 = direccion1;
    }

    public String getDireccion2() {
        return direccion2;
    }

    public void setDireccion2(String direccion2) {
        this.direccion2 = direccion2;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getNoregistro() {
        return noregistro;
    }

    public void setNoregistro(String noregistro) {
        this.noregistro = noregistro;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }


    public Character getTipoempresa() {
        return tipoempresa;
    }

    public void setTipoempresa(Character tipoempresa) {
        this.tipoempresa = tipoempresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getGiro() {
        return giro;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }

    public Character getActiva() {
        return activa;
    }

    public void setActiva(Character activa) {
        this.activa = activa;
    }

    @XmlTransient
    public List<Crubros> getCrubrosList() {
        return crubrosList;
    }

    public void setCrubrosList(List<Crubros> crubrosList) {
        this.crubrosList = crubrosList;
    }

    @XmlTransient
    public List<Tipodoc> getCchTipodocList() {
        return cchTipodocList;
    }

    public void setCchTipodocList(List<Tipodoc> cchTipodocList) {
        this.cchTipodocList = cchTipodocList;
    }

    @XmlTransient
    public List<Cuentas> getCuentasList() {
        return cuentasList;
    }

    public void setCuentasList(List<Cuentas> cuentasList) {
        this.cuentasList = cuentasList;
    }

    @XmlTransient
    public List<CchCanal> getCchCanalList() {
        return cchCanalList;
    }

    public void setCchCanalList(List<CchCanal> cchCanalList) {
        this.cchCanalList = cchCanalList;
    }

    @XmlTransient
    public List<Clasificaciones> getClasificacionesList() {
        return clasificacionesList;
    }

    public void setClasificacionesList(List<Clasificaciones> clasificacionesList) {
        this.clasificacionesList = clasificacionesList;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pluempresa != null ? pluempresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresas)) {
            return false;
        }
        Empresas other = (Empresas) object;
        if ((this.pluempresa == null && other.pluempresa != null) || (this.pluempresa != null && !this.pluempresa.equals(other.pluempresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Empresas[ pluempresa=" + pluempresa + " ]";
    }


    public Date getFechainicial() {
        return fechainicial;
    }

    public void setFechainicial(Date fechainicial) {
        this.fechainicial = fechainicial;
    }

    public Date getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(Date fechafinal) {
        this.fechafinal = fechafinal;
    }
    
}
