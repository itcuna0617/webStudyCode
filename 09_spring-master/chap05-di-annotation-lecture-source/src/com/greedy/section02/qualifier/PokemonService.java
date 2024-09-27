package com.greedy.section02.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PokemonService {
	
//	@Autowired
//	@Qualifier("charmander")
	private Pokemon pokemon;
	
	/*
	 * @Qualifier 어노테이션으로 의존성 주입을 하는 경우 타입뿐 아니라 이름(id)으로 주입을 한다.
	 * @Primary보다 더 우선권을 가진다.
	 */
	
	/* 생성자 주입을 사용하는 경우(파라미터에 어노테이션 적용할 것) */
	@Autowired
	public PokemonService(@Qualifier("squirtle") Pokemon pokemon) {
		this.pokemon = pokemon;
	}
	
	public void attack() {
		pokemon.attack();
	}
}
