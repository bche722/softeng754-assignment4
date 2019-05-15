package nz.ac.auckland.softeng754_assignment4.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.MergeStatus;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.PullRequestMarker;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.SearchRepository;

import javax.ws.rs.NotAuthorizedException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GithubServiceTest {

	String username;
	String password;
	String repoOwner;
	String repoName;

	IGitHubService github;
	PullRequest pullRequest;

	@Before
	public void SetUp() {
		// Given
		username = "Saltedfish754";
		password = "cafnyP-rathaz-7razho";
		repoOwner = "bche722";
		repoName = "softeng754-assignment4";
		github = new GitHubService();
		pullRequest = Mockito.mock(PullRequest.class);
	}

	@Test
	public void shouldReturnAuthWithCorrectUsernameAndPassword() {
		// When
		User user = github.signIn(username, password);

		// Then
		assertEquals(user.getLogin(), username);
	}

	@Test(expected = NotAuthorizedException.class)
	public void shouldThrowExceptionWithIncorrectyUsernameAndPassword() {

		// Given
		String wrong_password = "Wrong Password";

		// When
		github.signIn(username, wrong_password);

	}

	@Test
	public void shouldReturnPullRequestWhenCreatePullRequest() throws IOException {
		
		// Given
		String title = "Amazing new feature";
		String head = "softeng754-assignment4:new-feature";
		String base = "milestone-2";
		String body = "Please pull this in!";
		PullRequest request = new PullRequest();
		request.setTitle(title);
		request.setBody(body);
		request.setHead(new PullRequestMarker().setRef(head));
		request.setBase(new PullRequestMarker().setRef(base));
		RepositoryId repositoryId = new RepositoryId(repoOwner, repoName);
		
		// When
		PullRequest result = github.createPullRequest(repositoryId, request);
		
		// Then
		assertEquals(result, pullRequest);
	}
	
	@Test
	public void shouldFetchSourceCodeWithValidPullReuest() {
		// Given
		List<RepositoryContents> contents = new ArrayList<RepositoryContents>();
		
	  Mockito.doReturn(contents).when(github).getContents(pullRequest);
		
		// When
		List<RepositoryContents> result = github.getContents(pullRequest);
		
		// Then
		// assertEquals(result, source);
	}

	@Test
	public void shouldMergePullRequestSuccessful() throws IOException {
		// Given
		MergeStatus status = Mockito.mock(MergeStatus.class);
		RepositoryId repositoryId = new RepositoryId(repoOwner, repoName);
		String commitMessage = "pull request commit message here";
		Mockito.doReturn(status).when(github).merge(repositoryId, pullRequest, commitMessage);

		// When
		MergeStatus result = github.merge(repositoryId, pullRequest, commitMessage);

		// Then
		assertTrue(result.isMerged());
	}
}
