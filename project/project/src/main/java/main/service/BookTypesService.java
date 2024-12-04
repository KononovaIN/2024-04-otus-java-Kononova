package main.service;

import java.util.List;
import main.entity.BookTypes;

public interface BookTypesService {

  List<BookTypes> listBookTypes();

  BookTypes findBookType(Long id);

  BookTypes addBookType(BookTypes bookType);

  void deleteBookType(Long id);
}
