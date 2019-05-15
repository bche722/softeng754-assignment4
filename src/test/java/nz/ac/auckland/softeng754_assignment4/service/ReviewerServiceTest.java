package nz.ac.auckland.softeng754_assignment4.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;

import nz.ac.auckland.softeng754_assignment4.Reviewer;

public class ReviewerServiceTest {

  String username;
  Reviewer reviewer;

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
    Mockito.doReturn(Reviewer.class).when(reviewerService).addReviewer(username);

    // When
    reviewer = reviewerService.addReviewer(username);

		// Then
		assertEquals(reviewer.getUsername(), username);
  }
  
  @Test(expected = IOException.class)
  public void shouldAddReviewerFailureWithSameUsername() throws IOException {
    // Given
    Mockito.doReturn(Reviewer.class).when(reviewerService).addReviewer(username);

    // When
    reviewer = reviewerService.addReviewer(username);
  }

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
}
