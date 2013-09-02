package be.kuleuven.cs.campusforum.api;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

abstract class Resource {
	
	/**
	 * The constructor for the Resource class.
	 */
	Resource() {
		super();
	}
	
	private HttpClient httpClient = new DefaultHttpClient ();
	private HttpContext httpContext = new BasicHttpContext ();
	
	/**
	 * @return the HTTP Client.
	 */
	HttpClient getHttpClient() {
		return httpClient;
	}
	
	/**
	 * @return the HTTP Context.
	 */
	HttpContext getHttpContext() {
		return httpContext;
	}
}
