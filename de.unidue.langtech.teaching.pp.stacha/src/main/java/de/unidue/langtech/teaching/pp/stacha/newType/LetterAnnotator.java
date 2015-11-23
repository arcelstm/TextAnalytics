package de.unidue.langtech.teaching.pp.stacha.newType;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import de.unidue.langtech.teaching.pp.type.MyType;

public class LetterAnnotator extends JCasAnnotator_ImplBase{

    public void process(JCas jcas) throws AnalysisEngineProcessException{
    	
        String documentTextUpper = jcas.getDocumentText().toUpperCase();
        int countLetter = 0;
        
            for (char i = 'A'; i <= 'Z'; i++) {
            	countLetter = 0;
            	            	
				for (char c : documentTextUpper.toCharArray()) {
					if (c == i) {
						countLetter++;
					}
				}
				
				if(countLetter>0)
				System.out.println(countLetter +"x "+i);
			}
            
        //Set this integer value to the property of the new type 'MyType'
        MyType myType = new MyType(jcas);
        myType.setCountLetter(countLetter);
        myType.addToIndexes();

    }
}