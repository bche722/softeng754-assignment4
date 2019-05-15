package nz.ac.auckland.softeng754_assignment4.service;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.MergeStatus;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.UserService;

import javax.ws.rs.NotAuthorizedException;

public class GitHubService implements IGitHubService {

	private GitHubClient _client;
	
	private UserService _userService;
	
	private RepositoryService _repositoryService;
	
	private PullRequestService _pullRequestService;

	public GitHubService() {
		_client = new GitHubClient();
		_userService = new UserService(_client);
		_repositoryService = new RepositoryService(_client);
		_pullRequestService = new PullRequestService(_client);
	}

	@Override
	public User signIn(String username, String password) throws NotAuthorizedException {
		_client.setCredentials(username, password);
		try {
			return _userService.getUser();
		} catch (IOException e) {
			_client.setCredentials(null, null);
			throw new NotAuthorizedException(e);
		}
	}

	@Override
	public void signOut() {
		_client.setCredentials(null, null);
	}

	@Override
	public boolean isSignedIn() {
		return _client.getUser() == null ? false : true;
	}

	@Override
	public PullRequest createPullRequest(RepositoryId repository, PullRequest request) throws IOException {
		return _pullRequestService.createPullRequest(repository, request);
	}
	
	@Override
	public List<RepositoryContents> getContents(PullRequest pullRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MergeStatus merge(RepositoryId repository, PullRequest request, String commitMessage) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
