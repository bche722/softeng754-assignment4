package nz.ac.auckland.softeng754_assignment4.service;

import java.util.List;

public interface IDatabase {
    boolean updateReviewers(List<Reviewer> reviewerList);

    List<Reviewer> getAllReviewers();

}
