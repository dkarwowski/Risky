package unittest;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

import risky.common.PerlinNoise;

public class PerlinNoiseTest {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final double FEATURE_SIZE = 10;
    
    public static void main(String[] args) throws IOException {
        PerlinNoise noise = new PerlinNoise(512, 512, 0.0001, 9);
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        
        for(int y = 0; y < HEIGHT; y++) {
            for(int x = 0; x < WIDTH; x++) {
                double value = noise.eval(x/FEATURE_SIZE, y/FEATURE_SIZE);
                if (value < 0)
                    value *= -1;
                int rgb = (int)(value*200);
                image.setRGB(x, y, rgb);
            }
        }
       
        // if testing, change file location to wherever you'd like
        ImageIO.write(image, "png", new File("D:\\noise.png"));
    }
}
