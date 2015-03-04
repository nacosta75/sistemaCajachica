/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;


import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Noe Acosta
 */
public class DetGastosDTO {
    
  private int iddetgasto;
  private String provRegistro;
  private String provNombre;
  private Date fechaIng ;
  
  private String sucursal;
  private int idsucursal;
  private int idusuario;
  private String procesado;
  
  private Date fechaDoc;
  private int idtipoDoc;
  private String numDoc;
  
  private int idCanal;
  private int idGasto;
  private String ctaContable;
  private String concepto;
  private BigDecimal gravado;
  private BigDecimal iva;
  private BigDecimal fovial;
  private BigDecimal total;
  private BigDecimal galonesSD;
  private BigDecimal fondoCaja;
  private BigDecimal saldo;
  private int km_actual;
  private String placa;
  private String tipodoc;
  private BigDecimal debe;
  private BigDecimal haber;
  private Character tipoSaldo;

  
  
  

    public BigDecimal getDebe() {
        return debe;
    }

    public void setDebe(BigDecimal debe) {
        this.debe = debe;
    }

    public BigDecimal getHaber() {
        return haber;
    }

    public void setHaber(BigDecimal haber) {
        this.haber = haber;
    }

    public Character getTipoSaldo() {
        return tipoSaldo;
    }

    public void setTipoSaldo(Character tipoSaldo) {
        this.tipoSaldo = tipoSaldo;
    }
  
    public BigDecimal getFondoCaja() {
        return fondoCaja;
    }

    public void setFondoCaja(BigDecimal fondoCaja) {
        this.fondoCaja = fondoCaja;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
  
    public String getTipodoc() {
        return tipodoc;
    }

    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }
  
    public int getIddetgasto() {
        return iddetgasto;
    }

    public void setIddetgasto(int iddetgasto) {
        this.iddetgasto = iddetgasto;
    }

    public int getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(int idsucursal) {
        this.idsucursal = idsucursal;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
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

    public int getIdtipoDoc() {
        return idtipoDoc;
    }

    public void setIdtipoDoc(int idtipoDoc) {
        this.idtipoDoc = idtipoDoc;
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

    public int getIdCanal() {
        return idCanal;
    }

    public void setIdCanal(int idCanal) {
        this.idCanal = idCanal;
    }

    public int getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(int idGasto) {
        this.idGasto = idGasto;
    }

    public String getCtaContable() {
        return ctaContable;
    }

    public void setCtaContable(String ctaContable) {
        this.ctaContable = ctaContable;
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

    public BigDecimal getGalonesSD() {
        return galonesSD;
    }

    public void setGalonesSD(BigDecimal galonesSD) {
        this.galonesSD = galonesSD;
    }

    public int getKm_actual() {
        return km_actual;
    }

    public void setKm_actual(int km_actual) {
        this.km_actual = km_actual;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
  
  
}
