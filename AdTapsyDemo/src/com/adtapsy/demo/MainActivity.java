package com.adtapsy.demo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.adtapsy.sdk.AdTapsy;
import com.adtapsy.sdk.AdTapsyActivity;
import com.adtapsy.sdk.AdTapsyDelegate;

public class MainActivity extends AdTapsyActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AdTapsy.startSession(this, "54982cf7e4b052cd2a20a7b8");
		AdTapsy.showInterstitial(this);

		AdTapsy.setDelegate(new AdTapsyDelegate() {

			@Override
			public void onAdSkipped() {
				System.out.println("***onAdSkipped***");
			} 

			@Override
			public void onAdShown() {
				System.out.println("***onAdShown***");
			}

			@Override
			public void onAdFailToShow() {
				System.out.println("***onAdFailToShow***");
			}

			@Override
			public void onAdClicked() {
				System.out.println("***onAdClicked***");

			}
			
			@Override
			public void onAdCached() {
				System.out.println("***onAdCached***");
			}
		});

		((Button) findViewById(R.id.showAdBtn))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if(AdTapsy.isAdReadyToShow()){
							System.out.println("Ad is ready to show");
							AdTapsy.showInterstitial(MainActivity.this);
						} else {
							System.out.println("Ad is not ready to be shown");
						}
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
