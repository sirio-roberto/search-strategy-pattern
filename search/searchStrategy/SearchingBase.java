package search.searchStrategy;

import search.Person;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class SearchingBase {
    protected abstract List<Person> searchPeople(String userTerm, Map<String, Set<Integer>> keywordMaps, List<Person> people);
}
