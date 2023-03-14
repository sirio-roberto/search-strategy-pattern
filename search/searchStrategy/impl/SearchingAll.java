package search.searchStrategy.impl;

import search.Person;
import search.searchStrategy.SearchingBase;

import java.util.*;

public class SearchingAll extends SearchingBase {
    @Override
    protected List<Person> searchPeople(String userTerm, Map<String, Set<Integer>> keywordMaps, List<Person> people) {
        if (userTerm == null || userTerm.isBlank()) {
            return new ArrayList<>();
        }
        String[] userFieldsLowercase = userTerm.toLowerCase().split(" ");
        var resultSet = keywordMaps.get(userFieldsLowercase[0]);
        for (int i = 1; i < userFieldsLowercase.length; i++) {
            var indexSetAux = keywordMaps.get(userFieldsLowercase[i]);
            if (indexSetAux == null) {
                return new ArrayList<>();
            }
            resultSet.retainAll(indexSetAux);
        }
        if (resultSet.size() == 0) {
            return new ArrayList<>();
        }
        return resultSet.stream().map(people::get).toList();
    }
}
