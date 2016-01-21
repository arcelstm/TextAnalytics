package de.unidue.langtech.teaching.pp.stacha;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unidue.langtech.teaching.pp.type.DetectedLanguage;

public class BaselineExample extends JCasAnnotator_ImplBase{

	public static final String PARAM_MESSAGE= "PARAM_MESSAGE";
	@ConfigurationParameter
	(name= PARAM_MESSAGE, mandatory=true, defaultValue= "Scusi")
	protected String message;
	
    @Override
    public void process(JCas jcas) throws AnalysisEngineProcessException{
    	
    	System.out.println(message+"\n");
        System.out.println("Document is: " + jcas.getDocumentText());
        
        Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
        DetectedLanguage languageAnno = new DetectedLanguage(jcas);
        
        String lang = "EN";
        
        ArrayList<String> eng = new ArrayList<String>();
        	eng.add("test");
        	
        for(Token t:tokens){
        	
        	String x = t.getCoveredText().toLowerCase();
        	
        	System.out.println(t.getCoveredText());
        	        	
          		if(x.equals("das"))lang="DE";
        		if(x.equals("devoirs"))lang="FR";
        		if(x.equals("diez"))lang="ES";
        		if(x.equals("giorno"))lang="IT";
        		if(x.equals("rätt"))lang="SE";
        }
        
        System.out.println("CAS contains " + tokens.size() + " tokens.");
        languageAnno.setLanguage(lang);
        languageAnno.addToIndexes();
        
    }
}