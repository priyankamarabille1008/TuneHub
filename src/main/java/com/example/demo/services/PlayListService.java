package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.PlayList;
import com.example.demo.entity.Song;


public interface PlayListService {

	public void addPlayList(PlayList playlist);
	public List<PlayList> fetchPlayList();
	boolean playListExist(String name);
	PlayList getPlayListByName(String name);
	void updatePlayList(PlayList playlist);
}
