/* Ah, the game of life.  This is a cellular automation simulator.  The rules
 * are very simple.  The board is a grid.  Each grid square can hold at most one
 * cell.  For a cell to survive from generation to generation it needs two or
 * three neighbors.  Too many neighbors causes the cell to starve.  Too few
 * causes an incurable case of ennui.  All is not lost.  If a grid square has
 * exactly three neighbors, a new cell is born, which is kind of strange when
 * you think about it.
 *
 * There are lots of variations on this game.  Technically, this is Conway's
 * Game of Life and is a B3/S23 game.  That is, cells survive with two or three
 * neighbors and are born with exactly 3 neighbors.  This game can also be
 * extended into the real domain or more diminutions.  We, however, with stick
 * with Conway's original automation.
 *
 * Below is a very simple implementation.  This is your chance to apply some of
 * the loop optimization techniques we've been talking about in class: 
 *  1) Remove loop inefficiencies
 *  2) Reduce procedure calls
 *  3) Reduce unnecessary memory references
 *  4) Loop unrolling
 * There are, of course, more optimizations that you can apply.  For example,
 * moving sequentially through memory is more efficient than jumping around.
 * We'll see why in Chapter 9.  

 * C stores 2D arrays Column Major Order.  That is to say that cell
 * A[0][WIDTH-1] is right next to A[1][0] in memory.  If you notice the nested
 * loops in evolve (below) I'm incrementing the column index, i, more quickly
 * than the row index, j.  Try reversing these and see what happens.
 *
 * The experimental set-up is implemented in a library called liblife.a  Since
 * it has solutions, I'm providing it as a binary in my home directory:
 *   /home/clausoa/lib/liblife.a
 * The provided make files already links in this library.
 *
 * There are two ways to run the test program: with our without a window.
 * Without a window, the program runs the test cases against the provided
 * evolution methods as quickly as possible.  After about 100 generations, it
 * stops and reports the average performance of each method.  
 *
 * If you favor something a little more animated, and I know you do, run it test
 * program with windowing.  This is the default.  This is basically the same
 * set-up, but it displays the current generation and the relative performance
 * of the provided methods.  The scores are jumpy for a little while, but they
 * eventually calm down.  When you close the window, the final scores are
 * displayed on the console.  
 *
 * See how fast you can make your code.
 *
 * I have one final piece of advice.  You are free to add as many test methods
 * as you want.  I would encourage you to do this.  Copy the code, give it a new
 * name, and add it to the library's suite of methods.  This way you can really
 * see if you are making improvements.  However, only have one method added when
 * you are done.  I'll record a grade for the worst performing evolve method you
 * submit.  
 *
 */

#include "liblife.h"
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>


/* Simple Life evolution.  This function, and this tree support functions,
 * implement Conway's game of life rules.  Your millage may vary, void where
 * prohibited. 
 */
// static int min(int x, int y) {
//    return x < y ? x : y;
// }
// static int max(int x, int y) {
//    return x < y ? y : x;
// }

/* Neighbors
 * Given the board b, this function counts the number of neighbors of 
 * cell-(i,j).
 *
 * This implementation over-counts the neighborhood.  The loop counts the
 * cell-(i,j) and then subtracts off one if it is alive. 
 *
 * The use of min and max ensure that the neighborhood is truncated at the edges
 * of the board.  
 */
// static int neighbors (board b, int i, int j)
// {
//    int n = 0;
//    int i_left  = max(0,i-1);
//    int i_right = min(HEIGHT, i+2);
//    int j_left  = max(0,j-1);
//    int j_right = min(WIDTH, j+2);
//    int ii, jj;

//    for (ii = i_left; ii < i_right; ++ii) {
//       for (jj = j_left; jj < j_right; ++jj) {
//          n += b[ii][jj];
//       }
//    }

//    return n - b[i][j];
// }

/* Evolve
 * This is a very simple evolution function.  It applies the rules of Conway's
 * Game of Live as written.  There are a lot of improvements that you can make.
 * Good luck beating Ada, she is really good.
 */

// void evolve(board prv, board nxt)
// {
//    int i, j;
//    int n;

//    for (j=0; j < WIDTH; ++j) {
//       for (i = 0; i < HEIGHT; ++i) {
//          n = neighbors(prv, i, j);
//          if (prv[i][j] && (n == 3 || n == 2))
//             nxt[i][j] = true;
//          else if (!prv[i][j] && (n == 3)) 
//             nxt[i][j] = true;
//          else
//             nxt[i][j] = false;
//       }
//    }
// }


/* Tests */
// void fivePoints(board prv, board nxt){

//    int i, j;
//    int n;
//    int w = WIDTH + 2;
   
//    for (i=0; i < w; ++i) {
//       for (j = 0; j < w; ++j) {
//          n = neighbors(prv, i, j);
         
         
//          if (prv[i][j] && (n == 3 || n == 2))
//             nxt[i][j] = true;
//          else if (!prv[i][j] && (n == 3)) 
//             nxt[i][j] = true;
//          else
//             nxt[i][j] = false;
//       }
//    }
// }

void evolve3(board prv, board nxt){
   int i, j;
   int counter;
   int k;

   // Top Left
   counter = (prv[1][0]) +   (prv[0][1]) +   (prv[1][1]);
   nxt[0][0] = (counter == 3 || (counter == 2 && prv[0][0]));

   // Top Right
   counter = (prv[0][1022]) +   (prv[1][1023]) +   (prv[1][1022]);
   nxt[0][1023] = (counter == 3 || (counter == 2 && prv[0][1023]));
   // Bottom Left
   counter = (prv[1022][0]) +   (prv[1023][1]) +   (prv[1022][1]);
   nxt[1023][0] = (counter == 3 || (counter == 2 && prv[1023][0]));
   // Bottom RIght
   counter = (prv[1023][1022]) +   (prv[1022][1023]) +   (prv[1022][1022]);
   nxt[1023][1023] = (counter == 3 || (counter == 2 && prv[1023][1023]));



   for (i=1; i < 1023; ++i) {
      for (j = 1; j < 1023; ++j) {

         // n = neighbors(prv, i, j);
         counter = prv[i-1][j-1] + prv[i][j-1] + prv[i+1][j-1] + prv[i-1][j] + prv[i+1][j] + prv[i-1][j+1] + prv[i][j+1] + prv[i+1][j+1];
         // seperator
         nxt[i][j] = ((counter == 3) | ((counter == 2) & (prv[i][j])));
      }
   }
   // Top Row
   for (k=1; k < 1023; ++k) {
      counter = prv[0][k-1] + prv[0][k+1] + prv[1][k+1] + prv[1][k] + prv[1][k-1];
      nxt[0][k] = (counter == 3 || (counter == 2 && prv[0][k]));
   }

   // Bottom Row
   for (k=1; k < 1023; ++k) {
      counter = prv[1023][k-1] + prv[1023][k+1] + prv[1022][k+1] + prv[1022][k] + prv[1022][k-1];
      nxt[1023][k] = (counter == 3 || (counter == 2 && prv[1023][k]));
   }

   //Left Row
   for (k=1; k < 1023; ++k) {
      counter = prv[k - 1][0] + prv[k + 1][0] + prv[k - 1][1] + prv[k][1] + prv[k + 1][1];
      nxt[k][0] = (counter == 3 || (counter == 2 && prv[k][0]));
   }

   // Right Row
   for (k=1; k < 1023; ++k) {
      counter = prv[k-1][1023] + prv[k+1][1023] + prv[k-1][1022] + prv[k][1022] + prv[k+1][1022];
      nxt[k][1023] = (counter == 3 || (counter == 2 && prv[k][1023]));
   }

}



/* Main 
 *
 */
int main(int argc, char* argv[])
{
   //   add_method("Simple", &evolve);
   //   add_method("FivePoints", &fivePoints);
     add_method("Evolve3", &evolve3);
     run_game();
     return EXIT_SUCCESS;
}



