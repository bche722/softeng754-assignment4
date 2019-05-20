package nz.ac.auckland.softeng754_assignment4.service;

import java.util.List;

import nz.ac.auckland.softeng754_assignment4.model.CodeAnomaly;
import org.eclipse.egit.github.core.RepositoryContents;

public interface ICodeInspectionService
{
    // analysis code and get anomalies
    List<CodeAnomaly> inspector(RepositoryContents content);

    // analysis code and get anomalies
    List<CodeAnomaly> inspector(String content);
}