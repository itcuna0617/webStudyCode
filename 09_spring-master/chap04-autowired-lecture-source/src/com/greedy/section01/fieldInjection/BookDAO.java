package com.greedy.section01.fieldInjection;

import java.util.List;

public interface BookDAO {

	List<BookDTO> selectBookList();

	BookDTO selectOneBook(int sequence);

}
