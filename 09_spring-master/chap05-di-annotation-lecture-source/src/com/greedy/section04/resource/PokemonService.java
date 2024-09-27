package com.greedy.section04.resource;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class PokemonService {
	
	/* @Resource는 @Autowired 처럼 @Primary를 통해 하나의 bean을 선택할 수 있다. 필드 주입 가능 */
	/* 1. 필드 주입 O */
	@Resource
	private Pokemon pokemon;
	
	/* 2. 생성자 주입 X */
//	@Resource									// 생성자 주입은 지원하지 않음
	public PokemonService(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
	
	/* 3. 세터 주입 O */
//	@Resource
//	public void setPokemon(Pokemon pokemon) {
//		this.pokemon = pokemon;
//	}
	
	public void attack() {
		pokemon.attack();
	}
	
	/* collection 방식은 잘 구동한다. */
//	@Resource
//	private List<Pokemon> pokemonList;
//	
//	public void attack() {
//		for(Pokemon pokemon : pokemonList) {
//			pokemon.attack();
//		}
//	}
}



