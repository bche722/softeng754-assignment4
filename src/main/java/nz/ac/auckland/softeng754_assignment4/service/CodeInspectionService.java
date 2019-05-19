package nz.ac.auckland.softeng754_assignment4.service;

import java.util.List;

import nz.ac.auckland.softeng754_assignment4.Model.CodeAnomaly;
import org.eclipse.egit.github.core.RepositoryContents;

public class CodeInspectionService implements ICodeInspectionService
{

  @Override
  public List<CodeAnomaly> inspector(RepositoryContents content) {
      String codeContent = content.getContent();
      return this.inspector(codeContent);
  }

  @Override
  public List<CodeAnomaly> inspector(String content) {
      return null;
  }
}