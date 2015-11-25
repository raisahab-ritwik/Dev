package com.seva60plus.hum.knowledgebase;
import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import com.seva60plus.hum.R;

public class VideoPlayer extends Activity {

	private VideoView myVideoView;
	private int position = 0;
	private ProgressDialog progressDialog;
	private MediaController mediaControls;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Get the layout from video_main.xml
		setContentView(R.layout.video_player);

		if (mediaControls == null) {
			mediaControls = new MediaController(VideoPlayer.this);
		}

		// Find your VideoView in your video_main.xml layout
		myVideoView = (VideoView) findViewById(R.id.video_view);

		// Create a progressbar
		progressDialog = new ProgressDialog(VideoPlayer.this);
		// Set progressbar title
		progressDialog.setTitle("JavaCodeGeeks Android Video View Example");
		// Set progressbar message
		progressDialog.setMessage("Loading...");

		progressDialog.setCancelable(false);
		// Show progressbar
		progressDialog.show();

		try {
			myVideoView.setMediaController(mediaControls);
		//	myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));

		} catch (Exception e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}

		myVideoView.requestFocus();
		myVideoView.setOnPreparedListener(new OnPreparedListener() {
			// Close the progress bar and play the video
			public void onPrepared(MediaPlayer mp) {
				progressDialog.dismiss();
				myVideoView.seekTo(position);
				if (position == 0) {
					myVideoView.start();
				} else {
					myVideoView.pause();
				}
			}
		});

	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putInt("Position", myVideoView.getCurrentPosition());
		myVideoView.pause();
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		position = savedInstanceState.getInt("Position");
		myVideoView.seekTo(position);
	}
}

