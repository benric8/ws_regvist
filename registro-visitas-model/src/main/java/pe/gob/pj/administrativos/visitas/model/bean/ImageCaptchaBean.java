package pe.gob.pj.administrativos.visitas.model.bean;

public class ImageCaptchaBean  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private int length;
	private byte[] data;
	private String CAlfresco;

    public ImageCaptchaBean() {
    }


    public ImageCaptchaBean(String name) {
        this.name = name;
    }
    public ImageCaptchaBean(String name, int length, byte[] data) {
       this.name = name;
       this.length = length;
       this.data = data;
    }



    public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}


	public byte[] getData() {
		return data;
	}


	public void setData(byte[] data) {
		this.data = data;
	}

	public String getCAlfresco() {
		return CAlfresco;
	}

	public void setCAlfresco(String cAlfresco) {
		CAlfresco = cAlfresco;
	}

}




