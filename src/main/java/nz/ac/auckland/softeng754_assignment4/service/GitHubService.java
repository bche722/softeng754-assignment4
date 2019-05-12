package nz.ac.auckland.softeng754_assignment4.service;

import java.io.IOException;

import org.eclipse.egit.github.core.User;

public interface GitHubService {
	
	public void signIn(String username, String password);
	
	public void signOut();
	
	public boolean isSignedIn();
	
	public User getUser() throws IOException ;
}
