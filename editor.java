package homework;

import java.util.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class editor {
    public static void main(String[] args) {
        // Input the file name which is in your own file
        File file = new File("C:\\Users\\User\\Pictures\\Screenshots\\picture.png");
        BufferedImage img = null;

        try {
            // Try to read the image file
            img = ImageIO.read(file);
        } catch (IOException e) {e.printStackTrace(System.out);}
            // Handle any IOException (e.g., file not found)
            
        
        
        if (img != null) {
            // If the image was successfully loaded, display it
            //display(img);
             img = colorinImage(img);
            // img = toGrayScale(img);
            // img=resize(img,150);
            // img = pixelate(img, 15);
            // img = blur(img);
            // img = brithen(img,20);
            // img = heavyblur(img);
            // img = detectEdges(img);
            display(img);
        }
    }

   
    










//heavyblur 
	public static BufferedImage heavyblur (BufferedImage img) {
		BufferedImage blurImg = new BufferedImage(
			img.getWidth()-4, img.getHeight()-4, BufferedImage.TYPE_BYTE_GRAY);
		int pix = 0;
		for (int y=0; y<blurImg.getHeight(); y++) {
			for (int x=0; x<blurImg.getWidth(); x++) {
				pix = (int)(
				10*(img.getRGB(x+3, y+3)& 0xFF)
				+ 6*(img.getRGB(x+2, y+1)& 0xFF)
				+ 6*(img.getRGB(x+1, y+2)& 0xFF)
				+ 6*(img.getRGB(x+2, y+3)& 0xFF)
				+ 6*(img.getRGB(x+3, y+2)& 0xFF)
				+ 4*(img.getRGB(x+1, y+1)& 0xFF)
				+ 4*(img.getRGB(x+1, y+3)& 0xFF)
				+ 4*(img.getRGB(x+3, y+1)& 0xFF)
				+ 4*(img.getRGB(x+3, y+3)& 0xFF)
				+ 2*(img.getRGB(x, y+1)& 0xFF)
				+ 2*(img.getRGB(x, y+2)& 0xFF)
				+ 2*(img.getRGB(x, y+3)& 0xFF)
				+ 2*(img.getRGB(x+4, y+1)& 0xFF)
				+ 2*(img.getRGB(x+4, y+2)& 0xFF)
				+ 2*(img.getRGB(x+4, y+3)& 0xFF)
				+ 2*(img.getRGB(x+1, y)& 0xFF)
				+ 2*(img.getRGB(x+2, y)& 0xFF)
				+ 2*(img.getRGB(x+3, y)& 0xFF)
				+ 2*(img.getRGB(x+1, y+4)& 0xFF)
				+ 2*(img.getRGB(x+2, y+4)& 0xFF)
				+ 2*(img.getRGB(x+3, y+4)& 0xFF)
				+ (img.getRGB(x, y)& 0xFF)
				+ (img.getRGB(x, y+2)& 0xFF)
				+ (img.getRGB(x+2, y)& 0xFF)
				+ (img.getRGB(x+2, y+2)& 0xFF))/74;
				int p = (255<<24) | (pix<<16) | (pix<<8) | pix; 
				blurImg.setRGB(x,y,p);
			}
		}
		return blurImg;
	}













// detectEdge
public static BufferedImage detectEdges (BufferedImage img) {
    int h = img.getHeight(), w = img.getWidth(), threshold=30, p = 0;
    BufferedImage edgeImg = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
    int[][] vert = new int[w][h];
    int[][] horiz = new int[w][h];
    int[][] edgeWeight = new int[w][h];
    for (int y=1; y<h-1; y++) {
        for (int x=1; x<w-1; x++) {
            vert[x][y] = (int)(img.getRGB(x+1, y-1)& 0xFF + 2*(img.getRGB(x+1, y)& 0xFF) + img.getRGB(x+1, y+1)& 0xFF
                - img.getRGB(x-1, y-1)& 0xFF - 2*(img.getRGB(x-1, y)& 0xFF) - img.getRGB(x-1, y+1)& 0xFF);
            horiz[x][y] = (int)(img.getRGB(x-1, y+1)& 0xFF + 2*(img.getRGB(x, y+1)& 0xFF) + img.getRGB(x+1, y+1)& 0xFF
                - img.getRGB(x-1, y-1)& 0xFF - 2*(img.getRGB(x, y-1)& 0xFF) - img.getRGB(x+1, y-1)& 0xFF);
            edgeWeight[x][y] = (int)(Math.sqrt(vert[x][y] * vert[x][y] + horiz[x][y] * horiz[x][y]));
            if (edgeWeight[x][y] > threshold)
                p = (255<<24) | (255<<16) | (255<<8) | 255;
            else 
                p = (255<<24) | (0<<16) | (0<<8) | 0; 
            edgeImg.setRGB(x,y,p);
        }
    }
    return edgeImg;
}














//brighten
public static BufferedImage brithen(BufferedImage img, int precentage) {
    int r = 0, g = 0, b = 0, rgb = 0, p = 0;
    int amount = (int)(precentage * 255/100);
    BufferedImage newImage= new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_ARGB);
    for (int y = 0; y < img.getHeight(); y+=1) {
        for (int x = 0; x < img.getWidth(); x+=1) {
            rgb = img.getRGB(x, y);
            r = ((rgb >> 16) & 0xFF)+amount;
            g = ((rgb >> 8) & 0xFF)+amount; 
            b = ((rgb & 0xFF)+amount);
            if(r>255) r = 255;
            if(g>255) g = 255;
            if(b>255) b = 255;
            p = (255<<24) | (r << 16) | (g << 8) | b;
            newImage.setRGB(x, y, p);


            
        }
        
    }
        return newImage;
    }







//blur
public static BufferedImage blur(BufferedImage img) {
        BufferedImage blurImge = new BufferedImage(
            img.getWidth()-2,img.getHeight()-2,BufferedImage.TYPE_BYTE_GRAY
        );
        int pix = 0;
        for (int y = 0; y < blurImge.getHeight(); y++) {
            for (int x = 0; x < blurImge.getWidth(); x++) {
                pix = (int)(4*(img.getRGB(x+1, y+1) & 0xFF)
                + 2*(img.getRGB(x+1, y) & 0xFF)
                + 2*(img.getRGB(x+1, y+2) & 0xFF)
                + 2*(img.getRGB(x, y+1) & 0xFF)
                + 2*(img.getRGB(x+2, y+1) & 0xFF)

                + (img.getRGB(x, y) & 0xFF)
                + (img.getRGB(x, y+2) & 0xFF)
                + (img.getRGB(x+2, y) & 0xFF)
                + (img.getRGB(x+2, y+2) & 0xFF))/16;
                int p = (255<<24) | (pix << 16) | (pix << 8) | pix;
                blurImge.setRGB(x, y, p);
                
            }
            
        }
        return blurImge;
    }








//pixelate 
public static BufferedImage pixelate(BufferedImage img, int n) {
    BufferedImage pixImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

    for (int y = 0; y < img.getHeight(); y += n) {
        for (int x = 0; x < img.getWidth(); x += n) {
            int totalRed = 0, totalGreen = 0, totalBlue = 0, totalAlpha = 0;

            for (int a = 0; a < n; a++) {
                for (int b = 0; b < n; b++) {
                    int rgb = img.getRGB(x + a, y + b);
                    totalAlpha += (rgb >> 24) & 0xFF;
                    totalRed += (rgb >> 16) & 0xFF;
                    totalGreen += (rgb >> 8) & 0xFF;
                    totalBlue += rgb & 0xFF;
                }
            }

            int avgAlpha = totalAlpha / (n * n);
            int avgRed = totalRed / (n * n);
            int avgGreen = totalGreen / (n * n);
            int avgBlue = totalBlue / (n * n);

            int avgColor = (avgAlpha << 24) | (avgRed << 16) | (avgGreen << 8) | avgBlue;

            for (int a = 0; a < n; a++) {
                for (int b = 0; b < n; b++) {
                    pixImg.setRGB(x + a, y + b, avgColor);
                }
            }
        }
    }

    return pixImg;
}

// toGrayScale
    public static BufferedImage toGrayScale(BufferedImage img) {
        System.out.println("converting to GrayScale");
        BufferedImage grayImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = grayImage.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return grayImage;
    }

// display
    public static void display(BufferedImage img) {
        System.out.println("Displaying image.");

        // Create a new JFrame for displaying the image
        JFrame frame = new JFrame();
        
        // Create a JLabel to hold the image
        JLabel label = new JLabel();
        
        // Set the frame size to match the image dimensions
        frame.setSize(img.getWidth(), img.getHeight());
        
        // Set the image as an icon for the label
        label.setIcon(new ImageIcon(img));
        
        // Add the label to the center of the frame's content pane
        frame.getContentPane().add(label, BorderLayout.CENTER);
        
        // Set the default close operation for the frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        // Pack the frame to ensure proper sizing
        frame.pack();
        
        // Make the frame visible, displaying the image
        frame.setVisible(true);
    }

// colorinImage
    public static BufferedImage colorinImage(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage colorinImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = img.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = ((rgb >> 8) & 0xFF) + 30 ;
                int blue = rgb & 0xFF;

                int invertred = 255-red;
                int invertgreen = 255-green;
                int invertblue = 255-blue;

        
    
                // Create a new color with only the green channel
                int newRgb = (invertred << 16) | ((invertgreen) << 8) | invertblue;
    
                colorinImage.setRGB(x, y, newRgb);
            }
        }
    
        return colorinImage;
    }

// resize
public static BufferedImage resize (BufferedImage img, int newHeight) {
    System.out.println("  Scaling image.");
    double scaleFactor = (double) newHeight/img.getHeight();
    BufferedImage scaledImg = new BufferedImage(
        (int)(scaleFactor*img.getWidth()), newHeight, BufferedImage.TYPE_BYTE_GRAY);
    AffineTransform at = new AffineTransform();
    at.scale(scaleFactor, scaleFactor);
    AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
    return scaleOp.filter(img, scaledImg);
}
}