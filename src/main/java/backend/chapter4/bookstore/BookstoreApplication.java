package backend.chapter4.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.chapter4.bookstore.domain.Book;
import backend.chapter4.bookstore.domain.BookRepository;
import backend.chapter4.bookstore.domain.Category;
import backend.chapter4.bookstore.domain.CategoryRepository;



@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
@Bean
public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository) {
	return (args) -> {
		log.info("save a couple of books");
		Category category1 = new Category("Scifi");
		Category category2 = new Category("Comic");
		Category category3 = new Category("Novel");
		Category category4 = new Category("Historical fiction");
		Category category5 = new Category("Fantasy");
		
		categoryRepository.save(category1);
		categoryRepository.save(category2);
		categoryRepository.save(category3);
		categoryRepository.save(category4);
		categoryRepository.save(category5);
		
		
		Book b1 = new Book("Sinuhe egyptiläinen", "Mika Waltari", 1945, 978, 29.90, category4);
		Book b2 = new Book("Mikael Karvajalka", "Mika Waltari", 1948, 951, 24.90, category4);
		Book b3 = new Book("Silmarillion", "J.R.R Tolkien", 1977, 9516, 39.90, category5);
		
		bookRepository.save(b1);
		bookRepository.save(b2);
		bookRepository.save(b3);
		
		log.info("fetch all books");
		for (Book book : bookRepository.findAll()) {
			log.info(book.toString());
	}
	};
}
}
