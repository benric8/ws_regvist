package pe.gob.pj.administrativos.visitas.model.bean;

import java.io.Serializable;

public class PersonaReniecBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
//	private String nroDNI; // DNI de la persona
//	private String flgVerif; // D�gito de verificaci�n
//	private String apePat; // Apellido Paterno
//	private String apeMat; // Apellido Materno
//	private String apeCas; // Apellido Casada
//	private String nombres; // Nombres
//	private String codUbiDepDom; // Ubigeo Departamento Domicilio
//	private String codUbiProDom; // Ubigeo Provincia Domicilio
//	private String codUbiDisDom; // Ubigeo Distrito Domicilio
//	private String codUbiLocDom; // Ubigeo Localidad Domicilio
//	private String depDom; // Departamento Domicilio
//	private String proDom; // Provincia Domicilio
//	private String disDom; // Distrito Domicilio
//	private String locDom; // Localidad Domicilio
//	private String estCiv; // Estado Civil
//	private String graInst; // C�digo de Grado de Instrucci�n
//	private String estatura; // Estatura
//	private String sexo; // Sexo
//	private String docSustTipDoc; // Documento Sustentatorio - Tipo Doc.
//	private String docSustNroDoc; // Documento Sustentatorio - Nro Doc.
//	private String codUbiDepNac; // Ubigeo Departamento Nacimiento
//	private String codUbiProNac; // Ubigeo Provincia Nacimiento
//	private String codUbiDisNac; // Ubigeo Distrito Nacimiento
//	private String codUbiLocNac; // Ubigeo Localidad Nacimiento
//	private String depNac; // Departamento Nacimiento
//	private String proNac; // Provincia Nacimiento
//	private String disNac; // Distrito Nacimiento
//	private String locNac; // Localidad Nacimiento
//	private String fecNac; // Fecha Nacimiento
//	private String docPadTipDoc; // Documento Padre - Tipo Doc
//	private String docPadNumDoc; // Documento Padre - Num Doc
//	private String nomPad; // Nombre del Padre
//	private String docMadTipDoc; // Documento Madre - Tipo Doc
//	private String docMadNumDoc; // Documento Madre - Num Doc
//	private String nomMad; // Nombre de la Madre
//	private String fecIns; // Fecha de Inscripci�n
//	private String fecExp; // Fecha de Expedici�n
//	private String fecFall; // Fecha de Fallecimiento
//	private String consVot; // Constancia de Votaci�n
//	private String fecCad; // Fecha de caducidad
//	private String restric; // Restricciones
//	private String prefDir; // Prefijo direccion
//	private String direccion; // Direccion
//	private String nroDir; // Numero Direccion
//	private String blocOChal; // Block o chalet
//	private String interior; // Interior
//	private String urbanizacion; // Urbanizacion
//	private String etapa; // Etapa
//	private String manzana; // Manzana
//	private String lote; // Lote
//	private String preBlocOChal; // Prefijo Bloc o Chalet
//	private String preDptoPisoInt; // Prefijo Departamento Piso Interior
//	private String preUrbCondResid; // Prefijo de urb cond resid.
//	private String reservado; // Reservado
//	private int longitudFoto; // Longitud de la Foto en Bytes
//	private int longitudFirma; // Longitud de la Firma en Bytes
//	private int reservadoFotoFirma1; // ReservadoFotoFirma 1
//	private String reservadoFotoFirma2; // ReservadoFotoFirma 2
//	private byte[] foto; // Foto
//	private byte[] firma; // Firma
//	private String flgImagen; // Indica si tiene foto o no
//	private String tipFichaRegistral; // Tipo de Ficha registral
//	private String datos; // Indica si debe mostrar datos
	

//	public String getNroDNI() {
//		return nroDNI;
//	}
//	public void setNroDNI(String nroDNI) {
//		this.nroDNI = nroDNI;
//	}
//	public String getFlgVerif() {
//		return flgVerif;
//	}
//	public void setFlgVerif(String flgVerif) {
//		this.flgVerif = flgVerif;
//	}
//	public String getApePat() {
//		return apePat;
//	}
//	public void setApePat(String apePat) {
//		this.apePat = apePat;
//	}
//	public String getApeMat() {
//		return apeMat;
//	}
//	public void setApeMat(String apeMat) {
//		this.apeMat = apeMat;
//	}
//	public String getApeCas() {
//		return apeCas;
//	}
//	public void setApeCas(String apeCas) {
//		this.apeCas = apeCas;
//	}
//	public String getNombres() {
//		return nombres;
//	}
//	public void setNombres(String nombres) {
//		this.nombres = nombres;
//	}
//	public String getCodUbiDepDom() {
//		return codUbiDepDom;
//	}
//	public void setCodUbiDepDom(String codUbiDepDom) {
//		this.codUbiDepDom = codUbiDepDom;
//	}
//	public String getCodUbiProDom() {
//		return codUbiProDom;
//	}
//	public void setCodUbiProDom(String codUbiProDom) {
//		this.codUbiProDom = codUbiProDom;
//	}
//	public String getCodUbiDisDom() {
//		return codUbiDisDom;
//	}
//	public void setCodUbiDisDom(String codUbiDisDom) {
//		this.codUbiDisDom = codUbiDisDom;
//	}
//	public String getCodUbiLocDom() {
//		return codUbiLocDom;
//	}
//	public void setCodUbiLocDom(String codUbiLocDom) {
//		this.codUbiLocDom = codUbiLocDom;
//	}
//	public String getDepDom() {
//		return depDom;
//	}
//	public void setDepDom(String depDom) {
//		this.depDom = depDom;
//	}
//	public String getProDom() {
//		return proDom;
//	}
//	public void setProDom(String proDom) {
//		this.proDom = proDom;
//	}
//	public String getDisDom() {
//		return disDom;
//	}
//	public void setDisDom(String disDom) {
//		this.disDom = disDom;
//	}
//	public String getLocDom() {
//		return locDom;
//	}
//	public void setLocDom(String locDom) {
//		this.locDom = locDom;
//	}
//	public String getEstCiv() {
//		return estCiv;
//	}
//	public void setEstCiv(String estCiv) {
//		this.estCiv = estCiv;
//	}
//	public String getGraInst() {
//		return graInst;
//	}
//	public void setGraInst(String graInst) {
//		this.graInst = graInst;
//	}
//	public String getEstatura() {
//		return estatura;
//	}
//	public void setEstatura(String estatura) {
//		this.estatura = estatura;
//	}
//	public String getSexo() {
//		return sexo;
//	}
//	public void setSexo(String sexo) {
//		this.sexo = sexo;
//	}
//	public String getDocSustTipDoc() {
//		return docSustTipDoc;
//	}
//	public void setDocSustTipDoc(String docSustTipDoc) {
//		this.docSustTipDoc = docSustTipDoc;
//	}
//	public String getDocSustNroDoc() {
//		return docSustNroDoc;
//	}
//	public void setDocSustNroDoc(String docSustNroDoc) {
//		this.docSustNroDoc = docSustNroDoc;
//	}
//	public String getCodUbiDepNac() {
//		return codUbiDepNac;
//	}
//	public void setCodUbiDepNac(String codUbiDepNac) {
//		this.codUbiDepNac = codUbiDepNac;
//	}
//	public String getCodUbiProNac() {
//		return codUbiProNac;
//	}
//	public void setCodUbiProNac(String codUbiProNac) {
//		this.codUbiProNac = codUbiProNac;
//	}
//	public String getCodUbiDisNac() {
//		return codUbiDisNac;
//	}
//	public void setCodUbiDisNac(String codUbiDisNac) {
//		this.codUbiDisNac = codUbiDisNac;
//	}
//	public String getCodUbiLocNac() {
//		return codUbiLocNac;
//	}
//	public void setCodUbiLocNac(String codUbiLocNac) {
//		this.codUbiLocNac = codUbiLocNac;
//	}
//	public String getDepNac() {
//		return depNac;
//	}
//	public void setDepNac(String depNac) {
//		this.depNac = depNac;
//	}
//	public String getProNac() {
//		return proNac;
//	}
//	public void setProNac(String proNac) {
//		this.proNac = proNac;
//	}
//	public String getDisNac() {
//		return disNac;
//	}
//	public void setDisNac(String disNac) {
//		this.disNac = disNac;
//	}
//	public String getLocNac() {
//		return locNac;
//	}
//	public void setLocNac(String locNac) {
//		this.locNac = locNac;
//	}
//	public String getFecNac() {
//		return fecNac;
//	}
//	public void setFecNac(String fecNac) {
//		this.fecNac = fecNac;
//	}
//	public String getDocPadTipDoc() {
//		return docPadTipDoc;
//	}
//	public void setDocPadTipDoc(String docPadTipDoc) {
//		this.docPadTipDoc = docPadTipDoc;
//	}
//	public String getDocPadNumDoc() {
//		return docPadNumDoc;
//	}
//	public void setDocPadNumDoc(String docPadNumDoc) {
//		this.docPadNumDoc = docPadNumDoc;
//	}
//	public String getNomPad() {
//		return nomPad;
//	}
//	public void setNomPad(String nomPad) {
//		this.nomPad = nomPad;
//	}
//	public String getDocMadTipDoc() {
//		return docMadTipDoc;
//	}
//	public void setDocMadTipDoc(String docMadTipDoc) {
//		this.docMadTipDoc = docMadTipDoc;
//	}
//	public String getDocMadNumDoc() {
//		return docMadNumDoc;
//	}
//	public void setDocMadNumDoc(String docMadNumDoc) {
//		this.docMadNumDoc = docMadNumDoc;
//	}
//	public String getNomMad() {
//		return nomMad;
//	}
//	public void setNomMad(String nomMad) {
//		this.nomMad = nomMad;
//	}
//	public String getFecIns() {
//		return fecIns;
//	}
//	public void setFecIns(String fecIns) {
//		this.fecIns = fecIns;
//	}
//	public String getFecExp() {
//		return fecExp;
//	}
//	public void setFecExp(String fecExp) {
//		this.fecExp = fecExp;
//	}
//	public String getFecFall() {
//		return fecFall;
//	}
//	public void setFecFall(String fecFall) {
//		this.fecFall = fecFall;
//	}
//	public String getConsVot() {
//		return consVot;
//	}
//	public void setConsVot(String consVot) {
//		this.consVot = consVot;
//	}
//	public String getFecCad() {
//		return fecCad;
//	}
//	public void setFecCad(String fecCad) {
//		this.fecCad = fecCad;
//	}
//	public String getRestric() {
//		return restric;
//	}
//	public void setRestric(String restric) {
//		this.restric = restric;
//	}
//	public String getPrefDir() {
//		return prefDir;
//	}
//	public void setPrefDir(String prefDir) {
//		this.prefDir = prefDir;
//	}
//	public String getDireccion() {
//		return direccion;
//	}
//	public void setDireccion(String direccion) {
//		this.direccion = direccion;
//	}
//	public String getNroDir() {
//		return nroDir;
//	}
//	public void setNroDir(String nroDir) {
//		this.nroDir = nroDir;
//	}
//	public String getBlocOChal() {
//		return blocOChal;
//	}
//	public void setBlocOChal(String blocOChal) {
//		this.blocOChal = blocOChal;
//	}
//	public String getInterior() {
//		return interior;
//	}
//	public void setInterior(String interior) {
//		this.interior = interior;
//	}
//	public String getUrbanizacion() {
//		return urbanizacion;
//	}
//	public void setUrbanizacion(String urbanizacion) {
//		this.urbanizacion = urbanizacion;
//	}
//	public String getEtapa() {
//		return etapa;
//	}
//	public void setEtapa(String etapa) {
//		this.etapa = etapa;
//	}
//	public String getManzana() {
//		return manzana;
//	}
//	public void setManzana(String manzana) {
//		this.manzana = manzana;
//	}
//	public String getLote() {
//		return lote;
//	}
//	public void setLote(String lote) {
//		this.lote = lote;
//	}
//	public String getPreBlocOChal() {
//		return preBlocOChal;
//	}
//	public void setPreBlocOChal(String preBlocOChal) {
//		this.preBlocOChal = preBlocOChal;
//	}
//	public String getPreDptoPisoInt() {
//		return preDptoPisoInt;
//	}
//	public void setPreDptoPisoInt(String preDptoPisoInt) {
//		this.preDptoPisoInt = preDptoPisoInt;
//	}
//	public String getPreUrbCondResid() {
//		return preUrbCondResid;
//	}
//	public void setPreUrbCondResid(String preUrbCondResid) {
//		this.preUrbCondResid = preUrbCondResid;
//	}
//	public String getReservado() {
//		return reservado;
//	}
//	public void setReservado(String reservado) {
//		this.reservado = reservado;
//	}
//	public int getLongitudFoto() {
//		return longitudFoto;
//	}
//	public void setLongitudFoto(int longitudFoto) {
//		this.longitudFoto = longitudFoto;
//	}
//	public int getLongitudFirma() {
//		return longitudFirma;
//	}
//	public void setLongitudFirma(int longitudFirma) {
//		this.longitudFirma = longitudFirma;
//	}
//	public int getReservadoFotoFirma1() {
//		return reservadoFotoFirma1;
//	}
//	public void setReservadoFotoFirma1(int reservadoFotoFirma1) {
//		this.reservadoFotoFirma1 = reservadoFotoFirma1;
//	}
//	public String getReservadoFotoFirma2() {
//		return reservadoFotoFirma2;
//	}
//	public void setReservadoFotoFirma2(String reservadoFotoFirma2) {
//		this.reservadoFotoFirma2 = reservadoFotoFirma2;
//	}
//	public byte[] getFoto() {
//		return foto;
//	}
//	public void setFoto(byte[] foto) {
//		this.foto = foto;
//	}
//	public byte[] getFirma() {
//		return firma;
//	}
//	public void setFirma(byte[] firma) {
//		this.firma = firma;
//	}
//	public String getFlgImagen() {
//		return flgImagen;
//	}
//	public void setFlgImagen(String flgImagen) {
//		this.flgImagen = flgImagen;
//	}
//	public String getTipFichaRegistral() {
//		return tipFichaRegistral;
//	}
//	public void setTipFichaRegistral(String tipFichaRegistral) {
//		this.tipFichaRegistral = tipFichaRegistral;
//	}
//	public String getDatos() {
//		return datos;
//	}
//	public void setDatos(String datos) {
//		this.datos = datos;
//	}
	
	

	private String nroDocumentoIdentidad; // DNI de la persona
	private String codigoVerificacion; // Digito de verificacion
	private String tipoFichaRegistral; // Ficha registral
	private String datos; // Ficha registral
	private String flagImagen; // Ficha registral
	private String apellidoPaterno; // Apellido Paterno
	private String apellidoMaterno; // Apellido Materno
	private String apellidoCasado; // Apellido Casada
	private String nombres; // Nombres
	private String codigoUbigeoDepartamentoDomicilio; // Ubigeo Departamento Domicilio
	private String codigoUbigeoProvinciaDomicilio; // Ubigeo Provincia Domicilio
	private String codigoUbigeoDistritoDomicilio; // Ubigeo Distrito Domicilio
	private String codigoUbigeoLocalidadDomicilio; // Ubigeo Localidad Domicilio
	private String departamentoDomicilio; // Departamento Domicilio
	private String provinciaDomicilio; // Provincia Domicilio
	private String distritoDomicilio; // Distrito Domicilio
	private String localidadDomicilio; // Localidad Domicilio
	private String estadoCivil; // Estado Civil
	private String gradoInstruccion; // Código de Grado de Instrucción
	private String estatura; // Estatura
	private String sexo; // Sexo
	private String documentoSustentatorioTipoDocumento; // Documento Sustentatorio - Tipo Doc.
	private String documentoSustentatorioNroDocumento; // Documento Sustentatorio - Nro Doc.
	private String codigoUbigeoDepartamentoNacimiento; // Ubigeo Departamento Nacimiento
	private String codigoUbigeoProvinciaNacimiento; // Ubigeo Provincia Nacimiento
	private String codigoUbigeoDistritoNacimiento; // Ubigeo Distrito Nacimiento
	private String codigoUbigeoLocalidadNacimiento; // Ubigeo Localidad Nacimiento
	private String departamentoNacimiento; // Departamento Nacimiento
	private String provinciaNacimiento; // Provincia Nacimiento
	private String distritoNacimiento; // Distrito Nacimiento
	private String localidadNacimiento; // Localidad Nacimiento
	private String fechaNacimiento; // Fecha Nacimiento
	private String documentoPadreTipDocumento; // Documento Padre - Tipo Doc
	private String documentoPadreNumDocumento; // Documento Padre - Num Doc
	private String nombrePadre; // Nombre del Padre
	private String documentoMadreTipoDocumento; // Documento Madre - Tipo Doc
	private String documentoMadreNumeroDocumento; // Documento Madre - Num Doc
	private String nombreMadre; // Nombre de la Madre
	private String fechaInscripcion; // Fecha de Inscripción
	private String fechaEmision; // Fecha de Expedición
	private String fechaFallecimiento; // Fecha de Fallecimiento
	private String constanciaVotacion; // Constancia de Votación
	private String fechaCaducidad; // Fecha de caducidad
	private String restricciones; // Restricciones
	private String prefijoDireccion; // Prefijo direccion
	private String direccion; // Direccion
	private String nroDireccion; // Numero Direccion
	private String blockOChalet; // Block o chalet
	private String interior; // Interior
	private String urbanizacion; // Urbanizacion
	private String etapa; // Etapa
	private String manzana; // Manzana
	private String lote; // Lote
	private String preBlockOChalet; // Prefijo Bloc o Chalet
	private String preDptoPisoInterior; // Prefijo Departamento Piso Interior
	private String preUrbCondResid; // Prefijo de urb cond resid.
	private String reservado; // Reservado
	private int longitudFoto; // Longitud de la Foto en Bytes
	private int longitudFirma; // Longitud de la Firma en Bytes
	private int reservadoFotoFirma1; // ReservadoFotoFirma 1
	private String reservadoFotoFirma2; // ReservadoFotoFirma 2
	private byte[] foto; // Foto
	private byte[] firma; // Firma
	
	
	

	@Override
	public String toString() {
		return "PersonaReniecBean [nroDocumentoIdentidad=" + nroDocumentoIdentidad + ", codigoVerificacion="
				+ codigoVerificacion + ", tipoFichaRegistral=" + tipoFichaRegistral + ", datos=" + datos
				+ ", flagImagen=" + flagImagen + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno="
				+ apellidoMaterno + ", apellidoCasado=" + apellidoCasado + ", nombres=" + nombres
				+ ", codigoUbigeoDepartamentoDomicilio=" + codigoUbigeoDepartamentoDomicilio
				+ ", codigoUbigeoProvinciaDomicilio=" + codigoUbigeoProvinciaDomicilio
				+ ", codigoUbigeoDistritoDomicilio=" + codigoUbigeoDistritoDomicilio
				+ ", codigoUbigeoLocalidadDomicilio=" + codigoUbigeoLocalidadDomicilio + ", departamentoDomicilio="
				+ departamentoDomicilio + ", provinciaDomicilio=" + provinciaDomicilio + ", distritoDomicilio="
				+ distritoDomicilio + ", localidadDomicilio=" + localidadDomicilio + ", estadoCivil=" + estadoCivil
				+ ", gradoInstruccion=" + gradoInstruccion + ", estatura=" + estatura + ", sexo=" + sexo
				+ ", documentoSustentatorioTipoDocumento=" + documentoSustentatorioTipoDocumento
				+ ", documentoSustentatorioNroDocumento=" + documentoSustentatorioNroDocumento
				+ ", codigoUbigeoDepartamentoNacimiento=" + codigoUbigeoDepartamentoNacimiento
				+ ", codigoUbigeoProvinciaNacimiento=" + codigoUbigeoProvinciaNacimiento
				+ ", codigoUbigeoDistritoNacimiento=" + codigoUbigeoDistritoNacimiento
				+ ", codigoUbigeoLocalidadNacimiento=" + codigoUbigeoLocalidadNacimiento + ", departamentoNacimiento="
				+ departamentoNacimiento + ", provinciaNacimiento=" + provinciaNacimiento + ", distritoNacimiento="
				+ distritoNacimiento + ", localidadNacimiento=" + localidadNacimiento + ", fechaNacimiento="
				+ fechaNacimiento + ", documentoPadreTipDocumento=" + documentoPadreTipDocumento
				+ ", documentoPadreNumDocumento=" + documentoPadreNumDocumento + ", nombrePadre=" + nombrePadre
				+ ", documentoMadreTipoDocumento=" + documentoMadreTipoDocumento + ", documentoMadreNumeroDocumento="
				+ documentoMadreNumeroDocumento + ", nombreMadre=" + nombreMadre + ", fechaInscripcion="
				+ fechaInscripcion + ", fechaEmision=" + fechaEmision + ", fechaFallecimiento=" + fechaFallecimiento
				+ ", constanciaVotacion=" + constanciaVotacion + ", fechaCaducidad=" + fechaCaducidad
				+ ", restricciones=" + restricciones + ", prefijoDireccion=" + prefijoDireccion + ", direccion="
				+ direccion + ", nroDireccion=" + nroDireccion + ", blockOChalet=" + blockOChalet + ", interior="
				+ interior + ", urbanizacion=" + urbanizacion + ", etapa=" + etapa + ", manzana=" + manzana + ", lote="
				+ lote + ", preBlockOChalet=" + preBlockOChalet + ", preDptoPisoInterior=" + preDptoPisoInterior
				+ ", preUrbCondResid=" + preUrbCondResid + ", reservado=" + reservado + ", longitudFoto=" + longitudFoto
				+ ", longitudFirma=" + longitudFirma + ", reservadoFotoFirma1=" + reservadoFotoFirma1
				+ ", reservadoFotoFirma2=" + reservadoFotoFirma2 + "]";
	}


	public String getNroDocumentoIdentidad() {
		return nroDocumentoIdentidad;
	}


	public void setNroDocumentoIdentidad(String nroDocumentoIdentidad) {
		this.nroDocumentoIdentidad = nroDocumentoIdentidad;
	}


	public String getCodigoVerificacion() {
		return codigoVerificacion;
	}


	public void setCodigoVerificacion(String codigoVerificacion) {
		this.codigoVerificacion = codigoVerificacion;
	}


	public String getApellidoPaterno() {
		return apellidoPaterno;
	}


	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}


	public String getApellidoMaterno() {
		return apellidoMaterno;
	}


	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}


	public String getApellidoCasado() {
		return apellidoCasado;
	}


	public void setApellidoCasado(String apellidoCasado) {
		this.apellidoCasado = apellidoCasado;
	}


	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public String getCodigoUbigeoDepartamentoDomicilio() {
		return codigoUbigeoDepartamentoDomicilio;
	}


	public void setCodigoUbigeoDepartamentoDomicilio(String codigoUbigeoDepartamentoDomicilio) {
		this.codigoUbigeoDepartamentoDomicilio = codigoUbigeoDepartamentoDomicilio;
	}


	public String getCodigoUbigeoProvinciaDomicilio() {
		return codigoUbigeoProvinciaDomicilio;
	}


	public void setCodigoUbigeoProvinciaDomicilio(String codigoUbigeoProvinciaDomicilio) {
		this.codigoUbigeoProvinciaDomicilio = codigoUbigeoProvinciaDomicilio;
	}


	public String getCodigoUbigeoDistritoDomicilio() {
		return codigoUbigeoDistritoDomicilio;
	}


	public void setCodigoUbigeoDistritoDomicilio(String codigoUbigeoDistritoDomicilio) {
		this.codigoUbigeoDistritoDomicilio = codigoUbigeoDistritoDomicilio;
	}


	public String getCodigoUbigeoLocalidadDomicilio() {
		return codigoUbigeoLocalidadDomicilio;
	}


	public void setCodigoUbigeoLocalidadDomicilio(String codigoUbigeoLocalidadDomicilio) {
		this.codigoUbigeoLocalidadDomicilio = codigoUbigeoLocalidadDomicilio;
	}


	public String getDepartamentoDomicilio() {
		return departamentoDomicilio;
	}


	public void setDepartamentoDomicilio(String departamentoDomicilio) {
		this.departamentoDomicilio = departamentoDomicilio;
	}


	public String getProvinciaDomicilio() {
		return provinciaDomicilio;
	}


	public void setProvinciaDomicilio(String provinciaDomicilio) {
		this.provinciaDomicilio = provinciaDomicilio;
	}


	public String getDistritoDomicilio() {
		return distritoDomicilio;
	}


	public void setDistritoDomicilio(String distritoDomicilio) {
		this.distritoDomicilio = distritoDomicilio;
	}


	public String getLocalidadDomicilio() {
		return localidadDomicilio;
	}


	public void setLocalidadDomicilio(String localidadDomicilio) {
		this.localidadDomicilio = localidadDomicilio;
	}


	public String getEstadoCivil() {
		return estadoCivil;
	}


	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}


	public String getGradoInstruccion() {
		return gradoInstruccion;
	}


	public void setGradoInstruccion(String gradoInstruccion) {
		this.gradoInstruccion = gradoInstruccion;
	}


	public String getEstatura() {
		return estatura;
	}


	public void setEstatura(String estatura) {
		this.estatura = estatura;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public String getDocumentoSustentatorioTipoDocumento() {
		return documentoSustentatorioTipoDocumento;
	}


	public void setDocumentoSustentatorioTipoDocumento(String documentoSustentatorioTipoDocumento) {
		this.documentoSustentatorioTipoDocumento = documentoSustentatorioTipoDocumento;
	}


	public String getDocumentoSustentatorioNroDocumento() {
		return documentoSustentatorioNroDocumento;
	}


	public void setDocumentoSustentatorioNroDocumento(String documentoSustentatorioNroDocumento) {
		this.documentoSustentatorioNroDocumento = documentoSustentatorioNroDocumento;
	}


	public String getCodigoUbigeoDepartamentoNacimiento() {
		return codigoUbigeoDepartamentoNacimiento;
	}


	public void setCodigoUbigeoDepartamentoNacimiento(String codigoUbigeoDepartamentoNacimiento) {
		this.codigoUbigeoDepartamentoNacimiento = codigoUbigeoDepartamentoNacimiento;
	}


	public String getCodigoUbigeoProvinciaNacimiento() {
		return codigoUbigeoProvinciaNacimiento;
	}


	public void setCodigoUbigeoProvinciaNacimiento(String codigoUbigeoProvinciaNacimiento) {
		this.codigoUbigeoProvinciaNacimiento = codigoUbigeoProvinciaNacimiento;
	}


	public String getCodigoUbigeoDistritoNacimiento() {
		return codigoUbigeoDistritoNacimiento;
	}


	public void setCodigoUbigeoDistritoNacimiento(String codigoUbigeoDistritoNacimiento) {
		this.codigoUbigeoDistritoNacimiento = codigoUbigeoDistritoNacimiento;
	}


	public String getCodigoUbigeoLocalidadNacimiento() {
		return codigoUbigeoLocalidadNacimiento;
	}


	public void setCodigoUbigeoLocalidadNacimiento(String codigoUbigeoLocalidadNacimiento) {
		this.codigoUbigeoLocalidadNacimiento = codigoUbigeoLocalidadNacimiento;
	}


	public String getDepartamentoNacimiento() {
		return departamentoNacimiento;
	}


	public void setDepartamentoNacimiento(String departamentoNacimiento) {
		this.departamentoNacimiento = departamentoNacimiento;
	}


	public String getProvinciaNacimiento() {
		return provinciaNacimiento;
	}


	public void setProvinciaNacimiento(String provinciaNacimiento) {
		this.provinciaNacimiento = provinciaNacimiento;
	}


	public String getDistritoNacimiento() {
		return distritoNacimiento;
	}


	public void setDistritoNacimiento(String distritoNacimiento) {
		this.distritoNacimiento = distritoNacimiento;
	}


	public String getLocalidadNacimiento() {
		return localidadNacimiento;
	}


	public void setLocalidadNacimiento(String localidadNacimiento) {
		this.localidadNacimiento = localidadNacimiento;
	}


	public String getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getDocumentoPadreTipDocumento() {
		return documentoPadreTipDocumento;
	}


	public void setDocumentoPadreTipDocumento(String documentoPadreTipDocumento) {
		this.documentoPadreTipDocumento = documentoPadreTipDocumento;
	}


	public String getDocumentoPadreNumDocumento() {
		return documentoPadreNumDocumento;
	}


	public void setDocumentoPadreNumDocumento(String documentoPadreNumDocumento) {
		this.documentoPadreNumDocumento = documentoPadreNumDocumento;
	}


	public String getNombrePadre() {
		return nombrePadre;
	}


	public void setNombrePadre(String nombrePadre) {
		this.nombrePadre = nombrePadre;
	}


	public String getDocumentoMadreTipoDocumento() {
		return documentoMadreTipoDocumento;
	}


	public void setDocumentoMadreTipoDocumento(String documentoMadreTipoDocumento) {
		this.documentoMadreTipoDocumento = documentoMadreTipoDocumento;
	}


	public String getDocumentoMadreNumeroDocumento() {
		return documentoMadreNumeroDocumento;
	}


	public void setDocumentoMadreNumeroDocumento(String documentoMadreNumeroDocumento) {
		this.documentoMadreNumeroDocumento = documentoMadreNumeroDocumento;
	}


	public String getNombreMadre() {
		return nombreMadre;
	}


	public void setNombreMadre(String nombreMadre) {
		this.nombreMadre = nombreMadre;
	}


	public String getFechaInscripcion() {
		return fechaInscripcion;
	}


	public void setFechaInscripcion(String fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}


	public String getFechaEmision() {
		return fechaEmision;
	}


	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}


	public String getFechaFallecimiento() {
		return fechaFallecimiento;
	}


	public void setFechaFallecimiento(String fechaFallecimiento) {
		this.fechaFallecimiento = fechaFallecimiento;
	}


	public String getConstanciaVotacion() {
		return constanciaVotacion;
	}


	public void setConstanciaVotacion(String constanciaVotacion) {
		this.constanciaVotacion = constanciaVotacion;
	}


	public String getFechaCaducidad() {
		return fechaCaducidad;
	}


	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}


	public String getRestricciones() {
		return restricciones;
	}


	public void setRestricciones(String restricciones) {
		this.restricciones = restricciones;
	}


	public String getPrefijoDireccion() {
		return prefijoDireccion;
	}


	public void setPrefijoDireccion(String prefijoDireccion) {
		this.prefijoDireccion = prefijoDireccion;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getNroDireccion() {
		return nroDireccion;
	}


	public void setNroDireccion(String nroDireccion) {
		this.nroDireccion = nroDireccion;
	}


	public String getBlockOChalet() {
		return blockOChalet;
	}


	public void setBlockOChalet(String blockOChalet) {
		this.blockOChalet = blockOChalet;
	}


	public String getInterior() {
		return interior;
	}


	public void setInterior(String interior) {
		this.interior = interior;
	}


	public String getUrbanizacion() {
		return urbanizacion;
	}


	public void setUrbanizacion(String urbanizacion) {
		this.urbanizacion = urbanizacion;
	}


	public String getEtapa() {
		return etapa;
	}


	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}


	public String getManzana() {
		return manzana;
	}


	public void setManzana(String manzana) {
		this.manzana = manzana;
	}


	public String getLote() {
		return lote;
	}


	public void setLote(String lote) {
		this.lote = lote;
	}


	public String getPreBlockOChalet() {
		return preBlockOChalet;
	}


	public void setPreBlockOChalet(String preBlockOChalet) {
		this.preBlockOChalet = preBlockOChalet;
	}


	public String getPreDptoPisoInterior() {
		return preDptoPisoInterior;
	}


	public void setPreDptoPisoInterior(String preDptoPisoInterior) {
		this.preDptoPisoInterior = preDptoPisoInterior;
	}


	public String getPreUrbCondResid() {
		return preUrbCondResid;
	}


	public void setPreUrbCondResid(String preUrbCondResid) {
		this.preUrbCondResid = preUrbCondResid;
	}


	public String getReservado() {
		return reservado;
	}


	public void setReservado(String reservado) {
		this.reservado = reservado;
	}


	public int getLongitudFoto() {
		return longitudFoto;
	}


	public void setLongitudFoto(int longitudFoto) {
		this.longitudFoto = longitudFoto;
	}


	public int getLongitudFirma() {
		return longitudFirma;
	}


	public void setLongitudFirma(int longitudFirma) {
		this.longitudFirma = longitudFirma;
	}


	public int getReservadoFotoFirma1() {
		return reservadoFotoFirma1;
	}


	public void setReservadoFotoFirma1(int reservadoFotoFirma1) {
		this.reservadoFotoFirma1 = reservadoFotoFirma1;
	}


	public String getReservadoFotoFirma2() {
		return reservadoFotoFirma2;
	}


	public void setReservadoFotoFirma2(String reservadoFotoFirma2) {
		this.reservadoFotoFirma2 = reservadoFotoFirma2;
	}


	public byte[] getFoto() {
		return foto;
	}


	public void setFoto(byte[] foto) {
		this.foto = foto;
	}


	public byte[] getFirma() {
		return firma;
	}


	public void setFirma(byte[] firma) {
		this.firma = firma;
	}


	public String getTipoFichaRegistral() {
		return tipoFichaRegistral;
	}


	public void setTipoFichaRegistral(String tipoFichaRegistral) {
		this.tipoFichaRegistral = tipoFichaRegistral;
	}


	public String getDatos() {
		return datos;
	}


	public void setDatos(String datos) {
		this.datos = datos;
	}


	public String getFlagImagen() {
		return flagImagen;
	}


	public void setFlagImagen(String flagImagen) {
		this.flagImagen = flagImagen;
	}			
	
}
