package search.searchStrategy;

import search.Person;
import search.searchStrategy.impl.SearchingAll;
import search.searchStrategy.impl.SearchingAny;
import search.searchStrategy.impl.SearchingNone;

import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Searcher {
    private SearchingBase searchingStrategy;

    public Searcher(SearchingBase searchingStrategy) {
        this.searchingStrategy = searchingStrategy;
    }

    public Searcher(String searchingStrategyString) {
        this.searchingStrategy = getStrategyFromString(searchingStrategyString);
    }

    private SearchingBase getStrategyFromString(String searchingStrategyString) {
        SearchingBase searchingStrategy;
        switch (searchingStrategyString.toUpperCase()) {
            case "ALL" -> searchingStrategy = new SearchingAll();
            case "ANY" -> searchingStrategy = new SearchingAny();
            default -> searchingStrategy = new SearchingNone();
        }
        return searchingStrategy;
    }

    public void setSearchingAlgorithm(SearchingBase searchingStrategy) {
        this.searchingStrategy = searchingStrategy;
    }

    public List<Person> executeSearch(String userTerm, Map<String, Set<Integer>> keywordMaps, List<Person> people) {
        return searchingStrategy.searchPeople(userTerm, keywordMaps, people);
    }
}
