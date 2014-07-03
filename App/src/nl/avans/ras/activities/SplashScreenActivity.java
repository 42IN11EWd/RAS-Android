package nl.avans.ras.activities;

import nl.avans.ras.R;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends Activity {

	private static final int SPLASH_DISPLAY_TIME = 2000; /* 2 seconds */

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);

		// make sure AsyncTask is loaded in the Main thread
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				return null;
			}
		};

		new Handler().postDelayed(new Runnable() {

			public void run() {
				Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.mainfadein,
						R.anim.splashfadeout);
			}
		}, SPLASH_DISPLAY_TIME);
	}
}
