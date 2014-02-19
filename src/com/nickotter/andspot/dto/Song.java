package com.nickotter.andspot.dto;

public class Song {
	
	private String uri;
	private String name;
	private float popularity;
	private float track_number;
	private float length;
	
	public Artist[] artists = null;
	public Album album = null;
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPopularity() {
		return popularity;
	}
	public void setPopularity(float popularity) {
		this.popularity = popularity;
	}
	public float getTrack_number() {
		return track_number;
	}
	public void setTrack_number(float track_number) {
		this.track_number = track_number;
	}
	public float getLength() {
		return length;
	}
	public void setLength(float length) {
		this.length = length;
	}
	public Artist[] getArtists() {
		return artists;
	}
	public void setArtists(Artist[] artists) {
		this.artists = artists;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}

}

/**
 * {
 * 	"uri":"spotify:track:4LwU4Vp6od3Sb08CsP99GC",
 *  "name":"The Next Episode",
 *  "popularity":0.74,
 *  "track_number":11,
 *  "length":161.506,
 *  "artists":[
 *  	{"name":"Dr. Dre","uri":"spotify:artist:6DPYiyq5kWVQS4RGwxzPC7"},
 *  	{"name":"Snoop Dogg","uri":"spotify:artist:7hJcb9fa4alzcOq3EaNPoG"}
 *  ],
 *  "album":{
 *  	"name":"2001",
 *  	"released":"2008",
 *  	"uri":"spotify:album:7q2B4M5EiBkqrlsNW8lB7N"
 *   }
 * }
 */