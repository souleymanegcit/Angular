package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;

public class GenreDAO extends BaseDAO<Genre> implements Serializable, ResultSetExtractor<List<Genre>> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2292999931244237524L;
	
	private static final String GENRE_COLLECTION = "Genres";

	public void addGenre(Genre genre) throws SQLException { 
		genre.setGenreId(getNextSequenceId(GENRE_COLLECTION));
		mongoOps.insert(genre, GENRE_COLLECTION);
	} 
	 
	public void updatePublisher(Publisher publisher) throws SQLException {
 
	}

	public void removePublisher(Publisher publisher) throws SQLException {
 
	}

	public List<Genre> readAll() throws SQLException {		 ;
		return this.mongoOps.findAll(Genre.class, GENRE_COLLECTION);
	}

	public Genre readOne(int genreId) throws SQLException { 
		Query query = new Query(Criteria.where("_id").is(genreId));
		return this.mongoOps.findOne(query, Genre.class, GENRE_COLLECTION);
	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<Genre>();
		while (rs.next()) {
			Genre a = new Genre();
			a.setGenreId(rs.getInt("genreId"));
			a.setName(rs.getString("Name"));

			genres.add(a);
		}
		return genres;
	}
}
