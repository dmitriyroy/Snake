package com.codenjoy.dojo.snake.client;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2016 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.client.Direction;
import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.services.RandomDice;

import java.util.List;
import java.util.Map;

/**
 * User: your name
 */
public class YourSolver implements Solver<Board> {

    private static final String USER_NAME = "dmitriy.roy@ita.biz.ua";

    private Dice dice;
    private Board board;

    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;

        // получем змею
        List<Point> snake = board.getSnake();
        System.out.println("snake = " + snake);

        // получаем голову змеи с ее направлением
        // направление возможно потом пригодится, чтоб не пойти сам на себя
        Map<String,Point> head = board.getHead("");
        Point headPoint = null;
        for (String direction:new String[]{"UP","DOWN","LEFT","RIGHT"}){
            if(head.get(direction) != null){
                headPoint = head.get(direction);
            }
        }
        if(headPoint == null){
            headPoint = new PointImpl(-1,-1);
        }

        // получаем хвост
        Point tail = snake.get(snake.size()-1);

        // получаем яблоко
        Point apple = board.getApples().get(0);

        // получаем камень
        Point stone = board.getStones().get(0);

        // есть ли путь от головы к яблоку

            // есть путь от яблока к хвосту
            // идем за яблоком

            // нет пути от яблока к хвосту
            // идем за хвостом
        // если пути от головы к яблоку нет, то идем за хвостом



//        Point apple = board.getApples().get(0);
//        Point head = board.getHead();
//        Logger.logMsg(1, "qqqqqqqqqqqqqqqqqqqqwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
//        Head 
//        point.getX()
//        point.getY()

//        char[][] field = board.getField();
//
//        // found snake
//        int snakeHeadX = -1;
//        int snakeHeadY = -1;
//        for (int x = 0; x < field.length; x++) {
//            for (int y = 0; y < field.length; y++) {
//                char ch = field[x][y];
//                if (ch == Elements.HEAD_DOWN.ch() ||
//                        ch == Elements.HEAD_UP.ch() ||
//                        ch == Elements.HEAD_LEFT.ch() ||
//                        ch == Elements.HEAD_RIGHT.ch())
//                {
//                    snakeHeadX = x;
//                    snakeHeadY = y;
//                    break;
//
//                }
//            }
//            if (snakeHeadX != -1) {
//                break;
//            }
//        }
//
//        // нашли змейку
//        int appleX = -1;
//        int appleY = -1;
//        for (int x = 0; x < field.length; x++) {
//            for (int y = 0; y < field.length; y++) {
//                char ch = field[x][y];
//                if (ch == Elements.GOOD_APPLE.ch()) {
//                    appleX = x;
//                    appleY = y;
//                    break;
//
//                }
//            }
//            if (appleX != -1) {
//                break;
//            }
//        }
//
//        int dx = snakeHeadX - appleX;
//        int dy = snakeHeadY - appleY;

        int dx = headPoint.getX() - apple.getX();
        int dy = headPoint.getY() - apple.getY();

        if (dx < 0) {
            return Direction.RIGHT.toString();
        }
        if (dx > 0) {
            return Direction.LEFT.toString();
        }
        if (dy < 0) {
            return Direction.DOWN.toString();
        }
        if (dy > 0) {
            return Direction.UP.toString();
        }

        return Direction.UP.toString();
    }

    public static void main(String[] args) {
        start(USER_NAME, WebSocketRunner.Host.REMOTE);
    }

    public static void start(String name, WebSocketRunner.Host server) {
        try {
            WebSocketRunner.run(server, name,
                    new YourSolver(new RandomDice()),
                    new Board());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
