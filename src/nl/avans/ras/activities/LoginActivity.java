package nl.avans.ras.activities;

import java.util.Date;

import nl.avans.ras.R;
import nl.avans.ras.database.DatabaseHelper;
import nl.avans.ras.fragments.LoginFragment;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends Activity implements LoginFragment.OnLoginListener {

	// Fields
	private DatabaseHelper dbHelper = new DatabaseHelper(this);
	private boolean doubleBackToExitPressedOnce = false;
	
	private View mSplashScreen, mFragmentHolder;
	private int mShortAnimationDuration;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
//		
//		mSplashScreen = findViewById(R.id.splashscreen);
//		mFragmentHolder = findViewById(R.id.fragment_container);
//		
//		getActionBar().hide();
//		mFragmentHolder.setVisibility(View.GONE);
//		mShortAnimationDuration = getResources().getInteger(android.R.integer.config_mediumAnimTime);

		// Create a new login fragment
    	LoginFragment loginFragment = new LoginFragment();
     	
     	// Replace the fragment
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
     	transaction.replace(R.id.fragment_container, loginFragment);
     	transaction.commit();
		
//		new Handler().postDelayed(new Runnable() {
//     		@Override
//     		public void run() {
//     			// Let the splashscreen fade out
//     			showContentOrSplashScreen();
//			}					
//		}, 1500);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
		// Check if there is any backstack.
		if (getFragmentManager().getBackStackEntryCount() == 0) {
			if (doubleBackToExitPressedOnce) {
				super.onBackPressed();
				return;
			} else {
				doubleBackToExitPressedOnce = true;
				
				// Show the user a toast they need to press the back button
				// again to shut down the application
				Toast.makeText(this, "Press back again," + "\n" + "to shutdown the application", Toast.LENGTH_SHORT).show();
						
				// Start a handler to reset doubleBackToExitPressedOnce after 
				// the time is passed
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						doubleBackToExitPressedOnce = false;
					}					
				}, 2000);
			}
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public void OnLogin(String username, String password) {
		startActivity(new Intent(this, ProfileActivity.class));
		this.finish();
	}
	
//	/******************************************************************
//	 * 
//	 *                         Animations
//	 *
//	 ******************************************************************/
//	
//    private void showContentOrSplashScreen() {
//        // Decide which view to hide and which to show.
//        final View showView = mFragmentHolder;
//        final View hideView = mSplashScreen;
//
//        // Set the "show" view to 0% opacity but visible, so that it is visible
//        // (but fully transparent) during the animation.
//        showView.setAlpha(0f);
//        showView.setVisibility(View.VISIBLE);
//
//        // Animate the "show" view to 100% opacity, and clear any animation listener set on
//        // the view. Remember that listeners are not limited to the specific animation
//        // describes in the chained method calls. Listeners are set on the
//        // ViewPropertyAnimator object for the view, which persists across several
//        // animations.
//        showView.animate()
//                .alpha(1f)
//                .setDuration(mShortAnimationDuration)
//                .setListener(null);
//
//        // Animate the "hide" view to 0% opacity. After the animation ends, set its visibility
//        // to GONE as an optimization step (it won't participate in layout passes, etc.)
//        hideView.animate()
//                .alpha(0f)
//                .setDuration(mShortAnimationDuration)
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        hideView.setVisibility(View.GONE);
//                    }
//                });
//        
//        // Show the actionbar
//     	new Handler().postDelayed(new Runnable() {
//     		@Override
//     		public void run() {
//     			getActionBar().show();
//     		}
//     	}, 750);
//    }
}
