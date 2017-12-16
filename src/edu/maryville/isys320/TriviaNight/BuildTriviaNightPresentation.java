package edu.maryville.isys320.TriviaNight;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class BuildTriviaNightPresentation {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String fileName = "//Users//joedent//TriviaNight.pptx";
		SlideShowBuilder builder = new SlideShowBuilder();
		QuestionsManager qm = new QuestionsManager("data");
		
		builder.buildSlideShow(qm.getCategories(), qm.getQuestions(), qm.getAnswers(), fileName);
//		builder.printSlideShowInfo("Sample.pptx");

	}
	
}
