package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.OurHashMap;
import model.OurMap;

class OurHashMapTest {

  @Test
  void testConstructorAndToString() {
     OurMap<String, ArrayList<Character>> map = new OurHashMap<>();
     ArrayList<Character> followers = new ArrayList<>();
     followers.add('U');
     followers.add('A');
     map.put("Alice", followers);
     System.out.println(map.get("Alice").toString());
  }
  @Test
  void testConstructorAndString() {
	     OurMap<String, ArrayList<Character>> map = new OurHashMap<>();
	     ArrayList<Character> followers = new ArrayList<>();
	     followers.add('U');
	     followers.add('A');
	     map.put("Alice", followers);
	     ArrayList<Character> prices = new ArrayList<>();
	     prices.add('1');
	     prices.add('3');
	     prices.add('9');
	     map.put("Menu",prices);
	     System.out.println(map.toString());
	     assertEquals(2,map.size());
	  }
}