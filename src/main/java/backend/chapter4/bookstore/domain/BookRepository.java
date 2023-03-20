package backend.chapter4.bookstore.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<Book, Long> {

	List<Book> findByTitle(String title);
	Optional<Book> findById(Long id);
	Book getBookById(Long bookId);
	

}
