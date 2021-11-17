package TicTacToe;

import java.util.LinkedList;
import java.util.List;

public class CaseResponse
{
    private int x, y;
    private List<CaseCheck> caseCheck = new LinkedList<>();

    public CaseResponse(int x, int y, List<CaseCheck> caseCheck)
    {
        this.x = x;
        this.y = y;
        this.caseCheck = caseCheck;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public List<CaseCheck> getCaseCheck()
    {
        return caseCheck;
    }

    public int[] getResponse(CaseCheck caseCheck)
    {
        return caseCheck.getCoordResp();
    }
}
