package nz.ac.auckland.softeng754_assignment4.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import nz.ac.auckland.softeng754_assignment4.Feedback;
import nz.ac.auckland.softeng754_assignment4.ReviewTask;
import nz.ac.auckland.softeng754_assignment4.Reviewer;

public class ReviewTaskServiceTest {

  String username;
  Reviewer reviewer;
  PullRequest pullRequest;
  ReviewTask reviewTask;
  User user;

  IGitHubService githubService;
	IReviewerService reviewerService;
  IReviewTaskService reviewTaskService;
  IFeedbackService feedbackService;
  
  @Before
  public void SetUp() throws IOException {
    // Given
    username = UUID.randomUUID().toString(); // random username
		reviewerService = Mockito.mock(IReviewerService.class);
    reviewTaskService = Mockito.mock(IReviewTaskService.class);
    feedbackService = Mockito.mock(IFeedbackService.class);
    pullRequest = Mockito.mock(PullRequest.class);
    
    Mockito.doReturn(Reviewer.class).when(reviewerService).addReviewer(username);

    // create a reviewer
    reviewer = reviewerService.addReviewer(username);

    // get developer
    githubService = new GitHubService();
    user = githubService.signIn("Saltedfish754", "cafnyP-rathaz-7razho");
  }

  @After
  public void TearDown() throws IOException {
    // Given
    Mockito.doNothing().when(reviewerService).deleteReviewer(reviewer);

    // delete temp reviewer
    reviewerService.deleteReviewer(reviewer);
  }

  @Test
  public void shouldCreateReviewTaskSuccessfulWithValidPullRequest() {
    // Given
    Mockito.doReturn(ReviewTask.class).when(reviewTaskService).createReviewTask(pullRequest);

    // When
    reviewTask = reviewTaskService.createReviewTask(pullRequest);

    // Then
    // TODO
  }

  @Test
  public void shouldAllocateReviewTaskToReviewerSuccessful() {
    // Given
    Mockito.doNothing().when(reviewTaskService).allocateReviewTask(reviewTask);

    // When
    // TODO
  }

  @Test
  public void getAllReviewTasksByDeveloper() {
    // Given
		List<ReviewTask> tasks = new ArrayList<ReviewTask>();
    Mockito.doReturn(tasks).when(reviewTaskService).getReviewTasks(user);

    // When
    // TODO
  }

  @Test
  public void getAllReviewTasksByReviews() {
    // Given
		List<ReviewTask> tasks = new ArrayList<ReviewTask>();
    Mockito.doReturn(tasks).when(reviewTaskService).getReviewTasks(reviewer);

    // When
    // TODO
  }

  @Test
  public void shouldCreateFeedbackScuccessful() {
    // Given
    Mockito.doReturn(Feedback.class).when(feedbackService).createFeedback(reviewTask, "feedback message here");

    // When
    // TODO
  }

  @Test
  public void shouldGetFeedbackByReviewTask() {
    // Given
		List<Feedback> feedbacks = new ArrayList<Feedback>();
    Mockito.doReturn(feedbacks).when(feedbackService).getFeedback(reviewTask);

    // When
    // TODO
  }
}
