package search.searchStrategy.impl;

import search.Person;
import search.searchStrategy.SearchingBase;

import java.util.*;

public class SearchingAny extends SearchingBase {
    @Override
    protected List<Person> searchPeople(String userTerm, Map<String, Set<Integer>> keywordMaps, List<Person> people) {
        if (userTerm == null || userTerm.isBlank()) {
            return new ArrayList<>();
        }
        HashSet<Integer> resultSet = new HashSet<>();

        for (String word: userTerm.toLowerCase().split(" ")) {
            var indexSet = keywordMaps.get(word);
            if (indexSet != null) {
                resultSet.addAll(indexSet);
            }
        }
        if (resultSet.isEmpty()) {
            return new ArrayList<>();
        }
        return resultSet.stream().map(people::get).toList();
    }
}
