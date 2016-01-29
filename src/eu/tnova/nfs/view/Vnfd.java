package eu.tnova.nfs.view;

import eu.tnova.nfs.entity.VNFDescriptor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Vnfd implements Serializable {
	private String vnfdId;
	private String vnfId;
	private String providerId;
	private String provider;
	private String description;
	private String type;
	private Date creationDate;
	private Date modificationDate;
	private String descriptorVersion;
	private String version;
	private String vnfd;
	private List<String> files;

	public Vnfd(VNFDescriptor vnfDescriptor) {
		vnfdId = vnfDescriptor.getId().toString();
		vnfId = vnfDescriptor.getVnfId();
		if (vnfId == null)
			vnfId = "";
		providerId = vnfDescriptor.getVnfd().getProvider_id();
		if (providerId == null)
			providerId = "";
		provider = vnfDescriptor.getVnfd().getProvider();
		if (provider == null)
			provider = "";
		description = vnfDescriptor.getVnfd().getDescription();
		if (description == null)
			description = "";
		type = vnfDescriptor.getVnfd().getType();
		if (type == null)
			type = "";
		creationDate = vnfDescriptor.getVnfd().getCreationDate();
		if (creationDate == null)
			creationDate = new Date();
		modificationDate = vnfDescriptor.getVnfd().getModificationDate();
		if (modificationDate == null)
			modificationDate = new Date();
		version = vnfDescriptor.getVnfd().getVersion();
		if (version == null)
			version = "";
		vnfd = vnfDescriptor.getJson();
		if (vnfd == null)
			vnfd = "";
		files = vnfDescriptor.getvmImagesFileNames();
	}

	public String getVnfdId() {
		return vnfdId;
	}
	public void setVnfdId(String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public String getVnfId() {
		return vnfId;
	}
	public void setVnfId(String vnfId) {
		this.vnfId = vnfId;
	}

	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProvider()	{
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getType()	{
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getDescriptorVersion() {
		return descriptorVersion;
	}
	public void setDescriptorVersion(String descriptorVersion) {
		this.descriptorVersion = descriptorVersion;
	}

	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	public String getVnfd()	{
		return vnfd;
	}
	public void setVnfd(String vnfd) {
		this.vnfd = vnfd;
	}

	public List<String> getFiles() {
		return files;
	}
	public void setFiles(List<String> files) {
		this.files = files;
	}
}
