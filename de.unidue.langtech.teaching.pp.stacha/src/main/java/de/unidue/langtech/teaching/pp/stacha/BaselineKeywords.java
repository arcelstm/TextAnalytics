package de.unidue.langtech.teaching.pp.stacha;

import java.util.Collection;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

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
		
//        int i=0;
//        for(Token t:tokens){
//        	if(t.getEnd()-t.getStart()>1)
//        		if(t.getPos().getPosValue().contains("NN")){
//        			System.out.println("(" + t.getPos().getPosValue()+") " + t.getCoveredText() +"\n---");
//        			i++;
//        		}
//        }
        
        for(int i=0; i <tokens.size(); i++){
        	Annotation x= (Annotation) tokens.toArray()[i];
        	
        	if(x.getEnd()-x.getStart()>1)
        		
        	if(	((Token) x).getPos().getPosValue().contains("NN") || 
        		((Token) x).getPos().getPosValue().contains("JJ")) {
        			System.out.println("---\n(" + ((Token) x).getPos().getPosValue()+") " + x.getCoveredText());

        			//System.out.println(((Keyphrase) x).getScore());
        	}
        	        	
        	//Keyphrase y = null;
			//Keyphrase y = x.getCoveredText();
        	//System.out.println(y.getScore());
        	
        	i++;
        }
            
	}

}
