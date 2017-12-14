package edu.maryville.isys320.TriviaNight;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BuildTriviaNightPresentation {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		String categories[] = buildExampleCategories();
		String[] questions = buildExampleQuestions();
		String[] answers = buildExampleAnswers();

		SlideShowBuilder builder = new SlideShowBuilder();
		builder.buildSlideShow(categories, questions, answers, "//Users//joedent//TriviaNight.pptx");
//		builder.printSlideShowInfo("Sample.pptx");

	}

	private static String[] buildExampleAnswers() {
		String answers[] = new String[100];
		for (int r = 0; r < 10; r++) {
			for (int q = 0; q < 10; q++ ) {
				int index = r * 10 + q;
				answers[index] = "Answer " + (q+1);
			}
		}
		return answers;
	}

	private static String[] buildExampleQuestions() {
		String questions[] = new String[100];
		for (int r = 0; r < 10; r++) {
			for (int q = 0; q < 10; q++ ) {
				int index = r * 10 + q;
				questions[index] = "Question " + (q+1);
			}
		}
		return questions;
	}

	private static String[] buildExampleCategories() {
		return new String[]{"Cat1", "Cat2", "Cat3", "Cat4", "Cat5", "Cat6", "Cat7", "Cat8", "Cat9", "Cat10" };
	}
	
}
