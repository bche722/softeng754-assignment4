package nz.ac.auckland.softeng754_assignment4.service;

import java.util.List;

import nz.ac.auckland.softeng754_assignment4.exception.NoReviewerException;
import nz.ac.auckland.softeng754_assignment4.exception.NoReviewException;
import nz.ac.auckland.softeng754_assignment4.model.CodeAnomaly;
import nz.ac.auckland.softeng754_assignment4.model.Reviewer;

public class CodeReviewService {
	INetworkService _networkService;

	public CodeReviewService(INetworkService networkService) {
		this._networkService = networkService;
	}

	public void sendReviewResult(Reviewer reviewer, CodeAnomaly codeAnomaly) throws NoReviewerException {
		
	}

	public List<CodeAnomaly> receiveReviewResult(Reviewer reviewer) throws NoReviewException{
		
		return null;
	}

	public void sendFeedback(CodeAnomaly codeAnomaly, String comments) {

	}
}
