package eu.tnova.nfs.ws.entity;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.google.gson.annotations.SerializedName;

@XmlRootElement(name = "vnf")
public class VNFOrchestratorResponse {
	@SerializedName("id") @XmlElement(name="id")
	private String vnfId;
	@SerializedName("name") @XmlElement(name="name")
	private String name;
	@SerializedName("vnf-image") @XmlElement(name="vnf-image")
	private String vnfImage;
	@SerializedName("vnf-manager") @XmlElement(name="vnf-manager")
	private String vnfManager;
	@SerializedName("created_at") @XmlElement(name="created_at")
	private Date creationDate;
	@SerializedName("updated_at") @XmlElement(name="updated_at")
	private Date lastModificationDate;

	public VNFOrchestratorResponse() {
	}

	public String getVnfId() {
		return vnfId;
	}
	public void setVnfId(String vnfId) {
		this.vnfId = vnfId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVnfImage() {
		return vnfImage;
	}
	public void setVnfImage(String vnfImage) {
		this.vnfImage = vnfImage;
	}
	public String getVnfManager() {
		return vnfManager;
	}
	public void setVnfManager(String vnfManager) {
		this.vnfManager = vnfManager;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getLastModificationDate() {
		return lastModificationDate;
	}
	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

}
