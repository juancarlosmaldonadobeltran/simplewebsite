package io.github.jhipster.simplewebsite.cucumber.stepdefs;

import io.github.jhipster.simplewebsite.SimplewebsiteApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = SimplewebsiteApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
