package nz.ac.auckland.softeng754_assignment4.service;

import java.util.List;

import nz.ac.auckland.softeng754_assignment4.model.CodeAnalysisReport;
import org.eclipse.egit.github.core.RepositoryContents;

public class CodeAnalysisService implements ICodeAnalysisService
{
    @Override
    public CodeAnalysisReport analysis(List<RepositoryContents> contents) {
      return null;
    }

    @Override
    public void setInspectionService(ICodeInspectionService service) {

    }
}