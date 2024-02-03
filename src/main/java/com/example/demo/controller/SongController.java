package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Song;
import com.example.demo.services.PlayListService;
import com.example.demo.services.SongService;
import com.example.demo.services.UsersService;

/* 
 * 1.addSong 
 * check if song already exist or not if not add song
 * 2.viewSong
 * */

@Controller
public class SongController {
	
	@Autowired
	UsersService usv;
	
	@Autowired
	SongService songserv;
	
	@Autowired
	PlayListService playserv;
	
	@PostMapping("/addSong")
	public String addSong(@ModelAttribute Song songs, Model model) {
	    boolean songExists = songserv.songExists(songs.getName());

	    if (!songExists) {
	        songserv.addSong(songs);
	        model.addAttribute("message", "Song added successfully.");
	        return "addSong";
	    } else {
	        model.addAttribute("error", "Song already exists.");
	        return "addSong";
	    }
	}
	
	@GetMapping("/viewSongs")
	//Model is used to send data back to the F ront-End
	public String viewSongs(Model model){
		
//		List<Song> songsList=songserv.fetchAllSongs();
		
		model.addAttribute("songs",songserv.fetchAllSongs());
		
//		System.out.println(songsList);
		return "viewSongs";
	}
	
	@GetMapping("/isPremium")
	public String playSongs(Model model){
			model.addAttribute("songs",songserv.fetchAllSongs());
			return "customerHome";
	}
}

