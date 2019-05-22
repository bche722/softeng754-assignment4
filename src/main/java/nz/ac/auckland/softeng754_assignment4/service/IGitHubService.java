package nz.ac.auckland.softeng754_assignment4.service;

import java.io.IOException;
import java.util.List;

import nz.ac.auckland.softeng754_assignment4.exception.NotAuthorizedException;

import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.MergeStatus;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryContents;

public interface IGitHubService {

	public void signIn(String username, String password) throws NotAuthorizedException;

	public void signOut();

	public boolean isSignedIn();

	public PullRequest createPullRequest(String repository, String owner, PullRequest request) throws IOException;

	public List<RepositoryContents> getContents(PullRequest pullRequest) throws IOException;

	public MergeStatus merge(Repository repository, PullRequest request, String commitMessage)
			throws IOException;
	
	public CommitComment createComment(Repository repository,int id, CommitComment comment) throws IOException;
}
