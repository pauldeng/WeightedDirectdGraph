package de.dengpeng.assignements.tw.challenge.jbehave;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

@RunWith(JUnitReportingRunner.class)
public class TrainBehaveTest extends JUnitStories{
	public TrainBehaveTest() {
		super();
	}
	
	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(new MostUsefulConfiguration(), new TrainsSteps());
	}
 
	@Override
	protected List<String> storyPaths() {
		return Arrays.asList("Trains.story");
	}
}
