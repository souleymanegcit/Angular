package com.gcit.lms.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.ResultSetExtractor; 
import com.gcit.lms.domain.Book;

public class BookDAO extends BaseDAO<Book> implements Serializable, ResultSetExtractor<List<Book>> {

	private static final long serialVersionUID = 1619700647002508164L;
	
	@Autowired
	PublisherDAO pDAO;
	
	private static final String BOOK_COLLECTION = "Books";
	
	public void addBook(Book book) throws SQLException { 
		book.setBookId(getNextSequenceId(BOOK_COLLECTION));
		mongoOps.insert(book, BOOK_COLLECTION);
	}

	public void updateAuthor(Book book) throws SQLException {
	}

	public void removeAuthor(Book book) throws SQLException {
	}

	public List<Book> readAll(){ 
		//return this.mongoOps.findAll(Book.class, BOOK_COLLECTION); 
		List<Book> books = (List<Book>)this.mongoOps.findAll(Book.class, BOOK_COLLECTION); 
		return books;
	}

	public Book readOne(long bookId) throws SQLException {
		Query query = new Query(Criteria.where("_id").is(bookId));
		return this.mongoOps.findOne(query, Book.class, BOOK_COLLECTION); 		
	}

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();		
		while (rs.next()) {
			Book b = new Book();
			//b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			b.setPublisher(pDAO.readOne(rs.getInt("pubId")));
			books.add(b);
		}
		return books;
	}
 
}
