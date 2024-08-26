package pe.gob.pj.administrativos.visitas.model.bean;

import java.io.Serializable;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class CaptchaBean implements Serializable {
	private static final long serialVersionUID = 6761309110132395326L;

	private ImageCaptchaBean filesImagenc;
	private String codigoCaptcha;
	private DefaultStreamedContent imageStreamedContent;
	private StreamedContent graphicText;
	private String txtcaptcha;
	private String sessionID;


	public CaptchaBean() {
	}


	public ImageCaptchaBean getFilesImagenc() {
		return filesImagenc;
	}


	public void setFilesImagenc(ImageCaptchaBean filesImagenc) {
		this.filesImagenc = filesImagenc;
	}


	public String getCodigoCaptcha() {
		return codigoCaptcha;
	}


	public void setCodigoCaptcha(String codigoCaptcha) {
		this.codigoCaptcha = codigoCaptcha;
	}


	public DefaultStreamedContent getImageStreamedContent() {
		return imageStreamedContent;
	}


	public void setImageStreamedContent(DefaultStreamedContent imageStreamedContent) {
		this.imageStreamedContent = imageStreamedContent;
	}


	public StreamedContent getGraphicText() {
		return graphicText;
	}


	public void setGraphicText(StreamedContent graphicText) {
		this.graphicText = graphicText;
	}


	public String getTxtcaptcha() {
		return txtcaptcha;
	}


	public void setTxtcaptcha(String txtcaptcha) {
		this.txtcaptcha = txtcaptcha;
	}


	public String getSessionID() {
		return sessionID;
	}


	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}



}
