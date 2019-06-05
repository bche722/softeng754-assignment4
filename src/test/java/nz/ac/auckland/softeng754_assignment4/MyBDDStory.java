package nz.ac.auckland.softeng754_assignment4;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.MarkUnmatchedStepsAsPending;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.StepCollector;
import org.jbehave.core.steps.StepFinder;
import org.junit.runner.RunWith;

import com.github.valfirst.jbehave.junit.monitoring.JUnitReportingRunner;

@RunWith(JUnitReportingRunner.class)
public class MyBDDStory extends JUnitStories {
	public MyBDDStory() {
//		super();
		configuredEmbedder()
			.embedderControls()
			.doGenerateViewAfterStories(true)
			.doIgnoreFailureInStories(true)
			.doIgnoreFailureInView(true);
}
	@Override
	public Configuration configuration() {
	Class<? extends Embeddable> embeddableClass = this.getClass();
	StepCollector stepCollector = new MarkUnmatchedStepsAsPending(new StepFinder(new StepFinder.ByLevenshteinDistance()));
	return new MostUsefulConfiguration()
	.useStepCollector(stepCollector)
	.useStoryLoader(new LoadFromClasspath(embeddableClass))
	.useParameterConverters(new ParameterConverters().addConverters(new ParameterConverters.DateConverter(new SimpleDateFormat("yyyy-MM-dd"))))
	.useStoryReporterBuilder(new StoryReporterBuilder()
	.withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
	.withFormats(org.jbehave.core.reporters.Format.STATS,
	org.jbehave.core.reporters.Format.CONSOLE,
	org.jbehave.core.reporters.Format.HTML,
	org.jbehave.core.reporters.Format.XML)
	.withDefaultFormats()
	.withFailureTrace(true));
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), new MyBDDSteps());
	}

	@Override
	protected List<String> storyPaths() {
		return Arrays.asList("nz/ac/auckland/softeng754_assignment4/MyBDD.story");

//		StoryFinder finder = new StoryFinder();
//      return finder.findPaths(CodeLocations.codeLocationFromClass(this.getClass()).getFile(), Arrays.asList("**/*.story"), Arrays.asList(""));
	}

}
