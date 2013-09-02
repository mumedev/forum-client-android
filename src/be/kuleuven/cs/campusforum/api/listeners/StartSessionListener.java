package be.kuleuven.cs.campusforum.api.listeners;

public interface StartSessionListener {
	
	/**
	 * Handle the session key when the asynchronous task has returned a result.
	 * @param key
	 */
	void handleStartSession (String key);
	
}
