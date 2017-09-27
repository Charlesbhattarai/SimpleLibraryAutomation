package com.oktay.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oktay.models.Book;
import com.oktay.service.BookService;
/**
 * @author oktay
 *
 */
@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/list_books" , method = RequestMethod.GET)
	public ModelAndView ListBooks(ModelAndView model) throws IOException {
		List<Book> listBooks = bookService.getAllBooks();
		model.addObject("listBooks", listBooks);
		model.setViewName("books");
		
		return model;
	}
	
	@RequestMapping(value = "/add_book", method = RequestMethod.GET)
	public ModelAndView newBook(ModelAndView model) {
		Book book = new Book();
		model.addObject("book", book);
		model.setViewName("addBook");
		
		return model;
	}
	
	@RequestMapping(value = "/save_book", method = RequestMethod.POST)
	public ModelAndView saveBook(@ModelAttribute Book book) {
		bookService.addBook(book);
		return new ModelAndView("redirect:/list_books");
	}
	
}