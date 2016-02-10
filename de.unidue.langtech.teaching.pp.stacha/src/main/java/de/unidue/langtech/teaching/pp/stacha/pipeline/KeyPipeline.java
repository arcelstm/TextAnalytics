package de.unidue.langtech.teaching.pp.stacha.pipeline;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpNameFinder;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.snowball.SnowballStemmer;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordNamedEntityRecognizer;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordParser;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordSegmenter;
import de.tudarmstadt.ukp.dkpro.core.stopwordremover.StopWordRemover;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.candidate.CandidateAnnotator;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.candidate.CandidateAnnotatorFactory;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.coreference.ranking.CoreferencedTfidfAnnotator;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.evaluator.KeyphraseEvaluator;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.evaluator.KeyphraseWriter;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.filter.PosSequenceFilter;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.filter.factory.KeyphraseMergerFactory;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.postprocessing.KeyphraseMerger;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.ranking.PositionRanking;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.ranking.RandomRanking;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.ranking.TfidfRanking;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.reader.KeyphraseReader;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.type.Keyphrase;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.util.KeyphraseScoreComparator;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.util.KeyphraseUtil;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.wrapper.Candidate;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.wrapper.CooccurrenceGraphExtractor;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.wrapper.KeyphraseExtractor;
import de.tudarmstadt.ukp.dkpro.keyphrases.ranking.NodeDegreeRanking;
import de.tudarmstadt.ukp.dkpro.keyphrases.ranking.PageRankRanking;
import de.tudarmstadt.ukp.dkpro.keyphrases.textgraphs.CooccurrenceGraph;
import de.tudarmstadt.ukp.dkpro.keyphrases.textgraphs.CooccurrenceGraphFactory;
import de.tudarmstadt.ukp.dkpro.keyphrases.textgraphs.util.PageRank;
import de.unidue.langtech.teaching.pp.stacha.BaselineKeywords;
import de.unidue.langtech.teaching.pp.stacha.newType.Printer;
import de.unidue.langtech.teaching.pp.stacha.newType.Test;
import edu.stanford.nlp.dcoref.CoNLL2011DocumentReader.NamedEntityAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;

public class KeyPipeline {

	public static void main(String[] args) throws Exception{
    	
       // String keyphraseCandidateFeaturePath = null;
		
		SimplePipeline.runPipeline(
        		
                CollectionReaderFactory.createReader(
                        KeyphraseReader.class,
                        KeyphraseReader.PARAM_INPUTDIR, "src/test/resources/txt",
                        KeyphraseReader.PARAM_DATA_SUFFIX, "txt"
                )
                
               ,AnalysisEngineFactory.createEngineDescription(StanfordSegmenter.class, StanfordSegmenter.PARAM_LANGUAGE, "en",StanfordSegmenter.PARAM_STRICT_ZONING,true) //TOKENIZATION  
                      
               ,AnalysisEngineFactory.createEngineDescription(OpenNlpPosTagger.class,OpenNlpPosTagger.PARAM_LANGUAGE, "en")
               
          	,AnalysisEngineFactory.createEngineDescription(CooccurrenceGraph.class,CooccurrenceGraph.PARAM_FEATURE_PATH,Token.class,CooccurrenceGraph.PARAM_WINDOW_SIZE,5)
            ,AnalysisEngineFactory.createEngineDescription(PageRankRanking.class,PageRankRanking.PARAM_WEIGHTED,true)

           //	,CooccurrenceGraphFactory.getCooccurrenceGraph_token(),createEngineDescription(PageRankRanking.class,PageRankRanking.PARAM_WEIGHTED, true)
           	 
           	  ,AnalysisEngineFactory.createEngineDescription(CandidateAnnotator.class, CandidateAnnotator.PARAM_FEATURE_PATH, Token.class)
           	  // ,AnalysisEngineFactory.createEngineDescription(PositionRanking.class)
           // ,AnalysisEngineFactory.createEngineDescription(NodeDegreeRanking.class)
           	  ,AnalysisEngineFactory.createEngineDescription(StopWordRemover.class, StopWordRemover.PARAM_MODEL_LOCATION,"[en]src/test/resources/stopwords/stop.txt")
              ,AnalysisEngineFactory.createEngineDescription(PosSequenceFilter.class)

           	  ,AnalysisEngineFactory.createEngineDescription(KeyphraseMerger.class,KeyphraseMerger.PARAM_MAX_LENGTH,5,KeyphraseMerger.PARAM_KEEP_PARTS,false) 
           	 
           // ,AnalysisEngineFactory.createEngineDescription(BaselineKeywords.class, BaselineKeywords.PARAM_LANGUAGE, "en")

           	  ,AnalysisEngineFactory.createEngineDescription(Printer.class)
                //,AnalysisEngineFactory.createEngineDescription(EvaluatorExample.class)
                
         );
		
		//AnalysisEngineDescription graph = createEngineDescription(CooccurrenceGraphFactory.getCooccurrenceGraph_token(),createEngineDescription(PageRankRanking.class, PageRankRanking.PARAM_WEIGHTED, true));

	}

}