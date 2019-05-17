package nz.ac.auckland.softeng754_assignment4.service;

import java.util.ArrayList;
import java.util.List;

public interface IReviewerService {
    List<Reviewer> reviewers = new ArrayList<Reviewer>();

    Reviewer addReviewer(String username);
    Reviewer getReviewer(int id);
    void deleteReviewer(Reviewer reviewer);
    Task allocateTask(Task task, Reviewer reviewer);
    Reviewer chooseReviewer(List<Reviewer> reviewers);
}
