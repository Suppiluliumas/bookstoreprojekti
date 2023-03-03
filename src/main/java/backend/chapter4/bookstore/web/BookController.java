package backend.chapter4.bookstore.web;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import backend.chapter4.bookstore.domain.Book;
import backend.chapter4.bookstore.domain.BookRepository;
import backend.chapter4.bookstore.domain.CategoryRepository;
@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository categoryrepository;
	
	@RequestMapping(value="/booklist", method= RequestMethod.GET)
	public String bookList(Model model) {
		model.addAttribute("books",repository.findAll());
		return "booklist";
	}
	// RESTful service to get all students
	@RequestMapping(value="books", method= RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) repository.findAll();
	}
	// RESTful service to get student by id
	@RequestMapping(value="/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest (@PathVariable("id") Long bookId) {
		return repository.findById(bookId);
    }      
	
	@RequestMapping(value="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryrepository.findAll());
		return "addbook";
	}
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable Long id, Model model) {
		model.addAttribute("book", repository.findById(id));
		return "editbook";
	}
	
	@RequestMapping(value= "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	
	@RequestMapping(value= "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
}
