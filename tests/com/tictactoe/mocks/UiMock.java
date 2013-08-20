package com.tictactoe.mocks;

import com.tictactoe.Board.Board;
import com.tictactoe.UserInterface.UserInterface;

import java.util.Scanner;

public class UiMock implements UserInterface {
    public Scanner input;

    public UiMock(Readable reader) {
        input = new Scanner(reader);
    }

    public void welcomeMessage() {
        System.out.println();
    }

    public void endGame() {
        System.out.println();
    }

    public String askPlayerOneOption() {
        return "h";
    }

    public String askPlayerTwoOption() {
        return "h";
    }

    public int askBoardOption() {
        return 3;
    }

    public String getPlayerOptionInput() {
        return "";
    }

    public String getBoardOptionInput() {
        return "";
    }

    public boolean askContinue(){
        System.out.println();
        return false;
    }

    public int askPlayerMove(){
        System.out.println();
        return Integer.parseInt(input.nextLine());
    }

    public void printBoard(Board board){
       System.out.println();
    }

    public void displayResult(String result) {
        System.out.println();
    }
}
