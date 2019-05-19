package nz.ac.auckland.softeng754_assignment4.Model;

public class CodeAnomaly
{
    public enum Type {
      DEFECT, SMELL, MALICIOUS
    }

    // file name
    String file;

    // start line number of code range
    CodePosition start;

    // end line number of code range
    CodePosition end;

    // type of inspected anomalies
    Type type;

    public CodeAnomaly(CodePosition start, CodePosition end, Type type)
    {
        this.start = start;
        this.end = end;
        this.type = type;
    }

    public CodeAnomaly setFileName(String file)
    {
        this.file = file;
        return this;
    }

    public String getFileName()
    {
      return this.file;
    }

    public CodePosition getCodeStart()
    {
        return this.start;
    }

    public CodePosition getCodeEnd()
    {
        return this.end;
    }

    public Type getType()
    {
      return this.type;
    }
}