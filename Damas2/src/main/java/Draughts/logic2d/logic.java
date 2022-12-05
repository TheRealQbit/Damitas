package Draughts.logic2d;

import java.awt.*;
import java.io.IOException;
import java.util.Scanner;
import java.net.URI;

public class logic {
    int white = 12;
    int black = 12;
    int [][] whitePOS = new int[8][8];
    int [][] blackPOS = new int[8][8];
    char[][] board = new char[8][8];
    int x, y, nx, ny, ex, ey, WhiteScore, BlackScore, WhiteWins, BlackWins, timeSkippedBlack, timeSkippedWhite;
    Boolean turnOverlap = false;
    Boolean skip = false;

    void allocate(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                blackPOS[i][j] = 0;
            }
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 8; j++){
                if(i % 2 == 1){
                    if(j % 2 == 0){
                        blackPOS[i][j] = 1;
                    }
                }else{
                    if(j % 2 != 0){
                        blackPOS[i][j] = 1;
                    }
                }
            }
        }
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                whitePOS[i][j] = 0;
            }
        }
        for(int i = 5; i < 7; i++){
            for(int j = 0; j < 8; j++){
                if(i % 2 == 1){
                    if(j % 2 == 0){
                        whitePOS[i][j] = 1;
                    }
                }else{
                    if(j % 2 != 0){
                        whitePOS[i][j] = 1;
                    }
                }
            }
        }
        for(int i = 7; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(j % 2 == 0){
                    whitePOS[i][j] = 1;
                }
            }
        }
    }

     void print(int[][] whites, int[][] blacks) {
         whites = whitePOS;
         blacks = blackPOS;
         for (int i = 0; i < board.length; i++) {
             for (int j = 0; j < board.length; j++) {
                 if(whites[i][j] == 1){
                     board[i][j] = 'w';
                 }
                 else if(blacks[i][j] == 1){
                     board[i][j] = 'b';
                 }
                 else if(whites[i][j] == 2){
                     board[i][j] = 'W';
                     break;
                 }
                 else if(blacks[i][j] == 2){
                     board[i][j] = 'B';
                 }
                 else board[i][j] = ' ';
             }
         }
         System.out.println("    0   1   2   3   4   5   6   7");
         System.out.println("   _________________________________");
         for (int i = 0; i < board.length; i++) {
             System.out.print(i+ " |");
             for (int j = 0; j < board.length; j++) {
                 System.out.print(" " + board[i][j] + " |");
             }
             System.out.println();
             System.out.println("   _________________________________");
         }
    }

    void input(){
        String in, xs,xy, exs, eys;
        Scanner input = new Scanner(System.in);
        in = input.nextLine();
        if(in.length() == 11 && in.charAt(0) == '(' && in.charAt(2) == ',' && in.charAt(4) == ')' && in.charAt(5) == ' ' && in.charAt(6) == '(' && in.charAt(8) == ',' && in.charAt(10) == ')'){
                xs = in.substring(1,2);
                xy = in.substring(3,4);
                x = Integer.parseInt(xs);
                y = Integer.parseInt(xy);
                xs = in.substring(7,8);
                xy = in.substring(9,10);
                nx = Integer.parseInt(xs);
                ny = Integer.parseInt(xy);
        }
        else if(in.length() == 17 && in.charAt(0) == '(' && in.charAt(2) == ',' && in.charAt(4) == ')' && in.charAt(5) == ' ' && in.charAt(6) == '(' && in.charAt(8) == ',' && in.charAt(10) == ')' && in.charAt(11) == ' ' && in.charAt(12) == '(' && in.charAt(14) == ',' && in.charAt(16) == ')'){
                xs = in.substring(1,2);
                xy = in.substring(3,4);
                x = Integer.parseInt(xs);
                y = Integer.parseInt(xy);
                exs = in.substring(7,8);
                eys = in.substring(9,10);
                ex = Integer.parseInt(exs);
                ey = Integer.parseInt(eys);
                xs = in.substring(13,14);
                xy = in.substring(15,16);
                nx = Integer.parseInt(xs);
                ny = Integer.parseInt(xy);
        }
        else if(in.length() == 19 && in.charAt(0) == '(' && in.charAt(2) == ',' && in.charAt(4) == ')' && in.charAt(5) == ' ' && in.charAt(6) == '(' && in.charAt(8) == ',' && in.charAt(10) == ')' && in.charAt(11) == ' ' && in.charAt(12) == '(' && in.charAt(14) == ',' && in.charAt(16) == ')'){
            if (in.charAt(18) == 'W' || in.charAt(18) == 'B' || in.charAt(18) == 'w' || in.charAt(18) == 'b'){
                xs = in.substring(1,2);
                xy = in.substring(3,4);
                x = Integer.parseInt(xs);
                y = Integer.parseInt(xy);
                exs = in.substring(7,8);
                eys = in.substring(9,10);
                ex = Integer.parseInt(exs);
                ey = Integer.parseInt(eys);
                xs = in.substring(13,14);
                xy = in.substring(15,16);
                nx = Integer.parseInt(xs);
                ny = Integer.parseInt(xy);
                turnOverlap = true;
            }
        }
        else if(in.length() == 13 && in.charAt(0) == '(' && in.charAt(2) == ',' && in.charAt(4) == ')' && in.charAt(5) == ' ' && in.charAt(6) == '(' && in.charAt(8) == ',' && in.charAt(10) == ')'){
            if(in.charAt(12) == 'W' || in.charAt(12) == 'B' || in.charAt(12) == 'w' || in.charAt(12) == 'b'){
                xs = in.substring(1,2);
                xy = in.substring(3,4);
                x = Integer.parseInt(xs);
                y = Integer.parseInt(xy);
                xs = in.substring(7,8);
                xy = in.substring(9,10);
                nx = Integer.parseInt(xs);
                ny = Integer.parseInt(xy);
                turnOverlap = true;
            }
        }
        else if(in.length() == 4){
            if(in.equalsIgnoreCase("Skip")){
                skip = true;
            }
        }
        else{
            System.out.println("Invalid input");
            input();
        }
    }
    void basic(int[][] turn, int[][] opposite , int Score, int skiped){
        boolean Turn = true;
        while (Turn){
            input();
            if(turn[y][x] == 1 && turn[ny][nx] == 0 && nx-x != 0 && ny-y != 0 && nx<8 && ny<8 && nx>=0 && ny>=0){
                if(ex != 0 && ey != 0 && opposite[ey][ex] == 1){
                    opposite[ey][ex] = 0;
                    turn[y][x] = 0;
                    turn[ny][nx] = 1;
                    print(whitePOS, blackPOS);
                    ey = 0;
                    ex = 0;
                    Score--;
                    Turn = false;
                }
                else{
                    turn[y][x] = 0;
                    turn[ny][nx] = 1;
                    print(whitePOS, blackPOS);
                    Turn = false;
                }
            }
            else if(skip == true){
                Turn = false;
                skiped++;
                print(whitePOS, blackPOS);
            }
            else System.out.println("Invalid move!");
            skip = false;
        }

    }
    void Intermediate(int[][] turn, int[][] opposite , int Score, int skiped){
        boolean Turn = true;
        while(Turn == true){
            input();
            if(turn[y][x] == 1 && turn[ny][nx] == 0 && nx-x != 0 && ny-y != 0 && opposite[ny][nx] == 0 && nx<8 && ny<8 && nx>=0 && ny>=0){
                if(ex != 0 && ey != 0 && opposite[ey][ex] == 1){
                    opposite[ey][ex] = 0;
                    turn[y][x] = 0;
                    turn[ny][nx] = 1;
                    print(whitePOS, blackPOS);
                    ey = 0;
                    ex = 0;
                    Score--;
                    Turn = false;
                }
                else if(ny == 0 && turn == whitePOS){
                    turn[y][x] = 0;
                    turn[ny][nx] = 2;
                    print(whitePOS, blackPOS);
                    Turn = false;
                }
                else if(ny == 7 && turn == blackPOS){
                    turn[y][x] = 0;
                    turn[ny][nx] = 2;
                    print(whitePOS, blackPOS);
                    Turn = false;
                }
                else{
                    turn[y][x] = 0;
                    turn[ny][nx] = 1;
                    print(whitePOS, blackPOS);
                    Turn = false;
                }
            }
            else if(turn[y][x] == 2 && opposite[ny][nx] == 0 && nx<8 && ny<8 && nx>=0 && ny>=0){
                if(ex != 0 && ey != 0 && opposite[ey][ex] == 1 && ex<8 && ey<8 && ex>=0 && ey>=0){
                    opposite[ey][ex] = 0;
                    turn[y][x] = 0;
                    turn[ny][nx] = 1;
                    print(whitePOS, blackPOS);
                    ey = 0;
                    ex = 0;
                    Score--;
                    Turn = false;
                }
            }
            else if(skip == true){
                Turn = false;
                skiped++;
                print(whitePOS, blackPOS);
            }
            else System.out.println("Invalid move!");
            skip = false;
        }

    }
    void Advanced(int[][] turn, int[][] opposite , int Score, int skiped){
        boolean Turn = true;
        while(Turn == true){
            input();
            if(timeSkippedBlack == 2 || timeSkippedWhite == 2){
                Desktop d = Desktop.getDesktop();
                try {
                    d.browse(URI.create("https://www.youtube.com/watch?v=7jvWb-rHipo&ab_channel=SammyBoi%F0%9F%87%BA%F0%9F%87%A6"));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(turnOverlap == true){
                if(opposite[y][x] == 1 && opposite[ny][nx] == 0 && nx-x != 0 && ny-y != 0 && turn[ny][nx] == 0 && nx<8 && ny<8 && nx>=0 && ny>=0){
                    if(ex != 0 && ey != 0 && turn[ey][ex] == 1 && ex<8 && ey<8 && ex>=0 && ey>=0){
                        turn[ey][ex] = 0;
                        opposite[y][x] = 0;
                        opposite[ny][nx] = 1;
                        print(whitePOS, blackPOS);
                        ey = 0;
                        ex = 0;
                        Score--;
                        Turn = false;
                    }
                    else if(ny == 0 && turn == whitePOS){
                        opposite[y][x] = 0;
                        opposite[ny][nx] = 2;
                        print(whitePOS, blackPOS);
                        Turn = false;
                    }
                    else if(ny == 7 && turn == blackPOS){
                        opposite[y][x] = 0;
                        opposite[ny][nx] = 2;
                        print(whitePOS, blackPOS);
                        Turn = false;
                    }
                    else{
                        opposite[y][x] = 0;
                        opposite[ny][nx] = 1;
                        print(whitePOS, blackPOS);
                        Turn = false;
                    }
                }
                else if(opposite[y][x] == 2 && turn[ny][nx] == 0 && nx<8 && ny<8 && nx>=0 && ny>=0){
                    if(ex != 0 && ey != 0 && turn[ey][ex] == 1 && ex<8 && ey<8 && ex>=0 && ey>=0){
                        turn[ey][ex] = 0;
                        opposite[y][x] = 0;
                        opposite[ny][nx] = 1;
                        print(whitePOS, blackPOS);
                        ey = 0;
                        ex = 0;
                        Score--;
                        Turn = false;
                    }
                }
                else if(skip == true){
                    Turn = false;
                    skiped++;
                    print(whitePOS, blackPOS);
                }
                skip = false;
            }
            else if(turnOverlap == false){
                if(turn[y][x] == 1 && turn[ny][nx] == 0 && nx-x != 0 && ny-y != 0 && opposite[ny][nx] == 0 && nx<8 && ny<8 && nx>=0 && ny>=0){
                    if(ex != 0 && ey != 0 && opposite[ey][ex] == 1 && ex<8 && ey<8 && ex>=0 && ey>=0){
                        opposite[ey][ex] = 0;
                        turn[y][x] = 0;
                        turn[ny][nx] = 1;
                        print(whitePOS, blackPOS);
                        ey = 0;
                        ex = 0;
                        Score--;
                        Turn = false;
                    }
                    else if(ny == 0 && turn == whitePOS){
                        turn[y][x] = 0;
                        turn[ny][nx] = 2;
                        print(whitePOS, blackPOS);
                        Turn = false;
                    }
                    else if(ny == 7 && turn == blackPOS){
                        turn[y][x] = 0;
                        turn[ny][nx] = 2;
                        print(whitePOS, blackPOS);
                        Turn = false;
                    }
                    else{
                        turn[y][x] = 0;
                        turn[ny][nx] = 1;
                        print(whitePOS, blackPOS);
                        Turn = false;
                    }
                }
                else if(turn[y][x] == 2 && opposite[ny][nx] == 0 && nx<8 && ny<8 && nx>=0 && ny>=0){
                    if(ex != 0 && ey != 0 && opposite[ey][ex] == 1 && ex<8 && ey<8 && ex>=0 && ey>=0){
                        opposite[ey][ex] = 0;
                        turn[y][x] = 0;
                        turn[ny][nx] = 1;
                        print(whitePOS, blackPOS);
                        ey = 0;
                        ex = 0;
                        Score--;
                        Turn = false;
                    }
                }
                else if(skip == true){
                    Turn = false;
                    skiped++;
                    print(whitePOS, blackPOS);
                }
                skip = false;
            }
            else System.out.println("Invalid move!");
        }

    }
    void update(int[][] blacks, int[][] whites){
        whites = whitePOS;
        blacks = blackPOS;
        timeSkippedWhite = 0;
        timeSkippedBlack = 0;
        Boolean inProgress = true;
        Scanner input = new Scanner(System.in);
        System.out.println(
                "                                 Wellcome to Checkers!                                  \n" +
                "========================================================================================\n" +
                "  /€€€€€€  /€€   /€€ /€€€€€€€€   /€€€€€€  /€€    €€ /€€€€€€€€ /€€€€€€€   /€€€€€€   |€€€ \n"+
                " /€€__  €€| €€  | €€| €€_____/  /€€__  €€| €€   €€ | €€_____/| €€__  €€ /€€__  €€  |€€€ \n"+
                "| €€  \\__/| €€  | €€| €€       | €€  \\__/| €€  €€  | €€      | €€  \\ €€| €€  \\__/  |€€€ \n"+
                "| €€      | €€€€€€€€| €€€€€    | €€      | €€€€€€  | €€€€€   | €€€€€€€ | €€€€€€€\\  |€€€ \n"+
                "| €€      | €€__  €€| €€__/    | €€      | €€\\ €€  | €€__/   | €€__  €€ \\_____ €€  |__/ \n"+
                "| €€    €€| €€  | €€| €€       | €€      | €€ \\ €€ | €€      | €€  \\ €€ /€€   \\€€       \n"+
                "|  €€€€€€/| €€  | €€| €€€€€€€€ |  €€€€€€/| €€  \\ €€| €€€€€€€€| €€  | €€|  €€€€€€€/ /€€€ \n"+
                " \\_______/|__/  |__/|_________/ \\______/ |__/   \\_/|________/|__/  |__/ \\_______/  \\__/ \n"+
                "                                     -------------                                      \n"+
                "====================================|Yago Martínez|=====================================\n"+
                "                                     -------------                                      \n"+
                "\n=========================================RULES==========================================" +
                "\n 1. The game is played on a 8x8 board." +
                "\n 2. In order to move your piece you must first type the current position of the piece and then where you want to move it" +
                "\n    You must specify the movement in the following way \"(currentX, currentY) (newX, newY) \"" +
                "\n 3. If you want to jump over a pice of the opposite colour you must format your input in the following way:" +
                "\n    \"(currentX, currentY) (pieceYouWantToJumpX,pieceYouWantToJumpY) (newX, newY) \"" +
                "\n 4. You can play in three different modes:" +
                "\n    *Basic: It is the most basic of the three, it uses only the rules above" +
                "\n    *Intermediate: It is now possible to play with Kings, Kings can move horizontally and vertically and they are displayed as \"W\" or \"B\"" +
                "\n    *Advanced: It is now possible to play with Kings and with the huffling rule" +
                "\n 5. The game ends when one of the players has no more pieces on the board" +
                "\n Please type YES to check that you've read the rules ");
        String rules = input.nextLine();
        if(rules.equalsIgnoreCase("YES")){
            System.out.println("Please type the mode you want to play in: 1 is for Basic, 2 is for Intermediate and 3 is for Advanced");
            int mode = input.nextInt();
            if(mode == 1){
                print(whitePOS, blackPOS);
                while(inProgress == true){
                    if(inProgress == true){
                        System.out.println("Black it's your turn!");
                        basic(blacks, whites, white, timeSkippedBlack);
                        System.out.println(timeSkippedBlack);
                        if(white == 0 || black == 0 || timeSkippedBlack >= 3){
                            inProgress = false;
                        }
                    }
                    if(inProgress == true){
                        System.out.println("White it's your turn!");
                        basic(whites, blacks, black, timeSkippedWhite);
                        if(white == 0 || black == 0 || timeSkippedBlack >= 3){
                            inProgress = false;
                        }
                    }
                }
            }
            else if(mode == 2){
                print(whitePOS, blackPOS);
                while(inProgress == true){
                    if(inProgress == true){
                        System.out.println("Black it's your turn!");
                        Intermediate(blacks, whites, white, timeSkippedBlack);
                        if(white == 0 || black == 0 || timeSkippedBlack >= 3){
                            inProgress = false;
                        }
                    }
                    if(inProgress == true){
                        System.out.println("White it's your turn!");
                        Intermediate(whites, blacks, black, timeSkippedWhite);
                        if(white == 0 || black == 0 || timeSkippedBlack >= 3){
                            inProgress = false;
                        }
                    }
                }
            }
            else if(mode == 3){
                print(whitePOS, blackPOS);
                while(inProgress == true){
                    if(inProgress == true){
                        System.out.println("Black it's your turn!");
                        Advanced(blacks, whites, white, timeSkippedBlack);
                        if(white == 0 || black == 0 || timeSkippedBlack >= 3){
                            inProgress = false;
                        }
                    }
                    if(inProgress == true){
                        System.out.println("White it's your turn!");
                        Advanced(whites, blacks, black, timeSkippedWhite);
                        if(white == 0 || black == 0 || timeSkippedBlack >= 3){
                            inProgress = false;
                        }
                    }
                }
            }
            else{
                System.out.println("Invalid input");
            }
            if(BlackScore == 0 || timeSkippedWhite == 3){
                System.out.println("White wins!");
                WhiteWins++;
                System.out.println("White has won " + WhiteWins + " times");
            }
            else if(WhiteScore == 0 || timeSkippedBlack == 3){
                System.out.println("Black wins!");
                BlackWins++;
                System.out.println("White has won " + BlackWins + " times");
            }
            else{
                System.out.println("It's a tie!");
            }
        }
        else if(rules.equalsIgnoreCase("cat") || rules.equalsIgnoreCase("gato")){
            System.out.println("Easter EGG!!! \n" +
                    "Please enjoy this cat instead: \n");
            Desktop d = Desktop.getDesktop();
            try {
                d.browse(URI.create("https://www.youtube.com/watch?v=rizcWhjSROk&ab_channel=FuzzyMinotaur97"));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if(rules.equalsIgnoreCase("best rally ever") ){
            System.out.println("Easter EGG!!! \n");
            Desktop d = Desktop.getDesktop();
            try {
                d.browse(URI.create("https://www.youtube.com/watch?v=INwqyPct8qY&ab_channel=Icemanrider1"));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("The answer to the question was not YES, please read the rules and try again");
        }
    }
    public static void main(String[] args) {
        logic game = new logic();
        boolean playing = true;
        while (playing == true){game.allocate();
            game.update(game.blackPOS, game.whitePOS);
            System.out.println("Do you want to play again? Type YES if you do");
            Scanner input = new Scanner(System.in);
            String answer = input.nextLine();
            if(answer.equalsIgnoreCase("YES")){
                playing = true;
            }
            else if(answer.equalsIgnoreCase("ha ha no one cares")){
                Desktop d = Desktop.getDesktop();
                try {
                    d.browse(URI.create("https://www.youtube.com/watch?v=BLUkgRAy_Vo&ab_channel=hiimhaash"));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                playing = false;
            }
        }
    }
}
