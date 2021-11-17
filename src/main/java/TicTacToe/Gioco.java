package TicTacToe;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Predicate;

public class Gioco
{
    private Scanner plInput = new Scanner(System.in);


    private String player = "X", ai = "O";
    private String[][] gridMatrix = {
            {" ", " ", " "},
            {" ", " ", " "},
            {" ", " ", " "}
    };

    private String titolo, grid;

    private boolean isGameActive = false;
    private boolean isNewGame = false;

    private boolean isPlayerMove = false;

    private boolean isPlayerWin, isBotWin;

    private int mosse = 0;

    private AICaseResponse AICaseResponse = new AICaseResponse();


    public Gioco()
    {
        grid = "   X   0    1    2\n " +
                "Y  //===||===||===\\\\\n" +
                " 0  || " + gridMatrix[0][0] + " || " + gridMatrix[1][0] + " || " + gridMatrix[2][0] + " ||\n" +
                "    ||===||===||===||\n" +
                " 1  || " + gridMatrix[0][1] + " || " + gridMatrix[1][1] + " || " + gridMatrix[2][1] + " ||\n" +
                "    ||===||===||===||\n" +
                " 2  || " + gridMatrix[0][2] + " || " + gridMatrix[1][2] + " || " + gridMatrix[2][2] + " ||\n" +
                "    \\\\===||===||===//";

        titolo = "╔╦╗┬┌─┐╔╦╗┌─┐┌─┐╔╦╗┌─┐┌─┐\n" +
                " ║ ││   ║ ├─┤│   ║ │ │├┤ \n" +
                " ╩ ┴└─┘ ╩ ┴ ┴└─┘ ╩ └─┘└─┘\n V0.1";
        startGame();

    }

    private void startGame()
    {
        isGameActive = true;
        isNewGame = true;
        while (isGameActive)
        {
            if (isNewGame)
            {
                _cls();
                System.out.println(titolo);
                String[] stringhe = {"Caricamento gioco in corso", ".", ".", ".", ".", ".", "."};
                for (int i = 0; i < stringhe.length; i++)
                {
                    try
                    {
                        Thread.sleep(500);
                        System.out.print(stringhe[i]);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                System.out.println("\n\n" + grid);
                isNewGame = false;
                isPlayerMove = new Random().nextBoolean();
            }
            if (mosse < 9)
            {
                if (isPlayerMove)
                {
                    System.out.println("Tocca a te!");
                    setPlayerMoveToMatrix(parsePlayerMove());
                    isPlayerMove = false;
                    checkWinner(true);
                } else
                {
                    System.out.println("Tocca al PC");
                    aiParseMove();
                    isPlayerMove = true;
                    checkWinner(false);
                }

                mosse++;
                _render();
            } else
            {
                isGameActive = false;
                System.out.println("PAREGGIO!");
            }
        }

    }

    private void checkWinner(boolean isPlayerCheck)
    {


        boolean[] result = new boolean[9];

        String[] victories =
                {"012", "345", "678", "036", "147", "258", "048", "246"};
        int indiceResult = 0;

        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < 3; x++)
            {
                if (isPlayerCheck)
                {
                    if (gridMatrix[x][y].equals(player))
                    {
                        result[indiceResult] = true;
                    } else
                    {
                        result[indiceResult] = false;
                    }
                } else
                {
                    if (gridMatrix[x][y].equals(ai))
                    {
                        result[indiceResult] = true;
                    } else
                    {
                        result[indiceResult] = false;
                    }
                }
                if (indiceResult < 8) indiceResult++;
            }
        }

        boolean winner = false;


        for (String v : victories)
        {

            char[] vChar = v.toCharArray();
            String probWin = "";

            for (int b = 0; b < result.length; b++)
            {
                if (result[b]) probWin += b + "";
            }
            int sum = 0;
          //  System.out.println(probWin);
            for (int k = 0; k < vChar.length; k++)
            {
                if (probWin.contains(String.valueOf(vChar[k])))
                {
                    sum++;
                }
            }

            if (sum == 3)
            {
                winner = true;
            }
        }

        if (winner)
        {
            isGameActive = false;
            if (isPlayerCheck)
            {
                isPlayerWin = true;
                isBotWin = false;
            } else
            {
                isPlayerWin = false;
                isBotWin = true;
            }
        }


/*
        if (game.get(s.charAt(0)) == game.get(s.charAt(1)) && game.get(s.charAt(1)) == game.get(s.charAt(2))
        System.out.println(game.get(s.charAt(i) + " ha vinto"));

        int[] board = new int[9];

        for(int i=0;i<board.length;i++) board[i] = 0;

        board[3] = 1;
        board[4] = 1;
        board[5] = 1;

        String[] v = "123,456".split(",");

        for(String k:v)
        {
            int sum = 0;
            for(int i=0;i<k.length();i++)
                if(board[Integer.parseInt(k.charAt(i)+"")]==1)
                    sum++;
            if(sum==3)
                System.out.println("1 ha vinto su "+k);
        }
*/
    }

    private void aiParseMove()
    {
        boolean exec = false;
        int[] bestMove = AICaseResponse.getBestCases(gridMatrix, player);
        if (bestMove != null)
        {
            aiMove(bestMove[0], bestMove[1]);
        } else
        {
            while (!exec)
            {
                int x = new Random().nextInt(3), y = new Random().nextInt(3);
                if (gridMatrix[x][y].equals(" "))
                {
                    exec = true;
                    aiMove(x, y);
                }
            }
        }
    }

    private void aiMove(int x, int y)
    {
        System.out.println("\n\nAI MOVE -> X: " + x + " Y: " + y);
        gridMatrix[x][y] = ai;
    }


    private int[] parsePlayerMove()
    {
        int[] coord = new int[2];
        boolean validInput = false;
        do
        {
            try
            {
                System.out.print("X: ");
                coord[0] = plInput.nextInt();
                System.out.print("Y: ");
                coord[1] = plInput.nextInt();

                if (gridMatrix[coord[0]][coord[1]].equals(" "))
                {
                    validInput = true;
                }
            } catch (Exception e)
            {
                System.out.println("Inserisci delle coordinate valide");
            }
        } while (!validInput);
        return coord;
    }

    private void setPlayerMoveToMatrix(int[] playerMove)
    {
        //gridMatrix   X              Y          = "X";
        gridMatrix[playerMove[0]][playerMove[1]] = player;
    }

    private void _render()
    {

        _cls();
        grid = "   X   0    1    2\n " +
                "Y  //===||===||===\\\\\n" +
                " 0  || " + gridMatrix[0][0] + " || " + gridMatrix[1][0] + " || " + gridMatrix[2][0] + " ||\n" +
                "    ||===||===||===||\n" +
                " 1  || " + gridMatrix[0][1] + " || " + gridMatrix[1][1] + " || " + gridMatrix[2][1] + " ||\n" +
                "    ||===||===||===||\n" +
                " 2  || " + gridMatrix[0][2] + " || " + gridMatrix[1][2] + " || " + gridMatrix[2][2] + " ||\n" +
                "    \\\\===||===||===//";
        System.out.println(titolo);
        System.out.println(grid);
        if (isPlayerWin)
        {
            System.out.println("Hai vinto congratulazioni!");
        } else if (isBotWin)
        {
            System.out.println("Ha vinto il computer! Ritenta sarai più fortunato!");
        }
    }

    private void _cls()
    {
        try
        {
            if (isGameActive)
            {
                final String os = System.getProperty("os.name");
                if (os.contains("Windows"))
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                else
                    Runtime.getRuntime().exec("clear");
            }
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
