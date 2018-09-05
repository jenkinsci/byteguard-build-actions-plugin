package io.jenkins.plugins.sample;

import hudson.Launcher;
import hudson.Extension;
import hudson.FilePath;
import hudson.util.FormValidation;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;
import java.io.IOException;
import jenkins.tasks.SimpleBuildStep;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundSetter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.Proxy;
import java.net.InetSocketAddress;
import java.io.OutputStreamWriter;

public class BytegaurdActionBuilder extends Builder implements SimpleBuildStep {

    private final String token;
    private final String product;

    @DataBoundConstructor
    public BytegaurdActionBuilder(String token,String product) {
        this.token = token;
        this.product = product;
    }

    public String getToken() {
        return token;
    }

     public String getProduct() {
        return product;
    }

    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
         try{
             	String url = "https://byteguard.io/api/tasks/"+product+"/trigger/";
             	URL obj = new URL(url);
             	HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

             	conn.setRequestProperty("Content-Type", "application/json");
             	conn.setRequestProperty("Accept", "application/json");
             	conn.setRequestProperty("Authorization", "Token " + token);
             	conn.setDoOutput(true);

             	conn.setRequestMethod("POST");
             	conn.connect();
             	InputStreamReader in_strm = new InputStreamReader(conn.getInputStream());

             	int t;
             	String read_reslt="";
            	 while((t = in_strm.read()) != -1)
             	{
                    read_reslt = read_reslt+(char)t;
             	}
             	listener.getLogger().println(read_reslt);
             }catch(Exception e) {
		listener.getLogger().println(e);
	}
     }

    @Symbol("greet")
    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        public FormValidation doCheckName(@QueryParameter String value1, @QueryParameter String value2)
                throws IOException, ServletException {
            if (value1.length() == 0)
                return FormValidation.error(Messages.BytegaurdActionBuilder_DescriptorImpl_errors_missingName());
            if (value2.length() == 0)
                return FormValidation.error(Messages.BytegaurdActionBuilder_DescriptorImpl_errors_missingName());
            return FormValidation.ok();
        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return "ByteGuard Build Actions";
        }

    }

}
