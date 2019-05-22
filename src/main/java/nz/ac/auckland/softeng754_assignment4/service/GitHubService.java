package nz.ac.auckland.softeng754_assignment4.service;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.MergeStatus;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.UserService;

import nz.ac.auckland.softeng754_assignment4.exception.NotAuthorizedException;

public class GitHubService implements IGitHubService {

	private GitHubClient _client;

	private UserService _userService;

	private RepositoryService _repositoryService;

	private PullRequestService _pullRequestService;

	private ContentsService _contentsService;

	public GitHubService() {
		_client = new GitHubClient();
		_userService = new UserService(_client);
		_repositoryService = new RepositoryService(_client);
		_pullRequestService = new PullRequestService(_client);
		_contentsService = new ContentsService(_client);
	}

	public GitHubService(GitHubClient client, UserService userService, RepositoryService repositoryService,
			PullRequestService pullRequestService, ContentsService contentsService) {
		this._client = client;
		this._userService = userService;
		this._repositoryService = repositoryService;
		this._pullRequestService = pullRequestService;
		this._contentsService = contentsService;
	}

	@Override
	public void signIn(String username, String password) throws NotAuthorizedException {
		_client.setCredentials(username, password);
		try {
			_userService.getUser();
		} catch (IOException e) {
			_client.setCredentials(null, null);
			throw new NotAuthorizedException(e.getMessage());
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
	public PullRequest createPullRequest(String repository, String owner, PullRequest request) throws IOException {
		Repository repo = new Repository();
		for (Repository tempRepo : _repositoryService.getRepositories()) {
			if (owner.equals(tempRepo.getOwner().getLogin()) && repository.equals(tempRepo.getName())) {
				repo = tempRepo;
				break;
			}
		}
		return _pullRequestService.createPullRequest(repo, request);
	}

	@Override
	public List<RepositoryContents> getContents(PullRequest pullRequest) throws IOException {
		return _contentsService.getContents(pullRequest.getBase().getRepo());
	}

	@Override
	public MergeStatus merge(Repository repository, PullRequest request, String commitMessage)
			throws IOException {
		return _pullRequestService.merge(repository, (int) request.getId(), commitMessage);
	}
	
	@Override
	public CommitComment createComment(Repository repository,int id, CommitComment comment) throws IOException {
		return _pullRequestService.createComment(repository, id, comment);
	}

}
