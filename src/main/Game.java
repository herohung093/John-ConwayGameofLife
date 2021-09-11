package main;

import java.util.ArrayList;
import java.util.List;

public class Game {
    int rows;
    int columns;
    Cell[][] board;

    public Game(List<Cell> cells, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        board = new Cell[rows][columns];
        initBoard(cells);
    }

    private void initBoard(List<Cell> cells) {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                Cell initializedCell = new Cell(x, y);
                if (cells.contains(initializedCell)) {
                    initializedCell.setAlive(true);
                }
                board[x][y] = initializedCell;
            }
        }
    }

    private int getAliveNeighboursForCell(Cell cell) {
        int aliveNeighbours = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (cell.getX() + x >= 0 && cell.getX() + x < 200)
                    if (cell.getY() + y >= 0 && cell.getY() + y < 200) {
                        Cell currentNeighbourCell = board[cell.getX() + x][cell.getY() + y];
                        if (currentNeighbourCell.isAlive && !currentNeighbourCell.equals(cell)) {
                            aliveNeighbours++;
                        }
                    }
            }
        }
        return aliveNeighbours;
    }

    public void nextState() {
        List<Cell> stateChangingCells = new ArrayList<>();
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                Cell currentCell = board[x][y];
                int currentNeighbours = getAliveNeighboursForCell(currentCell);
                if (currentCell.isAlive && currentNeighbours < 2) {
                    stateChangingCells.add(currentCell);
                }
                if (currentCell.isAlive && currentNeighbours > 3) {
                    stateChangingCells.add(currentCell);
                }
                if (!currentCell.isAlive && currentNeighbours == 3) {
                    stateChangingCells.add(currentCell);
                }
            }
        }
        stateChangingCells.forEach(cell -> cell.setAlive(!cell.isAlive));
    }

    public void printAliveCells() {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                if (board[x][y].isAlive) {
                    System.out.print(board[x][y] + " ");
                }
            }
        }
    }

}
