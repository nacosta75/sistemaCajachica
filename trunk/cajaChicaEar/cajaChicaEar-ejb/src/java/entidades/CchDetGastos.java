/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author trompudo
 */
@Entity
@Table(name = "CCH_DET_GASTOS", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CchDetGastos.findAll", query = "SELECT c FROM CchDetGastos c"),
    @NamedQuery(name = "CchDetGastos.findByIddetgasto", query = "SELECT c FROM CchDetGastos c WHERE c.iddetgasto = :iddetgasto"),
    @NamedQuery(name = "CchDetGastos.findByProcesado", query = "SELECT c FROM CchDetGastos c WHERE c.procesado = :procesado"),
    @NamedQuery(name = "CchDetGastos.findByFechaIng", query = "SELECT c FROM CchDetGastos c WHERE c.fechaIng = :fechaIng"),
    @NamedQuery(name = "CchDetGastos.findByFechaDoc", query = "SELECT c FROM CchDetGastos c WHERE c.fechaDoc = :fechaDoc"),
    @NamedQuery(name = "CchDetGastos.findByNumDoc", query = "SELECT c FROM CchDetGastos c WHERE c.numDoc = :numDoc"),
    @NamedQuery(name = "CchDetGastos.findByProvRegistro", query = "SELECT c FROM CchDetGastos c WHERE c.provRegistro = :provRegistro"),
    @NamedQuery(name = "CchDetGastos.findByProvNombre", query = "SELECT c FROM CchDetGastos c WHERE c.provNombre = :provNombre"),
    @NamedQuery(name = "CchDetGastos.findByCuentacontable", query = "SELECT c FROM CchDetGastos c WHERE c.cuentacontable = :cuentacontable"),
    @NamedQuery(name = "CchDetGastos.findByConcepto", query = "SELECT c FROM CchDetGastos c WHERE c.concepto = :concepto"),
    @NamedQuery(name = "CchDetGastos.findByGravado", query = "SELECT c FROM CchDetGastos c WHERE c.gravado = :gravado"),
    @NamedQuery(name = "CchDetGastos.findByIva", query = "SELECT c FROM CchDetGastos c WHERE c.iva = :iva"),
    @NamedQuery(name = "CchDetGastos.findByFovial", query = "SELECT c FROM CchDetGastos c WHERE c.fovial = :fovial"),
    @NamedQuery(name = "CchDetGastos.findByTotal", query = "SELECT c FROM CchDetGastos c WHERE c.total = :total"),
    @NamedQuery(name = "CchDetGastos.findByGalSegDoc", query = "SELECT c FROM CchDetGastos c WHERE c.galSegDoc = :galSegDoc"),
    @NamedQuery(name = "CchDetGastos.findByKmActual", query = "SELECT c FROM CchDetGastos c WHERE c.kmActual = :kmActual"),
    @NamedQuery(name = "CchDetGastos.findByIdLiq", query = "SELECT c FROM CchDetGastos c WHERE c.idliq = :idliq"),
    @NamedQuery(name = "CchDetGastos.findByNoPlaca", query = "SELECT c FROM CchDetGastos c WHERE c.noPlaca = :noPlaca")})
public class CchDetGastos implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iddetgasto;
    @Column(name = "PROCESADO")
    private String procesado;
    
    
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_ING")
    private Date fechaIng;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_DOC")
    private Date fechaDoc;
    
   
    @Size(max = 30)
    @Column(name = "NUM_DOC", length = 30)
    private String numDoc;
    @Size(max = 30)
    @Column(name = "PROV_REGISTRO", length = 30)
    private String provRegistro;
    @Size(max = 60)
    @Column(name = "PROV_NOMBRE", length = 60)
    private String provNombre;
    @Size(max = 30)
    @Column(name = "CUENTACONTABLE", length = 30)
    private String cuentacontable;
    @Size(max = 100)
    @Column(name = "CONCEPTO", length = 100)
    private String concepto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "GRAVADO", precision = 9, scale = 2)
    private BigDecimal gravado;
    @Column(name = "IVA", precision = 9, scale = 2)
    private BigDecimal iva;
    @Column(name = "FOVIAL", precision = 9, scale = 2)
    private BigDecimal fovial;
    @Column(name = "TOTAL", precision = 9, scale = 2)
    private BigDecimal total;
    @Column(name = "GAL_SEG_DOC", precision = 9, scale = 2)
    private BigDecimal galSegDoc;
    @Column(name = "KM_ACTUAL")
    private Integer kmActual;
    @Size(max = 20)
    @Column(name = "NO_PLACA", length = 20)
    private String noPlaca;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuarios idusuario;
    @JoinColumn(name = "IDTIPODOC", referencedColumnName = "IDTIPODOC", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tipodoc idtipodoc;
    @JoinColumn(name = "IDGASTO", referencedColumnName = "IDGCATALOGO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CchCatalogo idgasto;
    @JoinColumn(name = "IDCANAL", referencedColumnName = "IDCANAL", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CchCanal idcanal;
    @JoinColumn(name = "IDSUCURSAL", referencedColumnName = "PLUBODEGA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Bodegas idsucursal;
    
    private Integer idliq;

    public Integer getIdliq() {
        return idliq;
    }

    public void setIdliq(Integer idliq) {
        this.idliq = idliq;
    }
    

    public CchDetGastos() {
    }

    public CchDetGastos(Integer iddetgasto) {
        this.iddetgasto = iddetgasto;
    }

    public Integer getIddetgasto() {
        return iddetgasto;
    }

    public void setIddetgasto(Integer iddetgasto) {
        this.iddetgasto = iddetgasto;
    }

    public String getProcesado() {
        return procesado;
    }

    public void setProcesado(String procesado) {
        this.procesado = procesado;
    }

    public Date getFechaIng() {
        return fechaIng;
    }

    public void setFechaIng(Date fechaIng) {
        this.fechaIng = fechaIng;
    }

    public Date getFechaDoc() {
        return fechaDoc;
    }

    public void setFechaDoc(Date fechaDoc) {
        this.fechaDoc = fechaDoc;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public String getProvRegistro() {
        return provRegistro;
    }

    public void setProvRegistro(String provRegistro) {
        this.provRegistro = provRegistro;
    }

    public String getProvNombre() {
        return provNombre;
    }

    public void setProvNombre(String provNombre) {
        this.provNombre = provNombre;
    }

    public String getCuentacontable() {
        return cuentacontable;
    }

    public void setCuentacontable(String cuentacontable) {
        this.cuentacontable = cuentacontable;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getGravado() {
        return gravado;
    }

    public void setGravado(BigDecimal gravado) {
        this.gravado = gravado;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getFovial() {
        return fovial;
    }

    public void setFovial(BigDecimal fovial) {
        this.fovial = fovial;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getGalSegDoc() {
        return galSegDoc;
    }

    public void setGalSegDoc(BigDecimal galSegDoc) {
        this.galSegDoc = galSegDoc;
    }

    public Integer getKmActual() {
        return kmActual;
    }

    public void setKmActual(Integer kmActual) {
        this.kmActual = kmActual;
    }

    public String getNoPlaca() {
        return noPlaca;
    }

    public void setNoPlaca(String noPlaca) {
        this.noPlaca = noPlaca;
    }

    public Usuarios getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuarios idusuario) {
        this.idusuario = idusuario;
    }

    public Tipodoc getIdtipodoc() {
        return idtipodoc;
    }

    public void setIdtipodoc(Tipodoc idtipodoc) {
        this.idtipodoc = idtipodoc;
    }

    public CchCatalogo getIdgasto() {
        return idgasto;
    }

    public void setIdgasto(CchCatalogo idgasto) {
        this.idgasto = idgasto;
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
        hash += (iddetgasto != null ? iddetgasto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CchDetGastos)) {
            return false;
        }
        CchDetGastos other = (CchDetGastos) object;
        if ((this.iddetgasto == null && other.iddetgasto != null) || (this.iddetgasto != null && !this.iddetgasto.equals(other.iddetgasto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CchDetGastos[ iddetgasto=" + iddetgasto + " ]";
    }
    
}
