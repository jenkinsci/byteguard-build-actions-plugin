package io.jenkins.plugins;

import hudson.model.FreeStyleBuild;
import hudson.model.FreeStyleProject;
import hudson.model.Label;
import org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.jenkinsci.plugins.workflow.job.WorkflowRun;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

public class BytegaurdActionBuilderTest {

    @Rule
    public JenkinsRule jenkins = new JenkinsRule();

    final String token =(System.getenv("TOKEN"));
    final String task_id =(System.getenv("TASK"));

    @Test
    public void testConfigRoundtrip() throws Exception {
        FreeStyleProject project = jenkins.createFreeStyleProject();
        project.getBuildersList().add(new BytegaurdActionBuilder(token,task_id));
        project = jenkins.configRoundtrip(project);
        jenkins.assertEqualDataBoundBeans(new BytegaurdActionBuilder(token,task_id), project.getBuildersList().get(0));
    }
}
