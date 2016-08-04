package com.marsXTU.qianghongbao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.domob.android.ads.AdManager.ErrorCode;
import cn.domob.android.ads.InterstitialAd;
import cn.domob.android.ads.InterstitialAdListener;

/**
 * @author marsXTU(hejunlin2013@gmail.com)
 */
public class HomeActivity extends Activity {

	public static final String PUBLISHER_ID = "56OJ2WLouNwZA2BR8E";
	public static final String InlinePPID = "16TLPDCvApSZYNUUL4gbAkCi";
	public static final String InterstitialPPID = "16TLPDCvApSZYNUULBuLz4rs";
	public static final String SplashPPID = "16TLPDCvApSZYNUULX6sD9Gk";
	public static boolean mIsStarted = false;
	private Button mStartServiceButton;
	private View mGuideViewButton;
	private Button mRightButton;
	private TextView mTextButton1;
	private TextView mTextButton2;
	private InterstitialAd mInterstitialAd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		mInterstitialAd = new InterstitialAd(this, HomeActivity.PUBLISHER_ID,
				HomeActivity.InterstitialPPID);
		mStartServiceButton = (Button) findViewById(R.id.start_button);
		mGuideViewButton = findViewById(R.id.home_icon);
		mRightButton = (Button) findViewById(R.id.right_button);
		mTextButton1 = (TextView) findViewById(R.id.personal_update_account);
		mTextButton1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this, BannerAdActivity.class);
				startActivity(intent);
			}
		});
		mTextButton2 = (TextView) findViewById(R.id.personal_help);
		mInterstitialAd.setInterstitialAdListener(new InterstitialAdListener() {
			@Override
			public void onInterstitialAdReady() {
				Log.i("DomobSDKDemo", "onAdReady");
			}

			@Override
			public void onLandingPageOpen() {
				Log.i("DomobSDKDemo", "onLandingPageOpen");
			}

			@Override
			public void onLandingPageClose() {
				Log.i("DomobSDKDemo", "onLandingPageClose");
			}

			@Override
			public void onInterstitialAdPresent() {
				Log.i("DomobSDKDemo", "onInterstitialAdPresent");
			}

			@Override
			public void onInterstitialAdDismiss() {
				// Request new ad when the previous interstitial ad was closed.
				mInterstitialAd.loadInterstitialAd();
				Log.i("DomobSDKDemo", "onInterstitialAdDismiss");
			}

			@Override
			public void onInterstitialAdFailed(ErrorCode arg0) {
				Log.i("DomobSDKDemo", "onInterstitialAdFailed");				
			}

			@Override
			public void onInterstitialAdLeaveApplication() {
				Log.i("DomobSDKDemo", "onInterstitialAdLeaveApplication");
				
			}

			@Override
			public void onInterstitialAdClicked(InterstitialAd arg0) {
				Log.i("DomobSDKDemo", "onInterstitialAdClicked");
			}
		});
		
		mInterstitialAd.loadInterstitialAd();
		mTextButton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (mInterstitialAd.isInterstitialAdReady()){
					mInterstitialAd.showInterstitialAd(HomeActivity.this);
				} else {
					Log.i("DomobSDKDemo", "Interstitial Ad is not ready");
					mInterstitialAd.loadInterstitialAd();
				}
			}
		});
		mRightButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent intent = new Intent(HomeActivity.this, AboutUsActivity.class);
				 startActivity(intent);
			}
		});
		mGuideViewButton.setOnClickListener(mOnGuideHongBaoListener);
		mStartServiceButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!mIsStarted) {
					open();
				} else {
					mStartServiceButton.setText("已开启自动抢红包服务啦！");
				}
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if (mIsStarted) {
			mStartServiceButton.setText("已开启自动抢红包服务啦！");
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	private OnClickListener mOnGuideHongBaoListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(HomeActivity.this, GuideActivity.class);
			startActivity(intent);
		}
		
	};

	private void open() {
		try {
			Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
			startActivity(intent);
			Toast.makeText(this, "找到【红包精灵】，然后开启服务即可", Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
