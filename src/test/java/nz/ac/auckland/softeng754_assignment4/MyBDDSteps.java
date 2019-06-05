package nz.ac.auckland.softeng754_assignment4;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.BeforeStories;

import java.io.IOException;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.ContentsService;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.UserService;
import org.mockito.Mockito;

import nz.ac.auckland.softeng754_assignment4.service.GitHubService;
import nz.ac.auckland.softeng754_assignment4.service.IGitHubService;
import nz.ac.auckland.softeng754_assignment4.exception.NotAuthorizedException;


public class MyBDDSteps{
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
	
	@BeforeStories
	public void SetUp() {
		// Given
		
		//username = "Saltedfish754";
		//password = "cafnyP-rathaz-7razho";
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
	
	@Given("Login with $Username and $Password")
	public void shouldLoginSuccessfullyWithCorrectUsernameAndPassword(@Named("Username") String username, @Named("Password") String password) throws NotAuthorizedException, IOException{
		 //TODO 
		this.username = username; // Saltedfish754
		this.password = password; // cafnyP-rathaz-7razho
		User user = new User();
		user.setName(username);
		Mockito.doReturn(_client).when(_client).setCredentials(username, password);
		Mockito.doReturn(user).when(_userService).getUser();
	}
	
	
	@Then("Should login successful")
	public void thenShouldLoginSuccessful() throws NotAuthorizedException, IOException{
		//TODO 
		github.signIn(username, password);
	}
}