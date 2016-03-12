package de.unidue.langtech.teaching.pp.stacha.pipeline;

import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordSegmenter;
import de.tudarmstadt.ukp.dkpro.core.stopwordremover.StopWordRemover;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.candidate.CandidateAnnotator;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.evaluator.KeyphraseEvaluator;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.evaluator.KeyphraseWriter;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.filter.PosSequenceFilter;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.filter.frequency.FrequencyFilter;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.postprocessing.KeyphraseMerger;
import de.tudarmstadt.ukp.dkpro.keyphrases.ranking.PageRankRanking;
import de.tudarmstadt.ukp.dkpro.keyphrases.textgraphs.CooccurrenceGraph;

public class KeyPipeline {

	@SuppressWarnings("null")
	public static void main(String[] args) throws Exception{
		
		int nr= 20;
		
		String[]  posPatterns = {
				"N",
				"N_N",
				"J_N"
		};
		
		SimplePipeline.runPipeline(
				
			CollectionReaderFactory.createReader(
					TextReader.class,
					//TextReader.PARAM_SOURCE_LOCATION, "src/test/resources/txt/ap880623-0135.body",
					//TextReader.PARAM_SOURCE_LOCATION, "src/test/resources/txt/irma_iro07e.txt",
					TextReader.PARAM_SOURCE_LOCATION, "src/test/resources/txt/foodfirst_ff08ne.txt",
					TextReader.PARAM_LANGUAGE,"en"				
			),
			
            AnalysisEngineFactory.createEngineDescription(
            		StanfordSegmenter.class
            ), 
            
            AnalysisEngineFactory.createEngineDescription(
            		OpenNlpPosTagger.class
            ),
                        
            AnalysisEngineFactory.createEngineDescription(
            		CandidateAnnotator.class, 
            		CandidateAnnotator.PARAM_FEATURE_PATH, Token.class
            ),
            
           
            
            AnalysisEngineFactory.createEngineDescription(
          			CooccurrenceGraph.class,
          			CooccurrenceGraph.PARAM_FEATURE_PATH,Token.class,
          			CooccurrenceGraph.PARAM_WINDOW_SIZE,3
          	),
            
          	
          	 
            AnalysisEngineFactory.createEngineDescription(
            		PageRankRanking.class,
            		PageRankRanking.PARAM_WEIGHTED,false
            ),  
             
                AnalysisEngineFactory.createEngineDescription(
           			KeyphraseMerger.class, 
           			KeyphraseMerger.PARAM_KEEP_PARTS, false,
           			KeyphraseMerger.PARAM_MAX_LENGTH,2
           	),  
                
                AnalysisEngineFactory.createEngineDescription(
					StopWordRemover.class,
			        StopWordRemover.PARAM_MODEL_LOCATION,"src/test/resources/stopwords/english_keyphrase_stopwords.txt"
			),
                
              AnalysisEngineFactory.createEngineDescription(
            		PosSequenceFilter.class,
            		PosSequenceFilter.PARAM_POS_PATTERNS,posPatterns
            ),   
                
           
           
        
            
            AnalysisEngineFactory.createEngineDescription(
            		FrequencyFilter.class,
            		FrequencyFilter.MAX_FREQUENCY,3,
            		FrequencyFilter.MIN_FREQUENCY,1
            ),
            
              
        	AnalysisEngineFactory.createEngineDescription(
        			KeyphraseWriter.class, KeyphraseWriter.PARAM_N, nr, 
        			KeyphraseWriter.PARAM_LOWERCASE,true,
        			KeyphraseWriter.PARAM_REMOVE_CONTAINED,true
        	),
        	
        	AnalysisEngineFactory.createEngineDescription(
                    KeyphraseEvaluator.class,
                    KeyphraseEvaluator.PARAM_N, nr,
                    KeyphraseEvaluator.PARAM_EVAL_TYPE, KeyphraseEvaluator.EvaluatorType.Token.toString(),
                    KeyphraseEvaluator.PARAM_REMOVE_GOLD_AFTER_MATCH, true,
                    KeyphraseEvaluator.PARAM_LOWERCASE,true
        	)
        	
		);
		
	}
	
}