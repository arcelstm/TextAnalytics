package de.unidue.langtech.teaching.pp.stacha.pipeline;

import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordSegmenter;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.candidate.CandidateAnnotator;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.filter.PosSequenceFilter;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.postprocessing.KeyphraseMerger;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.reader.KeyphraseReader;
import de.tudarmstadt.ukp.dkpro.keyphrases.ranking.PageRankRanking;
import de.tudarmstadt.ukp.dkpro.keyphrases.textgraphs.CooccurrenceGraph;
import de.unidue.langtech.teaching.pp.stacha.newType.Printer;

public class KeyPipeline {

	public static void main(String[] args) throws Exception{
		
		SimplePipeline.runPipeline(
        		
                CollectionReaderFactory.createReader(
                        KeyphraseReader.class,
                        KeyphraseReader.PARAM_INPUTDIR, "src/test/resources/txt",
                        KeyphraseReader.PARAM_DATA_SUFFIX, "txt"
                )

            ,AnalysisEngineFactory.createEngineDescription(StanfordSegmenter.class, StanfordSegmenter.PARAM_LANGUAGE, "en",StanfordSegmenter.PARAM_STRICT_ZONING, false) //TOKENIZATION  

            ,AnalysisEngineFactory.createEngineDescription(OpenNlpPosTagger.class,OpenNlpPosTagger.PARAM_LANGUAGE, "en")

          	,AnalysisEngineFactory.createEngineDescription(CooccurrenceGraph.class,CooccurrenceGraph.PARAM_FEATURE_PATH,Token.class,CooccurrenceGraph.PARAM_WINDOW_SIZE,5)
              ,AnalysisEngineFactory.createEngineDescription(PageRankRanking.class,PageRankRanking.PARAM_WEIGHTED, false)              

            ,AnalysisEngineFactory.createEngineDescription(CandidateAnnotator.class, CandidateAnnotator.PARAM_FEATURE_PATH, Token.class)
          
           	,AnalysisEngineFactory.createEngineDescription(PosSequenceFilter.class)
           //	,CooccurrenceGraphFactory.getCooccurrenceGraph_token(),createEngineDescription(PageRankRanking.class,PageRankRanking.PARAM_WEIGHTED, true)
           	            	
           	,AnalysisEngineFactory.createEngineDescription(KeyphraseMerger.class)
           	,AnalysisEngineFactory.createEngineDescription(Printer.class)

         );
		
		//AnalysisEngineDescription graph = createEngineDescription(CooccurrenceGraphFactory.getCooccurrenceGraph_token(),createEngineDescription(PageRankRanking.class, PageRankRanking.PARAM_WEIGHTED, true));

	}

}