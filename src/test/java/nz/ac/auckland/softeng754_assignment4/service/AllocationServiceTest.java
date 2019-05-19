package nz.ac.auckland.softeng754_assignment4.service;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import nz.ac.auckland.softeng754_assignment4.Model.Reviewer;
import nz.ac.auckland.softeng754_assignment4.Model.Task;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AllocationServiceTest {
    IDatabase iDatabase;
    AllocationService allocationService;
    List<Reviewer> reviewers;

    @Before
    public void SetUp() {
        // Given
        iDatabase = Mockito.mock(IDatabase.class);
        allocationService = new AllocationService(iDatabase);

        reviewers = new ArrayList<Reviewer>();
    }

    @Test
    public void shouldInvolveIDatabaseAddReviewersMethodWhenAddingReviewers(){
        //Given
        Reviewer reviewer = Mockito.mock(Reviewer.class);
        reviewers.add(reviewer);
        //When
        allocationService.addReviewers(reviewers);
        //Then
        Mockito.verify(iDatabase, Mockito.times(1)).addReviewers(reviewers);
    }

    @Test
    public void shouldInvolveDatabaseDeleteReviewersMethodWhenDeletingReviewers(){
        //Given
        Reviewer reviewer = Mockito.mock(Reviewer.class);
        reviewers.add(reviewer);
        //When
        allocationService.deleteReviewers(reviewers);
        //Then
        Mockito.verify(iDatabase, Mockito.times(1)).deleteReviewers(reviewers);
    }

    @Test
    public void shouldRandomlyChooseAReviewer(){
        //Given
        List<Reviewer> reviewersWithHighestCount = new ArrayList<Reviewer>();
        int LowestCount = 3;

        Reviewer reviewer1 = new Reviewer("reviewer1");
        Reviewer reviewer2 = new Reviewer("reviewer2");
        Reviewer reviewer3 = new Reviewer("reviewer3");

        reviewer1.setCount(LowestCount);
        reviewer2.setCount(LowestCount);
        reviewer3.setCount(LowestCount);

        reviewersWithHighestCount.add(reviewer1);
        reviewersWithHighestCount.add(reviewer2);
        reviewersWithHighestCount.add(reviewer3);

        Mockito.doReturn(LowestCount).when(iDatabase).getLowestCount();
        Mockito.doReturn(reviewersWithHighestCount).when(iDatabase).getReviewersByCount(LowestCount);

        //When
        Reviewer chosenReviewer = allocationService.chooseReviewer();

        //Then
        assertTrue(reviewersWithHighestCount.contains(chosenReviewer));
    }

    @Test
    public void shouldAllocateTaskToAReviewer(){
        //Given
        Task task = new Task();
        Reviewer reviewer = new Reviewer("reviewer1");
        //When
        allocationService.allocateTask(task, reviewer);
        //Then
        assertFalse(task.getReviewer() == null);
    }

    @Test
    public void shouldInvolveDatabaseUpdateTaskMethodAfterAllocation(){
        //Given
        Task task = new Task();
        //When
        allocationService.updateTask(task);
        //Then
        Mockito.verify(iDatabase, Mockito.times(1)).updateTask(task);
    }

    @Test
    public void shouldAddOneToTheCountAfterAllocation(){
        //Given
        Reviewer chosenReviewer = new Reviewer("chosenReviewer");
        int currentCount = 1;
        chosenReviewer.setCount(currentCount);
        //When
        allocationService.addCount(chosenReviewer);
        //Then
        assertEquals(currentCount + 1, chosenReviewer.getCount());
    }

    @Test
    public void shouldInvolveDatabaseUpdateReviewerMethodAfterAllocation(){
        //Given
        Reviewer chosenReviewer = new Reviewer("chosenReviewer");
        //When
        allocationService.saveCount(chosenReviewer);
        //Then
        Mockito.verify(iDatabase, Mockito.times(1)).updateReviewer(chosenReviewer);
    }

//    @Test
//    public void shouldFailWhenAddingReviewersWithExistingName(){
//        //Given
//        Reviewer reviewer = Mockito.mock(Reviewer.class);
//        Mockito.doReturn(reviewer).when(iDatabase).getReviewerByUsername(reviewer.getUsername());
//        //When
//        boolean result = allocationService.isReviewerExist(reviewer);
//        //then
//        assertTrue(result);
//    }





}
