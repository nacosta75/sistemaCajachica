/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CUENTAS", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuentas.findAll", query = "SELECT c FROM Cuentas c"),
    @NamedQuery(name = "Cuentas.findByPlucuenta", query = "SELECT c FROM Cuentas c WHERE c.plucuenta = :plucuenta"),
    @NamedQuery(name = "Cuentas.findByCodigo", query = "SELECT c FROM Cuentas c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Cuentas.findByDetalle", query = "SELECT c FROM Cuentas c WHERE c.detalle = :detalle"),
    @NamedQuery(name = "Cuentas.findByNivel", query = "SELECT c FROM Cuentas c WHERE c.nivel = :nivel"),
    @NamedQuery(name = "Cuentas.findByTiposaldo", query = "SELECT c FROM Cuentas c WHERE c.tiposaldo = :tiposaldo"),
    @NamedQuery(name = "Cuentas.findByNombre", query = "SELECT c FROM Cuentas c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cuentas.findByPlumaestra", query = "SELECT c FROM Cuentas c WHERE c.plumaestra = :plumaestra"),
    @NamedQuery(name = "Cuentas.findByDescripcion", query = "SELECT c FROM Cuentas c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Cuentas.findByTipo", query = "SELECT c FROM Cuentas c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "Cuentas.findByAnexobal", query = "SELECT c FROM Cuentas c WHERE c.anexobal = :anexobal"),
    @NamedQuery(name = "Cuentas.findByReservalab", query = "SELECT c FROM Cuentas c WHERE c.reservalab = :reservalab"),
    @NamedQuery(name = "Cuentas.findByTipo2", query = "SELECT c FROM Cuentas c WHERE c.tipo2 = :tipo2"),
    @NamedQuery(name = "Cuentas.findByBalance", query = "SELECT c FROM Cuentas c WHERE c.balance = :balance"),
    @NamedQuery(name = "Cuentas.findByInventario", query = "SELECT c FROM Cuentas c WHERE c.inventario = :inventario"),
    @NamedQuery(name = "Cuentas.findByResultado", query = "SELECT c FROM Cuentas c WHERE c.resultado = :resultado"),
    @NamedQuery(name = "Cuentas.findByAnexoresul", query = "SELECT c FROM Cuentas c WHERE c.anexoresul = :anexoresul"),
    @NamedQuery(name = "Cuentas.findByCostoventa", query = "SELECT c FROM Cuentas c WHERE c.costoventa = :costoventa"),
    @NamedQuery(name = "Cuentas.findByReservaleg", query = "SELECT c FROM Cuentas c WHERE c.reservaleg = :reservaleg"),
    @NamedQuery(name = "Cuentas.findByUtilidad", query = "SELECT c FROM Cuentas c WHERE c.utilidad = :utilidad"),
    @NamedQuery(name = "Cuentas.findByGanancias", query = "SELECT c FROM Cuentas c WHERE c.ganancias = :ganancias"),
    @NamedQuery(name = "Cuentas.findByCapital", query = "SELECT c FROM Cuentas c WHERE c.capital = :capital"),
    @NamedQuery(name = "Cuentas.findByPagoacuenta", query = "SELECT c FROM Cuentas c WHERE c.pagoacuenta = :pagoacuenta"),
    @NamedQuery(name = "Cuentas.findByRenta", query = "SELECT c FROM Cuentas c WHERE c.renta = :renta"),
    @NamedQuery(name = "Cuentas.findByNombrecompleto", query = "SELECT c FROM Cuentas c WHERE c.nombrecompleto = :nombrecompleto"),
    @NamedQuery(name = "Cuentas.findByPerdidas", query = "SELECT c FROM Cuentas c WHERE c.perdidas = :perdidas"),
    @NamedQuery(name = "Cuentas.findByExento", query = "SELECT c FROM Cuentas c WHERE c.exento = :exento"),
    @NamedQuery(name = "Cuentas.findByPlucliente", query = "SELECT c FROM Cuentas c WHERE c.plucliente = :plucliente"),
    @NamedQuery(name = "Cuentas.findByBalanceDefinicion", query = "SELECT c FROM Cuentas c WHERE c.balanceDefinicion = :balanceDefinicion"),
    @NamedQuery(name = "Cuentas.findBySaldoactual", query = "SELECT c FROM Cuentas c WHERE c.saldoactual = :saldoactual"),
    @NamedQuery(name = "Cuentas.findBySaldoinicial", query = "SELECT c FROM Cuentas c WHERE c.saldoinicial = :saldoinicial"),
    @NamedQuery(name = "Cuentas.findByCuentamayor", query = "SELECT c FROM Cuentas c WHERE c.cuentamayor = :cuentamayor"),
    @NamedQuery(name = "Cuentas.findBySaldo", query = "SELECT c FROM Cuentas c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "Cuentas.findByPlubodega", query = "SELECT c FROM Cuentas c WHERE c.plubodega = :plubodega")})
public class Cuentas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PLUCUENTA", nullable = false)
    private Integer plucuenta;
    @Size(max = 20)
    @Column(name = "CODIGO", length = 20)
    private String codigo;
    @Column(name = "DETALLE")
    private Character detalle;
    @Column(name = "NIVEL")
    private Integer nivel;
    @Column(name = "TIPOSALDO")
    private Character tiposaldo;
    @Size(max = 60)
    @Column(name = "NOMBRE", length = 60)
    private String nombre;
    @Column(name = "PLUMAESTRA")
    private Integer plumaestra;
    @Size(max = 200)
    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;
    @Size(max = 5)
    @Column(name = "TIPO", length = 5)
    private String tipo;
    @Column(name = "ANEXOBAL")
    private Character anexobal;
    @Column(name = "RESERVALAB")
    private Character reservalab;
    @Size(max = 5)
    @Column(name = "TIPO2", length = 5)
    private String tipo2;
    @Column(name = "BALANCE")
    private Character balance;
    @Column(name = "INVENTARIO")
    private Character inventario;
    @Column(name = "RESULTADO")
    private Character resultado;
    @Column(name = "ANEXORESUL")
    private Character anexoresul;
    @Column(name = "COSTOVENTA")
    private Character costoventa;
    @Column(name = "RESERVALEG")
    private Character reservaleg;
    @Column(name = "UTILIDAD")
    private Character utilidad;
    @Column(name = "GANANCIAS")
    private Character ganancias;
    @Column(name = "CAPITAL")
    private Character capital;
    @Column(name = "PAGOACUENTA")
    private Character pagoacuenta;
    @Column(name = "RENTA")
    private Character renta;
    @Size(max = 100)
    @Column(name = "NOMBRECOMPLETO", length = 100)
    private String nombrecompleto;
    @Column(name = "PERDIDAS")
    private Character perdidas;
    @Column(name = "EXENTO")
    private Character exento;
    @Column(name = "PLUCLIENTE")
    private Integer plucliente;
    @Size(max = 2)
    @Column(name = "BALANCE_DEFINICION", length = 2)
    private String balanceDefinicion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SALDOACTUAL", precision = 15)
    private Double saldoactual;
    @Column(name = "SALDOINICIAL", precision = 15)
    private Double saldoinicial;
    @Column(name = "CUENTAMAYOR")
    private Integer cuentamayor;
    @Column(name = "SALDO", precision = 15)
    private Double saldo;
    @Column(name = "PLUBODEGA")
    private Integer plubodega;

    @JoinColumn(name = "PLUEMPRESA", referencedColumnName = "PLUEMPRESA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Empresas pluempresa;
    @JoinColumn(name = "PLUCRUBRO", referencedColumnName = "PLUCRUBRO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Crubros plucrubro;

    public Cuentas() {
    }

    public Cuentas(Integer plucuenta) {
        this.plucuenta = plucuenta;
    }

    public Integer getPlucuenta() {
        return plucuenta;
    }

    public void setPlucuenta(Integer plucuenta) {
        this.plucuenta = plucuenta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Character getDetalle() {
        return detalle;
    }

    public void setDetalle(Character detalle) {
        this.detalle = detalle;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Character getTiposaldo() {
        return tiposaldo;
    }

    public void setTiposaldo(Character tiposaldo) {
        this.tiposaldo = tiposaldo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPlumaestra() {
        return plumaestra;
    }

    public void setPlumaestra(Integer plumaestra) {
        this.plumaestra = plumaestra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Character getAnexobal() {
        return anexobal;
    }

    public void setAnexobal(Character anexobal) {
        this.anexobal = anexobal;
    }

    public Character getReservalab() {
        return reservalab;
    }

    public void setReservalab(Character reservalab) {
        this.reservalab = reservalab;
    }

    public String getTipo2() {
        return tipo2;
    }

    public void setTipo2(String tipo2) {
        this.tipo2 = tipo2;
    }

    public Character getBalance() {
        return balance;
    }

    public void setBalance(Character balance) {
        this.balance = balance;
    }

    public Character getInventario() {
        return inventario;
    }

    public void setInventario(Character inventario) {
        this.inventario = inventario;
    }

    public Character getResultado() {
        return resultado;
    }

    public void setResultado(Character resultado) {
        this.resultado = resultado;
    }

    public Character getAnexoresul() {
        return anexoresul;
    }

    public void setAnexoresul(Character anexoresul) {
        this.anexoresul = anexoresul;
    }

    public Character getCostoventa() {
        return costoventa;
    }

    public void setCostoventa(Character costoventa) {
        this.costoventa = costoventa;
    }

    public Character getReservaleg() {
        return reservaleg;
    }

    public void setReservaleg(Character reservaleg) {
        this.reservaleg = reservaleg;
    }

    public Character getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(Character utilidad) {
        this.utilidad = utilidad;
    }

    public Character getGanancias() {
        return ganancias;
    }

    public void setGanancias(Character ganancias) {
        this.ganancias = ganancias;
    }

    public Character getCapital() {
        return capital;
    }

    public void setCapital(Character capital) {
        this.capital = capital;
    }

    public Character getPagoacuenta() {
        return pagoacuenta;
    }

    public void setPagoacuenta(Character pagoacuenta) {
        this.pagoacuenta = pagoacuenta;
    }

    public Character getRenta() {
        return renta;
    }

    public void setRenta(Character renta) {
        this.renta = renta;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public Character getPerdidas() {
        return perdidas;
    }

    public void setPerdidas(Character perdidas) {
        this.perdidas = perdidas;
    }

    public Character getExento() {
        return exento;
    }

    public void setExento(Character exento) {
        this.exento = exento;
    }

    public Integer getPlucliente() {
        return plucliente;
    }

    public void setPlucliente(Integer plucliente) {
        this.plucliente = plucliente;
    }

    public String getBalanceDefinicion() {
        return balanceDefinicion;
    }

    public void setBalanceDefinicion(String balanceDefinicion) {
        this.balanceDefinicion = balanceDefinicion;
    }

    public Double getSaldoactual() {
        return saldoactual;
    }

    public void setSaldoactual(Double saldoactual) {
        this.saldoactual = saldoactual;
    }

    public Double getSaldoinicial() {
        return saldoinicial;
    }

    public void setSaldoinicial(Double saldoinicial) {
        this.saldoinicial = saldoinicial;
    }

    public Integer getCuentamayor() {
        return cuentamayor;
    }

    public void setCuentamayor(Integer cuentamayor) {
        this.cuentamayor = cuentamayor;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Integer getPlubodega() {
        return plubodega;
    }

    public void setPlubodega(Integer plubodega) {
        this.plubodega = plubodega;
    }

 

    public Empresas getPluempresa() {
        return pluempresa;
    }

    public void setPluempresa(Empresas pluempresa) {
        this.pluempresa = pluempresa;
    }

    public Crubros getPlucrubro() {
        return plucrubro;
    }

    public void setPlucrubro(Crubros plucrubro) {
        this.plucrubro = plucrubro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plucuenta != null ? plucuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuentas)) {
            return false;
        }
        Cuentas other = (Cuentas) object;
        if ((this.plucuenta == null && other.plucuenta != null) || (this.plucuenta != null && !this.plucuenta.equals(other.plucuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cuentas[ plucuenta=" + plucuenta + " ]";
    }
    
}
