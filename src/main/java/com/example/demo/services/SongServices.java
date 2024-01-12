package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Song;

public interface SongServices {

	public void addSong(Song song);
	
	public void updateSong(Song song);
	
	


	public List<Song> fetchAllSongs();

	public boolean songExist(String name);
	

}
