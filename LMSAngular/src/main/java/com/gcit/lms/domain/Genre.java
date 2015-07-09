package com.gcit.lms.domain;

import java.io.Serializable;
 

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Genre implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5406550796957034888L;
	@Id
	public long genreId;
	
	private String name;

	public long getGenreId() {
		return genreId;
	}

	public void setGenreId(long genreId) {
		this.genreId = genreId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (genreId ^ (genreId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		if (genreId != other.genreId)
			return false;
		return true;
	}
	
 

	 
	
}
