package matrixmultiplier;
import java.util.Arrays;
/**
 * @author Timothy Rine
 * @CSC 4103
 * @Programming Assignment #1
 * @Dr. Karki & Dr. Douglas
 */
public class MatrixMultiplier extends Thread
{
    public void run()
    {
        //To test which threads are being used
        /**
        System.out.println("The current thread being used is: " + getName());
        */
        
    }
    
    public static void Matrices()
    {
        int r11 = 2*1 + 4*6 + 7*3 + 6*8; 
    }
    
    public static void main(String[] args) 
    {
        int[][] p = 
                    {{1,4,7}, 
                     {6,5,2}, 
                     {3,7,0}, 
                     {8,1,9}};
        int[][] q = 
                    {{2,4,7,6},
                     {5,6,3,3},
                     {4,0,9,8}};                  
        
        Thread t1 = new Thread(new Runnable() 
        {
            public void run()
            {
                 int r11 = 2*1 + 4*6 + 7*3 + 6*8;    
            }           
        });
        t1.start();
        
        Thread t2 = new Thread(new Runnable() 
        {
            public void run()
            {
                 int r12 = 2*4 + 4*5 + 7*7 + 6*1;
            }           
        });
        t2.start();
        
        Thread t3 = new Thread(new Runnable() 
        {
            public void run()
            {
                 int r13 = 2*7 + 4*2 + 7*0 + 6*9;
            }           
        });
        t3.start();
        
        Thread t4 = new Thread(new Runnable() 
        {
            public void run()
            {
                 int r21 = 5*1 + 6*6 + 3*3 + 3*8;  
            }           
        });
        t4.start();
        
        Thread t5 = new Thread(new Runnable() 
        {
            public void run()
            {
                 int r22 =  5*4 + 6*5 + 3*7 + 3*1;
            }           
        });
        t5.start();
        
        Thread t6 = new Thread(new Runnable() 
        {
            public void run()
            {
                 int r23 = 5*7 + 6*2 + 3*0 + 3*9;
            }           
        });
        t6.start();
        
        Thread t7 = new Thread(new Runnable() 
        {
            public void run()
            {
                 int r31 = 4*1 + 0*6 + 9*3 + 8*8;
            }           
        });
        t7.start();
        
        Thread t8 = new Thread(new Runnable() 
        {
            public void run()
            {
                 int r32 = 4*4 + 0*5 + 9*7 + 8*1;
            }           
        });
        t8.start();
        
        Thread t9 = new Thread(new Runnable() 
        {
            public void run()
            {
                 int r33 = 4*7 + 0*2 + 9*0 + 8*9; 
            }           
        });
        t9.start();
        
        int r11 = 2*1 + 4*6 + 7*3 + 6*8;  
        int r12 = 2*4 + 4*5 + 7*7 + 6*1;
        int r13 = 2*7 + 4*2 + 7*0 + 6*9;
        int r21 = 5*1 + 6*6 + 3*3 + 3*8;  
        int r22 = 5*4 + 6*5 + 3*7 + 3*1;
        int r23 = 5*7 + 6*2 + 3*0 + 3*9;
        int r31 = 4*1 + 0*6 + 9*3 + 8*8;
        int r32 = 4*4 + 0*5 + 9*7 + 8*1;
        int r33 = 4*7 + 0*2 + 9*0 + 8*9; 
               
        int r[][] =    {{r11,r12,r13},
                        {r21,r22,r23},
                        {r31,r32,r33}};
               
        System.out.printf("R[i][j] = %n" + Arrays.toString(r[0]));
        System.out.printf("%n" + Arrays.toString(r[1]));
        System.out.printf("%n" + Arrays.toString(r[2]) + "%n%n");
        
        //print the threads being used
        /**
        System.out.println("The current threads in use are: " + Thread.currentThread().getName());
        */
    }
    
}
