/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 *
 * @author trompudo
 */
@Entity
public class Bodegas implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private Integer plubodega;
    
    @Size(min = 1, max = 10)
    @Column(name = "CODIGO")
    private String codigo;
    @Size(max = 60)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 60)
    @Column(name = "DIRECCION1")
    private String direccion1;
    @Size(max = 20)
    @Column(name = "CIUDAD")
    private String ciudad;
    @Size(max = 20)
    @Column(name = "TELEFONO")
    private String telefono;
  
    @Size(max = 20)
    @Column(name = "FAX")
    private String fax;
    @Size(max = 60)
    @Column(name = "ENCARGADO")
    private String encargado;
    @Column(name = "ACTIVA")
    private String activa;
    @Column(name = "LOCAL")
    private String local;
    @Size(max = 15)
    @Column(name = "DIR_IP")
    private String dirIp;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CAJA_CHICA")
    private BigDecimal cajaChica;
    
    @Column(name ="CTACONTABLE")
    private String ctacontable;

    public String getCtacontable() {
        return ctacontable;
    }

    public void setCtacontable(String ctacontable) {
        this.ctacontable = ctacontable;
    }
       
  
    private Integer pluempresas;

    public Integer getPlubodega() {
        return plubodega;
    }

    public void setPlubodega(Integer plubodega) {
        this.plubodega = plubodega;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getActiva() {
        return activa;
    }

    public void setActiva(String activa) {
        this.activa = activa;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDirIp() {
        return dirIp;
    }

    public void setDirIp(String dirIp) {
        this.dirIp = dirIp;
    }

    public BigDecimal getCajaChica() {
        return cajaChica;
    }

    public void setCajaChica(BigDecimal cajaChica) {
        this.cajaChica = cajaChica;
    }

    public Integer getPluempresas() {
        return pluempresas;
    }

    public void setPluempresas(Integer pluempresas) {
        this.pluempresas = pluempresas;
    }

    

    
    
    
}
