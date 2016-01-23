package de.unidue.langtech.teaching.pp.stacha;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.Type;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTagger;
import opennlp.tools.postag.POSTaggerME;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unidue.langtech.teaching.pp.type.DetectedLanguage;

public class BaselineKeywords extends JCasAnnotator_ImplBase{

	public static final String PARAM_LANGUAGE= "PARAM_LANGUAGE";
	@ConfigurationParameter
	(name= PARAM_LANGUAGE, mandatory=false, defaultValue= "en")
	protected String language;
	
	@SuppressWarnings("deprecation")
	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {

		System.out.println("Document is: " + jcas.getDocumentText());
        
        Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
        DetectedLanguage languageAnno = new DetectedLanguage(jcas);
		
        
        int i=0;
        for(Token t:tokens){
        	if(t.getEnd()-t.getStart()>2)
        	System.out.println(t.getCoveredText());
        	i++;
        }
        
        languageAnno.setLanguage(language);    
        languageAnno.addToIndexes();
    
	}

}
