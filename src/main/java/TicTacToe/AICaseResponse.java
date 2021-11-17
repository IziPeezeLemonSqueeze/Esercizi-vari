package TicTacToe;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AICaseResponse
{
    private List<CaseResponse> gridMatrixBestCase = new LinkedList<>();

    public AICaseResponse()
    {
        List<CaseCheck> casesZZ = new LinkedList<>();
        casesZZ.add(new CaseCheck(1, 0, new int[]{2, 0}));
        casesZZ.add(new CaseCheck(1, 1, new int[]{2, 2}));
        casesZZ.add(new CaseCheck(0, 1, new int[]{0, 2}));
        gridMatrixBestCase.add(0, new CaseResponse(0, 0, casesZZ));

        List<CaseCheck> casesUZ = new LinkedList<>();
        casesUZ.add(new CaseCheck(2, 0, new int[]{0, 0}));
        casesUZ.add(new CaseCheck(1, 1, new int[]{1, 2}));
        casesUZ.add(new CaseCheck(0, 0, new int[]{2, 0}));
        gridMatrixBestCase.add(1, new CaseResponse(1, 0, casesUZ));

        List<CaseCheck> casesDZ = new LinkedList<>();
        casesDZ.add(new CaseCheck(2, 1, new int[]{2, 2}));
        casesDZ.add(new CaseCheck(1, 1, new int[]{0, 2}));
        casesDZ.add(new CaseCheck(1, 0, new int[]{0, 0}));
        gridMatrixBestCase.add(2, new CaseResponse(2, 0, casesDZ));

        List<CaseCheck> casesZU = new LinkedList<>();
        casesZU.add(new CaseCheck(0, 0, new int[]{0, 2}));
        casesZU.add(new CaseCheck(1, 1, new int[]{2, 1}));
        casesZU.add(new CaseCheck(0, 2, new int[]{0, 0}));
        gridMatrixBestCase.add(3, new CaseResponse(0, 1, casesZU));

        List<CaseCheck> casesUU = new LinkedList<>();
        casesUU.add(new CaseCheck(1, 0, new int[]{1, 2}));
        casesUU.add(new CaseCheck(2, 0, new int[]{0, 2}));
        casesUU.add(new CaseCheck(2, 1, new int[]{0, 1}));
        casesUU.add(new CaseCheck(2, 2, new int[]{0, 0}));
        casesUU.add(new CaseCheck(1, 2, new int[]{1, 0}));
        casesUU.add(new CaseCheck(0, 2, new int[]{2, 0}));
        casesUU.add(new CaseCheck(0, 1, new int[]{2, 1}));
        casesUU.add(new CaseCheck(0, 0, new int[]{2, 2}));
        gridMatrixBestCase.add(4, new CaseResponse(1, 1, casesUU));

        List<CaseCheck> casesDU = new LinkedList<>();
        casesDU.add(new CaseCheck(2, 2, new int[]{2, 0}));
        casesDU.add(new CaseCheck(1, 1, new int[]{0, 1}));
        casesDU.add(new CaseCheck(2, 0, new int[]{2, 2}));
        gridMatrixBestCase.add(5, new CaseResponse(2, 1, casesDU));

        List<CaseCheck> casesZD = new LinkedList<>();
        casesZD.add(new CaseCheck(0, 1, new int[]{0, 0}));
        casesZD.add(new CaseCheck(1, 1, new int[]{2, 0}));
        casesZD.add(new CaseCheck(1, 2, new int[]{2, 2}));
        gridMatrixBestCase.add(6, new CaseResponse(0, 2, casesZD));

        List<CaseCheck> casesUD = new LinkedList<>();
        casesUD.add(new CaseCheck(0, 2, new int[]{2, 2}));
        casesUD.add(new CaseCheck(1, 1, new int[]{1, 0}));
        casesUD.add(new CaseCheck(2, 2, new int[]{0, 2}));
        gridMatrixBestCase.add(7, new CaseResponse(1, 2, casesUD));

        List<CaseCheck> casesDD = new LinkedList<>();
        casesDD.add(new CaseCheck(1, 2, new int[]{0, 2}));
        casesDD.add(new CaseCheck(1, 1, new int[]{0, 0}));
        casesDD.add(new CaseCheck(2, 1, new int[]{2, 0}));
        gridMatrixBestCase.add(8, new CaseResponse(2, 2, casesDD));
    }

    private int x, y;
    private boolean founded = false;
    private int[] targetResp = null;

    public int[] getBestCases(String[][] gridMatrix, String player)
    {

        targetResp = null;
        for (y = 0; y < 3; y++)
        {
            for (x = 0; x < 3; x++)
            {
                if (gridMatrix[x][y].equals(player) && !founded)
                {
                    gridMatrixBestCase.forEach(caseResponse ->
                    {
                        caseResponse.getCaseCheck().forEach(caseCheck ->
                        {
                            if (caseResponse.getX() == x && caseResponse.getY() == y)
                            {
                                if (gridMatrix[caseCheck.getX()][caseCheck.getY()].equals(player) &&
                                        gridMatrix[caseCheck.getCoordResp()[0]][caseCheck.getCoordResp()[1]].equals(" "))
                                {
                                    targetResp = caseCheck.getCoordResp();
                                    founded = true;
                                }
                            }
                        });
                    });
                }
            }
        }
        return targetResp;
    }



}
