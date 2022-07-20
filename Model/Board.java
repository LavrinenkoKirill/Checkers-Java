package Model;
import java.awt.Point;
import java.util.LinkedList;

public class Board {
    private final int BOARD_SIZE = 8;
    private int white_counter = 12;
    private int black_counter = 12;
    public static final boolean BLACK = true;
    public static final boolean WHITE = false;
    public static final int FREE = 0;
    public static final int BLACK_PLAYER = 1;
    public static final int WHITE_PLAYER = 2;
    protected Cell[][] board = new Cell[BOARD_SIZE][BOARD_SIZE];
    protected LinkedList<Move> matchHistory;
    protected boolean  move = WHITE;
    public static final int W_WIN = 1;
    public static final int B_WIN = 2;
    public static final int CONTINUE = 3;

    public Board() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Cell();
                board[i][j].row = i;
                board[i][j].column = j;
                if (i % 2 == 0 && j % 2 == 1) {
                    board[i][j].colour = BLACK;
                }
                else if (i % 2 == 1 && j % 2 == 0){
                    board[i][j].colour = BLACK;
                }
                else {
                    board[i][j].colour = WHITE;
                }
            }
        }

        matchHistory = new LinkedList<>();
    }


    public void startingPosition() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (i != 3 && i != 4 && board[i][j].colour == BLACK) {
                    if (i < 3) {
                        board[i][j].state = BLACK_PLAYER;
                    } else {
                        board[i][j].state = WHITE_PLAYER;
                    }
                }
            }
        }
    }

    private int checkDistance(Cell move1, Cell move2, Cell destination){
        if (move1.row == destination.row && move1.column == destination.column) {
            return 0;
        }
        else if (move2.row == destination.row && move2.column == destination.column) {
            return 1;
        }
        else {
            return -1;
        }
    }

    private void doQueenMove(Cell source,Cell destination){
        int enemy = -1;
        int player = -1;
        if (move == WHITE)
        {
            player = WHITE_PLAYER;
            enemy = BLACK_PLAYER;
        }
        else if (move == BLACK) {
            player = BLACK_PLAYER;
            enemy = WHITE_PLAYER;
        }

        int i = source.row;
        for (int j = source.column; j >= 0; j--) {
            if (i >= BOARD_SIZE) break;
            if (board[i][j].state == enemy && j != 0 && i != 7) {
                if (board[i + 1][j - 1].state == FREE) {
                    if (checkDistance(board[i + 1][j - 1], board[i + 1][j - 1], destination) >= 0) {
                        if (move == WHITE) black_counter--;
                        else if (move == BLACK) white_counter--;
                        board[i + 1][j - 1].state = player;
                        board[i + 1][j - 1].queen = true;
                        source.queen = false;
                        source.state = FREE;
                        board[i][j].state = FREE;
                        board[i][j].queen = false;
                    }
                    else {
                        if (checkEnemies(board[i + 1][j - 1], destination, 0)){
                            if (move == WHITE) black_counter--;
                            else if (move == BLACK) white_counter--;
                            source.queen = false;
                            source.state = FREE;
                            board[i][j].state = FREE;
                            board[i][j].queen = false;
                            cutEnemies(board[i + 1][j - 1],destination);
                        }
                    }
                }
            }
            if (board[i][j].state == FREE && checkDistance(board[i][j],board[i][j],destination)>= 0){
                board[i][j].state = player;
                board[i][j].queen = true;
                source.queen = false;
            }
            i++;

        }
        i = source.row;
        for (int j = source.column; j < BOARD_SIZE; j++) {
            if (i >= BOARD_SIZE) break;
            if (board[i][j].state == enemy && i != 7 && j != 7) {
                if (board[i + 1][j + 1].state == FREE){
                    if (checkDistance(board[i + 1][j + 1],board[i + 1][j + 1],destination) >= 0) {
                        if (move == WHITE) black_counter--;
                        else if (move == BLACK) white_counter--;
                        board[i + 1][j + 1].state = player;
                        board[i + 1][j + 1].queen = true;
                        source.state = FREE;
                        source.queen = false;
                        board[i][j].state = FREE;
                        board[i][j].queen = false;
                    }
                    else if (checkEnemies(board[i + 1][j + 1], destination, 1)) {
                        if (move == WHITE) black_counter--;
                        else if (move == BLACK) white_counter--;
                        source.state = FREE;
                        source.queen = false;
                        board[i][j].state = FREE;
                        board[i][j].queen = false;
                        cutEnemies(board[i + 1][j + 1],destination);
                    }

                }
            }
            if (board[i][j].state == FREE && checkDistance(board[i][j],board[i][j],destination)>= 0){
                board[i][j].state = player;
                board[i][j].queen = true;
                source.queen = false;
                source.state = FREE;
            }
            i++;
        }

        i = source.row;
        for (int j = source.column;j >= 0;j--){
            if (i <= 0) break;
            if (board[i][j].state == enemy && j != 0){
                if (board[i - 1][j - 1].state == FREE) {
                    if (checkDistance(board[i - 1][j - 1],board[i - 1][j - 1],destination)>= 0) {
                        if (move == WHITE) black_counter--;
                        else if (move == BLACK) white_counter--;
                        board[i - 1][j - 1].state = player;
                        board[i - 1][j - 1].queen = true;
                        source.queen = false;
                        board[i][j].state = FREE;
                        board[i][j].queen = false;
                    }
                    else if (checkEnemies(board[i - 1][j - 1], destination, 2)){
                        if (move == WHITE) black_counter--;
                        else if (move == BLACK) white_counter--;
                        source.queen = false;
                        board[i][j].state = FREE;
                        board[i][j].queen = false;
                        cutEnemies(board[i - 1][j - 1],destination);
                    }
                }
            }
            if (board[i][j].state == FREE && checkDistance(board[i][j],board[i][j],destination)>= 0){
                board[i][j].state = player;
                board[i][j].queen = true;
                source.queen = false;
            }
            i--;
        }

        i = source.row;
        for (int j = source.column;j < BOARD_SIZE;j++){
            if (i <= 0) break;
            if (board[i][j].state == enemy && j != 7){
                if (board[i - 1][j + 1].state == FREE) {
                    if (checkDistance(board[i - 1][j + 1], board[i - 1][j + 1], destination) >= 0){
                        if (move == WHITE) black_counter--;
                        else if (move == BLACK) white_counter--;
                        board[i - 1][j + 1].state = player;
                        source.queen = false;
                        board[i - 1][j + 1].queen = true;
                        board[i][j].state = FREE;
                        board[i][j].queen = false;

                    }
                    else if (checkEnemies(board[i - 1][j + 1], destination, 3)){
                        if (move == WHITE) black_counter--;
                        else if (move == BLACK) white_counter--;
                        source.queen = false;
                        board[i][j].state = FREE;
                        board[i][j].queen = false;
                        cutEnemies(board[i - 1][j + 1],destination);
                    }
                }
            }
            if (board[i][j].state == FREE && checkDistance(board[i][j],board[i][j],destination)>= 0){
                board[i][j].state = player;
                board[i][j].queen = true;
                source.queen = false;
            }
            i--;
        }
    }

    private boolean checkQueenMove(Cell source,Cell destination){

        Point friend = new Point(-1,-1);
        int enemy = -1;
        int player = -1;
        if (move == WHITE) {
            player = WHITE_PLAYER;
            enemy = BLACK_PLAYER;
        }
        else if (move == BLACK) {
            player = BLACK_PLAYER;
            enemy = WHITE_PLAYER;
        }
            int i = source.row;
            for (int j = source.column; j >= 0; j--) {
                if (i >= BOARD_SIZE) break;
                if (board[i][j].state == enemy && j != 0 && i != 7) {
                    if (board[i + 1][j - 1].state == FREE) {
                        if (checkDistance(board[i + 1][j - 1], board[i + 1][j - 1], destination) >= 0) {
                            if (friend.x == -1 || friend.y == -1) return true;
                            else {
                                return i < friend.x && j > friend.y;
                            }
                        }
                        else {
                            if (!checkEnemies(board[i + 1][j - 1], destination, 0)) break;
                            else return true;
                        }
                    }
                }

                if (board[i][j].state == player && checkDistance(board[i][j],board[i][j],source) == -1){
                    if (friend.x == -1 && friend.y == -1){
                        friend.x = board[i][j].row;
                        friend.y = board[i][j].column;
                    }
                }

                if (board[i][j].state == FREE && checkDistance(board[i][j],board[i][j],destination)>= 0){
                    if (friend.x == -1 && friend.y == -1) return true;
                    else {
                        return i < friend.x && j > friend.y;
                    }
                }
                i++;

            }
            i = source.row;
            friend.x = -1;
            friend.y = -1;
            for (int j = source.column; j < BOARD_SIZE; j++) {
                if (i >= BOARD_SIZE) break;
                if (board[i][j].state == enemy && i != 7 && j != 7) {
                    if (board[i + 1][j + 1].state == FREE){
                        if (checkDistance(board[i + 1][j + 1],board[i + 1][j + 1],destination) >= 0) {
                            if (friend.x == -1 && friend.y == -1) return true;
                            else {
                                return i < friend.x && j < friend.y;
                            }
                        }
                        else {
                            if (!checkEnemies(board[i + 1][j + 1], destination, 1)) break;
                            else return true;
                        }
                    }
                }

                if (board[i][j].state == player && checkDistance(board[i][j],board[i][j],source) == -1){
                    if (friend.x == -1 && friend.y == -1){
                        friend.x = board[i][j].row;
                        friend.y = board[i][j].column;
                    }
                }

                if (board[i][j].state == FREE && checkDistance(board[i][j],board[i][j],destination)>= 0){
                    if (friend.x == -1 && friend.y == -1) return true;
                    else {
                        return i < friend.x && j < friend.y;
                    }
                }
                i++;
            }

            i = source.row;
            friend.x = -1;
            friend.y = -1;
            for (int j = source.column;j >= 0;j--){
                if (i < 0) break;
                if (board[i][j].state == enemy && i != 0 && j!=0){
                    if (board[i - 1][j - 1].state == FREE) {
                        if (checkDistance(board[i - 1][j - 1],board[i - 1][j - 1],destination)>= 0) {
                            if (friend.x == -1 && friend.y == -1) return true;
                            else {
                                return i > friend.x && j > friend.y;
                            }
                        }
                        else {
                            if (!checkEnemies(board[i - 1][j - 1], destination, 2)) break;
                            else return true;
                        }
                    }
                }

                if (board[i][j].state == player && checkDistance(board[i][j],board[i][j],source) == -1){
                    if (friend.x == -1 && friend.y == -1){
                        friend.x = board[i][j].row;
                        friend.y = board[i][j].column;
                    }
                }

                if (board[i][j].state == FREE && checkDistance(board[i][j],board[i][j],destination)>= 0){
                    if (friend.x == -1 && friend.y == -1) return true;
                    else {
                        return i > friend.x && j > friend.y;
                    }
                }
                i--;
            }

            i = source.row;
            friend.x = -1;
            friend.y = -1;
            for (int j = source.column;j < BOARD_SIZE;j++){
                if (i < 0) break;
                if (board[i][j].state == enemy && j != 7 && i != 0){
                    if (board[i - 1][j + 1].state == FREE) {
                        if (checkDistance(board[i - 1][j + 1], board[i - 1][j + 1], destination) >= 0) {
                            if (friend.x == -1 && friend.y == -1) return true;
                            else {
                                return i > friend.x && j < friend.y;
                            }
                        }
                        else {
                            if (!checkEnemies(board[i - 1][j + 1], destination, 3))break;
                            else return true;
                        }
                    }
                }

                if (board[i][j].state == player && checkDistance(board[i][j],board[i][j],source) == -1){
                    if (friend.x == -1 && friend.y == -1){
                        friend.x = board[i][j].row;
                        friend.y = board[i][j].column;
                    }
                }

                if (board[i][j].state == FREE && checkDistance(board[i][j],board[i][j],destination)>= 0){
                    if (friend.x == -1 && friend.y == -1) return true;
                    else {
                        return i > friend.x && j < friend.y;
                    }
                }
                i--;
            }
        return false;
    }

    private boolean checkEnemies(Cell source,Cell destination,int side){

        if (checkDistance(source,source,destination) >= 0) {
            return true;
        }

        int enemy = -1;
        if (move == WHITE) enemy = BLACK_PLAYER;
        else if (move == BLACK) enemy = WHITE_PLAYER;
        if (source.row > 1 && source.column > 1) {
            if (board[source.row - 1][source.column - 1].state == enemy && board[source.row - 2][source.column - 2].state == FREE && side != 1) {
                if (checkDistance(board[source.row - 2][source.column - 2], board[source.row - 2][source.column - 2], destination) >= 0) {
                    return true;
                }
                else {
                    if (checkEnemies(board[source.row - 2][source.column - 2], destination, 2)){
                        return checkEnemies(board[source.row - 2][source.column - 2], destination, 2);
                    }
                }
            }
        }
        if (source.row > 1 && source.column < 6) {
            if (board[source.row - 1][source.column + 1].state == enemy && board[source.row - 2][source.column + 2].state == FREE && side != 0) {
                if (checkDistance(board[source.row - 2][source.column + 2], board[source.row - 2][source.column + 2], destination) >= 0) {
                    return true;
                }
                else {
                    if (checkEnemies(board[source.row - 2][source.column + 2], destination, 3)) {
                        return checkEnemies(board[source.row - 2][source.column + 2], destination, 3);
                    }
                }
            }
        }

        if (source.row < 6 && source.column < 6) {
            if (board[source.row + 1][source.column + 1].state == enemy && board[source.row + 2][source.column + 2].state == FREE && side != 2) {
                if (checkDistance(board[source.row + 2][source.column + 2], board[source.row + 2][source.column + 2], destination) >= 0) {
                    return true;
                }
                else {
                    if (checkEnemies(board[source.row + 2][source.column + 2], destination, 1)) {
                        return checkEnemies(board[source.row + 2][source.column + 2], destination, 1);
                    }
                }
            }
        }

        if (source.row < 6 && source.column > 1) {
            if (board[source.row + 1][source.column - 1].state == enemy && board[source.row + 2][source.column - 2].state == FREE && side != 3) {
                if (checkDistance(board[source.row + 2][source.column - 2], board[source.row + 2][source.column - 2], destination) >= 0) {
                    return true;
                }
                else {
                    if (checkEnemies(board[source.row + 2][source.column - 2], destination, 0)) {
                        return checkEnemies(board[source.row + 2][source.column - 2], destination, 0);
                    }
                }
            }
        }



        return false;
    }

    private void cutEnemies(Cell source,Cell destination){
        int enemy = -1;
        if (move == WHITE)
        {
            enemy = BLACK_PLAYER;
        }
        else if (move == BLACK) {
            enemy = WHITE_PLAYER;
        }

        if (source.row > 1 && source.column > 1) {
            if (board[source.row - 1][source.column - 1].state == enemy && board[source.row - 2][source.column - 2].state == FREE) {
                if (checkEnemies(board[source.row - 2][source.column - 2], destination, 2)) {
                    board[source.row - 1][source.column - 1].state = FREE;
                    board[source.row - 1][source.column - 1].queen = false;
                    if (move == WHITE) black_counter--;
                    else if (move == BLACK) white_counter--;
                    if (checkDistance(board[source.row - 2][source.column - 2], board[source.row - 2][source.column - 2], destination) >= 0) {
                        return;
                    }
                    else {
                        cutEnemies(board[source.row - 2][source.column - 2], destination);
                    }
                }
            }
        }



        if (source.row > 1 && source.column < 6) {
            if (board[source.row - 1][source.column + 1].state == enemy && board[source.row - 2][source.column + 2].state == FREE) {
                if (checkEnemies(board[source.row - 2][source.column + 2], destination, 3)) {
                    board[source.row - 1][source.column + 1].state = FREE;
                    board[source.row - 1][source.column + 1].queen = false;
                    if (move == WHITE) black_counter--;
                    else if (move == BLACK) white_counter--;
                    if (checkDistance(board[source.row - 2][source.column + 2], board[source.row - 2][source.column + 2], destination) >= 0) {
                        return;
                    }
                    else {
                        cutEnemies(board[source.row - 2][source.column + 2], destination);
                    }
                }
            }
        }


        if (source.row < 6 && source.column < 6) {
            if (board[source.row + 1][source.column + 1].state == enemy && board[source.row + 2][source.column + 2].state == FREE) {
                if (checkEnemies(board[source.row + 2][source.column + 2], destination, 1)) {
                    board[source.row + 1][source.column + 1].state = FREE;
                    board[source.row + 1][source.column + 1].queen = false;
                    if (move == WHITE) black_counter--;
                    else if (move == BLACK) white_counter--;
                    if (checkDistance(board[source.row + 2][source.column + 2], board[source.row + 2][source.column + 2], destination) >= 0) {
                        return;
                    }
                    else {
                        cutEnemies(board[source.row + 2][source.column + 2], destination);
                    }
                }
            }

        }


        if (source.row < 6 && source.column > 1) {
            if (board[source.row + 1][source.column - 1].state == enemy && board[source.row + 2][source.column - 2].state == FREE) {
                if (checkEnemies(board[source.row + 2][source.column - 2], destination, 0)) {
                    board[source.row + 1][source.column - 1].state = FREE;
                    board[source.row + 1][source.column - 1].queen = false;
                    if (move == WHITE) black_counter--;
                    else if (move == BLACK) white_counter--;
                    if (checkDistance(board[source.row + 2][source.column - 2], board[source.row + 2][source.column - 2], destination) < 0) {
                        cutEnemies(board[source.row + 2][source.column - 2], destination);
                    }
                }
            }

        }


    }


    public boolean isValidMove(Cell source,Cell destination){
        try {
            if (destination.colour == WHITE) {
                throw new Exception("Checkers can’t be moved to white cells");
            }

            if (destination.state != FREE) {
                throw new Exception("Destination cell is occupied by another checker");
            }

            if (move == WHITE) {
                if (source.state == BLACK_PLAYER) {
                    throw new Exception("Can not move opponents checkers");
                }

                if (!source.queen) {
                    if (source.row - destination.row == 1 && (destination.column - source.column == 1 || source.column - destination.column == 1)) {
                        if (source.column != 0 && source.column != 7) {
                            return checkDistance(board[source.row - 1][source.column + 1], board[source.row - 1][source.column - 1], destination) >= 0;
                        }
                        else if (source.column == 0){
                            return checkDistance(board[source.row - 1][source.column + 1], board[source.row - 1][source.column + 1], destination) >= 0;
                        }
                        else {
                            return checkDistance(board[source.row - 1][source.column - 1], board[source.row - 1][source.column - 1], destination) >= 0;
                        }
                    }
                        if (checkEnemies(source,destination,-1)){
                            return true;
                        }
                        else {
                            throw new Exception("Out of range of this checker");
                        }
                }
                else return checkQueenMove(source,destination);


            }

            if (move == BLACK) {
                if (source.state == WHITE_PLAYER) {
                    throw new Exception("Can not move opponents checkers");
                }
                if (!source.queen) {
                    if (destination.row - source.row == 1 && (destination.column - source.column == 1 || source.column - destination.column == 1)) {
                        if (source.column != 0 && source.column != 7) {
                            return checkDistance(board[source.row + 1][source.column + 1], board[source.row + 1][source.column - 1], destination) >= 0;
                        }
                        else if (source.column == 0){
                            return checkDistance(board[source.row + 1][source.column + 1], board[source.row + 1][source.column + 1], destination) >= 0;
                        }
                        else {
                            return checkDistance(board[source.row + 1][source.column - 1], board[source.row + 1][source.column - 1], destination) >= 0;
                        }
                    }

                    if (checkEnemies(source,destination,-1)){
                        return true;
                    }
                    else {
                        throw new Exception("Out of range of this checker");
                    }



                }
                else return checkQueenMove(source,destination);


            }
        }
        catch(Exception e){
      //    System.out.println(e.getMessage());
        }

        return false;
    }

    public void addMove(Move mv){
        this.matchHistory.add(mv);
    }

    public boolean isHistoryEmpty(){
        return this.matchHistory.isEmpty();
    }

    public int HistorySize(){
        return this.matchHistory.size();
    }

    public Move getHistoryMove(int index){
        return this.matchHistory.get(index);
    }
    public Move getLastMove(){return this.matchHistory.getLast();}

    public void doMove(Cell source,Cell destination){
        if (isWin() != CONTINUE) {
            System.out.println("Game have already finished");
        }
        else {
            if (isValidMove(source, destination)) {
                Point src = new Point(source.row,source.column);
                Point dest = new Point(destination.row,destination.column);
                Move mv = new Move(src,dest);
                this.addMove(mv);
                if (!source.queen) {
                    if ((destination.row - source.row != 1 && source.row - destination.row != 1) || (destination.column - source.column != 1 && source.column - destination.column != 1)) {
                        cutEnemies(source, destination);
                    }
                    source.state = FREE;

                    if (move == WHITE) {
                        destination.state = WHITE_PLAYER;
                        if (destination.row == 0) {
                            destination.queen = true;
                        }
                        move = BLACK;

                    } else if (move == BLACK) {
                        destination.state = BLACK_PLAYER;
                        if (destination.row == 7) {
                            destination.queen = true;
                        }
                        move = WHITE;
                    }
                }
                else {
                    doQueenMove(source,destination);
                    source.state = FREE;
                    source.queen = false;
                    destination.queen = true;
                    if (move == WHITE) {
                        destination.state = WHITE_PLAYER;
                        move = BLACK;
                    }
                    else if (move == BLACK){
                        destination.state = BLACK_PLAYER;
                        move = WHITE;
                    }


                }
                isWin();

            }

        }
    }

    public boolean canMove(){
        if (move == WHITE) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (board[i][j].state == WHITE_PLAYER) {
                        for (int x = 0; x < BOARD_SIZE; x++) {
                            for (int y = 0; y < BOARD_SIZE; y++) {
                                if (isValidMove(board[i][j], board[x][y])) return true;
                            }
                        }
                    }
                }
            }
        }
        if (move == BLACK){
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (board[i][j].state == BLACK_PLAYER) {
                        for (int x = 0; x < BOARD_SIZE; x++) {
                            for (int y = 0; y < BOARD_SIZE; y++) {
                                if (isValidMove(board[i][j], board[x][y])) return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    public int isWin() {
        if (white_counter == 0) {
            System.out.println("GAME OVER. BLACK PLAYER WINS");
            return B_WIN;
        }
        else if (black_counter == 0) {
            System.out.println("GAME OVER. WHITE PLAYER WINS");
            return W_WIN;
        }
       else if (move == WHITE && !canMove()) return B_WIN;
        else if (move == BLACK && !canMove()) return W_WIN;
        else return CONTINUE;

    }


    public int getBoardSize(){
        return BOARD_SIZE;
    }

    public Cell getCell(int row,int column){
        try {
            if (row < 0 || row > 7 || column < 0 || column > 7) {
                throw new Exception ("Out of bounds in getCell");
            }
        }
        catch(Exception e){
            System.out.println("error: " + e.getMessage());
            return null;
        }
        return board[row][column];
    }

    public boolean isWhiteMove(){
        return move == WHITE;
    }

    public Board getBoard(){
        return this;
    }

    public void setCell(int row,int column,int state,boolean queen){
        if (state == 0 ){
            board[row][column].state = FREE;
        }
        else if (state == 1){
            board[row][column].state = BLACK_PLAYER;
        }
        else if (state == 2){
            board[row][column].state = WHITE_PLAYER;
        }

        board[row][column].queen = queen;
    }

    public void setMove(boolean mv){
        if (mv){
            move = BLACK;
        }
        else move = WHITE;
    }


    public void setWhiteCounter(int number){
        white_counter = number;
    }

    public void setBlackCounter(int number){
        black_counter = number;
    }

    public int getWhiteCounter(){
        return white_counter;
    }
    public int getBlackCounter(){
        return black_counter;
    }

    public Board getPreviousTurn(){
        try {
            if (this.matchHistory.isEmpty()) {
                throw new Exception("Empty match history");
            }
            else {
                Board brd = new Board();
                brd.startingPosition();
                for (int i = 0; i < matchHistory.size() - 1; i++) {
                    brd.doMove(brd.board[matchHistory.get(i).getSource().x][matchHistory.get(i).getSource().y],brd.board[matchHistory.get(i).getDestination().x][matchHistory.get(i).getDestination().y]);
                }

                return brd;
            }
        }

        catch(Exception e){
            System.out.println("error: " + e.getMessage());
            return null;
        }
    }


    public Board getPreviousTurn(int index){
        try {
            if (this.matchHistory.isEmpty()) {
                throw new Exception("Empty match history");
            }
            else {
                Board brd = new Board();
                brd.startingPosition();
                for (int i = 0; i <= index; i++) {
                    brd.doMove(brd.board[matchHistory.get(i).getSource().x][matchHistory.get(i).getSource().y],brd.board[matchHistory.get(i).getDestination().x][matchHistory.get(i).getDestination().y]);
                }

                return brd;
            }
        }

        catch(Exception e){
            System.out.println("error: " + e.getMessage());
            return null;
        }
    }

    public Board clone(){
        Board brd = new Board();
        brd.startingPosition();
        for (int i = 0; i < matchHistory.size(); i++){
            brd.doMove(brd.getCell(matchHistory.get(i).getSourceX(),matchHistory.get(i).getSourceY()),brd.getCell(matchHistory.get(i).getDestinationX(),matchHistory.get(i).getDestinationY()));
        }

        return brd;
    }



}
