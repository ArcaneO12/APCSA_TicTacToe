public class TicTacToe {
    public static final int EMPTY = 0;
    public static final int PLAYER_X = 1;
    public static final int PLAYER_O = -1;

    public static boolean isValidMove(int[][] board, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length && board[row][col] == 0;
    }

    public static boolean makeMove(int[][] board, int row, int col, int player) {
        if (isValidMove(board, row, col)) {
            board[row][col] = player;
            return true;
        }
        return false;
    }

    public static int checkWin(int[][] board) {
        // If a player has 3 in a row, they win
        // Hint: Use a loop to check rows, columns, and diagonals
        // If the sum of a row, column, or diagonal is 3, return PLAYER_X
        // If the sum is -3, return PLAYER_O
        // If no one wins, return EMPTY

        // Check rows
        for (int row = 0; row < board.length; row++) {
            int sumOfRow = board[row][0] + board[row][1] + board[row][2];
            if (sumOfRow == 3)
                return PLAYER_X;
            if (sumOfRow == -3)
                return PLAYER_O;
        }

        // Check columns
        for (int column = 0; column < board[0].length; column++) {
            int sumOfColumn = board[0][column] + board[1][column] + board[2][column];
            if (sumOfColumn == 3)
                return PLAYER_X;
            if (sumOfColumn == -3)
                return PLAYER_O;
        }

        // Check diagonals
        int sumOfDiagonal = board[0][0] + board[1][1] + board[2][2];
        if (sumOfDiagonal == 3)
            return PLAYER_X;
        if (sumOfDiagonal == -3)
            return PLAYER_O;

        sumOfDiagonal = board[2][0] + board[1][1] + board[0][2];
        if (sumOfDiagonal == 3)
            return PLAYER_X;
        if (sumOfDiagonal == -3)
            return PLAYER_O;

        return EMPTY; // No winner
    }

    public static boolean isBoardFull(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == EMPTY)
                    return false;
            }
        }
        return true;
    }

    public static void printBoard(int[][] board) {
        System.out.println("  c 0 1 2");
        System.out.println("r -------");
        for (int row = 0; row < board.length; row++) {
            System.out.print(row + " | ");
            for (int col = 0; col < board[row].length; col++) {
                int cell = board[row][col];
                if (cell == PLAYER_X)
                    System.out.print("X ");
                else if (cell == PLAYER_O)
                    System.out.print("O ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = new int[3][3];
        int currentPlayer = PLAYER_X;
        boolean gameRunning = true;

        while (gameRunning) {
            printBoard(board);
            System.out.println("Current player: " + (currentPlayer == PLAYER_X ? "X" : "O"));
            // Simulate a move
            int row;
            int col;
            while (true) {
                row = (int) (Math.random() * 3);
                col = (int) (Math.random() * 3);
                // Uncomment the following lines to allow user input
                // System.out.print("Enter row (0-2): ");
                // row = scanner.nextInt();
                // System.out.print("Enter column (0-2): ");
                // col = scanner.nextInt();
                if (isValidMove(board, row, col))
                    break;
                // Uncomment the following line to print invalid move message
                // Prints a lot if you are using random moves
                System.out.println("Invalid move, try again.");
            }

            if (makeMove(board, row, col, currentPlayer)) {
                int winner = checkWin(board);
                if (winner != 0) {
                    printBoard(board);
                    System.out.println("Player " + (winner == PLAYER_X ? "X" : "O") + " wins!");
                    gameRunning = false;
                } else if (isBoardFull(board)) {
                    printBoard(board);
                    System.out.println("It's a draw!");
                    gameRunning = false;
                } else {
                    currentPlayer *= -1; // Switch player
                }
            }
        }
    }
}
