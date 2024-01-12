package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Playlist;
import com.example.demo.entity.Song;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.SongServices;

@Controller
public class PlayListController {
	@Autowired
	SongServices songServices;
	@Autowired
	PlaylistService playlistService;
	@GetMapping("/createPlaylist")
	public String createPlaylist(Model model) {
		List<Song> songList=songServices.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "createPlaylist";
		
		
	}
	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		//updating playlist table
		playlistService.addPlaylist(playlist);
		//updating song table
		List<Song> songList=playlist.getSongs();
		for(Song s:songList) {
			s.getPlaylists().add(playlist);
			songServices.updateSong(s);
			//update song object in db
		}
		return "administrationHome";
	}
	@GetMapping("/viewPlaylists")
	public String viewPlaylists(Model model) {
		List<Playlist> allPlaylists=playlistService.fetchAllPlaylists();
		model.addAttribute("allPlaylists", allPlaylists);
		return "displayPlaylists";
	}

}