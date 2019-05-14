package nz.ac.auckland.softeng754_assignment4.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.egit.github.core.User;
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
	IGitHubService github;
	PullRequest pullRequest;

	@Before
	public void SetUp() {
		// Given
		username = "Saltedfish754@gmail.com";
		password = "S@lted_754";
		github = Mockito.mock(IGitHubService.class);
		pullRequest = Mockito.mock(PullRequest.class);
	}

	@Test
	public void shouldReturnAuthWithCorrectUsernameAndPassword() {

		// Given
		User user = new User();
		user.setLogin(username);
		Mockito.doReturn(user).when(github).signIn(username, password);

		// When
		github.signIn(username, password);

		String login = user.getLogin();

		// Then
		assertEquals(login, username);
	}

	@Test(expected = NotAuthorizedException.class)
	public void shouldThrowExceptionWithIncorrectyUsernameAndPassword() {

		// Given
		String wrong_password = "Wrong Password";
		Mockito.doThrow(NotAuthorizedException.class).when(github).signIn(username, wrong_password);

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
		String repoOwner = "bche722";
		String repoName = "softeng754-assignment4";
		RepositoryId repositoryId = new RepositoryId(repoOwner, repoName);
		Mockito.doReturn(pullRequest).when(github).createPullRequest(repositoryId, request);
		
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
}
