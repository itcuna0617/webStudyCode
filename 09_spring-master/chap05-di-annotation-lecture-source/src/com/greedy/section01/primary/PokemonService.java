package com.greedy.section01.primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PokemonService {
	
	private Pokemon pokemon;
	
	@Autowired
	public PokemonService(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
	
	public void attack() {
		pokemon.attack();
	}
}
