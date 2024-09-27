package com.greedy.section01.fieldInjection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	/* 스프링 컨테이너가 관리중인 bean 중에 BookDAO 타입인 bean을 꺼내서 해당 필드에 주입(대입)해줌 */
	@Autowired
	private BookDAO bookDAO;		// new 연산자 X(IOC), 대입연산자 X(DI)
	
	public List<BookDTO> selectBookList() {
		return bookDAO.selectBookList();
	}

	public BookDTO selectOneBook(int sequence) {
		return bookDAO.selectOneBook(sequence);
	}
	
}







