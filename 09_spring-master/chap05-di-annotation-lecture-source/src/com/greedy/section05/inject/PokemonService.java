package com.greedy.section05.inject;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component
public class PokemonService {
	
	/* @Inject는 빈의 id로 하나의 빈만 선택 시 @Named와 @Qualifier 둘 다 사용이 가능하다. */
	
	/* @Inject도 @Autowired 처럼 @Primary를 통해 하나의 bean을 선택할 수 있다. 필드 주입 가능 */
	/* 1. 필드 주입 O */
//	@Inject
////	@Qualifier("charmander")
//	@Named("charmander")
//	private Pokemon pokemon;
	
	/* 2. 생성자 주입 O */
//	@Inject					
//	@Named("charmander")
//	public PokemonService(@Qualifier("charmander") Pokemon pokemon) {
//	public PokemonService(Pokemon pokemon) {
//		this.pokemon = pokemon;
//	}
	
	/* 3. 세터 주입 O */
//	@Inject					
//	@Named("charmander")
//	public void setPokemon(@Qualifier("squirtle") Pokemon pokemon) {
//	public void setPokemon(Pokemon pokemon) {
//		this.pokemon = pokemon;
//	}
	
//	public void attack() {
//		pokemon.attack();
//	}
	
	/* collection 방식은 잘 구동한다. */
	@Inject
	private List<Pokemon> pokemonList;
	
	public void attack() {
		for(Pokemon pokemon : pokemonList) {
			pokemon.attack();
		}
	}
	
	/*
	 * 정리
	 * 1. @Autowired, @Resource, @Inject는 모두 DI와 관련이 있지만 작동 방식 및 문법에서 약간씩 차이가 있다.
	 * 2. 주입 받는 변수의 변수명도 유의미하다.(해당 변수명의 bean이 있는지 검색하게 된다.)
	 * 3. bean을 인식하는 과정
	 * 	  @Resource
	 * 	  이름 -> 타입 -> @Qualifier 유무
	 *    @Autowired
	 *    타입 -> 이름 -> @Qulifier 유무
	 *    @Inject
	 *    타입 -> @Qualifier -> 이름
	 */
	
}



