package com.greedy.section01.fieldInjection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class BookDAOImpl implements BookDAO{
	
	private Map<Integer, BookDTO> bookList;
	
	public BookDAOImpl() {
		bookList = new HashMap<>();
		bookList.put(1,  new BookDTO(1, 1234, "자바의 정석", "남궁성", "도우출판", new java.util.Date()));
		bookList.put(2,  new BookDTO(2, 2222, "스프링은 쉬운가", "용승김", "승소프트", new java.util.Date()));
		bookList.put(3,  new BookDTO(3, 3333, "칭찬은 고래도 춤추게 한다", "고래", "종로출판", new java.util.Date()));
	}

	/* 도서 목록 전체 조회 */
	@Override
	public List<BookDTO> selectBookList() {
		return new ArrayList<BookDTO>(bookList.values());	// map의 값을 list로 간단히 변환
	}

	/* 하나의 도서 조회 */
	@Override
	public BookDTO selectOneBook(int sequence) {
		return bookList.get(sequence);
	}

}



