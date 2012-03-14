package org.safaproject.safa.service.search;

import java.util.Map;

import javax.annotation.Resource;

public class SearchStrategyFactory {

	private static final String DEFAULT_STRATEGY = "default";
	private static final String SIMPLE_ORDER_STRATEGY = "simpleOrder";

	@Resource
	Map<String, SearchStrategy> searchStrategies;

	public SearchStrategy getStrategyFor(String strategyKey) {

		return (DEFAULT_STRATEGY.equals(strategyKey)) ? searchStrategies
				.get(DEFAULT_STRATEGY) : searchStrategies
				.get(SIMPLE_ORDER_STRATEGY);
	}
}
