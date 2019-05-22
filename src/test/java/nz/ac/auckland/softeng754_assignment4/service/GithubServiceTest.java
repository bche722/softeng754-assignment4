package nz.ac.auckland.softeng754_assignment4.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.UserService;
import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.MergeStatus;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.PullRequestMarker;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryContents;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import nz.ac.auckland.softeng754_assignment4.exception.NotAuthorizedException;

public class GithubServiceTest {

	String username;
	String password;
	String wrongPassword;
	String repoOwner;
	String repoName;

	IGitHubService github;

	GitHubClient _client;
	UserService _userService;
	RepositoryService _repositoryService;
	PullRequestService _pullRequestService;
	ContentsService _contentsService;

	@Before
	public void SetUp() {
		// Given
		username = "Saltedfish754";
		password = "cafnyP-rathaz-7razho";
		wrongPassword = "aaaa";
		repoOwner = "bche722";
		repoName = "softeng754-assignment4";

		_client = Mockito.mock(GitHubClient.class);
		_userService = Mockito.mock(UserService.class);
		_repositoryService = Mockito.mock(RepositoryService.class);
		_pullRequestService = Mockito.mock(PullRequestService.class);
		_contentsService = Mockito.mock(ContentsService.class);
		github = new GitHubService(_client, _userService, _repositoryService, _pullRequestService, _contentsService);

	}

	@Test
	public void shouldLoginSuccessfullyWithCorrectUsernameAndPassword() throws NotAuthorizedException, IOException {

		// Given
		User user = new User();
		user.setName(username);
		Mockito.doReturn(_client).when(_client).setCredentials(username, password);
		Mockito.doReturn(user).when(_userService).getUser();

		// When
		github.signIn(username, password);

	}

	@Test(expected = NotAuthorizedException.class)
	public void shouldThrowExceptionWithIncorrectyUsernameAndPassword() throws NotAuthorizedException, IOException {

		// Given
		User user = new User();
		user.setName(username);
		Mockito.doReturn(_client).when(_client).setCredentials(username, wrongPassword);
		Mockito.doThrow(NotAuthorizedException.class).when(_userService).getUser();

		// When
		github.signIn(username, password);

	}

	@Test
	public void shouldReturnPullRequestWhenCreatePullRequest() throws IOException {

		// Given
		List<Repository> list = new ArrayList<Repository>();
		Repository repository = new Repository().setName(repoName).setOwner(new User().setLogin(repoOwner));
		PullRequest request = new PullRequest();
		list.add(repository);
		Mockito.doReturn(list).when(_repositoryService).getRepositories();
		Mockito.doReturn(request).when(_pullRequestService).createPullRequest(repository, request);

		// When

		PullRequest result = github.createPullRequest(repoName, repoOwner, request);

		assertEquals(result, request);

	}

	@Test
	public void shouldFetchSourceCodeWithValidPullReuest() throws IOException {
		// Given
		PullRequest pullRequest = new PullRequest().setBase(new PullRequestMarker());
		List<RepositoryContents> contents = new ArrayList<RepositoryContents>();
		Mockito.doReturn(contents).when(_contentsService).getContents(pullRequest.getBase().getRepo());

		// When
		List<RepositoryContents> result = github.getContents(pullRequest);

		// Then
		assertEquals(result, contents);
	}

	@Test
	public void shouldMergePullRequestSuccessful() throws IOException {
		// Given
		MergeStatus status = new MergeStatus().setMerged(true);
		Repository repository = new Repository();
		PullRequest pullRequest = new PullRequest().setBase(new PullRequestMarker());
		String commitMessage = "pull request commit message here";
		Mockito.doReturn(status).when(_pullRequestService).merge(repository, 0 , commitMessage);

		// When
		MergeStatus result = github.merge(repository, pullRequest, commitMessage);

		// Then
		assertTrue(result.isMerged());
	}
	
	@Test
	public void shouldAddComments() throws IOException {
		// Given
		Repository repository = new Repository();
		CommitComment comment = new CommitComment();
		Mockito.doReturn(comment).when(_pullRequestService).createComment(repository, 0, comment);

		// When
		CommitComment result = github.createComment(repository, 0, comment);

		// Then
		assertEquals(result,comment);
	}
}
