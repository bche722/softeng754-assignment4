package nz.ac.auckland.softeng754_assignment4.integration;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.NotAuthorizedException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GithubTest {
	
	String username;
	String password;
	IGithub github;
	PullRequest pullRequest;
	
	@Before
	public void SetUp() {
		//Given
		username = "Saltedfish754@gmail.com";
		password = "S@lted_754";
		github = Mockito.mock(IGithub.class);
		pullRequest = Mockito.mock(PullRequest.class);
	}

	@Test
	public void shouldReturnAuthWithCorrectUsernameAndPassword() {
		
		// Given
		Auth auth = new Auth();
		auth.username = username;
		Mockito.doReturn(auth).when(github).login(username, password);
		
		// When
		github.login(username, password);
		
		// Then
		assertEquals(auth.username, username);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void shouldThrowExceptionWithIncorrectyUsernameAndPassword() {

		// Given
		String wrong_password = "Wrong Password";
		Mockito.doThrow(NotAuthorizedException.class).when(github).login(username, wrong_password);
		
		// When
		github.login(username, wrong_password);
		
	}
	
	@Test
	public void shouldReturnPullRequestWhenCreatePullRequest() {
		
		// Given
		String title = "Amazing new feature";
		String head = "softeng754-assignment4:new-feature";
		String base = "milestone-2";
		String body = "Please pull this in!";
		Mockito.doReturn(pullRequest).when(github).createPullRequest(title, head, base, body);
		
		// When
		PullRequest result = github.createPullRequest(title, head, base, body);
		
		// Then
		assertEquals(result, pullRequest);
	}
	
	@Test
	public void shouldFetchSourceCodeWithValidPullReuest() {
		// Given
		SourceCode source = Mockito.mock(SourceCode.class);
		Mockito.doReturn(source).when(github).fetchSource(pullRequest);
		
		// When
		SourceCode result = github.fetchSource(pullRequest);
		
		// Then
		// assertEquals(result, source);
	}
}
