package nz.ac.auckland.softeng754_assignment4.service;

import java.util.List;

import nz.ac.auckland.softeng754_assignment4.model.CodeAnalysisReport;
import org.eclipse.egit.github.core.RepositoryContents;

public interface ICodeAnalysisService
{
    // analysis code and get report
    CodeAnalysisReport analysis(List<RepositoryContents> contents);

    void setInspectionService(ICodeInspectionService service);
}