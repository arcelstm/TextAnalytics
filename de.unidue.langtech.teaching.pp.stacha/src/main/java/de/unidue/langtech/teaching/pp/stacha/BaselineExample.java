package de.unidue.langtech.teaching.pp.stacha;

import java.util.Collection;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unidue.langtech.teaching.pp.type.DetectedLanguage;

/**
 * The baseline always identifies "EN" as the document language.
 * 
 * @author zesch
 *
 */
public class BaselineExample
    extends JCasAnnotator_ImplBase
{

    @Override
    public void process(JCas jcas)
        throws AnalysisEngineProcessException
    {
        System.out.println("Document is: " + jcas.getDocumentText());
        
        Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
        
        DetectedLanguage languageAnno = new DetectedLanguage(jcas);
        
        String lang = "EN";
              
        for(Token t:tokens){
        	String x = t.getCoveredText().toLowerCase();
        	        	
        		if(x.equals("das")){
        			lang="DE";
        		}
        		//
        		if(x.equals("devoirs")){
        			lang="FR";
        		}
        		
        		if(x.equals("diez")){
        			lang="ES";
        		}
        		
        		if(x.equals("giorno")){
        			lang="IT";
        		}
        		
        		if(x.equals("rätt")){
        			lang="SE";
        		}
        		
        }
        
        languageAnno.setLanguage(lang);
        
        System.out.println("CAS contains " + tokens.size() + " tokens.");
        
        languageAnno.addToIndexes();
    }
}