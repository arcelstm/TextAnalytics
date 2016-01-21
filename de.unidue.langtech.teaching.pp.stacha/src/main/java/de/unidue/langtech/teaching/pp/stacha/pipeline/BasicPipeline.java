package de.unidue.langtech.teaching.pp.stacha.pipeline;

import org.apache.uima.fit.component.CasDumpWriter;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.snowball.SnowballStemmer;
import de.tudarmstadt.ukp.dkpro.core.stopwordremover.StopWordRemover;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.unidue.langtech.teaching.pp.stacha.BaselineCandidate;
import de.unidue.langtech.teaching.pp.stacha.BaselineExample;
import de.unidue.langtech.teaching.pp.stacha.EvaluatorExample;
import de.unidue.langtech.teaching.pp.stacha.ReaderExample;
import de.unidue.langtech.teaching.pp.stacha.newType.LetterAnnotator;
import de.unidue.langtech.teaching.pp.stacha.newType.Printer;

@SuppressWarnings("unused")
public class BasicPipeline{
	
    public static void main(String[] args) throws Exception{
    	    	
        SimplePipeline.runPipeline(
        		
                CollectionReaderFactory.createReader(
                        ReaderExample.class,
                        ReaderExample.PARAM_INPUT_FILE, "src/test/resources/test/input.txt"
                ),
              //  AnalysisEngineFactory.createEngineDescription(BaselineExample.class),
                AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class) //TOKENIZATION
                //,AnalysisEngineFactory.createEngineDescription(SnowballStemmer.class,SnowballStemmer.PARAM_LANGUAGE,"en") //STEMMING
                ,AnalysisEngineFactory.createEngineDescription(StopWordRemover.class, StopWordRemover.PARAM_MODEL_LOCATION,"src/main/resources/txt/stop.txt")     
                ,AnalysisEngineFactory.createEngineDescription(BaselineExample.class,BaselineExample.PARAM_MESSAGE, "BaselineExample.PARAM_MESSAGE")
                ,AnalysisEngineFactory.createEngineDescription(LetterAnnotator.class)
                ,AnalysisEngineFactory.createEngineDescription(EvaluatorExample.class)
                ,AnalysisEngineFactory.createEngineDescription(Printer.class) //More Console Output
                ,AnalysisEngineFactory.createEngineDescription(BaselineCandidate.class)
           
              
              	
             //AnalysisEngineFactory.createEngineDescription(CasDumpWriter.class)
        );
                
        
    }
}
