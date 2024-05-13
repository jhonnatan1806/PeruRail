package com.tsoft.bot.frontend.runner;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

@CucumberOptions(
		features={"src//main//resources//features"},
		glue={
				"com.tsoft.bot.frontend.steps",
				"com.tsoft.bot.frontend.helpers"
		},
		plugin = {"pretty", "html:target/cucumber"},
		tags = {"@Login"},
		strict =  false,
		monochrome = true
	)

@Test
public class RunTest extends AbstractTestNGCucumberTests{ }

