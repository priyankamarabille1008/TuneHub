package com.example.demo.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PlayList;
import com.example.demo.entity.Song;
import com.example.demo.repository.PlayListRepository;


@Service
public class PlayListServiceImplementation implements PlayListService{
	@Autowired
	PlayListRepository playRepo;
	
	@Override
	public void addPlayList(PlayList playlist) {
		playRepo.save(playlist);
	}

	@Override
	public List<PlayList> fetchPlayList() {
		return playRepo.findAll();
	}

	@Override
	public boolean playListExist(String name) {
		if(playRepo.findByName(name) == null) {
			return false;
		}else {
		return true;
		}
	}

	@Override
	public PlayList getPlayListByName(String name) {
		PlayList playlist=playRepo.findByName(name);
		return playlist;
	}

	@Override
	public void updatePlayList(PlayList playlist) {
		playRepo.save(playlist);
		
	}
}
