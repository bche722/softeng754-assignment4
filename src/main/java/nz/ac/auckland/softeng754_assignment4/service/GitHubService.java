package nz.ac.auckland.softeng754_assignment4.service;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.UserService;

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
	public boolean signIn(String username, String password) {
		_client.setCredentials(username, password);
		try {
			_userService.getUser();
		} catch (IOException e) {
			_client.setCredentials(null, null);
			return false;
		}
		return true;
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
	public PullRequest createPullRequest(String repository, PullRequest request) throws IOException {
		Repository repo = new Repository();
		for(Repository tempRepo : _repositoryService.getRepositories()) {
			if (tempRepo.getName().equals(repository)){
				repo = tempRepo;
				break;
			}
		}
		_pullRequestService.createPullRequest(repo, request);
		return null;
	}
	
	@Override
	public List<RepositoryContents> getContents(PullRequest pullRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
