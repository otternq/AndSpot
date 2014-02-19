package com.nickotter.andspot.adapter;

import java.util.ArrayList;
import java.util.Collection;

import com.nickotter.andspot.dto.Song;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SongAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<Song> songs;
	
	public SongAdapter(Context tempContext, ArrayList<Song> tempSongs) {
		this.context = tempContext;
		this.songs = tempSongs;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return songs.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return songs.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View v = convertView;
	    if (v == null) {
	        LayoutInflater mInflater = (LayoutInflater) context
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        v = mInflater.inflate(android.R.layout.simple_list_item_1, null);
	    }
	    
	    TextView text = (TextView) v.findViewById(android.R.id.text1);
	    text.setText(songs.get(position).getName());
	    
	    return v;
		
	}
	

}
