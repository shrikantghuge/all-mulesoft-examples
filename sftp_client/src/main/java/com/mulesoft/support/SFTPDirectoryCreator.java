package com.mulesoft.support;

import java.util.Arrays;

import org.mule.api.MuleEventContext;
import org.mule.api.endpoint.ImmutableEndpoint;
import org.mule.api.registry.MuleRegistry;
import org.mule.endpoint.EndpointURIEndpointBuilder;
import org.mule.transport.sftp.SftpClient;
import org.mule.transport.sftp.SftpConnector;

public class SFTPDirectoryCreator implements org.mule.api.lifecycle.Callable
{
  public SFTPDirectoryCreator() {}
  
  public Object onCall(MuleEventContext eventContext) throws Exception
  {
	//Get the SFTP connector configuration instance
    SftpConnector connector = (SftpConnector)eventContext.getMuleContext().getRegistry().lookupConnector("SFTP-Configuration");
    
    //Endpoint cofiguration loader for the SFTp
    ImmutableEndpoint endpoint = ((EndpointURIEndpointBuilder)eventContext.getMuleContext().getRegistry().lookupObject("SFTPEndPoint")).buildOutboundEndpoint();
    
    //Create connection
    SftpClient client = connector.createSftpClient(endpoint);
    
    //Go to given permitted directory
    client.changeWorkingDirectory(endpoint.getEndpointURI().getUri().getPath());
    

   // Check if the directory which we are going to create is already present!
   if(!Arrays.asList(client.listDirectories()).contains(eventContext.getMessage().getInvocationProperty("directoryToCreate"))){
	   
	   //If the directory is not present then create the directory
	   client.mkdir(eventContext.getMessage().getInvocationProperty("directoryToCreate"));
	   
   }
   
   //Close the connection with SFTP
    connector.releaseClient(endpoint, client);
    return eventContext.getMessage().getPayload();
  }
}
