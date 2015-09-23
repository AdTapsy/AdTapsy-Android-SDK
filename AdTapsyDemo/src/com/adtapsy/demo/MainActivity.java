package com.adtapsy.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.adtapsy.sdk.AdTapsy;
import com.adtapsy.sdk.AdTapsyActivity;
import com.adtapsy.sdk.AdTapsyDelegate;
import com.adtapsy.sdk.AdTapsyRewardedDelegate;

public class MainActivity extends AdTapsyActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AdTapsy.startSession(this, "54982cf7e4b052cd2a20a7b8");
		AdTapsy.setRewardedVideoAmount(10);
		AdTapsy.showInterstitial(this);
		
		AdTapsy.setDelegate(new AdTapsyDelegate() {
			@Override
			public void onAdSkipped(int zoneId) {
				if(zoneId == AdTapsy.INTERSTITIAL_ZONE){
					Log.d("AdTapsy Delegate", "Ad skipped from interstitial zone ");
				} else if(zoneId == AdTapsy.REWARDED_VIDEO_ZONE){
					Log.d("AdTapsy Delegate", "Ad skipped from rewarded video zone ");
				}
			}
			@Override
			public void onAdShown(int zoneId) {
				if(zoneId == AdTapsy.INTERSTITIAL_ZONE){
					Log.d("AdTapsy Delegate", "Ad shown from interstitial zone");					
				} else if(zoneId == AdTapsy.REWARDED_VIDEO_ZONE){
					Log.d("AdTapsy Delegate", "Ad shown from rewarded video zone");
				}
			}
			@Override
			public void onAdFailToShow(int zoneId) {
				if(zoneId == AdTapsy.INTERSTITIAL_ZONE){
					Log.d("AdTapsy Delegate", "Ad failed to show from zone " + zoneId);					
				} else if(zoneId == AdTapsy.REWARDED_VIDEO_ZONE){
					Log.d("AdTapsy Delegate", "Ad failed to show from rewarded video zone");					
				}
			}
			@Override
			public void onAdClicked(int zoneId) {
				if(zoneId == AdTapsy.INTERSTITIAL_ZONE){
					Log.d("AdTapsy Delegate", "Ad clicked from interstitial zone");					
				} else if(zoneId == AdTapsy.REWARDED_VIDEO_ZONE){
					Log.d("AdTapsy Delegate", "Ad clicked from rewarded video zone");	
				}
			}
			@Override
			public void onAdCached(int zoneId) {
				if(zoneId == AdTapsy.INTERSTITIAL_ZONE){
					Log.d("AdTapsy Delegate", "Ad loaded from interstitial zone");					
				} else if(zoneId == AdTapsy.REWARDED_VIDEO_ZONE){
					Log.d("AdTapsy Delegate", "Ad loaded from rewarded zone zone");
				}
				 
			}
		});
		AdTapsy.setRewardedDelegate(new AdTapsyRewardedDelegate() {
			
			@Override
			public void onRewardEarned(int amount) {
				Log.d("AdTapsy Rewarded Delegate", "User earned " + amount + " coins");
			}
		});

		((Button) findViewById(R.id.showAdBtn))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if(AdTapsy.isInterstitialReadyToShow()){
							System.out.println("Ad is ready to show");
							AdTapsy.showInterstitial(MainActivity.this);
						} else {
							System.out.println("Ad is not ready to be shown");
						}
					}
				});
		((Button) findViewById(R.id.showRewardedBtn))
		.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(AdTapsy.isRewardedVideoReadyToShow()){
					System.out.println("Ad is ready to show");
					AdTapsy.showRewardedVideo(MainActivity.this);
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
