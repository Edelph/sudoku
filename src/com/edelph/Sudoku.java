package com.edelph;

public class Sudoku {
    private int modulo;
    private int dimention ;
    private int[][] sudoku ;

    public Sudoku(int[][] sudoku, int dimention)  {
        try {
            this.setDimention(dimention);
            this.sudoku = sudoku;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean solve(){
        for ( int row = 0; row < this.dimention; row++ ) {
            for ( int column = 0; column < this.dimention; column++ ) {
                if ( this.sudoku[row][column] == 0 ){
                    for (int value = 1; value <= this.dimention ; value++) {
                        if (this.isRightPlace(row,column,value)){
                            this.sudoku[row][column] = value;
                            System.out.println( this.sudoku[row][column]);
                            if(solve())return true;
                            else this.sudoku[row][column] = 0;
                        }
                    }
                    System.out.println("fa");
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] getResult(){
        if(this.solve()){
            return this.sudoku;
        }
        return null;
    }
    public void showResult(){
        this.solve();
        for (int row = 0; row < this.dimention; row++) {
            if(row != 0 && (row%this.modulo == 0)){
                System.out.println("----------------");
            }
            for (int column = 0; column < this.dimention; column++) {
                if(column != 0 && (column%this.modulo == 0)){
                    System.out.print(" | ");
                }
                System.out.print(this.sudoku[row][column]);
            }
             System.out.println("");
        }
    }

    private boolean isAtRow(int row, int number){
        for( int column=0 ; column < this.dimention ; column++ ){
            if(number == this.sudoku[row][column]) return true;
        }
        return false;
    }

    private boolean isAtColumn(int column, int number){
        for( int row=0 ; row < this.dimention ; row++){
            if(number == this.sudoku[row][column]) return true;
        }
        return false;
    }

    private boolean isAtSquare(int row, int column, int number){
        int startRow = row - row % this.modulo;
        int startColumn = column - column % this.modulo;

        for (int r  = startRow; r < startRow + this.modulo ; r++) {
            for (int c = startColumn; c < startColumn + this.modulo ; c++) {
                if(this.sudoku[r][c] == number) return true;
            }
        }
        return false;
    }

    private boolean isRightPlace(int row, int column, int number){
        return (!isAtRow(row,number) && !isAtColumn(column, number) && !isAtSquare(row,column,number));
    }
    private void setDimention(int dimention) throws Exception {
        if(dimention == 0) throw new Exception("invalide dimention sudok");
        if(dimention%3 == 0){
            this.dimention = dimention;
            this.modulo = dimention/3;
        }
        else throw new Exception("invalide dimention sudok");
    }
}
