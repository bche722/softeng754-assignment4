package nz.ac.auckland.softeng754_assignment4.integration;

public interface IGithub {
	public Auth login(String username, String password);
	
	public void logout();
	
	public PullRequest createPullRequest(String title, String head, String base, String body);
	
	public SourceCode fetchSource(PullRequest pullRequest);
}
