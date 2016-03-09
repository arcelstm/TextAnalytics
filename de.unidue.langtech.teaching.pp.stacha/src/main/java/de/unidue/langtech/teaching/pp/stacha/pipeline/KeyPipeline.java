package de.unidue.langtech.teaching.pp.stacha.pipeline;

import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordSegmenter;
import de.tudarmstadt.ukp.dkpro.core.stopwordremover.StopWordRemover;
import de.tudarmstadt.ukp.dkpro.keyphrases.bookindexing.aggregation.RankedPhraseAggregationAnnotator;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.candidate.CandidateAnnotator;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.evaluator.KeyphraseEvaluator;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.filter.CorpusFilter;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.filter.KeyphraseFilter;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.filter.PosSequenceFilter;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.postprocessing.KeyphraseMerger;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.reader.KeyphraseReader;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.util.KeyphraseOffsetComparator;
import de.tudarmstadt.ukp.dkpro.keyphrases.ranking.PageRankRanking;
import de.tudarmstadt.ukp.dkpro.keyphrases.textgraphs.CooccurrenceGraph;
import de.unidue.langtech.teaching.pp.stacha.BaselineKeywords;
import de.unidue.langtech.teaching.pp.stacha.newType.Printer;

public class KeyPipeline {

	public static void main(String[] args) throws Exception{
		
		String goldSuffix = null;
		SimplePipeline.runPipeline(
        		
                CollectionReaderFactory.createReader(
                        KeyphraseReader.class,
                        KeyphraseReader.PARAM_INPUTDIR, "src/test/resources/txt",
                        KeyphraseReader.PARAM_DATA_SUFFIX, "txt",
                        KeyphraseReader.PARAM_LANGUAGE, "en"
                )
            ,AnalysisEngineFactory.createEngineDescription(StanfordSegmenter.class) //TOKENIZATION  

            ,AnalysisEngineFactory.createEngineDescription(OpenNlpPosTagger.class)

          	,AnalysisEngineFactory.createEngineDescription(CooccurrenceGraph.class,CooccurrenceGraph.PARAM_FEATURE_PATH,Token.class,CooccurrenceGraph.PARAM_WINDOW_SIZE,5)
            ,AnalysisEngineFactory.createEngineDescription(PageRankRanking.class,PageRankRanking.PARAM_WEIGHTED, false)              

            ,AnalysisEngineFactory.createEngineDescription(CandidateAnnotator.class, CandidateAnnotator.PARAM_FEATURE_PATH, Token.class)
           	,AnalysisEngineFactory.createEngineDescription(PosSequenceFilter.class)
           //	,CooccurrenceGraphFactory.getCooccurrenceGraph_token(),createEngineDescription(PageRankRanking.class,PageRankRanking.PARAM_WEIGHTED, true)
           	
           	,AnalysisEngineFactory.createEngineDescription(KeyphraseMerger.class, KeyphraseMerger.PARAM_KEEP_PARTS, false, KeyphraseMerger.PARAM_MAX_LENGTH,4)
           	
           	
           	
           /*	,AnalysisEngineFactory.createEngineDescription(
                    KeyphraseEvaluator.class,
                    KeyphraseEvaluator.PARAM_N, 0,
                    KeyphraseEvaluator.PARAM_EVAL_TYPE, KeyphraseEvaluator.EvaluatorType.Token.toString(),
                    KeyphraseEvaluator.PARAM_REMOVE_GOLD_AFTER_MATCH, true,
                    KeyphraseEvaluator.PARAM_GOLD_SUFFIX, goldSuffix,
                    KeyphraseEvaluator.PARAM_LOWERCASE,true
            )*/
           	
           	,AnalysisEngineFactory.createEngineDescription(Printer.class)

         );
		
		//AnalysisEngineDescription graph = createEngineDescription(CooccurrenceGraphFactory.getCooccurrenceGraph_token(),createEngineDescription(PageRankRanking.class, PageRankRanking.PARAM_WEIGHTED, true));

	}

}