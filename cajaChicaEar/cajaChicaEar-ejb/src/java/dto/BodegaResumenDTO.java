/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dto;

import java.math.BigDecimal;

/**
 *
 * @author Noe Acosta
 */
public class BodegaResumenDTO {
    
    
    private int idbodega;
    private String bodega;
    private BigDecimal totCaja;
    private BigDecimal gastos;
    private BigDecimal saldo;
    private String titulo;

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
   
    public int getIdbodega() {
        return idbodega;
    }

    public void setIdbodega(int idbodega) {
        this.idbodega = idbodega;
    }

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public BigDecimal getTotCaja() {
        return totCaja;
    }

    public void setTotCaja(BigDecimal totCaja) {
        this.totCaja = totCaja;
    }

    public BigDecimal getGastos() {
        return gastos;
    }

    public void setGastos(BigDecimal gastos) {
        this.gastos = gastos;
    }

  
}
