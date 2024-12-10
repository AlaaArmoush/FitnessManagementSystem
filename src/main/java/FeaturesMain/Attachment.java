package FeaturesMain;

public class Attachment {
	
	private String type;
	private String path;
	
	public Attachment(String type, String path) {
		this.type = type;
		this.path = path;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	

}
