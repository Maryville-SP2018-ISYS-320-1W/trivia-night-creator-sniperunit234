package edu.maryville.isys320.TriviaNight;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

public class SlideShowBuilder {
	
	public void printSlideShowInfo(String fileName) throws FileNotFoundException, IOException {
		XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(fileName));
		// get slides
	    for (XSLFSlide slide : ppt.getSlides()) {
	        for (XSLFShape sh : slide.getShapes()) {
	            // name of the shape
	            String name = sh.getShapeName();
	            System.out.println("Shape: " + name);

	            // shapes's anchor which defines the position of this shape in the slide
	            if (sh instanceof PlaceableShape) {
	                java.awt.geom.Rectangle2D anchor = ((PlaceableShape)sh).getAnchor();
	                System.out.printf("(%f, %f, %f, %f)\n", anchor.getX(), anchor.getY(),  anchor.getWidth(), anchor.getHeight());
	            }


	            if (sh instanceof XSLFTextShape) {
	                XSLFTextShape shape = (XSLFTextShape) sh;
	                System.out.printf("%s\n", shape);

	            }
	        }
	    }
	}

	public void buildSlideShow(String[] categories, String[] questions, String[] answers, String fileName) {
		XMLSlideShow ppt = new XMLSlideShow();
		XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
		
		for (int i = 0; i < 10; i++) {
			makeRoundSlide(ppt, defaultMaster, "Round " + (i+1));
			for (int j = 0; j < 10; j++) {
				makeSlide(ppt, defaultMaster, categories[i], questions[i * 10 + j], answers[i * 10 + j]);
			}
		}
		
	    savePPTX(ppt, fileName);
	}

	private static void makeRoundSlide(XMLSlideShow ppt, XSLFSlideMaster master, String round) {
		XSLFSlideLayout titleSlide = master.getLayout(SlideLayout.SECTION_HEADER);
		XSLFSlide slide = ppt.createSlide(titleSlide);

		
		XSLFTextShape sh1 = (XSLFTextShape) slide.getShapes().get(0);
		sh1.setText(round).setFontSize(36.0d);
		sh1.setHorizontalCentered(true);

	}

	private static void makeSlide(XMLSlideShow ppt, XSLFSlideMaster master, String title, String question, String answer) {
		XSLFSlideLayout blankSlide = master.getLayout(SlideLayout.BLANK);
		XSLFSlide slide = ppt.createSlide(blankSlide);

		XSLFTextShape header = slide.createTextBox();
		header.setAnchor(new Rectangle2D.Double(16.988740, 17.797717, 685.011260, 50.892205));
		header.setText(title).setFontSize(36.0d);
		header.setHorizontalCentered(true);

		XSLFTextShape question1 = slide.createTextBox();
		question1.setAnchor(new Rectangle2D.Double(16.988740, 68.689921, 685.011260, 298.120866));
		question1.setText(question).setFontSize(44.0);
		question1.setHorizontalCentered(true);

		XSLFTextShape answer1 = slide.createTextBox();
		answer1.setAnchor(new Rectangle2D.Double(16.988740, 366.810787, 685.011260, 74.918976));
		answer1.setText(answer).setFontSize(36.0d);
		answer1.setHorizontalCentered(true);
}

	private void savePPTX(XMLSlideShow ppt, String filePath) {
		File file = new File(filePath);
	    FileOutputStream out;
		try {
			out = new FileOutputStream(file);
			ppt.write(out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
