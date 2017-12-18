package org.streams.academy;

import java.util.*;
import java.util.stream.Collectors;

public class StreamMap {

	public static class Pokemon {
	
	  private String name;
	  private int damage;
	
	  Pokemon(String name, int damage) {
	    this.name = name;
	    this.damage = damage;
	  }
	
	  public String getName() {
	    return name;
	  }
	
	  public int getDamage() {
	    return damage;
	  }
	  
	}

  	public static List<String> execute() {
		List<Pokemon> allPokemon = Arrays.asList(
	      new Pokemon("Pikachu", 100),
	      new Pokemon("Charmander", 80),
	      new Pokemon("Snorlax", 75)
	    );
	    
		return allPokemon.stream()
	      .[PLACEHOLDER]
	      .collect(Collectors.toList());
	}
	
}

