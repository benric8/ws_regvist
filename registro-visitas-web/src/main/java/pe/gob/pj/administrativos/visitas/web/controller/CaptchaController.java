package pe.gob.pj.administrativos.visitas.web.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DefaultStreamedContent;

import pe.gob.pj.administrativos.visitas.model.bean.CaptchaBean;
import pe.gob.pj.administrativos.visitas.model.bean.ImageCaptchaBean;

@ManagedBean(name="captchaMB")
@SessionScoped
public class CaptchaController implements Serializable {
	private static final long serialVersionUID = 6761309110132395326L;

	private CaptchaBean captchaBean;


	public CaptchaController() {
	}

	public CaptchaBean generarNuevaImagen(){
		try {
			captchaBean = createImage();

			InputStream in = new ByteArrayInputStream( captchaBean.getFilesImagenc().getData() );
			BufferedImage bImageFromConvert = ImageIO.read(in);

	        ByteArrayOutputStream os = new ByteArrayOutputStream();
	        ImageIO.write(bImageFromConvert, "jpg", os);

	        captchaBean.setGraphicText( new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png") );

	        return captchaBean;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public CaptchaBean createImage() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		HttpSession session = request.getSession(true);

		try {
			String texto = CadenaAlfanumAleatoria();
			session.setAttribute( request.getSession().getId() , texto);

			BufferedImage buffImage = new BufferedImage(50, 30,BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = buffImage.createGraphics();
			Color Background = new Color(119, 3, 4);
			graphics.setBackground(Background);
			graphics.clearRect(0, 0, 52, 30);

			graphics.setFont(new Font("Arial", Font.ITALIC, 12));
			graphics.setPaint(Color.white);
			graphics.drawString(texto, 5, 20);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(buffImage, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();

			ImageCaptchaBean file333 = new ImageCaptchaBean();
			file333.setData(imageInByte);

			CaptchaBean captchaBean = new CaptchaBean();
			captchaBean.setFilesImagenc( file333 );
			captchaBean.setTxtcaptcha( texto );
			captchaBean.setCodigoCaptcha( texto );
			captchaBean.setSessionID(request.getSession().getId());

			baos.close();

			return captchaBean;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String CadenaAlfanumAleatoria() {
		int longitud = 5;

		String cadenaAleatoria = "";
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(milis);
		int i = 0;
		while (i < longitud) {
			char c = (char) r.nextInt(255);
			if ((c >= '1' && c <= '9')
					|| (c >= 'A' && c <= 'Z' && c != 'W' && c != 'O')) {
				cadenaAleatoria += c;
				i++;
			}
		}
		return cadenaAleatoria;
	}

	public boolean validarCaptcha(String captchaIngreso, String sessionID) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        javax.servlet.http.HttpSession session = request.getSession();
		boolean estado = false;
		String captchaOriginal = null;

		try{
			captchaIngreso = captchaIngreso.toUpperCase();

			captchaOriginal = (String) session.getAttribute( sessionID );
			if ( captchaIngreso.equals(captchaOriginal) ) {
				estado = true;
			} else {
				estado = false;
			}
		}catch(Exception e){

		}

		return estado;
	}

	public CaptchaBean getCaptchaBean() {
		return captchaBean;
	}

	public void setCaptchaBean(CaptchaBean captchaBean) {
		this.captchaBean = captchaBean;
	}

}
