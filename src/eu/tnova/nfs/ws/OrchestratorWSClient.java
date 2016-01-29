package eu.tnova.nfs.ws;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.client.ServerWebApplicationException;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import eu.tnova.nfs.entity.VNFDescriptor;
import eu.tnova.nfs.producers.EnvValue;
import eu.tnova.nfs.valves.GatekeeperAuthenticationValve;
import eu.tnova.nfs.ws.entity.VNFOrchestratorRequest;
import eu.tnova.nfs.ws.entity.VNFOrchestratorResponse;

@SuppressWarnings("serial")
public class OrchestratorWSClient implements Serializable {
	@Inject	private Logger log;
	@Inject @EnvValue(EnvValue.orchestratorUrl) private String orchestrator;
	@Inject @EnvValue(EnvValue.nfsServiceKey) private String serviceKey;
	
	OrchestratorWSClient () {
	}

	public void create_VNF(VNFDescriptor vnfd, String userToken) throws Exception {
		log.info("Send create VNF to Orchestrator");
		WebClient webClient = getClient(null, userToken);
		if ( webClient==null )
			return;
		try {
			String msg = getRequestMessage(vnfd);
			String resp = webClient.post(msg, String.class);
			log.debug("Response received from Orchestrator :\n{}",resp); 
			VNFOrchestratorResponse vnf = new Gson().fromJson(resp, VNFOrchestratorResponse.class);
			vnfd.setVnfId(vnf.getVnfId());
		} catch (ServerWebApplicationException e) {
			throw new Exception("Wrong Response ("+e.getStatus()+")");
		} catch (Exception e) {
			throw new Exception("No response from Orchestrator : "+e.getMessage());
		} 
	}

	public void update_VNF(VNFDescriptor vnfd, String userToken) throws Exception {
		log.info("Send update VNF to Orchestrator");
		WebClient webClient = getClient(vnfd.getVnfId(),userToken);
		if ( webClient==null )
			return;
		try {
			String msg = getRequestMessage(vnfd);
			String resp = webClient.put(msg, String.class);
			log.debug("Response received from Orchestrator :\n{}",resp); 
			VNFOrchestratorResponse vnf = new Gson().fromJson(resp, VNFOrchestratorResponse.class);
			vnfd.setVnfId(vnf.getVnfId());
		} catch (ServerWebApplicationException e) {
			throw new Exception("Wrong Response ("+e.getStatus()+")");
		} catch (Exception e) {
			throw new Exception("No response from Orchestrator : "+e.getMessage());
		} 
	}

	public void delete_VNF(VNFDescriptor vnfd, String userToken) throws Exception {
		log.info("Send delete VNF to Orchestrator");
		WebClient webClient = getClient(vnfd.getVnfId(), userToken);
		if ( webClient==null )
			return;
		try {
			Response response = webClient.delete();
			if ( response.getStatus() != Status.OK.getStatusCode() )
				throw new Exception("Wrong Response ("+response.getStatus()+")");
			log.debug("Response {} received from Orchestrator", response.getStatus()); 
			vnfd.setVnfId(null);
		} catch (Exception e) {
			throw new Exception("No response from Orchestrator : "+e.getMessage());
		} 
	}

	private WebClient getClient (String vnfId, String userToken) 
			throws MalformedURLException {
		if ( orchestrator.isEmpty() || orchestrator.equals("0.0.0.0" )) {
			log.info("Orchestrator host unknown, operation not done");
			return null;
		}
		String url = orchestrator;
		if ( vnfId!=null )
			url += "/"+vnfId;
		URL orchestratorURL = new URL(url); 
		log.info("Using orchestrator URL : {}", orchestratorURL.toString());
		WebClient webClient = WebClient.create(orchestratorURL.toString()).
				type(MediaType.APPLICATION_JSON).
				accept(MediaType.APPLICATION_JSON);
		// timeout (in mSec)
		WebClient.getConfig(webClient).getHttpConduit().getClient().setReceiveTimeout(10000);
		WebClient.getConfig(webClient).getHttpConduit().getClient().setConnectionTimeout(10000);
		// add user token
		if ( !serviceKey.isEmpty() && userToken!=null ) {
			webClient.getHeaders().add(GatekeeperAuthenticationValve.AUTH_TOKEN, userToken);
		}
		return webClient;
	}
	private String getRequestMessage(VNFDescriptor vnfd) {
		VNFOrchestratorRequest request = new VNFOrchestratorRequest();
//TODO - temporary fixed values
		request.setName("TEMP");
		request.setVnfManager("TEMP");
		request.setVnfImage("TEMP");
		Gson gson = new Gson();
		JsonElement vnfElement = gson.fromJson(gson.toJson(request), JsonElement.class);
		JsonElement vnfdElement = gson.fromJson(vnfd.getJson(), JsonElement.class);
		vnfElement.getAsJsonObject().add("vnfd", vnfdElement);
		String msg = vnfElement.getAsJsonObject().toString();
		log.debug("Request Message to Orchestrator:\n{}",msg);
		return msg;
	}
}
