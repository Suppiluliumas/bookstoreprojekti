package backend.chapter4.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import backend.chapter4.bookstore.domain.Book;
import backend.chapter4.bookstore.domain.BookRepository;
import backend.chapter4.bookstore.domain.Category;
import backend.chapter4.bookstore.domain.CategoryRepository;
import backend.chapter4.bookstore.domain.User;
import backend.chapter4.bookstore.domain.UserRepository;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookstoreRepositoryTests {
	
	@Autowired
	private BookRepository bookrepository;
	@Autowired
	private CategoryRepository categoryrepository;
	@Autowired
	private UserRepository userrepository;
	
	//BookRepository testit alkavat
	@Test // Testi kirjan hakua varten
	public void findByTitleShouldReturnBook() {
		List<Book> books = bookrepository.findByTitle("Silmarillion");
		
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getIsbn()).isEqualTo(9516);
	}
	@Test // Testi uuden kirjan lisäämiseksi
	public void createNewBook() {
		Book book = new Book("Aapinen","Koulu",2023,12345,19.90,null);
		bookrepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	@Test //Testi kirjan poistamista varten
	public void deleteBookTest() {
		Book book = new Book("Aapinen","Koulu",2023,12345,19.90,null);
		bookrepository.save(book);
		bookrepository.delete(book);
		assertThat(bookrepository.findByTitle("Aapinen")).hasSize(0);
	}
	//BookRepository testit päättyvät
	
	//CategoryRepository testit alkavat
	
	@Autowired // Testi kategorian hakua varten
	public void findCategoryByName() {
		List<Category> categories = categoryrepository.findByName("Comic");
		
		assertThat(categories).hasSize(1);
	}
	@Autowired // Testi kategorian hakua varten jos kategoriaa ei löydy
	public void findCategoryByNameIfNotFound() {
		List<Category> categories = categoryrepository.findByName("Roskalehti");
		
		assertThat(categories).hasSize(0);
	}
	
	@Test // Testi onko kategorioissa duplikaatteja
	public void findByNameIsThereDuplicates() {
		List<Category> categories = categoryrepository.findByName("Science");
		
		assertThat(categories).hasSize(0);
		assertThat(categories).doesNotHaveDuplicates().isEqualTo(categories);
	}
	//CategoryRepository testit päättyvät
	//UserRepository testit alkavat
	
	@Test // Testi löytyykö käyttäjää
	public void findByUsername() {
		User user = userrepository.findByUsername("vieras");
		
		assertThat(user).hasNoNullFieldsOrProperties();
	}
	@Test // Testi jos ei löydy käyttäjänimellä käyttäjää
	public void findByUsernameNot() {
		User user = userrepository.findByUsername("Martti");
		
		assertNull(user);
}
	//UserRepository testit päättyvät
}