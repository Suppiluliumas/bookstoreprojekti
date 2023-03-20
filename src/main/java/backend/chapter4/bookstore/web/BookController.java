package backend.chapter4.bookstore.web;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping(value="/books", method= RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) repository.findAll();
	}
	
	@RequestMapping(value="/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest (@PathVariable("id") long bookId) {
		return repository.findById(bookId);
    }      
	
	@RequestMapping(value="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryrepository.findAll());
		return "addbook";
	}
	@RequestMapping(value = "/editbook/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookId, Model model){
        // get book from repo
		Book book = repository.getBookById(bookId);
		model.addAttribute("categories", categoryrepository.findAll());
        model.addAttribute("book", book);
		return "editbook";
    }
	
	
	@RequestMapping(value= "/save", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	
	@RequestMapping(value= "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteBook(@PathVariable("id") long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
    @RequestMapping(value="/login")
	public String login() {
		return "login";
	}   
}
