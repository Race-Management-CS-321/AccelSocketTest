package ip;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.JPanel;

import org.opencv.core.Mat;
import org.opencv.videoio.*;
import org.opencv.imgproc.Imgproc;




@SuppressWarnings("serial")
public class videoCamera extends JPanel
{
    VideoCapture camera; 

    public videoCamera(VideoCapture cam) 
    {

        camera  = cam; 

    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {

    }
    public BufferedImage Mat2BufferedImage(Mat m)
    {

        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (m.channels() > 1)
        {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels() * m.cols() * m.rows();
        byte[] b = new byte[bufferSize];
        m.get(0, 0, b); // get all the pixels
        BufferedImage img = new BufferedImage(m.cols(), m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return img;


    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Mat mat = new Mat();

        if( camera.read(mat))
        {
            //System.out.print("IMAGE");


        }
//System.out.print(this);


		try {
			BufferedImage image = Mat2BufferedImage(mat);
			g.drawImage(image,10,10,480,320, null);
		}
		catch(Exception e) {
			System.out.println(e);
			
			if (this==openCVTest.cam1){
				openCVTest.frameKiller(openCVTest.IP1,openCVTest.j1);
			}
			else if (this==openCVTest.cam2){
				openCVTest.frameKiller(openCVTest.IP2,openCVTest.j2);
			}
			else if (this==openCVTest.cam3){
				openCVTest.frameKiller(openCVTest.IP3,openCVTest.j3);
			}
			else if (this==openCVTest.cam4){
				openCVTest.frameKiller(openCVTest.IP4,openCVTest.j4);
			}
			else {
				openCVTest.frameKiller(openCVTest.IP5,openCVTest.j5);
			}
		}

        //Mat gray = turnGray(mat);
        //MatOfRect objects = new MatOfRect();
        //CascadeClassifier cas = new CascadeClassifier();
        //cas.detectMultiScale(gray,objects);
        //Mat thresh  = threash( gray);

        //BufferedImage image = Mat2BufferedImage(thresh);

		

    }
    public Mat turnGray( Mat img)

    {
        Mat mat1 = new Mat();
        Imgproc.cvtColor(img, mat1, Imgproc.COLOR_RGB2GRAY);
        return mat1;
    }
    public Mat threash(Mat img)
    {
        Mat threshed = new Mat();
        int SENSITIVITY_VALUE = 100;
        Imgproc.threshold(img, threshed, SENSITIVITY_VALUE,255,Imgproc.THRESH_BINARY);
        return threshed;
    }

}