package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.PlayList;
import com.example.demo.entity.Song;
import com.example.demo.services.PlayListService;
import com.example.demo.services.SongService;

@Controller
public class PlayListController {
	@Autowired
	SongService serv;
	
	@Autowired
	PlayListService playserv;
	
	
	@GetMapping("/createPlayList")
	public String createPlayList(Model model) {
		model.addAttribute("songs",serv.fetchAllSongs());
		return "createPlayList";
	}
	
	@PostMapping("/addPlayList")
	public String addPlayList(@ModelAttribute PlayList playlist) {
		
		//update playlist table
		playserv.addPlayList(playlist);
		
		//update song table
		List<Song> songlist = playlist.getSongs();

			for(Song s: songlist) {

					 s.getPlaylists().add(playlist);
					 serv.updateSong(s);

			}

		return "adminHome";
	}
	
	@GetMapping("/viewPlaylists")
	public String viewPlaylists(Model model) {
		
		model.addAttribute("playList",playserv.fetchPlayList());
		
		return "displayPlayList";
	}

}
