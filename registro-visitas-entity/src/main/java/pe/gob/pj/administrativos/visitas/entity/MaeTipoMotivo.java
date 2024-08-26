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
@Table(name = "MAE_TIPO_MOTIVO", schema = BaseEntity.ESQUEMA_BD)
public class MaeTipoMotivo extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="MAE_TIPO_MOTIVO_GENERATOR", sequenceName="USRREGVIS.USEQ_MAE_TIPO_MOTIVO", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAE_TIPO_MOTIVO_GENERATOR")
	@Column(name = "N_ID_TIPO_MOTIVO")
	private Long nIdTipoMotivo;
	
	@Column(name = "X_DESCRIPCION")
	private String xDescripcion;
	
	@Column(name = "L_FLAG_ACTIVO")
	private String lFlagActivo;
	
	@Column(name = "F_REGISTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fRegistro = new Date();
	
    @OneToMany(mappedBy = "maeTipoMotivo", fetch = FetchType.LAZY)
    private List<MovVisita> movVisitaList;

	public Long getnIdTipoMotivo() {
		return nIdTipoMotivo;
	}

	public void setnIdTipoMotivo(Long nIdTipoMotivo) {
		this.nIdTipoMotivo = nIdTipoMotivo;
	}

	public String getxDescripcion() {
		return xDescripcion;
	}

	public void setxDescripcion(String xDescripcion) {
		this.xDescripcion = xDescripcion;
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

	public String getlFlagActivo() {
		return lFlagActivo;
	}

	public void setlFlagActivo(String lFlagActivo) {
		this.lFlagActivo = lFlagActivo;
	}
	
	
    
    
}
