package com.gcit.lms;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;

/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {
	
	@Autowired
	AuthorDAO authorDAO;
	
	@Autowired
	BookDAO bookDAO;
	
	@Autowired
	PublisherDAO pubDAO;
	
	@Autowired
  GenreDAO genreDAO; 
 
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value="/author/get", method={RequestMethod.GET, RequestMethod.POST} ,
			produces="application/json")
	public List<Author> getAuthors(){
		try {
			return authorDAO.readAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping(value="/book/getOne", method={RequestMethod.GET, RequestMethod.POST} ,
			produces="application/json")
	public Book getBook(@RequestBody Book book){
		try {
			return bookDAO.readOne(book.getBookId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping(value="/publisher/get", method={RequestMethod.GET, RequestMethod.POST} ,
			produces="application/json")
	public List<Publisher> getPublisher(){
		try {
			return pubDAO.readAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/genre/get", method={RequestMethod.GET, RequestMethod.POST} ,
			produces="application/json")
	public List<Genre> getGenre(){
		try {
			return genreDAO.readAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/book/get", method={RequestMethod.GET, RequestMethod.POST} , produces="application/json")
	public List<Book> getBooks(){

			return bookDAO.readAll();
	} 
	
	@RequestMapping(value="/author/add", method={RequestMethod.GET, RequestMethod.POST} , 
	consumes="application/json")
	public String addAuthor(@RequestBody Author author) { 
				try {
					authorDAO.addAuthor(author);
				} catch (SQLException e) {					
					e.printStackTrace();
				} 
			return "Author added sucessfully"; 
	}
	
	@RequestMapping(value="/publisher/add", method={RequestMethod.GET, RequestMethod.POST} , 
			consumes="application/json")
			public String addPublisher(@RequestBody Publisher publisher) { 
						try {
							pubDAO.addPublisher(publisher);
						} catch (SQLException e) {					
							e.printStackTrace();
						} 
					return "Publisher added sucessfully"; 
			}
	
	@RequestMapping(value="/genre/add", method={RequestMethod.GET, RequestMethod.POST} , 
			consumes="application/json")
			public String addGenre(@RequestBody Genre genre) { 
						try {
							genreDAO.addGenre(genre);
						} catch (SQLException e) {					
							e.printStackTrace();
						} 
					return "Genre added sucessfully"; 
			}
	
	@RequestMapping(value = "/author/delete", method={RequestMethod.GET, RequestMethod.POST} ,
			consumes = "application/json")	 
		public String deleteAuthor(@RequestBody Author author)   {
			authorDAO.deleteAuthor(author); 
			return "Author deleted succesfully!";
		} 
 
	@RequestMapping(value="/book/add", method={RequestMethod.GET, RequestMethod.POST} , consumes="application/json")
	public String addBook(@RequestBody Book book){
		try {
			bookDAO.addBook(book);
			return "Book added sucessfully";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Book add failed";
		}
	}
	
	@RequestMapping(value="/author/getOne", method={RequestMethod.GET, RequestMethod.POST} , produces="application/json", consumes="application/json")
	public Author getOneAuthor(@RequestBody Author author){
		try {
			return authorDAO.readOne(author.getAuthorId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/author/update", method={RequestMethod.GET, RequestMethod.POST} ,
			consumes="application/json", produces="application/json")
	public String updateAuthor(@RequestBody Author author){
		try {
			authorDAO.updateAuthor(author); 
			return "Author updated sucessfully";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Author update failed";
		}
		
	}
	
}
