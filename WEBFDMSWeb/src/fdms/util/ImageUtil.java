package fdms.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class ImageUtil {
	
	public static BufferedImage horizontalflip(BufferedImage img) {
		int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage flippedImage = new BufferedImage(w, h, img.getType());

        Graphics2D g = (Graphics2D) flippedImage.getGraphics();

        g.drawImage(img, 0, 0, w, h, 0, h, w, 0, null);

        g.dispose();

        return flippedImage;
	}
	
    public static BufferedImage verticalflip(BufferedImage img) {
		int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage flippedImage = new BufferedImage(w, h, img.getColorModel().getTransparency());

        Graphics2D g = (Graphics2D) flippedImage.getGraphics();

        g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);

        g.dispose();

        return flippedImage;
	}
    
    public static BufferedImage rotateRight(BufferedImage img, int angle) {
		int w = img.getWidth();
		int h = img.getHeight();

		BufferedImage dimg = new BufferedImage(w, h, img.getType());

		Graphics2D g = dimg.createGraphics();

		g.rotate(Math.toRadians(angle), w/2, h/2);
		g.drawImage(img, null, 0, 0);

		return dimg;
	}
    
    public static BufferedImage rotateImage(BufferedImage img, double angle) {
        int w = img.getWidth();
        int h = img.getHeight();
        
        int maxDim;

        if(h > w) {
            maxDim = h;
        } else {
            maxDim = w;
        }

        BufferedImage dimg = new BufferedImage(maxDim, maxDim, img.getType());

        //BufferedImage dimg = new BufferedImage(w, h, img.getType());

        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.rotate(Math.toRadians(angle), maxDim / 2, maxDim / 2);

        //g.drawImage(img, null, 0, 0);
        g.drawImage(img, 0, 0, null);

        g.dispose();
        
        BufferedImage finalImage;
        
        if(angle > 0) {
        	finalImage = writeClockwiseImage(dimg, img);
        } else {
        	finalImage = writeCounterClockwiseImage(dimg, img);
        }
        
        return finalImage;
    }
    
    private static BufferedImage writeClockwiseImage(BufferedImage processedImage, BufferedImage originalImage) {
    	
    	int originalHeight = originalImage.getHeight();
        int originalWidth = originalImage.getWidth();

        int diff;

        BufferedImage bi = new BufferedImage(originalImage.getHeight(), originalImage.getWidth(), processedImage.getType());
        
        Graphics2D g2d = (Graphics2D) bi.getGraphics();

		if(originalHeight > originalWidth) {
		    diff = originalHeight - originalWidth;

		    g2d.drawImage(processedImage, 0, 0, bi.getWidth(), bi.getHeight(), 0, 0, processedImage.getWidth(), processedImage.getHeight() - diff, null);
		} else {
		    diff = originalWidth - originalHeight;

		    g2d.drawImage(processedImage, 0, 0, bi.getWidth(), bi.getHeight(), diff, 0, processedImage.getWidth(), processedImage.getHeight(), null);
		}

		g2d.dispose();
        
        return bi;
    }
    
    private static BufferedImage writeCounterClockwiseImage(BufferedImage processedImage, BufferedImage originalImage) {
    	
    	int originalHeight = originalImage.getHeight();
        int originalWidth = originalImage.getWidth();

        int diff;

        BufferedImage bi = new BufferedImage(originalImage.getHeight(), originalImage.getWidth(), processedImage.getType());
        
        Graphics2D g2d = (Graphics2D) bi.getGraphics();

		if(originalHeight > originalWidth) {
		    diff = originalHeight - originalWidth;

		    g2d.drawImage(processedImage, 0, 0, bi.getWidth(), bi.getHeight(), 0, diff, processedImage.getWidth(), processedImage.getHeight(), null);
		} else {
		    diff = originalWidth - originalHeight;

		    g2d.drawImage(processedImage, 0, 0, bi.getWidth(), bi.getHeight(), 0, 0, processedImage.getWidth() - diff, processedImage.getHeight(), null);
		}

		g2d.dispose();
        
        return bi;
    }
    
    public static BufferedImage resizeImage(BufferedImage img, int desiredHeight, int desiredWidth) {
    	int w = img.getWidth();
        int h = img.getHeight();
        
        if(desiredHeight > h) {
        	desiredHeight = h;
        }
        
        if(desiredWidth > w) {
        	desiredWidth = w;
        }

        BufferedImage resizedImage = new BufferedImage(desiredWidth, desiredHeight, img.getColorModel().getTransparency());

        Graphics2D g = (Graphics2D) resizedImage.getGraphics();

        g.drawImage(img, 0, 0, desiredWidth, desiredHeight, 0, 0, w, h, null);

        g.dispose();

        return resizedImage;
    }
    
    public static BufferedImage resizeImage(BufferedImage img, int resizePercent) {
    	int w = img.getWidth();
        int h = img.getHeight();
        
        int desiredHeight, desiredWidth;
        
        // Divided by 100, because it is an percentage amount.
        double ratio = (double) resizePercent / 100;
        
        desiredHeight = (int) (h * ratio);
        desiredWidth = (int) (w * ratio);
        
        /*if(desiredHeight > h) {
        	desiredHeight = h;
        }
        
        if(desiredWidth > w) {
        	desiredWidth = w;
        }*/

        BufferedImage resizedImage = new BufferedImage(desiredWidth, desiredHeight, img.getColorModel().getTransparency());

        Graphics2D g = (Graphics2D) resizedImage.getGraphics();

        g.drawImage(img, 0, 0, desiredWidth, desiredHeight, 0, 0, w, h, null);

        g.dispose();

        return resizedImage;
    }

}
