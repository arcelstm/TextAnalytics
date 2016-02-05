package de.unidue.langtech.teaching.pp.stacha.newType;

import java.util.Collection;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.type.Keyphrase;
import de.unidue.langtech.teaching.pp.type.DetectedLanguage;
import de.unidue.langtech.teaching.pp.type.GoldLanguage;
import de.unidue.langtech.teaching.pp.type.MyType;

public class Printer
    extends JCasAnnotator_ImplBase
{

    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    { 
    	//System.out.println(jcas.getDocumentText());
    	
    	//System.out.println(BreakIteratorSegmenter.PARAM_WRITE_SENTENCE);

    	Collection<Keyphrase> keyphrases = JCasUtil.select(jcas, Keyphrase.class);
    	Collection<Token> tokens = JCasUtil.select(jcas, Token.class);

    	for(Keyphrase k:keyphrases){
    		if(k.getScore()>0)
    		System.out.println(k.getKeyphrase()+ " " +k.getScore());
    	}
    	
    	System.out.println(jcas.getDocumentText());

       
        
    }

}