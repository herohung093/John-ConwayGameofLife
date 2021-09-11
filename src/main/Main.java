package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        int[][] input= new int[200][200];
        Game myGame = new Game(readInput(), 200 , 200);
        System.out.println("Input cells");
        myGame.printAliveCells();
        System.out.println();
        for(int i= 0; i< 100; i++){
            myGame.nextState();
        }
        myGame.printAliveCells();
    }

    public static List<Cell> readInput() throws FileNotFoundException {
        List<Cell> cells = new ArrayList<>();
        File myFile = new File("input.txt");
        Scanner myReader = new Scanner(myFile);
        while(myReader.hasNext()){
            String data = myReader.nextLine().trim();
            Cell cell = new Cell(Character.getNumericValue( data.charAt(0)),Character.getNumericValue(data.charAt(2)));
            cells.add(cell);
        }
        return cells.stream().distinct().collect(Collectors.toList());
    }
}

