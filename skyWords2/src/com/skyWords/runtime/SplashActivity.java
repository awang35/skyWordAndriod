package com.skyWords.runtime;

import com.skywords2.R;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

public class SplashActivity extends Activity {
	private void jump() {
		// it is safe to use this code even if you
		// do not intend to allow users to skip the splash
		if (isFinishing())
			return;
		startActivity(new Intent(this, SkyWordsActivity.class));
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.splash_activity);
		try {
			VideoView videoHolder = new VideoView(this);
			setContentView(videoHolder);
			Uri video = Uri.parse("android.resource://" + getPackageName()
					+ "/" + R.raw.splash);
			videoHolder.setVideoURI(video);
			//videoHolder.
			videoHolder.setOnCompletionListener(new OnCompletionListener() {

				public void onCompletion(MediaPlayer mp) {
					jump();
				}

			});
			videoHolder.start();
		} catch (Exception ex) {
			jump();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		jump();
		return super.onTouchEvent(event);
	}

}
