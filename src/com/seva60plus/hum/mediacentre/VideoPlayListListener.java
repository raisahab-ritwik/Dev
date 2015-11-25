package com.seva60plus.hum.mediacentre;

import java.util.ArrayList;

import com.seva60plus.hum.model.Video;

public interface VideoPlayListListener {
	void videoPlaylistAsyncCallback(ArrayList<Video> result);

}
