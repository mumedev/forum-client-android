package be.kuleuven.cs.campusforum;

import be.kuleuven.cs.campusforum.api.ForumAPI;
import be.kuleuven.cs.campusforum.api.listeners.StartSessionListener;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;


/**
 * <p><code>MainActivity</code> is the central activity class of this application.</p>
 * @author Joris Schelfaut
 */
public class MainActivity extends Activity implements StartSessionListener {

	ForumAPI api = new ForumAPI();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		api.authentication().startSession(this, -1, "joris", "joris");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void handleStartSession(String key) {
		showPopup("Test", key);
	}
	
	private void showPopup(String title, String txt) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(txt);
		alertDialog.show();
	}
	
}
