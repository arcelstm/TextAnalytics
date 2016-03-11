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
import de.tudarmstadt.ukp.dkpro.keyphrases.core.postprocessing.KeyphraseMerger;
import de.tudarmstadt.ukp.dkpro.keyphrases.ranking.PageRankRanking;
import de.tudarmstadt.ukp.dkpro.keyphrases.textgraphs.CooccurrenceGraph;

public class KeyPipeline {

	public static void main(String[] args) throws Exception{
		
		SimplePipeline.runPipeline(
        		
			CollectionReaderFactory.createReader(
						
					TextReader.class,
					TextReader.PARAM_SOURCE_LOCATION, "src/test/resources/txt/engTexts.txt",
					TextReader.PARAM_LANGUAGE,"en"				
			)
				
                /*CollectionReaderFactory.createReader(
                        KeyphraseReader.class,
                        KeyphraseReader.PARAM_INPUTDIR, "src/test/resources/txt",
                        KeyphraseReader.PARAM_DATA_SUFFIX, "txt",
                        KeyphraseReader.PARAM_LANGUAGE, "en"
                )*/
                
            ,AnalysisEngineFactory.createEngineDescription(StanfordSegmenter.class) //TOKENIZATION  

            ,AnalysisEngineFactory.createEngineDescription(OpenNlpPosTagger.class)

          	,AnalysisEngineFactory.createEngineDescription(
          			CooccurrenceGraph.class,
          			CooccurrenceGraph.PARAM_FEATURE_PATH,Token.class,
          			CooccurrenceGraph.PARAM_WINDOW_SIZE,3
          	)
          	
            ,AnalysisEngineFactory.createEngineDescription(PageRankRanking.class)              

            ,AnalysisEngineFactory.createEngineDescription(
            		CandidateAnnotator.class, 
            		CandidateAnnotator.PARAM_FEATURE_PATH, Token.class
            )

            ,AnalysisEngineFactory.createEngineDescription(PosSequenceFilter.class)
            
           	,AnalysisEngineFactory.createEngineDescription(
           			KeyphraseMerger.class, 
           			KeyphraseMerger.PARAM_KEEP_PARTS, false, 
           			KeyphraseMerger.PARAM_MAX_LENGTH,4
           	)
           	           
			,AnalysisEngineFactory.createEngineDescription(
					StopWordRemover.class,
			        StopWordRemover.PARAM_MODEL_LOCATION,"src/test/resources/stopwords/stop.txt"
			)
			
        	,AnalysisEngineFactory.createEngineDescription(
        			KeyphraseWriter.class, KeyphraseWriter.PARAM_N, 15, 
        			KeyphraseWriter.PARAM_LOWERCASE,true,
        			KeyphraseWriter.PARAM_REMOVE_CONTAINED,true
        	)
        	
        	,AnalysisEngineFactory.createEngineDescription(
                    KeyphraseEvaluator.class,
                    KeyphraseEvaluator.PARAM_N, 15,
                    KeyphraseEvaluator.PARAM_EVAL_TYPE, KeyphraseEvaluator.EvaluatorType.Token.toString(),
                    KeyphraseEvaluator.PARAM_REMOVE_GOLD_AFTER_MATCH, true,
                    KeyphraseEvaluator.PARAM_LOWERCASE,true
            )
        	
         );
		
	}

}