package com.seva60plus.hum.mediacentre;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.seva60plus.hum.R;

public class YouTubeMainActivity extends YouTubeBaseActivity implements
		YouTubePlayer.OnInitializedListener {

	private static final int RECOVERY_DIALOG_REQUEST = 1;

	// YouTube player view
	private YouTubePlayerView youTubeView;

	String DEVELOPER_KEY = "AIzaSyDU-loRUzeR9FhreB9mQWXc9NwjRNUm94s";
	String YOUTUBE_VIDEO_CODE= "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.youtube_activity_main);

		Intent intObj = getIntent();
		YOUTUBE_VIDEO_CODE = intObj.getStringExtra("videocode");
		
		System.out.println("Video Code : "+YOUTUBE_VIDEO_CODE);
		
		youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);

		// Initializing video player with developer key
		youTubeView.initialize(DEVELOPER_KEY, this);

	}

	@Override
	public void onInitializationFailure(YouTubePlayer.Provider provider,
			YouTubeInitializationResult errorReason) {
		if (errorReason.isUserRecoverableError()) {
			errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
		} else {
			String errorMessage = String.format(
					getString(R.string.error_player), errorReason.toString());
			Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onInitializationSuccess(YouTubePlayer.Provider provider,
			YouTubePlayer player, boolean wasRestored) {
		if (!wasRestored) {

			// loadVideo() will auto play video
			// Use cueVideo() method, if you don't want to play it automatically
			player.loadVideo(YOUTUBE_VIDEO_CODE);

			player.setFullscreen(true);
			// Hiding player controls
			//player.setPlayerStyle(PlayerStyle.CHROMELESS);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == RECOVERY_DIALOG_REQUEST) {
			// Retry initialization if user performed a recovery action
			getYouTubePlayerProvider().initialize(DEVELOPER_KEY, this);
		}
	}

	private YouTubePlayer.Provider getYouTubePlayerProvider() {
		return (YouTubePlayerView) findViewById(R.id.youtube_view);
	}

}
