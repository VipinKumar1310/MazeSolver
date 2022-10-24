/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project;

import java.util.*;

public class DFS {
    public  static boolean searchPath(int [] [] maze, int x, int y, List<Integer> list) {
        if(maze[x][y] == 9) {
            list.add(x);
            list.add(y);
            return true;
        }
        
        if(maze[x][y] == 0) {
            maze[x][y] = -1;
            int [] dx = {0,0,1,-1};
            int [] dy = {1,-1,0,0};
            for(int i = 0; i < 4; i ++) {
                int X = dx[i] + x;
                int Y = dy[i] + y;
                if(X < 0 || Y < 0 || X >= maze.length || Y >= maze[0].length) {
                    continue;
                }
                if(searchPath(maze, X, Y, list)){
                    list.add(x);
                    list.add(y);
                    return true;
                }
            }
        }
        
        return false;
    }
}
