package be.kuleuven.cs.campusforum.api;

/**
 * Collection of resources of the forum API.
 * @author Joris Schelfaut
 */
public class ForumAPI {
	
	/**
	 * The base URL of the forum REST API.
	 */
	static final String BASE_URL 	= "http://soundsuggest.host-ed.me/forum-server-restapi/index.php/api/";
	/**
	 * The format of the reply : "&format=json"
	 */
	static final String FORMAT_JSON	= "&format=json"; 
	
	/**
	 * Constructor for the ForumAPI class.
	 */
	public ForumAPI() {
		super();
	}
	
	/**
	 * The Authentication Resource.
	 * @return the authentication resource method interface.
	 */
	public final Authentication authentication () {
		return new Authentication ();
	}
	
}
