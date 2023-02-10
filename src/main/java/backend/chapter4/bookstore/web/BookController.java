package backend.chapter4.bookstore.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import backend.chapter4.bookstore.domain.Book;
import backend.chapter4.bookstore.domain.BookRepository;
@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	
	@RequestMapping(value="/booklist", method= RequestMethod.GET)
	public String bookList(Model model) {
		model.addAttribute("books",repository.findAll());
		return "booklist";
	}
	@RequestMapping(value="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
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
