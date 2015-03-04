/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author trompudo
 */
@Entity
@Table(name = "CCH_LIQUIDACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CchLiquidacion.findAll", query = "SELECT c FROM CchLiquidacion c"),
    @NamedQuery(name = "CchLiquidacion.findByIdliq", query = "SELECT c FROM CchLiquidacion c WHERE c.idliq = :idliq"),
    @NamedQuery(name = "CchLiquidacion.findByFecha", query = "SELECT c FROM CchLiquidacion c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "CchLiquidacion.findByTotalCaja", query = "SELECT c FROM CchLiquidacion c WHERE c.totalCaja = :totalCaja"),
    @NamedQuery(name = "CchLiquidacion.findByTotalDocs", query = "SELECT c FROM CchLiquidacion c WHERE c.totalDocs = :totalDocs"),
    @NamedQuery(name = "CchLiquidacion.findByTotalEfec", query = "SELECT c FROM CchLiquidacion c WHERE c.totalEfec = :totalEfec"),
    @NamedQuery(name = "CchLiquidacion.findByFaltante", query = "SELECT c FROM CchLiquidacion c WHERE c.faltante = :faltante"),
    @NamedQuery(name = "CchLiquidacion.findByObservaciones", query = "SELECT c FROM CchLiquidacion c WHERE c.observaciones = :observaciones"),
    @NamedQuery(name = "CchLiquidacion.findByCantDocs", query = "SELECT c FROM CchLiquidacion c WHERE c.cantDocs = :cantDocs"),
    @NamedQuery(name = "CchLiquidacion.findByPost", query = "SELECT c FROM CchLiquidacion c WHERE c.post = :post")})

public class CchLiquidacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDLIQ")
    private Integer idliq;
    
    
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TOTAL_CAJA")
    private BigDecimal totalCaja;
    @Column(name = "TOTAL_DOCS")
    private BigDecimal totalDocs;
    @Column(name = "TOTAL_EFEC")
    private BigDecimal totalEfec;
    @Column(name = "FALTANTE")
    private BigDecimal faltante;
    @Size(max = 60)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "CANT_DOCS")
    private Integer cantDocs;
    @Size(max = 1)
    @Column(name = "POST")
    private String post;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne
    private Usuarios idusuario;
    @JoinColumn(name = "IDSUCURSAL", referencedColumnName = "PLUBODEGA")
    @ManyToOne
    private Bodegas idsucursal;

    public CchLiquidacion() {
    }

    public CchLiquidacion(Integer idliq) {
        this.idliq = idliq;
    }

    public Integer getIdliq() {
        return idliq;
    }

    public void setIdliq(Integer idliq) {
        this.idliq = idliq;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotalCaja() {
        return totalCaja;
    }

    public void setTotalCaja(BigDecimal totalCaja) {
        this.totalCaja = totalCaja;
    }

    public BigDecimal getTotalDocs() {
        return totalDocs;
    }

    public void setTotalDocs(BigDecimal totalDocs) {
        this.totalDocs = totalDocs;
    }

    public BigDecimal getTotalEfec() {
        return totalEfec;
    }

    public void setTotalEfec(BigDecimal totalEfec) {
        this.totalEfec = totalEfec;
    }

    public BigDecimal getFaltante() {
        return faltante;
    }

    public void setFaltante(BigDecimal faltante) {
        this.faltante = faltante;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getCantDocs() {
        return cantDocs;
    }

    public void setCantDocs(Integer cantDocs) {
        this.cantDocs = cantDocs;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Usuarios getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuarios idusuario) {
        this.idusuario = idusuario;
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
        hash += (idliq != null ? idliq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CchLiquidacion)) {
            return false;
        }
        CchLiquidacion other = (CchLiquidacion) object;
        if ((this.idliq == null && other.idliq != null) || (this.idliq != null && !this.idliq.equals(other.idliq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CchLiquidacion[ idliq=" + idliq + " ]";
    }
    
}
