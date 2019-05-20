package nz.ac.auckland.softeng754_assignment4.Model;

public class CodePosition
{
    // the line of code
    int line;

    // the column in line of code
    int column;

    public CodePosition(int line, int column)
    {
        this.line = line;
        this.column = column;
    }

    public int getLine()
    {
        return line;
    }

    public int getColumn()
    {
        return column;
    }
}