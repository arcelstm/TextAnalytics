package de.unidue.langtech.teaching.pp.stacha.pipeline;

import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.snowball.SnowballStemmer;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordNamedEntityRecognizer;
import de.tudarmstadt.ukp.dkpro.core.stopwordremover.StopWordRemover;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.tudarmstadt.ukp.dkpro.keyphrases.core.reader.KeyphraseReader;
import de.unidue.langtech.teaching.pp.stacha.BaselineKeywords;

public class KeyPipeline {

	public static void main(String[] args) throws Exception{
    	
        SimplePipeline.runPipeline(
        		
                CollectionReaderFactory.createReader(
                        KeyphraseReader.class,
                        KeyphraseReader.PARAM_INPUTDIR, "src/test/resources/txt",
                        KeyphraseReader.PARAM_DATA_SUFFIX, "txt"
                )
                ,AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class, BreakIteratorSegmenter.PARAM_LANGUAGE, "en") //TOKENIZATION  
                ,AnalysisEngineFactory.createEngineDescription(SnowballStemmer.class,SnowballStemmer.PARAM_LANGUAGE,"en") //STEMMING                
                ,AnalysisEngineFactory.createEngineDescription(OpenNlpPosTagger.class,OpenNlpPosTagger.PARAM_LANGUAGE, "en")
                //,AnalysisEngineFactory.createEngineDescription(StanfordNamedEntityRecognizer.class, StanfordNamedEntityRecognizer.PARAM_LANGUAGE,"en")
                ,AnalysisEngineFactory.createEngineDescription(StopWordRemover.class, StopWordRemover.PARAM_MODEL_LOCATION,"src/test/resources/stopwords/stop.txt")
                ,AnalysisEngineFactory.createEngineDescription(BaselineKeywords.class, BaselineKeywords.PARAM_LANGUAGE, "en")
                //,AnalysisEngineFactory.createEngineDescription(EvaluatorExample.class)
                
                
         );       
	}

}