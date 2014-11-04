package org.tigercontact.contacts.filter

import groovy.util.logging.Log4j

import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerResponseContext
import javax.ws.rs.container.ContainerResponseFilter
import javax.ws.rs.container.PreMatching
import javax.ws.rs.ext.Provider


@Log4j
@Provider
@PreMatching
public class ResponseCorsFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
	throws IOException {
		log.info "inside cors filter!"
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*")
		responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true")
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")
	}
}
