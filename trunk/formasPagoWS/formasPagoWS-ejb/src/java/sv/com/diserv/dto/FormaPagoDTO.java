/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.diserv.dto;

import java.util.Date;

/**
 *
 * @author abraham.acosta
 */
public class FormaPagoDTO {
    
    private String codigo;
    private Date fecha;
    private String descripcion;
    private String observacion;
    private String tipo;
    private Double monto;
    private int liquidacion;
    private String sucursal;
    private String vendedor;
    private String usuario;
    private Date fechaLiq;
    private int codliq;
    private int idsucursal;
    private int idvendedor;
    private int idliq;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCodliq() {
        return codliq;
    }

    public void setCodliq(int codliq) {
        this.codliq = codliq;
    }
           
    
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public int getLiquidacion() {
        return liquidacion;
    }

    public void setLiquidacion(int liquidacion) {
        this.liquidacion = liquidacion;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
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

    public Date getFechaLiq() {
        return fechaLiq;
    }

    public void setFechaLiq(Date fechaLiq) {
        this.fechaLiq = fechaLiq;
    }

    public int getIdsucursal() {
        return idsucursal;
    }

    public void setIdsucursal(int idsucursal) {
        this.idsucursal = idsucursal;
    }

    public int getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(int idvendedor) {
        this.idvendedor = idvendedor;
    }

    public int getIdliq() {
        return idliq;
    }

    public void setIdliq(int idliq) {
        this.idliq = idliq;
    }
    
    
    
    
    
    
}
