package com.cyanogenmod.eleven.utils;

import android.content.Context;

import com.baidu.music.model.LrcPicList;
import com.baidu.music.model.Music;
import com.baidu.music.model.SearchResult;
import com.baidu.music.model.SearchSuggestion;
import com.baidu.music.onlinedata.OnlineManagerEngine;
import com.baidu.music.onlinedata.SearchManager;

/**
 * Created by CHUER on 2015/01/19.
 */
public class SearchManagerWrapper {
    private SearchManager mSearchManager;

    public SearchManagerWrapper(Context context) {
        mSearchManager = OnlineManagerEngine.getInstance(context).getSearchManager(context);
    }

    public static interface LrcPicSearchListener extends SearchManager.LrcPicSearchListener {
        @Override
        void onGetLrcPicList(LrcPicList lrcPicList);
    }

    public static interface SearchListener extends SearchManager.SearchListener {
        @Override
        void onSearchMusic(SearchResult searchResult);

        @Override
        void onGetSearchSuggestion(SearchSuggestion searchSuggestion);

        @Override
        void onSearchLyric(String s);

        @Override
        void onSearchArtistAvatar(String s);

        @Override
        void onSearchAlbumPicture(String s);
    }

    public SearchResult searchMusic(String keyword,
                                    int pageNo,
                                    int pageSize) {
        return mSearchManager.searchMusic(keyword, pageNo, pageSize);
    }

    public void searchMusicAsync(String keyword,
                                 int pageNo,
                                 int pageSize,
                                 SearchListener listener) {
        mSearchManager.searchMusicAsync(keyword, pageNo, pageSize, listener);
    }

    public SearchSuggestion getSearchSuggestion(String keyword) {
        return mSearchManager.getSearchSuggestion(keyword);
    }

    public void getSearchSuggestionAsync(String keyword,
                                         SearchListener listener) {
        mSearchManager.getSearchSuggestionAsync(keyword, listener);
    }

    @Deprecated
    public String searchLyricSync(String musicTitle,
                                            String artist) {
        return mSearchManager.searchLyricSync(musicTitle, artist);
    }

    @Deprecated
    public void searchLyricAsync(String musicTitle,
                                 String artist,
                                 SearchListener listener) {
        mSearchManager.searchLyricAsync(musicTitle, artist, listener);
    }

    public String searchArtistAvatar(String artistName) {
        return mSearchManager.searchArtistAvatar(artistName);
    }

    public void searchArtistAvatarAsync(String artistName,
                                        SearchListener listener) {
        mSearchManager.searchArtistAvatarAsync(artistName, listener);
    }

    @Deprecated
    public String searchAlbumPictureSync(String albumName,
                                                   String artist) {
        return mSearchManager.searchAlbumPictureSync(albumName, artist);
    }

    @Deprecated
    public void searchAlbumPictureAsync(String albumName,
                                        String artistName,
                                        SearchListener listener) {
        mSearchManager.searchAlbumPictureAsync(albumName, artistName, listener);
    }

    public LrcPicList getLyricPic(Context context,
                                  String title,
                                  String artist) {
        return mSearchManager.getLyricPic(context, title, artist);
    }

    public void getLyricPicAsync(Context context,
                                 String title,
                                 String artist,
                                 LrcPicSearchListener listener) {
        mSearchManager.getLyricPicAsync(context, title, artist, listener);
    }
    
    public void playRecommandSongList(Music music){
    	if(mSearchManager == null){
    		return;
    	}
    	mSearchManager.playRecommandSongList(music);
    }
    
    public void playRecommandSongList(long musicId){
    	if(mSearchManager == null){
    		return;
    	}
    	mSearchManager.playRecommandSongList(musicId);;
    }
    
    public void play(){
    	if(mSearchManager == null){
    		return;
    	}
    	mSearchManager.play();
    }
    
    public void pause(){
    	if(mSearchManager == null){
    		return;
    	}
    	mSearchManager.pause();
    }
    
    public long duration(){
    	if(mSearchManager == null){
    		return 0;
    	}
    	return mSearchManager.duration();
    }
    
    public void seekTo(int progress) {
		if (mSearchManager == null) {
			return;
		}
		mSearchManager.seekTo(progress);
	}
    
    public int getPosition() {
		if (mSearchManager != null) {
			return mSearchManager.getPosition();
		}
		return 0;
	}
    
    public String getMusicTitle() {
		if (mSearchManager != null) {
			return mSearchManager.getMusicTitle();
		}
		return null;
	}

	public String getMusicAlbum() {
		if (mSearchManager != null) {
			return mSearchManager.getMusicAlbum();
		}
		return null;
	}

	public String getMusicArtist() {
		if (mSearchManager != null) {
			return mSearchManager.getMusicArtist();
		}
		return null;
	}
    
    public long getMusicId(){
    	if(mSearchManager == null){
    		return 0;
    	}
    	return mSearchManager.getMusicId();
    }
    
    public boolean isPlaying() {
    	if(mSearchManager == null){
    		return false;
    	}
    	return mSearchManager.isPlaying();
    }
    
    public void playPrevious() {
    	if(mSearchManager == null){
    		return;
    	}
    	mSearchManager.playPrevious();
    }
    
    public void playNext() {
    	if(mSearchManager == null){
    		return;
    	}
    	mSearchManager.playNext();
    }
    
    public void stop() {
		if (mSearchManager == null) {
			return;
		}
		mSearchManager.seekTo(0);
		mSearchManager.pause();
		mSearchManager.reset();
	}
    
    public void reset() {
		if (mSearchManager == null) {
			return;
		}
		mSearchManager.reset();
	}
}
