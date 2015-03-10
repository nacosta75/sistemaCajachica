/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.diserv.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario01
 */
@Entity
@Table(name = "REMESAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Remesas.findAll", query = "SELECT r FROM Remesas r"),
    @NamedQuery(name = "Remesas.findByIdremesa", query = "SELECT r FROM Remesas r WHERE r.idremesa = :idremesa"),
    @NamedQuery(name = "Remesas.findByFecha", query = "SELECT r FROM Remesas r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "Remesas.findByTipo", query = "SELECT r FROM Remesas r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Remesas.findByCodpago", query = "SELECT r FROM Remesas r WHERE r.codpago = :codpago"),
    @NamedQuery(name = "Remesas.findByDescripcion", query = "SELECT r FROM Remesas r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "Remesas.findByObservacion", query = "SELECT r FROM Remesas r WHERE r.observacion = :observacion"),
    @NamedQuery(name = "Remesas.findByMonto", query = "SELECT r FROM Remesas r WHERE r.monto = :monto"),
    @NamedQuery(name = "Remesas.findByFechaliq", query = "SELECT r FROM Remesas r WHERE r.fechaliq = :fechaliq"),
    @NamedQuery(name = "Remesas.findByCodliq", query = "SELECT r FROM Remesas r WHERE r.codliq = :codliq"),
    @NamedQuery(name = "Remesas.findByIdsucursal", query = "SELECT r FROM Remesas r WHERE r.idsucursal = :idsucursal"),
    @NamedQuery(name = "Remesas.findBySucursal", query = "SELECT r FROM Remesas r WHERE r.sucursal = :sucursal"),
    @NamedQuery(name = "Remesas.findByIdvendedor", query = "SELECT r FROM Remesas r WHERE r.idvendedor = :idvendedor"),
    @NamedQuery(name = "Remesas.findByVendedor", query = "SELECT r FROM Remesas r WHERE r.vendedor = :vendedor"),
    @NamedQuery(name = "Remesas.findByUsuario", query = "SELECT r FROM Remesas r WHERE r.usuario = :usuario"),
    @NamedQuery(name = "Remesas.findByIdliq", query = "SELECT r FROM Remesas r WHERE r.idliq = :idliq")})
public class Remesas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDREMESA")
    private Integer idremesa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 10)
    @Column(name = "TIPO")
    private String tipo;
    @Size(max = 30)
    @Column(name = "CODPAGO")
    private String codpago;
    @Size(max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 50)
    @Column(name = "OBSERVACION")
    private String observacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONTO")
    private BigDecimal monto;
    @Column(name = "FECHALIQ")
    @Temporal(TemporalType.DATE)
    private Date fechaliq;
    @Column(name = "CODLIQ")
    private Integer codliq;
    @Column(name = "IDSUCURSAL")
    private Integer idsucursal;
    @Size(max = 30)
    @Column(name = "SUCURSAL")
    private String sucursal;
    @Column(name = "IDVENDEDOR")
    private Integer idvendedor;
    @Size(max = 60)
    @Column(name = "VENDEDOR")
    private String vendedor;
    @Size(max = 30)
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "IDLIQ")
    private Integer idliq;

    public Remesas() {
    }

    public Remesas(Integer idremesa) {
        this.idremesa = idremesa;
    }

    public Remesas(Integer idremesa, Date fecha) {
        this.idremesa = idremesa;
        this.fecha = fecha;
    }

    public Integer getIdremesa() {
        return idremesa;
    }

    public void setIdremesa(Integer idremesa) {
        this.idremesa = idremesa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodpago() {
        return codpago;
    }

    public void setCodpago(String codpago) {
        this.codpago = codpago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFechaliq() {
        return fechaliq;
    }

    public void setFechaliq(Date fechaliq) {
        this.fechaliq = fechaliq;
    }

    public Integer getCodliq() {
        return codliq;
    }

    public void setCodliq(Integer codliq) {
        this.codliq = codliq;
    }

    public Integer getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(Integer idsucursal) {
        this.idsucursal = idsucursal;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public Integer getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(Integer idvendedor) {
        this.idvendedor = idvendedor;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getIdliq() {
        return idliq;
    }

    public void setIdliq(Integer idliq) {
        this.idliq = idliq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idremesa != null ? idremesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Remesas)) {
            return false;
        }
        Remesas other = (Remesas) object;
        if ((this.idremesa == null && other.idremesa != null) || (this.idremesa != null && !this.idremesa.equals(other.idremesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.diserv.entity.Remesas[ idremesa=" + idremesa + " ]";
    }
    
}
