package org.safaproject.safa.model.indicator.builder;

import org.junit.Assert;
import org.junit.Test;
import org.safaproject.safa.model.content.Content;
import org.safaproject.safa.model.content.builder.ContentBuilder;
import org.safaproject.safa.model.indicator.Indicator;
import org.safaproject.safa.model.indicator.IndicatorType;
import org.safaproject.safa.model.indicator.Vote;
import org.safaproject.safa.model.user.User;
import org.safaproject.safa.model.user.builder.UserBuilder;

public class VoteBuilderTest {

	@Test
	public void shallBuild() {
		Content content = new Content();
		Indicator indicator = new Indicator();

		User user = new UserBuilder().withUsername("Test")
				.withEmail("test@test.com")
				.withOpenIDurlToken("http://laputamadre.com").build();

		Vote vote = new VoteBuilder().withContent(content)
				.withIndicator(indicator).withUser(user).withValue(50).build();

		Assert.assertEquals(content, vote.getContent());
		Assert.assertEquals(indicator, vote.getIndicator());
		Assert.assertEquals(user, vote.getUser());
		Assert.assertEquals(Integer.valueOf(50), vote.getValue());
	}

	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullContent() {
		IndicatorType indicatorType = new IndicatorTypeBuilder()
				.withIndicatorName("Indicator").withMaxValue(500)
				.withMinValue(200).build();
		Indicator indicator = new IndicatorBuilder().withValue(250)
				.withIndicatorType(indicatorType).build();

		User user = new UserBuilder().withUsername("Test")
				.withEmail("test@test.com")
				.withOpenIDurlToken("http://laputamadre.com").build();

		new VoteBuilder().withIndicator(indicator).withUser(user).withValue(50).build();
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullIndicator() {
		Content content = new ContentBuilder().withAvailable(true)
				.withDescription("Desc").withReviewed(true)
				.withTitle("Design Patterns").build();

		User user = new UserBuilder().withUsername("Test")
				.withEmail("test@test.com")
				.withOpenIDurlToken("http://laputamadre.com").build();

		new VoteBuilder().withContent(content).withUser(user).withValue(50).build();
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullUser() {
		Content content = new ContentBuilder().withAvailable(true)
				.withDescription("Desc").withReviewed(true)
				.withTitle("Design Patterns").build();

		IndicatorType indicatorType = new IndicatorTypeBuilder()
				.withIndicatorName("Indicator").withMaxValue(500)
				.withMinValue(200).build();
		Indicator indicator = new IndicatorBuilder().withValue(250)
				.withIndicatorType(indicatorType).build();

		new VoteBuilder().withContent(content).withIndicator(indicator).withValue(50).build();
	}
	
	@Test(expected = NullPointerException.class)
	public void shallFailBecauseOfNullValue() {
		Content content = new ContentBuilder().withAvailable(true)
				.withDescription("Desc").withReviewed(true)
				.withTitle("Design Patterns").build();

		IndicatorType indicatorType = new IndicatorTypeBuilder()
				.withIndicatorName("Indicator").withMaxValue(500)
				.withMinValue(200).build();
		Indicator indicator = new IndicatorBuilder().withValue(250)
				.withIndicatorType(indicatorType).build();

		User user = new UserBuilder().withUsername("Test")
				.withEmail("test@test.com")
				.withOpenIDurlToken("http://laputamadre.com").build();

		new VoteBuilder().withContent(content).withIndicator(indicator).withUser(user).build();
	}
}
