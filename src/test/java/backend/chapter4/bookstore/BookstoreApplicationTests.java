package backend.chapter4.bookstore;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import backend.chapter4.bookstore.web.BookController;
import backend.chapter4.bookstore.web.CategoryController;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookstoreApplicationTests {

	@Autowired
	private BookController bookController;
	
	@Test
	public void contextLoads1() {
		assertThat(bookController).isNotNull();
		
	}
	
	@Autowired
	private CategoryController categoryController;
	
	@Test
	public void contextLoads2() {
		assertThat(categoryController).isNotNull();
	
	
	}
	
	@Autowired
	private UserDetailsService userDetailServiceImpl;
	
	@Test
	public void contextLoads3() {
		assertThat(userDetailServiceImpl).isNotNull();
	
	
	}
}
