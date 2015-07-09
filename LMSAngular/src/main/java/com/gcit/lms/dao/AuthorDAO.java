package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.domain.Author;

public class AuthorDAO extends BaseDAO<Author> implements Serializable, ResultSetExtractor<List<Author>> {

	private static final long serialVersionUID = 1619700647002508164L;
	
	private static final String AUTH_COLLECTION = "Authors";

	public void addAuthor(Author author) throws SQLException { 
		author.setAuthorId(getNextSequenceId(AUTH_COLLECTION));
		mongoOps.insert(author, AUTH_COLLECTION); 
	} 
	
	public Author updateAuthor(Author author) throws SQLException { 
		Query query = new Query(Criteria.where("_id").is(author.authorId));
		Update update=  new Update();
		update.set("authorName", author.getAuthorName());
		 mongoOps.updateFirst(query, update, AUTH_COLLECTION);
		 return author;
	} 
	public Author deleteAuthor(Author author)  {      
		mongoOps.remove(author, AUTH_COLLECTION); 
		return author;
	}  
	public List<Author> readAll() throws SQLException {
		return this.mongoOps.findAll(Author.class, AUTH_COLLECTION);
	} 
	public Author readOne(long authorId) throws SQLException {
		Query query = new Query(Criteria.where("_id").is(authorId));
		return this.mongoOps.findOne(query, Author.class, AUTH_COLLECTION); 
	}
	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<Author>();
		while (rs.next()) {
			Author a = new Author();
			//a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			authors.add(a);
		}
		return authors;
	}
}
