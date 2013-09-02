//////////////////////////////////////////////////////////////////////////
//
//		AUTHENTICATION RESOURCE
//
//////////////////////////////////////////////////////////////////////////

package be.kuleuven.cs.campusforum.api;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import be.kuleuven.cs.campusforum.api.listeners.EndSessionListener;
import be.kuleuven.cs.campusforum.api.listeners.StartSessionListener;

import android.os.AsyncTask;
import android.util.Log;

/**
 * 
 * @author Joris Schelfaut
 */
public class Authentication extends Resource {

	/**
	 * Constructor for the authentication resource class.
	 */
	Authentication() {
		super();
	}
	
	static final String RESOURCE_PREFIX = "authentication_";
	
	/**
	 * Log the user in.
	 * @param userid
	 * @param username
	 * @param password
	 * @return
	 */
	public final void startSession(StartSessionListener listener, int userid, String username, String password) {
		StartSession task = new StartSession(listener, userid, username, password);
		task.execute();
	}
	
	/**
	 * Log the user off.
	 * @param sessionkey
	 * @return
	 */
	public final void endSession(EndSessionListener listener, String sessionkey) {
		EndSession es = new EndSession(listener, sessionkey);
		es.execute();
	}
	
	/**
	 * Starts a session at the server. The client recieves a SessionKey.
	 * @author Joris Schelfaut
	 */
	private class StartSession extends AsyncTask<String, Void, String> {
		
		static final String METHOD_PREFIX = "startsession/";
		private final StartSessionListener listener;
		private final int userid;
		private final String username;
		private final String password;
		
		/**
		 * @param listener
		 */
		StartSession(StartSessionListener listener, int userid, String username, String password) {
			super ();
			this.listener = listener;
			this.userid = userid;
			this.username = username;
			this.password = password;
		}
		
		@Override
		protected String doInBackground(String... params) {
			
			String sessionkey = null;
			
            try {
            	
            	String httprequest = ForumAPI.BASE_URL + Authentication.RESOURCE_PREFIX + StartSession.METHOD_PREFIX + "?";
            	if (this.userid > 0) httprequest += "id=" + this.userid + "&";
            	if (this.username != null) httprequest += "username=" 	+ this.username + "&";
         		httprequest += "password=" + this.password;
         		httprequest += ForumAPI.FORMAT_JSON;
            	
                HttpGet httpGET = new HttpGet(httprequest);
                
                Log.i("ForumAPI", "HTTPGet == '" + httprequest + "'");
                
                HttpResponse response = getHttpClient().execute(httpGET, getHttpContext());
                Log.i("ForumAPI", "response.toString() == '" + response.toString() + "'");
                HttpEntity entity = response.getEntity();
                Log.i("ForumAPI", "EntityUtils.toString(entity) == '" + EntityUtils.toString(entity) + "'");
                
                //try {
                    //JSONObject json = new JSONObject(EntityUtils.toString(entity));
                    //JSONObject item = json.getJSONObject("item");
                    //sessionkey = item.getString("key");
                	sessionkey = EntityUtils.toString(entity);
                    Log.i("ForumAPI", "Session key : " + sessionkey);
                    
                //} catch (JSONException e) {
                //    Log.e("ForumAPI", e.getClass().getName() +  " : "  + e.getLocalizedMessage());
                //}
                
            } catch (IOException e) {
                Log.e("ForumAPI", e.getClass().getName() +  " : "  + Log.getStackTraceString(e));
            }
            
        	return sessionkey;
		}
		
		@Override
	    protected void onPostExecute(String key) {
	        super.onPostExecute(key);
	        if(this.listener != null) this.listener.handleStartSession (key);
	    }
	}
	
	/**
	 * 
	 * @author Joris Schelfaut
	 */
	private class EndSession extends AsyncTask<String, Void, Boolean> {
		
		static final String METHOD_PREFIX = "endsession/";
		private final EndSessionListener listener;
		private final String sessionkey;
		
		/**
		 * @param listener
		 * @param sessionkey
		 */
		public EndSession(EndSessionListener listener, String sessionkey) {
			super();
			this.listener = listener;
			this.sessionkey = sessionkey;
		}
		
		@Override
		protected Boolean doInBackground(String... params) {
			
			return false;
		}
		
		@Override
	    protected void onPostExecute(Boolean flag) {
	        super.onPostExecute(flag);
	        if(this.listener != null) this.listener.handleEndSession (flag);
	    }
	}
}
