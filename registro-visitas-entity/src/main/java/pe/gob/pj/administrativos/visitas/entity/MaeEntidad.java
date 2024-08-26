package pe.gob.pj.administrativos.visitas.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MAE_ENTIDAD", schema = BaseEntity.ESQUEMA_BD)
public class MaeEntidad extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="MAE_ENTIDAD_GENERATOR", sequenceName="USRREGVIS.USEQ_MAE_ENTIDAD", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAE_ENTIDAD_GENERATOR")
	
	@Column(name = "N_ID_ENTIDAD")
	private Long nIdEntidad;
	@Column(name = "X_RUC")
	private String xRuc;
	@Column(name = "X_RAZON_SOCIAL")
	private String xRazonSocial;
	@Column(name = "L_FLAG_ACTIVO")
	private String lFlagActivo;
	@Column(name = "F_REGISTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fRegistro = new Date();
	@OneToMany(mappedBy = "maeEntidad", fetch = FetchType.LAZY)
	private List<MovVisita> movVisitaList;

	public Long getnIdEntidad() {
		return nIdEntidad;
	}

	public void setnIdEntidad(Long nIdEntidad) {
		this.nIdEntidad = nIdEntidad;
	}


	public Date getfRegistro() {
		return fRegistro;
	}

	public void setfRegistro(Date fRegistro) {
		this.fRegistro = fRegistro;
	}

	public List<MovVisita> getMovVisitaList() {
		return movVisitaList;
	}

	public void setMovVisitaList(List<MovVisita> movVisitaList) {
		this.movVisitaList = movVisitaList;
	}

	public String getxRuc() {
		return xRuc;
	}

	public void setxRuc(String xRuc) {
		this.xRuc = xRuc;
	}


	public String getxRazonSocial() {
		return xRazonSocial;
	}

	public void setxRazonSocial(String xRazonSocial) {
		this.xRazonSocial = xRazonSocial;
	}

	public String getlFlagActivo() {
		return lFlagActivo;
	}

	public void setlFlagActivo(String lFlagActivo) {
		this.lFlagActivo = lFlagActivo;
	}
	
	

}
