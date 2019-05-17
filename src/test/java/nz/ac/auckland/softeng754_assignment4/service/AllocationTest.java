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

public class AllocationTest {
    IDatabase iDatabase;
    Allocation allocation;
    List<Reviewer> reviewers;

    @Before
    public void SetUp() {
        // Given
        iDatabase = Mockito.mock(IDatabase.class);
        allocation = new Allocation(iDatabase);

        reviewers = new ArrayList<Reviewer>();
    }

    @Test
    public void shouldSuccessfullyAddOneReviewerToReviewerList(){
        // Given
        Reviewer reviewer1 = new Reviewer();
        reviewers.add(reviewer1);

        // When
        allocation.addReviewersToReviewers(reviewers);

        // then
        assertEquals(1, allocation.getReviewers().size());
    }

    @Test
    public void shouldSuccessfullyAddMultipleReviewersToReviewerList(){
        // Given
        Reviewer reviewer1 = new Reviewer();
        reviewers.add(reviewer1);
        Reviewer reviewer2 = new Reviewer();
        reviewers.add(reviewer2);
        Reviewer reviewer3 = new Reviewer();
        reviewers.add(reviewer3);

        // When
        allocation.addReviewersToReviewers(reviewers);

        // then
        assertEquals(3, allocation.getReviewers().size());
    }

    @Test
    public void shouldSuccessfullyAddReviewersToDatabase(){
        // Given
        Reviewer reviewer1 = new Reviewer();
        reviewers.add(reviewer1);
        allocation.addReviewersToReviewers(reviewers);
        Mockito.doReturn(true).when(iDatabase).updateReviewers(allocation.getReviewers());
        // When
        allocation.addReviewersToDB(allocation.getReviewers());

        // Then
        assertTrue(allocation.addReviewersToDB(allocation.getReviewers()));
//        Mockito.verify(iDatabase, Mockito.times(1)).updateReviewers(allocation.getReviewers());
    }

}
