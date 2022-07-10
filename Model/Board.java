package Model;
import java.awt.Point;
public class Board {
    private final int board_size = 8;
    private int white_counter = 12;
    private int black_counter = 12;
    private final boolean black = true;
    private final boolean white = false;
    public static final int FREE = 0;
    public static final int BLACK_PLAYER = 1;
    public static final int WHITE_PLAYER = 2;
    protected Cell[][] board = new Cell[board_size][board_size];
    boolean move = white;
    public static final int W_WIN = 1;
    public static final int B_WIN = 2;
    public static final int CONTINUE = 3;

    public Board() {
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                board[i][j] = new Cell();
                board[i][j].row = i;
                board[i][j].column = j;
                if (i % 2 == 0 && j % 2 == 1) {
                    board[i][j].colour = black;
                }
                else if (i % 2 == 1 && j % 2 == 0){
                    board[i][j].colour = black;
                }
                else {
                    board[i][j].colour = white;
                }
            }
        }
        startingPosition();
    }

    public void startingPosition() {
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                if (i != 3 && i != 4 && board[i][j].colour == black) {
                    if (i < 3) {
                        board[i][j].state = BLACK_PLAYER;
                    } else {
                        board[i][j].state = WHITE_PLAYER;
                    }
                }
            }
        }
    }

    public int checkEnemies(Cell source,Cell destination, int counter){
        int flag = -1;
        if (move == white) {
            if (source.column != 0 && source.column != 7 && source.column != 1 && source.column != 6) {
                if (board[source.row - 1][source.column - 1].state == BLACK_PLAYER && board[source.row - 2][source.column - 2].state == FREE) {
                    if (checkDistance(board[source.row - 2][source.column - 2],board[source.row - 2][source.column - 2],destination) >= 0){
                        flag = 0;
                    }
                    else {
                        if (counter == 2) flag = -1;
                        else {
                            return checkEnemies(board[source.row - 2][source.column - 2],destination,counter + 1);
                        }
                    }
                }
                else if (board[source.row - 1][source.column + 1].state == BLACK_PLAYER && board[source.row - 2][source.column + 2].state == FREE) {

                    if (checkDistance(board[source.row - 2][source.column + 2],board[source.row - 2][source.column + 2],destination)>=0) {
                        flag = 1;
                    }
                    else {
                        if (counter == 2) flag = -1;
                        else {
                            return checkEnemies(board[source.row - 2][source.column + 2],destination,counter + 1);
                        }

                    }
                }
            }
            else if (source.column == 0 || source.column == 1){
                if (board[source.row - 1][source.column + 1].state == BLACK_PLAYER && board[source.row - 2][source.column + 2].state == FREE) {
                    if (checkDistance(board[source.row - 2][source.column + 2],board[source.row - 2][source.column + 2],destination)>=0) {
                        flag = 1;
                    }
                    else {
                        if (counter == 2) flag = -1;
                        else {
                            return checkEnemies(board[source.row - 2][source.column + 2],destination,counter + 1);
                        }

                    }
                }

            }
            else {
                if (board[source.row - 1][source.column - 1].state == BLACK_PLAYER && board[source.row - 2][source.column - 2].state == FREE) {
                    if (checkDistance(board[source.row - 2][source.column - 2],board[source.row - 2][source.column - 2],destination)>=0) {
                        flag = 0;
                    }
                    else {
                        if (counter == 2) flag = -1;
                        else {
                            return checkEnemies(board[source.row - 2][source.column - 2],destination,counter + 1);
                        }

                    }
                }
            }
        }
        if (move == black) {
            if (source.column != 0 && source.column != 7 && source.column != 1 && source.column != 6) {
                if (board[source.row + 1][source.column - 1].state == WHITE_PLAYER && board[source.row + 2][source.column - 2].state == FREE) {
                    if (checkDistance(board[source.row + 2][source.column - 2],board[source.row + 2][source.column - 2],destination) >= 0){
                        flag = 0;
                    }
                    else {
                        if (counter == 2) flag = -1;
                        else {
                            return checkEnemies(board[source.row + 2][source.column - 2],destination,counter + 1);
                        }
                    }
                }
                else if (board[source.row + 1][source.column + 1].state == WHITE_PLAYER && board[source.row + 2][source.column + 2].state == FREE) {
                    if (checkDistance(board[source.row + 2][source.column + 2],board[source.row + 2][source.column + 2],destination)>=0) {
                        flag = 1;
                    }
                    else {
                        if (counter == 2) flag = -1;
                        else {
                            return checkEnemies(board[source.row + 2][source.column + 2],destination,counter + 1);
                        }

                    }
                }
            }
            else if (source.column == 0 || source.column == 1){
                if (board[source.row + 1][source.column + 1].state == WHITE_PLAYER && board[source.row + 2][source.column + 2].state == FREE) {
                    if (checkDistance(board[source.row + 2][source.column + 2],board[source.row + 2][source.column + 2],destination)>=0) {
                        flag = 1;
                    }
                    else {
                        if (counter == 2) flag = -1;
                        else {
                            return checkEnemies(board[source.row + 2][source.column + 2],destination,counter + 1);
                        }

                    }
                }

            }
            else {
                if (board[source.row + 1][source.column - 1].state == WHITE_PLAYER && board[source.row + 2][source.column - 2].state == FREE) {
                    if (checkDistance(board[source.row + 2][source.column - 2],board[source.row + 2][source.column - 2],destination) >= 0){
                        flag = 0;
                    }
                    else {
                        if (counter == 2) flag = -1;
                        else {
                            return checkEnemies(board[source.row + 2][source.column - 2],destination,counter + 1);
                        }
                    }
                }

            }
        }
        return flag;
    }

    public int checkDistance(Cell move1, Cell move2, Cell destination){
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

    public void doQueenMove(Cell source,Cell destination){
        System.out.println("zashel");
        int enemy = -1;
        int player = -1;
        if (move == white)
        {
            player = WHITE_PLAYER;
            enemy = BLACK_PLAYER;
        }
        else if (move == black) {
            player = BLACK_PLAYER;
            enemy = WHITE_PLAYER;
        }

        int i = source.row;
        for (int j = source.column; j >= 0; j--) {
            if (i >= board_size) break;
            if (board[i][j].state == enemy && j != 0 && i != 7) {
                if (board[i + 1][j - 1].state == FREE) {
                    if (checkDistance(board[i + 1][j - 1], board[i + 1][j - 1], destination) >= 0) {
                        System.out.println("opachki");
                        if (move == white) black_counter--;
                        else if (move == black) white_counter--;
                        board[i + 1][j - 1].state = player;
                        board[i + 1][j - 1].queen = true;
                        source.queen = false;
                        source.state = FREE;
                        board[i][j].state = FREE;
                    }
                    else {
                        System.out.println("noooooo");
                        cutQueenEnemies(board[i + 1][j - 1],destination);
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
        for (int j = source.column; j < board_size; j++) {
            if (i >= board_size) break;
            if (board[i][j].state == enemy && i != 7 && j != 7) {
                if (board[i + 1][j + 1].state == FREE){
                    if (checkDistance(board[i + 1][j + 1],board[i + 1][j + 1],destination) >= 0) {
                        if (move == white) black_counter--;
                        else if (move == black) white_counter--;
                        board[i + 1][j + 1].state = player;
                        board[i + 1][j + 1].queen = true;
                        source.state = FREE;
                        source.queen = false;
                        board[i][j].state = FREE;
                    }
                    else cutQueenEnemies(board[i + 1][j + 1],destination);

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
            if (board[i][j].state == enemy && i != 0 && j!=0){
                if (board[i - 1][j - 1].state == FREE) {
                    if (checkDistance(board[i - 1][j - 1],board[i - 1][j - 1],destination)>= 0) {
                        if (move == white) black_counter--;
                        else if (move == black) white_counter--;
                        board[i - 1][j - 1].state = player;
                        board[i - 1][j - 1].queen = true;
                        source.queen = false;
                        board[i][j].state = FREE;
                    }
                    else cutQueenEnemies(board[i - 1][j - 1],destination);
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
        for (int j = source.column;j < board_size;j++){
            if (i <= 0) break;
            if (board[i][j].state == enemy && j != 7 && i != 0){
                if (board[i - 1][j + 1].state == FREE) {
                    if (checkDistance(board[i - 1][j + 1], board[i - 1][j + 1], destination) >= 0){
                        if (move == white) black_counter--;
                        else if (move == black) white_counter--;
                        board[i - 1][j + 1].state = player;
                        source.queen = false;
                        board[i - 1][j + 1].queen = true;
                        board[i][j].state = FREE;

                    }
                    else cutQueenEnemies(board[i - 1][j + 1],destination);
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

    public boolean checkQueenMove(Cell source,Cell destination){
        Point friend = new Point(-1,-1);
        int enemy = -1;
        int player = -1;
        if (move == white) {
            player = WHITE_PLAYER;
            enemy = BLACK_PLAYER;
        }
        else if (move == black) {
            player = BLACK_PLAYER;
            enemy = WHITE_PLAYER;
        }
            int i = source.row;
            System.out.println("cycle 1");
            for (int j = source.column; j >= 0; j--) {
                if (i >= board_size) break;
                if (board[i][j].state == enemy && j != 0 && i != 7) {
                    if (board[i + 1][j - 1].state == FREE) {
                        if (checkDistance(board[i + 1][j - 1], board[i + 1][j - 1], destination) >= 0) {
                            if (friend.x == -1 || friend.y == -1) return true;
                            else {
                                if (i < friend.x && j > friend.y) return true;
                                else {
                                    System.out.println("cho?");
                                    return false;
                                }
                            }
                        }
                        else {
                            if (checkQueenEnemies(board[i + 1][j - 1], destination, 0,0) == false) break;
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
                        System.out.println("zashel 1.1");
                        if (i < friend.x && j > friend.y) return true;
                        else return false;
                    }
                }
                i++;

            }
            i = source.row;
            friend.x = -1;
            friend.y = -1;
        System.out.println("cycle 2");
            for (int j = source.column; j < board_size; j++) {
                if (i >= board_size) break;
                if (board[i][j].state == enemy && i != 7 && j != 7) {
                    if (board[i + 1][j + 1].state == FREE){
                        if (checkDistance(board[i + 1][j + 1],board[i + 1][j + 1],destination) >= 0) {
                            if (friend.x == -1 && friend.y == -1) return true;
                            else {
                                System.out.println("zashel 2");
                                if (i < friend.x && j < friend.y) return true;
                                else return false;
                            }
                        }
                        else {
                            System.out.println("why you");
                            if (checkQueenEnemies(board[i + 1][j + 1],destination,1,0) == false) break;
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
                    System.out.println("norm");
                    if (friend.x == -1 && friend.y == -1) return true;
                    else {
                        System.out.println("zashel 2.2");
                        if (i < friend.x && j < friend.y) return true;
                        else return false;
                    }
                }
                i++;
            }

            i = source.row;
            friend.x = -1;
            friend.y = -1;
        System.out.println("cycle 3");
            for (int j = source.column;j >= 0;j--){
                if (i < 0) break;
                if (board[i][j].state == enemy && i != 0 && j!=0){
                    if (board[i - 1][j - 1].state == FREE) {
                        if (checkDistance(board[i - 1][j - 1],board[i - 1][j - 1],destination)>= 0) {
                            if (friend.x == -1 && friend.y == -1) return true;
                            else {
                                System.out.println("zashel 3");
                                if (i > friend.x && j > friend.y) return true;
                                else return false;
                            }
                        }
                        else if (checkQueenEnemies(board[i - 1][j - 1],destination,2,0) == false) break;
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
                        System.out.println("zashel 3.3");
                        if (i > friend.x && j > friend.y) return true;
                        else return false;
                    }
                }
                i--;
            }

            i = source.row;
            friend.x = -1;
            friend.y = -1;
        System.out.println("cycle 4");
            for (int j = source.column;j < board_size;j++){
                if (i < 0) break;
                if (board[i][j].state == enemy && j != 7 && i != 0){
                    if (board[i - 1][j + 1].state == FREE) {
                        if (checkDistance(board[i - 1][j + 1], board[i - 1][j + 1], destination) >= 0) {
                            if (friend.x == -1 && friend.y == -1) return true;
                            else {
                                System.out.println("zashel 4");
                                if (i > friend.x && j < friend.y) return true;
                                else return false;
                            }
                        }
                        else if (checkQueenEnemies(board[i-1][j+1],destination,3,0) == false)break;
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
                        System.out.println("zashel 4.4");
                        if (i > friend.x && j < friend.y) return true;
                        else return false;
                    }
                }
                i--;
            }
        return false;
    }

    public boolean checkQueenEnemies(Cell source,Cell destination,int side, int counter){
        int enemy = -1;
        if (move == white) enemy = BLACK_PLAYER;
        else if (move == black) enemy = WHITE_PLAYER;

            if (source.column != 0 && source.column != 7 && source.column != 1 && source.column != 6 && source.row != 0 && source.row !=1 && source.row != 6 && source.row != 7) {
                if (board[source.row - 1][source.column - 1].state == enemy && board[source.row - 2][source.column - 2].state == FREE && side != 1) {
                    if (checkDistance(board[source.row - 2][source.column - 2],board[source.row - 2][source.column - 2],destination) >= 0){
                        return true;
                    }
                    else {
                        if (counter == 3) return false;
                        else {
                            return checkQueenEnemies(board[source.row - 2][source.column - 2],destination,2,counter + 1);
                        }
                    }
                }
                 if (board[source.row - 1][source.column + 1].state == enemy && board[source.row - 2][source.column + 2].state == FREE && side != 0) {
                    if (checkDistance(board[source.row - 2][source.column + 2],board[source.row - 2][source.column + 2],destination)>=0) {
                        return true;
                    }
                    else {
                        if (counter == 3) return false;
                        else {
                            return checkQueenEnemies(board[source.row - 2][source.column + 2],destination,3,counter + 1);
                        }

                    }
                 }

                 if (board[source.row + 1][source.column + 1].state == enemy && board[source.row + 2][source.column + 2].state == FREE && side != 2){
                     if (checkDistance(board[source.row + 2][source.column + 2],board[source.row + 2][source.column + 2],destination)>=0) {
                         return true;
                     }
                     else {
                         if (counter == 3) return false;
                         else {
                             return checkQueenEnemies(board[source.row + 2][source.column + 2],destination,1,counter + 1);
                         }

                     }
                 }

                 if (board[source.row + 1][source.column - 1].state == enemy && board[source.row + 2][source.column - 2].state == FREE && side != 3){
                     if (checkDistance(board[source.row + 2][source.column - 2],board[source.row + 2][source.column - 2],destination)>=0) {
                         return true;
                     }
                     else {
                         if (counter == 3) return false;
                         else {
                             return checkQueenEnemies(board[source.row + 2][source.column - 2],destination,0,counter + 1);
                         }

                     }
                 }

            }

            if (source.row == 0 || source.row == 1) {
                if (board[source.row + 1][source.column - 1].state == enemy && board[source.row + 2][source.column - 2].state == FREE && side != 3){
                    if (checkDistance(board[source.row + 2][source.column - 2],board[source.row + 2][source.column - 2],destination)>=0) {
                        return true;
                    }
                    else {
                        if (counter == 2) return false;
                        else {
                            return checkQueenEnemies(board[source.row + 2][source.column - 2],destination,0,counter + 1);
                        }

                    }
                }

                if (board[source.row + 1][source.column + 1].state == enemy && board[source.row + 2][source.column + 2].state == FREE && side != 2){
                    if (checkDistance(board[source.row + 2][source.column + 2],board[source.row + 2][source.column + 2],destination)>=0) {
                        return true;
                    }
                    else {
                        if (counter == 3) return false;
                        else {
                            return checkQueenEnemies(board[source.row + 2][source.column + 2],destination,1,counter + 1);
                        }

                    }
                }


            }

            else if (source.row == 7 || source.row == 6){

                if (board[source.row - 1][source.column - 1].state == enemy && board[source.row - 2][source.column - 2].state == FREE && side != 1) {
                    if (checkDistance(board[source.row - 2][source.column - 2],board[source.row - 2][source.column - 2],destination) >= 0){
                        return true;
                    }
                    else {
                        if (counter == 3) return false;
                        else {
                            return checkQueenEnemies(board[source.row - 2][source.column - 2],destination,2,counter + 1);
                        }
                    }
                }

                if (board[source.row - 1][source.column + 1].state == enemy && board[source.row - 2][source.column + 2].state == FREE && side != 0) {
                    if (checkDistance(board[source.row - 2][source.column + 2],board[source.row - 2][source.column + 2],destination)>=0) {
                        return true;
                    }
                    else {
                        if (counter == 3) return false;
                        else {
                            return checkQueenEnemies(board[source.row - 2][source.column + 2],destination,3,counter + 1);
                        }

                    }
                }


            }



        return false;
    }

    public void cutQueenEnemies(Cell source,Cell destination){
        int enemy = -1;
        int player = -1;
        if (move == white)
        {
            enemy = BLACK_PLAYER;
            player = WHITE_PLAYER;
        }
        else if (move == black) {
            enemy = WHITE_PLAYER;
            player = BLACK_PLAYER;
        }

        if (source.column != 0 && source.column != 7 && source.column != 1 && source.column != 6 && source.row != 0 && source.row !=1 && source.row != 6 && source.row != 7) {
            if (board[source.row - 1][source.column - 1].state == enemy && board[source.row - 2][source.column - 2].state == FREE) {
                board[source.row - 1][source.column - 1].state = FREE;
                if (move == white) black_counter--;
                else if (move == black) white_counter--;
                if (checkDistance(board[source.row - 2][source.column - 2],board[source.row - 2][source.column - 2],destination) >= 0){
                    return;
                }
                else {
                    cutQueenEnemies(board[source.row - 2][source.column - 2],destination);
                }
            }
            else if (board[source.row - 1][source.column + 1].state == enemy && board[source.row - 2][source.column + 2].state == FREE) {
                board[source.row - 1][source.column + 1].state = FREE;
                if (move == white) black_counter--;
                else if (move == black) white_counter--;
                if (checkDistance(board[source.row - 2][source.column + 2],board[source.row - 2][source.column + 2],destination) >= 0){
                    return;
                }
                else {
                    cutQueenEnemies(board[source.row - 2][source.column + 2],destination);
                }
            }

            else if (board[source.row + 1][source.column + 1].state == enemy && board[source.row + 2][source.column + 2].state == FREE){
                board[source.row + 1][source.column + 1].state = FREE;
                if (move == white) black_counter--;
                else if (move == black) white_counter--;
                if (checkDistance(board[source.row + 2][source.column + 2],board[source.row + 2][source.column + 2],destination) >= 0){
                    return;
                }
                else {
                    cutQueenEnemies(board[source.row + 2][source.column + 2],destination);
                }
            }

            else if (board[source.row + 1][source.column - 1].state == enemy && board[source.row + 2][source.column - 2].state == FREE){
                board[source.row + 1][source.column - 1].state = FREE;
                if (move == white) black_counter--;
                else if (move == black) white_counter--;
                if (checkDistance(board[source.row + 2][source.column - 2],board[source.row + 2][source.column - 2],destination) >= 0){
                    return;
                }
                else {
                    cutQueenEnemies(board[source.row + 2][source.column - 2],destination);
                }
            }

        }

        else if (source.row == 0 || source.row == 1) {
            if (board[source.row + 1][source.column - 1].state == enemy && board[source.row + 2][source.column - 2].state == FREE){
                board[source.row + 1][source.column - 1].state = FREE;
                if (move == white) black_counter--;
                else if (move == black) white_counter--;
                if (checkDistance(board[source.row + 2][source.column - 2],board[source.row + 2][source.column - 2],destination) >= 0){
                    return;
                }
                else {
                    cutQueenEnemies(board[source.row + 2][source.column - 2],destination);
                }
            }

            else if (board[source.row + 1][source.column + 1].state == enemy && board[source.row + 2][source.column + 2].state == FREE){
                board[source.row + 1][source.column + 1].state = FREE;
                if (move == white) black_counter--;
                else if (move == black) white_counter--;
                if (checkDistance(board[source.row + 2][source.column + 2],board[source.row + 2][source.column + 2],destination) >= 0){
                    return;
                }
                else {
                    cutQueenEnemies(board[source.row + 2][source.column + 2],destination);
                }
            }


        }

        else if (source.row == 7 || source.row == 6){

            if (board[source.row - 1][source.column - 1].state == enemy && board[source.row - 2][source.column - 2].state == FREE) {
                board[source.row - 1][source.column - 1].state = FREE;
                if (move == white) black_counter--;
                else if (move == black) white_counter--;
                if (checkDistance(board[source.row - 2][source.column - 2],board[source.row - 2][source.column - 2],destination) >= 0){
                    return;
                }
                else {
                    cutQueenEnemies(board[source.row - 2][source.column - 2],destination);
                }
            }

            else if (board[source.row - 1][source.column + 1].state == enemy && board[source.row - 2][source.column + 2].state == FREE) {
                board[source.row - 1][source.column + 1].state = FREE;
                if (move == white) black_counter--;
                else if (move == black) white_counter--;
                if (checkDistance(board[source.row - 2][source.column + 2],board[source.row - 2][source.column + 2],destination) >= 0){
                    return;
                }
                else {
                    cutQueenEnemies(board[source.row - 2][source.column + 2],destination);
                }
            }


        }





    }


    public boolean isValidMove(Cell source,Cell destination){
        try {
            if (destination.colour == white) {
                Exception e = new Exception("Checkers can’t be moved to white cells");
                throw e;
            }

            if (destination.state != FREE) {
                System.out.println(destination.state);
                Exception e = new Exception("Destination cell is occupied by another checker");
                throw e;
            }

            if (move == white) {
                if (source.state == BLACK_PLAYER) {
                    Exception e = new Exception("Can not move opponents checkers");
                    throw e;
                }

                if (source.queen == false) {
                    if (source.row - destination.row == 1 && (destination.column - source.column == 1 || source.column - destination.column == 1)) {
                        if (source.column != 0 && source.column != 7) {
                            if (checkDistance(board[source.row - 1][source.column + 1], board[source.row - 1][source.column - 1], destination) >= 0) {
                                return true;
                            } else return false;
                        }
                        else if (source.column == 0){
                            if (checkDistance(board[source.row - 1][source.column + 1],board[source.row - 1][source.column + 1],destination) >= 0) {
                                return true;
                            }
                            else return false;
                        }
                        else {
                            if (checkDistance(board[source.row - 1][source.column - 1],board[source.row - 1][source.column - 1],destination) >= 0) {
                                return true;
                            }
                            else return false;
                        }
                    }

                     if (destination.row - source.row > 1 || source.row - destination.row > 1 ){
                        System.out.println("+");
                        if (checkEnemies(source,destination,0)>=0){
                            return true;
                        }
                        else {
                            Exception e = new Exception("Out of range of this checker");
                            throw e;
                        }
                    }
                }

                if (source.queen == true) {
                    return checkQueenMove(source,destination);
                }


            }

            if (move == black) {
                if (source.state == WHITE_PLAYER) {
                    Exception e = new Exception("Can not move opponents checkers");
                    throw e;
                }
                if (source.queen == false) {
                    if (destination.row - source.row == 1 && (destination.column - source.column == 1 || source.column - destination.column == 1)) {
                        if (source.column != 0 && source.column != 7) {
                            if (checkDistance(board[source.row + 1][source.column + 1], board[source.row + 1][source.column - 1], destination) >= 0) {
                                return true;
                            } else return false;
                        }
                        else if (source.column == 0){
                            if (checkDistance(board[source.row + 1][source.column + 1],board[source.row + 1][source.column + 1],destination) >= 0){
                                return true;
                            }
                            else return false;
                        }
                        else {
                            if (checkDistance(board[source.row + 1][source.column - 1],board[source.row + 1][source.column - 1],destination) >= 0){
                                return true;
                            }
                            else return false;
                        }
                    }
                    else if (destination.row - source.row > 1){
                        if (checkEnemies(source,destination,0) >= 0){
                            return true;
                        }
                        else {
                            Exception e = new Exception("Out of range of this checker");
                            throw e;
                        }
                    }


                }

                if (source.queen == true) {
                   return checkQueenMove(source,destination);
                }

            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    public void doMove(Cell source,Cell destination){
        if (isWin() != CONTINUE) {
            System.out.println("Game have already finished");
        }
        else {
            if (isValidMove(source, destination) == true) {
                if (source.queen == false) {
                    if ((destination.row - source.row == 1 || source.row - destination.row == 1) && (destination.column - source.column == 1 || source.column - destination.column == 1)) {
                    }
                    else {
                        cutEnemies(source, destination);
                    }
                    source.state = FREE;

                    if (move == white) {
                        destination.state = WHITE_PLAYER;
                        if (destination.row == 0) {
                            destination.queen = true;
                        }
                        move = black;

                    } else if (move == black) {
                        destination.state = BLACK_PLAYER;
                        if (destination.row == 7) {
                            destination.queen = true;
                        }
                        move = white;
                    }
                }
                else {
                    doQueenMove(source,destination);
                    source.state = FREE;
                    source.queen = false;
                    destination.queen = true;
                    if (move == white) {
                        destination.state = WHITE_PLAYER;
                        move = black;
                    }
                    else if (move == black){
                        destination.state = BLACK_PLAYER;
                        move = white;
                    }


                }
                isWin();

            }
            else {
                System.out.println("Incorrect move. Try again");
            }
        }
    }

    public void cutEnemies(Cell source, Cell destination){
        if (move == white) {
            if (source.column != 0 && source.column != 7 && source.column != 1 && source.column != 6) {
                if (board[source.row - 1][source.column - 1].state == BLACK_PLAYER && board[source.row - 2][source.column - 2].state == FREE) {
                    black_counter--;
                    board[source.row - 1][source.column - 1].state = FREE;
                    if (checkDistance(board[source.row - 2][source.column - 2],board[source.row - 2][source.column - 2],destination) >= 0){
                        destination.state = WHITE_PLAYER;
                        return;
                    }
                    else {
                        cutEnemies(board[source.row - 2][source.column - 2],destination);
                    }
                }
                else if (board[source.row - 1][source.column + 1].state == BLACK_PLAYER && board[source.row - 2][source.column + 2].state == FREE) {
                    black_counter--;
                    board[source.row - 1][source.column + 1].state = FREE;
                    if (checkDistance(board[source.row - 2][source.column + 2],board[source.row - 2][source.column + 2],destination)>=0) {
                        destination.state = WHITE_PLAYER;
                        return;
                    }
                    else {
                        cutEnemies(board[source.row - 2][source.column + 2],destination);
                    }
                }
            }
            else if (source.column == 0 || source.column == 1){
                if (board[source.row - 1][source.column + 1].state == BLACK_PLAYER && board[source.row - 2][source.column + 2].state == FREE) {
                    black_counter--;
                    board[source.row - 1][source.column + 1].state = FREE;
                    if (checkDistance(board[source.row - 2][source.column + 2],board[source.row - 2][source.column + 2],destination)>=0) {
                        destination.state = WHITE_PLAYER;
                        return;
                    }
                    else {
                        cutEnemies(board[source.row - 2][source.column + 2],destination);
                    }
                }
            }
            else {
                if (board[source.row - 1][source.column - 1].state == BLACK_PLAYER && board[source.row - 2][source.column - 2].state == FREE) {
                    black_counter--;
                    board[source.row - 1][source.column - 1].state = FREE;
                    if (checkDistance(board[source.row - 2][source.column - 2],board[source.row - 2][source.column - 2],destination) >= 0){
                        destination.state = WHITE_PLAYER;
                        return;
                    }
                    else {
                        cutEnemies(board[source.row - 2][source.column - 2],destination);
                    }
                }

            }
        }
        if (move == black) {
            if (source.column != 0 && source.column != 7 && source.column != 1 && source.column != 6) {
                if (board[source.row + 1][source.column - 1].state == WHITE_PLAYER && board[source.row + 2][source.column - 2].state == FREE) {
                    white_counter--;
                    board[source.row + 1][source.column - 1].state = FREE;
                    if (checkDistance(board[source.row + 2][source.column - 2],board[source.row + 2][source.column - 2],destination) >= 0){
                        destination.state = BLACK_PLAYER;
                        return;
                    }
                    else {
                        cutEnemies(board[source.row+2][source.column - 2],destination);
                    }
                }
                else if (board[source.row + 1][source.column + 1].state == WHITE_PLAYER && board[source.row + 2][source.column + 2].state == FREE) {
                    white_counter--;
                    board[source.row + 1][source.column + 1].state = FREE;
                    if (checkDistance(board[source.row + 2][source.column + 2],board[source.row + 2][source.column + 2],destination) >= 0){
                        destination.state = BLACK_PLAYER;
                        return;
                    }
                    else {
                        cutEnemies(board[source.row+2][source.column + 2],destination);
                    }
                }
            }
            else if (source.column == 0 || source.column == 1){
                if (board[source.row + 1][source.column + 1].state == WHITE_PLAYER && board[source.row + 2][source.column + 2].state == FREE) {
                    white_counter--;
                    board[source.row + 1][source.column + 1].state = FREE;
                    if (checkDistance(board[source.row + 2][source.column + 2],board[source.row + 2][source.column + 2],destination) >= 0){
                        destination.state = BLACK_PLAYER;
                        return;
                    }
                    else {
                        cutEnemies(board[source.row+2][source.column + 2],destination);
                    }
                }

            }
            else {
                if (board[source.row + 1][source.column - 1].state == WHITE_PLAYER && board[source.row + 2][source.column - 2].state == FREE) {
                    white_counter--;
                    board[source.row + 1][source.column - 1].state = FREE;
                    if (checkDistance(board[source.row + 2][source.column - 2],board[source.row + 2][source.column - 2],destination) >= 0){
                        destination.state = BLACK_PLAYER;
                        return;
                    }
                    else {
                        cutEnemies(board[source.row+2][source.column - 2],destination);
                    }
                }

            }
        }

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
        else{
            return CONTINUE;
        }
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                sb.append(board[i][j].row);
                sb.append("-");
                sb.append(board[i][j].column);
                sb.append(" ");
            }
            sb.append("\n");
        }
        String s = sb.toString();
        return s;
    }

    public int getBoard_size(){
        return board_size;
    }

    public Cell getCell(int row,int column){
        try {
            if (row < 0 || row > 7 || column < 0 || column > 7) {
                Exception e = new Exception ("Out of bounds in getCell");
                throw e;
            }
        }
        catch(Exception e){
            e.getMessage();
        }
        return board[row][column];
    }

    public boolean isWhiteMove(){
        if (move == white) return true;
        else return false;
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

        if (queen == true){
            board[row][column].queen = true;
        }
        else board[row][column].queen = false;
    }


}
