package search.searchStrategy.impl;

import search.Person;
import search.searchStrategy.SearchingBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchingNone extends SearchingBase {
    @Override
    protected List<Person> searchPeople(String userTerm, Map<String, Set<Integer>> keywordMaps, List<Person> people) {
        if (userTerm == null || userTerm.isBlank()) {
            return new ArrayList<>();
        }
        var allIndexesSet = Stream.iterate(0, n -> n + 1).limit(people.size()).collect(Collectors.toSet());

        for (String word: userTerm.toLowerCase().split(" ")) {
            var indexSet = keywordMaps.get(word);
            allIndexesSet.removeAll(indexSet);
        }
        if (allIndexesSet.isEmpty()) {
            return new ArrayList<>();
        }
        return allIndexesSet.stream().map(people::get).toList();
    }
}
