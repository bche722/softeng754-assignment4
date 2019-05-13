package nz.ac.auckland.softeng754_assignment4.service;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.RepositoryContents;

public interface IGitHubService {

	public boolean signIn(String username, String password);

	public void signOut();

	public boolean isSignedIn();

	public PullRequest createPullRequest(String repository, PullRequest request) throws IOException;

	public List<RepositoryContents> getContents(PullRequest pullRequest);
}
