package nz.ac.auckland.softeng754_assignment4.service;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.RepositoryId;

import javax.ws.rs.NotAuthorizedException;

public interface IGitHubService {

	public User signIn(String username, String password) throws NotAuthorizedException;

	public void signOut();

	public boolean isSignedIn();

	public PullRequest createPullRequest(RepositoryId repository, PullRequest request) throws IOException;

	public List<RepositoryContents> getContents(PullRequest pullRequest);
}
