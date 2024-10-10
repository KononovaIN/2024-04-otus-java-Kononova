package main.service;

import java.util.List;
import main.entity.Books;

public interface BooksService {

  List<Books> listBooks();

  Books findBook(Long id);

  Books addBook(Books book);

  void deleteBook(Long id);
}
