package de.unidue.langtech.teaching.pp.stacha.newType;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import de.unidue.langtech.teaching.pp.type.MyType2;

public class LetterAnnotator2
    extends JCasAnnotator_ImplBase
{

    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
        String documentText = jcas.getDocumentText();

        int countLetterA = 0;
        for (char c : documentText.toCharArray()) {
            if (c == 'a' || c == 'A') {
                countLetterA++;
            }
        }
        
        //Set this integer value to the property of the new type 'MyType'
        MyType2 myType2 = new MyType2(jcas);
        myType2.setCountLetterA(countLetterA);
        myType2.addToIndexes();
        
        System.out.println("Letter A " + countLetterA +"x");
    }

}