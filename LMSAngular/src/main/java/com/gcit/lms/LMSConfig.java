package com.gcit.lms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.mongodb.MongoClient;

@EnableTransactionManagement
@Configuration
public class LMSConfig { 
	
	@Bean
	public BookDAO bkDao() {
		return new BookDAO();
	}

	@Bean
	public AuthorDAO authDao() {
		return new AuthorDAO();
	}

	@Bean
	public PublisherDAO pubDao() {
		return new PublisherDAO();
	}
	 
	@Bean
	public GenreDAO genreDAO() {
		return new GenreDAO();
	}
 
	@Bean
	public MongoDbFactory getMongoDbFactory() throws Exception{
		return new SimpleMongoDbFactory(new MongoClient("localhost", 27017), "local");
	}
	
	@Bean
	public MongoTemplate getMongoTemplate() throws Exception{
		MongoTemplate mongoTemplate = new MongoTemplate(getMongoDbFactory());
		return mongoTemplate;
	}
}
