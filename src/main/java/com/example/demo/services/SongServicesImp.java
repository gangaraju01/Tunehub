package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Song;
import com.example.demo.repository.SongRepository;

@Service
public class SongServicesImp implements SongServices{
	@Autowired
	SongRepository repo;

	@Override
	public void addSong(Song song) {
		repo.save(song);
		
	}

	@Override
	public List<Song> fetchAllSongs() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public boolean songExist(String name) {
		// TODO Auto-generated method stub
		Song song=repo.getByName(name);
		if(song==null)
		{
			return false;
		}
		else {
			return true;
		}
		
	}

	@Override
	public void updateSong(Song song) {
		repo.save(song);
		
	}

}
