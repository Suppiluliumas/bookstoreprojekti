package backend.chapter4.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.chapter4.bookstore.domain.Book;
import backend.chapter4.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
@Bean
public CommandLineRunner demo(BookRepository bookRepository) {
	return (args) -> {
		Book b1 = new Book("Sinuhe egyptiläinen", "Mika Waltari", 1945, 978, 29.90);
		Book b2 = new Book("Mikael Karvajalka", "Mika Waltari", 1948, 951, 24.90);
		Book b3 = new Book("Silmarillion", "J.R.R Tolkien", 1977, 9516, 39.90);
		
		bookRepository.save(b1);
		bookRepository.save(b2);
		bookRepository.save(b3);
	};
}
}
