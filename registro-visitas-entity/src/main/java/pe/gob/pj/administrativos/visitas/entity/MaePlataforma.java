package pe.gob.pj.administrativos.visitas.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="MAE_PLATAFORMA", schema = BaseEntity.ESQUEMA_BD)
public class MaePlataforma extends BaseEntity implements Serializable{
	
	@Id
	@SequenceGenerator(name="MAE_PLATAFORMA_GENERATOR", sequenceName="USRREGVIS.USEQ_MAE_PLATAFORMA", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAE_PLATAFORMA_GENERATOR")
	@Column(name="N_PLATAFORMA")
	private int nPlataforma;
	@Column(name="X_DESCRIPCION")
	private String xDescripcion;
	@Column(name="L_ESTADO",length=1)
	private String lEstado;
	@Column(name = "F_REGISTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fRegistro;
	
	public int getnPlataforma() {
		return nPlataforma;
	}
	public void setnPlataforma(int nPlataforma) {
		this.nPlataforma = nPlataforma;
	}
	public String getxDescripcion() {
		return xDescripcion;
	}
	public void setxDescripcion(String xDescripcion) {
		this.xDescripcion = xDescripcion;
	}
	public String getlEstado() {
		return lEstado;
	}
	public void setlEstado(String lEstado) {
		this.lEstado = lEstado;
	}
	public Date getfRegistro() {
		return fRegistro;
	}
	public void setfRegistro(Date fRegistro) {
		this.fRegistro = fRegistro;
	}
	
}
