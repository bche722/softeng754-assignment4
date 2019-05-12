package nz.ac.auckland.softeng754_assignment4.service;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.RepositoryContents;

public interface GitHubService {
	
	public User signIn(String username, String password);
	
	public void signOut();
	
	public boolean isSignedIn();
	
	public PullRequest createPullRequest(String title, String head, String base, String body);
	
	public List<RepositoryContents> getContents(PullRequest pullRequest);
}
