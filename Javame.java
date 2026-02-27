import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Javame extends JPanel implements ActionListener {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private Timer timer;
    
    public Javame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        timer = new Timer(16, this); // ~60 FPS
        timer.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }
    
    private void render(Graphics g) {
        // Clear screen
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        // Draw game elements here
        g.setColor(Color.WHITE);
        g.drawString("Welcome to Javame!", 350, 300);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); // Update the game state and render the next frame
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Javame");
        Javame game = new Javame();
        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}