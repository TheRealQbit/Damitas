package Draughts.logic2d;

    /*
        Copyright (C) 2022 Yago Martínez aka TheRealQbit.
        SPDX-License-Identifier: GPL-3.0-or-later
        Original Repository: https://github.com/TheRealQbit/Damitas
     */

import java.awt.*;
import java.io.IOException;
import java.util.Scanner;
import java.net.URI;
import java.util.concurrent.TimeUnit;

public class logic {
    int white = 12;
    int black = 12;
    int [][] whitePOS = new int[8][8];
    int [][] blackPOS = new int[8][8];
    char[][] board = new char[8][8];
    int x, y, nx, ny, ex, ey, hx, hy, WhiteScore, BlackScore, WhiteWins, BlackWins, timeSkippedBlack, timeSkippedWhite, mode;
    boolean HuffleMode = false;
    boolean skip = false;

    //==================================================================================================================
    // Allocate Function
    // In this function we will assign the initial positions of the white and black pieces
    //==================================================================================================================
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
    //==================================================================================================================
    // Print Function
    // Each time we call this function the board char matrix is updated based on the values of the control matrices
    // whitePOS and blackPOS and then the board matrix is printed, as a result we have an updated version of the board
    //==================================================================================================================
    void print(int[][] whites, int[][] blacks) {
        whites = whitePOS;
        blacks = blackPOS;
        //whitePos and blackPos assignment into board
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
        //Board Print
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
    //==================================================================================================================
    // input Function
    // This function is the one in charge of the inout handling.
    // Inside this function we do three things:
    //  *Input: The function will read the input, and it will store it in an internal String variable.
    //  *Formatting: The function will check if the input fits some conditions, if so it will filter it through some
    //               filters that will assign the values in a way or another. If it doesn't meet the require format then
    //               it will ask you to enter a new input.
    //  *Value conversion: If the input format is correct the function will convert the string into an Integer value so
    //                     that it is usable.
    //==================================================================================================================
    void input(){
        String in, xs,xy, exs, eys, hxs, hys;
        Scanner input = new Scanner(System.in);
        //Input Read
        in = input.nextLine();
        //HuffleModeCheck
        if(HuffleMode == false){
            //Normal Input mode
            //Filters
            if(in.length() == 11 && in.charAt(0) == '(' && in.charAt(2) == ',' && in.charAt(4) == ')' && in.charAt(5) == ' ' && in.charAt(6) == '(' && in.charAt(8) == ',' && in.charAt(10) == ')'){
                //String to Integer Conversion
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
                //String to Integer Conversion
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
            else if(in.equalsIgnoreCase("Huff")){
                //Enter huff mode command Handler
                HuffleMode = true;
                System.out.println("Please enter the position you want to huff around:");
                input();
            }
            else if(in.length() == 4){
                //Skip handler
                if(in.equalsIgnoreCase("Skip")){
                    skip = true;
                }
            }
            else{
                System.out.println("Invalid input");
                input();
            }
        }
        //Huff mode input formatting
        else if (HuffleMode == true && mode == 3){
            if(in.length() == 5 && in.charAt(0) == '(' && in.charAt(2) == ',' && in.charAt(4) == ')'){
                hxs = in.substring(1,2);
                hys = in.substring(3,4);
                hx = Integer.parseInt(hxs);
                hy = Integer.parseInt(hys);
            }
            else{
                System.out.println("Invalid input");
                input();
            }
        }
        else{
            System.out.println("Invalid input");
            input();
        }

    }
    //==================================================================================================================
    // Basic Function
    // A modular function where all of the Basic game mode logic is stored.
    //==================================================================================================================
    void basic(int[][] turn, int[][] opposite , int Score, int skiped){
        boolean Turn = true;
        while (Turn){
            //input call
            input();
            //Valid movements check
            if(turn[y][x] == 1 && turn[ny][nx] == 0 && opposite[ny][nx] == 0 && nx-x != 0 && ny-y != 0 && nx<8 && ny<8 && nx>=0 && ny>=0 && (x-nx == 1 || x-nx == -1) && (y-ny == 1 || y-ny == -1)){
                //Movement has eat characteristics?
                if(ex != 0 && ey != 0 && opposite[ey][ex] == 1){
                    //Modular matrix modifier system
                    opposite[ey][ex] = 0;
                    turn[y][x] = 0;
                    turn[ny][nx] = 1;
                    print(whitePOS, blackPOS);
                    ey = 0;
                    ex = 0;
                    Score--;
                    Turn = false;
                }
                //It was a regular move
                else{
                    //Modular matrix modifier system
                    turn[y][x] = 0;
                    turn[ny][nx] = 1;
                    print(whitePOS, blackPOS);
                    Turn = false;
                }
            }
            //Skip handler
            else if(skip == true){
                if(turn==blackPOS){
                    timeSkippedBlack ++;
                }
                else timeSkippedWhite++;
                System.out.println("HasSkipped!");
                Turn = false;
                print(whitePOS, blackPOS);
            }
            else System.out.println("Invalid move!");
            skip = false;
        }

    }
    //==================================================================================================================
    // Intermediate Function
    // A modular function where all of the Intermediate game mode logic is stored.
    //==================================================================================================================
    void Intermediate(int[][] turn, int[][] opposite , int Score, int skiped){
        boolean Turn = true;
        while(Turn == true){
            //input call
            input();
            //Valid movements check with a regular piece
            if(turn[y][x] == 1 && turn[ny][nx] == 0 && opposite[ny][nx] == 0 && nx-x != 0 && ny-y != 0 && nx<8 && ny<8 && nx>=0 && ny>=0 && (x-nx == 1 || x-nx == -1) && (y-ny == 1 || y-ny == -1)){
                //Movement has eat characteristics?
                if(ex != 0 && ey != 0 && opposite[ey][ex] == 1){
                    //Modular matrix modifier system
                    opposite[ey][ex] = 0;
                    turn[y][x] = 0;
                    turn[ny][nx] = 1;
                    print(whitePOS, blackPOS);
                    ey = 0;
                    ex = 0;
                    Score--;
                    Turn = false;
                }
                //white Crowned handler
                else if(ny == 0 && turn == whitePOS){
                    //Modular matrix modifier system
                    turn[y][x] = 0;
                    turn[ny][nx] = 2;
                    print(whitePOS, blackPOS);
                    Turn = false;
                }
                //black Crowned handler
                else if(ny == 7 && turn == blackPOS){
                    //Modular matrix modifier system
                    turn[y][x] = 0;
                    turn[ny][nx] = 2;
                    print(whitePOS, blackPOS);
                    Turn = false;
                }
                //Regular movement
                else{
                    //Modular matrix modifier system
                    turn[y][x] = 0;
                    turn[ny][nx] = 1;
                    print(whitePOS, blackPOS);
                    Turn = false;
                }
            }
            //Valid movements check with a King
            else if(turn[y][x] == 2 && opposite[ny][nx] == 0 && nx<8 && ny<8 && nx>=0 && ny>=0 && ((x-nx == 1 || x-nx == -1)) || (y-ny == 1 || y-ny == -1)){
                //Movement has eat characteristics?
                if(ex != 0 && ey != 0 && opposite[ey][ex] == 1 && ex<8 && ey<8 && ex>=0 && ey>=0){
                    //Modular matrix modifier system
                    opposite[ey][ex] = 0;
                    turn[y][x] = 0;
                    turn[ny][nx] = 1;
                    print(whitePOS, blackPOS);
                    ey = 0;
                    ex = 0;
                    Score--;
                    Turn = false;
                }
                //Regular movement
                else{
                    //Modular matrix modifier system
                    turn[y][x] = 0;
                    turn[ny][nx] = 1;
                    print(whitePOS, blackPOS);
                    Turn = false;
                }
            }
            //Skip Handler
            else if(skip == true){
                if(turn==blackPOS){
                    timeSkippedBlack ++;
                }
                else timeSkippedWhite++;
                System.out.println("HasSkipped!");
                Turn = false;
                print(whitePOS, blackPOS);
            }
            else System.out.println("Invalid move!");
            skip = false;
        }

    }
    //==================================================================================================================
    // Advanced Function
    // A modular function where all of the Advanced game mode logic is stored.
    //==================================================================================================================
    void Advanced(int[][] turn, int[][] opposite , int Score, int skiped) throws InterruptedException {
        boolean Turn = true;
        while(Turn == true){
            //input call
            input();
            //Checks if huff mode is NOT active
            if(!HuffleMode){
                //Valid movements check with a regular piece
                if(turn[y][x] == 1 && turn[ny][nx] == 0 && opposite[ny][nx] == 0 && nx-x != 0 && ny-y != 0 && nx<8 && ny<8 && nx>=0 && ny>=0 && (x-nx == 1 || x-nx == -1) && (y-ny == 1 || y-ny == -1)){
                    //Movement has eat characteristics?
                    if(ex != 0 && ey != 0 && opposite[ey][ex] == 1){
                        //Modular matrix modifier system
                        opposite[ey][ex] = 0;
                        turn[y][x] = 0;
                        turn[ny][nx] = 1;
                        print(whitePOS, blackPOS);
                        ey = 0;
                        ex = 0;
                        Score--;
                        Turn = false;
                    }
                    //White Crowned handler
                    else if(ny == 0 && turn == whitePOS){
                        //Modular matrix modifier system
                        turn[y][x] = 0;
                        turn[ny][nx] = 2;
                        print(whitePOS, blackPOS);
                        Turn = false;
                    }
                    //Black Crowned handler
                    else if(ny == 7 && turn == blackPOS){
                        //Modular matrix modifier system
                        turn[y][x] = 0;
                        turn[ny][nx] = 2;
                        print(whitePOS, blackPOS);
                        Turn = false;
                    }
                    //Regular Movement
                    else{
                        //Modular matrix modifier system
                        turn[y][x] = 0;
                        turn[ny][nx] = 1;
                        print(whitePOS, blackPOS);
                        Turn = false;
                    }
                }
                //Valid movements check with a king
                else if(turn[y][x] == 2 && opposite[ny][nx] == 0 && nx<8 && ny<8 && nx>=0 && ny>=0 && ((x-nx == 1 || x-nx == -1)) || (y-ny == 1 || y-ny == -1)){
                    if(ex != 0 && ey != 0 && opposite[ey][ex] == 1 && ex<8 && ey<8 && ex>=0 && ey>=0){
                        //Modular matrix modifier system
                        opposite[ey][ex] = 0;
                        turn[y][x] = 0;
                        turn[ny][nx] = 1;
                        print(whitePOS, blackPOS);
                        ey = 0;
                        ex = 0;
                        Score--;
                        Turn = false;
                    }
                    //Regular Movement
                    else{
                        //Modular matrix modifier system
                        turn[y][x] = 0;
                        turn[ny][nx] = 1;
                        print(whitePOS, blackPOS);
                        Turn = false;
                    }
                }
                //Skip Handler
                else if(skip == true){
                    //Modular matrix modifier system
                    if(turn==blackPOS){
                        timeSkippedBlack ++;
                    }
                    else timeSkippedWhite++;
                    System.out.println("HasSkipped!");
                    Turn = false;
                    print(whitePOS, blackPOS);
                }
                else System.out.println("Invalid move!");
                skip = false;
            }
            //Huff Mode
            else{
                //Checks if the piece has moved there in the previous round
                if(hy != ny && hx != nx){
                    //Checks if there is a piece at that position and that it is a regular piece
                    if(opposite[hy][hx] == 1){
                        //checks if there is a piece of the person pointing out the error near the piece you want to huff
                        if(turn[hy+1][hx+1] != 0 || turn[hy-1][hx-1] != 0 || turn[hy+1][hx-1] != 0 || turn[hy-1][hx+1] != 0){
                            //Modular matrix modifier system
                            opposite[hy][hx] = 0;
                            Score --;
                            HuffleMode = false;
                            print(whitePOS, blackPOS);
                        }
                    }
                    //Checks if there is a piece at that position and that it is a king
                    else if(opposite[hy][hx] == 2){
                        //checks if there is a piece of the person pointing out the error near the piece you want to huff
                        if(turn[hy][hx+1] != 0 || turn[hy][hx-1] != 0 || turn[hy+1][hx] != 0 || turn[hy-1][hx] != 0 || turn[hy+1][hx+1] != 0 || turn[hy-1][hx-1] != 0 || turn[hy+1][hx-1] != 0 || turn[hy-1][hx+1] != 0){
                            //Modular matrix modifier system
                            opposite[hy][hx] = 0;
                            Score --;
                            HuffleMode = false;
                            print(whitePOS, blackPOS);
                        }
                    }
                    else System.out.println("The huff rule can't be applied there!");
                }
                else System.out.println("Invalid, the piece just moved there!");
                Scanner in = new Scanner(System.in);
                System.out.println("Do you want to try again or exit huff mode? Write yes to try again or write any other thing to exit huff mode: ");
                String input = in.nextLine();
                if(input.equalsIgnoreCase("Yes")){
                    HuffleMode = false;
                    System.out.println("You've exited huffle mode");
                    TimeUnit.SECONDS.sleep(1);
                    print(whitePOS, blackPOS);
                }
                else System.out.println("Enter again your position: ");
            }
        }
    }
    //==================================================================================================================
    // Update Function
    // You can think of this function as the back end of the game and as one that update each tick. Here everything is
    // managed, from the game-mode selected to the specific game loops. If you are familiarized with modern
    // game engines, specially with Unity, you will recognize this function.
    // In this function we will be calling first the game mode that you want to play in, then a loop will be exectued
    // based on your game mode. Inside this "game mode loops" the specific way that the game must be played in is
    // handled, from the rules to when the game must end.
    //==================================================================================================================
    void update(int[][] blacks, int[][] whites) throws InterruptedException {
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
                        "\n 3. If you want to jump over and capture a piece of the opposite color you must format your input in the following way:" +
                        "\n    \"(currentX, currentY) (pieceYouWantToJumpX,pieceYouWantToJumpY) (newX, newY) \"" +
                        "\n 4. If you can't move a piece in the board for whatever reason just write down \"SKIP\" and you will lose the turn but" +
                        "\n    BE AWARE that if you skip three times you will lose the round! So keep that in mind." +
                        "\n 4. The huffing rule is used when the opponent had the opportunity to capture a piece you can write down \"Huff\"." +
                        "\n    By doing so you enter Huffing Mode, when this mode is enabled you will be able to eliminate a piece of the opponent." +
                        "\n    How does the huffing mode work? Simple, once you are in huffing mode you just need to select a piece of opponent" +
                        "\n    and the program will automatically detect if the huffing rule can be applied on that specific case." +
                        "\n 4. You can play in three different modes:" +
                        "\n    *Basic: It is the most basic of the three, it uses only the rules above" +
                        "\n    *Intermediate: It is now possible to play with Kings, Kings can move horizontally and vertically and they are displayed as \"W\" or \"B\"" +
                        "\n    *Advanced: It is now possible to play with Kings and with the huffing rule." +
                        "\n 5. The game ends when one of the players has no more pieces on the board or when a player has skipped 3 times." +
                        "\n Please type YES to check that you've read the rules." +
                        "\n if you writte anything else the program will ask you if you want to play again!");
        String rules = input.nextLine();
        if(rules.equalsIgnoreCase("YES")){
            System.out.println("Please type the mode you want to play in: 1 is for Basic, 2 is for Intermediate and 3 is for Advanced");
            mode = input.nextInt();
            if(mode == 1){
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("You have selected Basic mode, please wait until the game loads");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                print(whitePOS, blackPOS);
                while(inProgress == true){
                    if(inProgress == true){
                        System.out.println("Black it's your turn!");
                        basic(blacks, whites, white, timeSkippedBlack);
                        //System.out.println(timeSkippedBlack);
                        if(white == 0 || black == 0 || timeSkippedBlack == 3){
                            inProgress = false;
                        }
                    }
                    if(inProgress == true){
                        System.out.println("White it's your turn!");
                        basic(whites, blacks, black, timeSkippedWhite);
                        if(white == 0 || black == 0 || timeSkippedWhite == 3){
                            inProgress = false;
                        }
                    }
                }
            }
            else if(mode == 2){
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("You have selected Intermediate mode, please wait until the game loads");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("You have selected Advanced mode, please wait until the game loads");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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
    //==================================================================================================================
    // Main Function
    // You can think of this function as the front end of the game. Here is where basically the game is told to display
    // a function based on the update function status, it is also the one in charge of terminating the game.
    //==================================================================================================================

    public static void main(String[] args) throws InterruptedException {
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
