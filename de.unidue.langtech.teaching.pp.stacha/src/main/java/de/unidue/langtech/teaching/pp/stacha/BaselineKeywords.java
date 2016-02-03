package de.unidue.langtech.teaching.pp.stacha;

import java.util.Collection;

import org.apache.bcel.generic.IFGT;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.type.Keyphrase;
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
       // Collection<Keyphrase> candidates = JCasUtil.select(jcas, Keyphrase.class);
        Collection<Keyphrase> keyphrases = JCasUtil.select(jcas, Keyphrase.class);
		
//        int i=0;
//        for(Token t:tokens){
//        	if(t.getEnd()-t.getStart()>1)
//        		if(t.getPos().getPosValue().contains("NN")){
//        			System.out.println("(" + t.getPos().getPosValue()+") " + t.getCoveredText() +"\n---");
//        			i++;
//        		}
//        }
        
        for(int i=0; i <tokens.size();i++){
        
        	Token t= (Token) tokens.toArray()[i];
        	Keyphrase k= (Keyphrase) keyphrases.toArray()[i];
        	
        	if (t.getPos().getPosValue().equals("NNP")){
        		k.setScore(k.getScore()+10);
        	}
        	
        		if(
        			(k.getScore()>9) && 
        			(t.getPos().getPosValue().contains("NN") || t.getPos().getPosValue().contains("JJ"))) {
                		System.out.println("---\n(" + t.getPos().getPosValue()+") " + t.getCoveredText() +" "+ k.getScore());
                }
        }
	}

}
