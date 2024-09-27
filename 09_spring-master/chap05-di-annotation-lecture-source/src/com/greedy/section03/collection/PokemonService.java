package com.greedy.section03.collection;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PokemonService {
	
	/* Collection을 활용해서 의존성 주입을 받을 해당 타입의 모든 빈을 주입 받아보자. */
	
	/* 1. List 타입으로 주입 받기 */
//	private List<Pokemon> pokemonList;
//	
//	@Autowired
//	public PokemonService(List<Pokemon> pokemonList) {
//		this.pokemonList = pokemonList;
//	}
//	
//	public void attack() {
//		for(Pokemon pokemon : pokemonList) {
//			pokemon.attack();
//		}
//	}
	
	/* 2. Map 타입으로 주입 받기 */
	private Map<String, Pokemon> pokemonMap;
	
	@Autowired
	public PokemonService(Map<String, Pokemon> pokemonMap) {
		this.pokemonMap = pokemonMap;
	}
	
	public void attack() {
		Iterator<String> iter = pokemonMap.keySet().iterator();
		while(iter.hasNext()) {
//			System.out.println(iter.next());
			pokemonMap.get(iter.next()).attack();
		}
	}
	
}



