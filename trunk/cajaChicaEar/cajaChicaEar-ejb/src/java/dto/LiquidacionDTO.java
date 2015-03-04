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
public class LiquidacionDTO {
    
   private int idliq;
   private Date fecha;
   private int idusuario ;
   private BigDecimal totalCaja ;
   private BigDecimal totalDocs ;
   private BigDecimal totalEfec ;
   private BigDecimal faltante ;
   private String observaciones;
   private int cantDocs;
   private int idSucursal;
   private String post;
   private Boolean procesado;

    public Boolean isProcesado() {
        return procesado;
    }

    public void setProcesado(Boolean procesado) {
        this.procesado = procesado;
    }
   
    

    public int getIdliq() {
        return idliq;
    }

    public void setIdliq(int idliq) {
        this.idliq = idliq;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
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

    public int getCantDocs() {
        return cantDocs;
    }

    public void setCantDocs(int cantDocs) {
        this.cantDocs = cantDocs;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
   
    
   
   
}
