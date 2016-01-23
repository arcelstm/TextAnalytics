package de.unidue.langtech.teaching.pp.stacha.pipeline;

import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.stopwordremover.StopWordRemover;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.unidue.langtech.teaching.pp.stacha.BaselineKeywords;
import de.unidue.langtech.teaching.pp.stacha.ReaderExample;

@SuppressWarnings("unused")
public class KeyPipeline {

	public static void main(String[] args) throws Exception{
    	
        SimplePipeline.runPipeline(
        		
                CollectionReaderFactory.createReader(
                        ReaderExample.class,
                        ReaderExample.PARAM_INPUT_FILE, "src/test/resources/test/engTexts.txt"
                )
                ,AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class, BreakIteratorSegmenter.PARAM_LANGUAGE, "en") //TOKENIZATION                
                ,AnalysisEngineFactory.createEngineDescription(StopWordRemover.class, StopWordRemover.PARAM_MODEL_LOCATION,"src/main/resources/txt/stop.txt")
                ,AnalysisEngineFactory.createEngineDescription(BaselineKeywords.class)
                
                
               
         );       
	}

	
}
