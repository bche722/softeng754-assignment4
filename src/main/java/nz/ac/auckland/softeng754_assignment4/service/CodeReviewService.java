package nz.ac.auckland.softeng754_assignment4.service;

import java.util.List;

import nz.ac.auckland.softeng754_assignment4.model.CodeAnomaly;
import nz.ac.auckland.softeng754_assignment4.model.Reviewer;

public class CodeReviewService {
	INetworkService _networkService;
	
	public CodeReviewService() {
		super();
	}
	public void sendReviewResult(Reviewer reviewer, CodeAnomaly codeAnomaly) {

	}

	public List<CodeAnomaly> receiveReviewResult(Reviewer reviewer) {
		
		return null;
	}
	
	public void sendFeedback(CodeAnomaly codeAnomaly) {
		
	}
}
