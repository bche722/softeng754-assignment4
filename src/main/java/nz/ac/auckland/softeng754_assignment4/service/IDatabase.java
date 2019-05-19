package nz.ac.auckland.softeng754_assignment4.service;

import java.util.List;
import nz.ac.auckland.softeng754_assignment4.model.Reviewer;
import nz.ac.auckland.softeng754_assignment4.model.Task;

public interface IDatabase {
    void addReviewers(List<Reviewer> reviewers);
    void deleteReviewers(List<Reviewer> reviewers);

    int getLowestCount();
    List<Reviewer> getReviewersByCount(int count);

    void updateTask(Task task);

    void updateReviewer(Reviewer chosenReviewer);

//    Reviewer getReviewerByUsername(String username);

//    List<Reviewer> getAllReviewers();

}
