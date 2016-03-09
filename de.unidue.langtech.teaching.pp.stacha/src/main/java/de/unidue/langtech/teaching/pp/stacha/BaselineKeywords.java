package de.unidue.langtech.teaching.pp.stacha;

import java.util.Collection;

import org.apache.bcel.generic.IFGT;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.type.Keyphrase;
import de.unidue.langtech.teaching.pp.type.DetectedLanguage;

public class BaselineKeywords extends JCasAnnotator_ImplBase{
	
	public void process(JCas jcas) throws AnalysisEngineProcessException {

		Collection<Token> tokens = JCasUtil.select(jcas, Token.class);
		
		for (Token t:tokens){
			if (!(t.getPos().getPosValue().contains("NN")||t.getPos().getPosValue().contains("VB")||t.getPos().getPosValue().contains("J"))){
		       t.removeFromIndexes();
			}
		}
	}
	
}
