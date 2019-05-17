package nz.ac.auckland.softeng754_assignment4.service;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;

public class ReviewerServiceTest {

  String username;
  Reviewer reviewer;
  List<Reviewer> reviewers = null;
  Task task;
  IReviewerService reviewerService;

	@Before
	public void SetUp() {
    // Given
    username = UUID.randomUUID().toString(); // random username
    reviewerService = Mockito.mock(IReviewerService.class);
  }

  @Test
	public void shouldAddReviewerSuccessfulWithUniqueUsername() throws IOException {
    // Given
    Mockito.doReturn(Mockito.mock(Reviewer.class)).when(reviewerService).addReviewer(username);

    // When
    reviewer = reviewerService.addReviewer(username);

		// Then
		assertEquals(reviewer.getUsername(), username);
  }
  
  @Test(expected = IOException.class)
  public void shouldAddReviewerFailureWithSameUsername() throws IOException {
    // Given
    Mockito.doThrow(IOException.class).when(reviewerService).addReviewer(username);

    // When
    reviewer = reviewerService.addReviewer(username);
  }

  @Test
  public void shouldReturnReviewerSuccessful() throws IOException {
    int id = reviewer.getId();
  
    // Given
    Mockito.doReturn(Reviewer.class).when(reviewerService).getReviewer(id);

    // When
    Reviewer result = reviewerService.getReviewer(id);

    // Then
    assertEquals(result.getId(), id);
    assertEquals(result.getUsername(), username);
  }
  
  @Test
  public void shouldDeleteReviewerSuccessful() throws IOException {
    int id = reviewer.getId();

    // delete reviewer
    reviewerService.deleteReviewer(reviewer);

    // Given
    Mockito.doReturn(Null.class).when(reviewerService).getReviewer(id);

    // When: try get reviewer by id
    Reviewer reviewer = reviewerService.getReviewer(id);

    // Then
    assertNull(reviewer);
  }


  @Test
  public void shouldRandomlyChooseAReviewer(){
    //Given
    reviewers = new ArrayList<Reviewer>();
    Reviewer reviewer1 = reviewerService.addReviewer("reviewer1");
    Reviewer reviewer2 = reviewerService.addReviewer("reviewer1");
    Reviewer reviewer3 = reviewerService.addReviewer("reviewer1");
    reviewers.add(reviewer1);
    reviewers.add(reviewer2);
    reviewers.add(reviewer3);

    Random rand = new Random();
    Reviewer randomReviewer = reviewers.get(rand.nextInt() % reviewers.size());

    Mockito.doReturn(randomReviewer).when(reviewerService).chooseReviewer(reviewers);

    //When
    Reviewer chosenReviewer = reviewerService.chooseReviewer(reviewers);

    //then
    assertTrue(reviewers.contains(chosenReviewer));
  }

  @Test
  public void shouldchooseReviewerByLowestCount(){
    //Given
    int lowestCount = 0;
    Mockito.doReturn(Mockito.mock(Reviewer.class)).when(reviewerService).chooseReviewer(reviewers);
    //when
    Reviewer chosenReviewer = reviewerService.chooseReviewer(reviewers);
    //then
    assertEquals(lowestCount, chosenReviewer.getCount());
  }

  @Test(expected = NoReviewerException.class)
  public void throwExceptionWhenNoExistingReviewer(){
    //Given
    Mockito.doThrow(NoReviewerException.class).when(reviewerService).chooseReviewer(reviewers);
    //when
    reviewerService.chooseReviewer(reviewers);
  }


  @Test
  public void shouldAllocateTaskToAReviewer(){
    //Given
    Mockito.doReturn(Mockito.mock(Task.class)).when(reviewerService).allocateTask(task, reviewer);
    //when
    Task allocatedTask = reviewerService.allocateTask(task, reviewer);
    //then
    assertEquals(allocatedTask, null);
  }


  @Test
  public void shouldPlusOneToTheCount(){
    //Given
    reviewer = new Reviewer();

    //When
    reviewer.increment();

    //then
    assertEquals(reviewer.getCount(), 1);
  }


  @Test
  public void shouldSuccessfullyAddOneReviewerToReviewers(){
    //When
    List<Reviewer> reviewers = new ArrayList<Reviewer>();
	Reviewer reviewer1 = new Reviewer();
	reviewers.add(reviewer1);

    IDatabase iDatabase = Mockito.mock(IDatabase.class);
    Allocation allocation  = new Allocation(iDatabase);

    //when
    allocation.addReviewersToReviewers(reviewers);

    //then
    assertEquals(1, allocation.getReviewers().size());
  }

  @Test
  public void shouldSuccessfullyAddReviewerToDatabase(){
    //Given
    IDatabase iDatabase = Mockito.mock(IDatabase.class);
    Allocation allocation  = new Allocation(iDatabase);

    List<Reviewer> reviewers = new ArrayList<Reviewer>();
    Reviewer reviewer1 = new Reviewer();
    reviewers.add(reviewer1);
    allocation.addReviewersToReviewers(reviewers);
    //when
    allocation.addReviewersToDB(allocation.getReviewers());
    //then
    Mockito.verify(iDatabase, Mockito.times(1)).updateReviewers(allocation.getReviewers());
  }

}
