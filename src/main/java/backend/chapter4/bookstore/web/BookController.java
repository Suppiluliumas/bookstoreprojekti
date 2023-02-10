package backend.chapter4.bookstore.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import backend.chapter4.bookstore.domain.Book;
@Controller
public class BookController {
	@RequestMapping(value="/index", method= RequestMethod.GET)
	public String getBooks(Model model) {
		List<Book> books = new ArrayList<Book>();
		model.addAttribute("index", books);
		return "booklist";
	}
}
