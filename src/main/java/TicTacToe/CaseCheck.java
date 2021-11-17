package TicTacToe;

public class CaseCheck
{
    private int x, y;
    private int[] coordResp;

    public CaseCheck(int x, int y, int[] coordResp)
    {
        this.x = x;
        this.y = y;
        this.coordResp = coordResp;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int[] getCoordResp()
    {
        return coordResp;
    }
}
