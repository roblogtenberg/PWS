package engine;


import input.InputHandler; 

import java.awt.*; 
import java.awt.event.KeyEvent; 
import java.awt.image.BufferedImage; 
import javax.swing.JFrame; 

/** 
 * Main class for the game 
 */ 
public class GameEngine extends JFrame 
{        
	private static final long serialVersionUID = 1L;
		
		boolean isRunning = true; 
        int fps = 30; 
        int windowWidth = 500; 
        int windowHeight = 500; 
        
        BufferedImage backBuffer; 
        Insets insets; 
        InputHandler input; 
        
        int x = 0; 
        int y = 0;
        
        public static void main(String[] args) 
        { 
                GameEngine game = new GameEngine(); 
                game.run(); 
                System.exit(0); 
        } 
        
        /** 
         * This method starts the game and runs it in a loop 
         */ 
        public void run() 
        { 
                initialize(); 
                
                while(isRunning) 
                { 
                        long time = System.currentTimeMillis(); 
                        
                        update(); 
                        draw(); 
                        
                        //  delay for each frame  -   time it took for one frame 
                        time = (1000 / fps) - (System.currentTimeMillis() - time); 
                        
                        if (time > 0) 
                        { 
                                try 
                                { 
                                        Thread.sleep(time); 
                                } 
                                catch(Exception e){} 
                        } 
                } 
                
                setVisible(false); 
        } 
        
        /** 
         * This method will set up everything need for the game to run 
         */ 
        void initialize() 
        { 
                setTitle("Game Tutorial"); 
                setSize(windowWidth, windowHeight); 
                setResizable(false); 
                setDefaultCloseOperation(EXIT_ON_CLOSE); 
                setVisible(true); 
                
                insets = getInsets(); 
                setSize(insets.left + windowWidth + insets.right, 
                                insets.top + windowHeight + insets.bottom); 
                
                backBuffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB); 
                input = new InputHandler(this); 
        } 
        
        /** 
         * This method will check for input, move things 
         * around and check for win conditions, etc 
         */ 
        void update() 
        { 
                if (input.isKeyDown(KeyEvent.VK_RIGHT)) 
                { 
                        x += 5; 
                } 
                if (input.isKeyDown(KeyEvent.VK_LEFT)) 
                { 
                        x -= 5; 
                } 
                if (input.isKeyDown(KeyEvent.VK_UP)) 
                { 
                        y -= 5; 
                } 
                if (input.isKeyDown(KeyEvent.VK_DOWN)) 
                { 
                        y += 5; 
                } 
        } 
        
        /** 
         * This method will draw everything 
         */ 
        void draw() 
        {               
                Graphics g = getGraphics(); 
                
                Graphics bbg = backBuffer.getGraphics(); 
                
                bbg.setColor(Color.WHITE); 
                bbg.fillRect(0, 0, windowWidth, windowHeight); 
                
                bbg.setColor(Color.BLACK); 
                bbg.drawOval(x, y, 20, 20); 
                
                g.drawImage(backBuffer, insets.left, insets.top, this); 
        } 
} 