package de.unidue.langtech.teaching.pp.stacha.pipeline;

import org.apache.uima.fit.component.CasDumpWriter;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.uima.analysis_component.AnalysisComponent;

import de.tudarmstadt.ukp.dkpro.core.snowball.SnowballStemmer;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.tudarmstadt.ukp.dkpro.core.treetagger.TreeTaggerPosTagger;
import de.unidue.langtech.teaching.pp.ownReaderTest.NewReaderSolution;
import de.unidue.langtech.teaching.pp.ownReaderTest.NewReaderTest;
import de.unidue.langtech.teaching.pp.stacha.BaselineExample;
import de.unidue.langtech.teaching.pp.stacha.EvaluatorExample;
import de.unidue.langtech.teaching.pp.stacha.ReaderExample;
import de.unidue.langtech.teaching.pp.stacha.newType.LetterAnnotator;

public class BasicPipeline{
	
    public static void main(String[] args) throws Exception{
    	
        SimplePipeline.runPipeline(
        		
                CollectionReaderFactory.createReader(
                        ReaderExample.class,
                        ReaderExample.PARAM_INPUT_FILE, "src/test/resources/test/input.txt"
                ),
                //AnalysisEngineFactory.createEngineDescription(BaselineExample.class),
                AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class),             
                AnalysisEngineFactory.createEngineDescription(BaselineExample.class,BaselineExample.PARAM_MESSAGE, "BaselineExample.class,BaselineExample.PARAM_MESSAGE"),
                AnalysisEngineFactory.createEngineDescription(LetterAnnotator.class),
                AnalysisEngineFactory.createEngineDescription(EvaluatorExample.class),
                //AnalysisEngineFactory.createEngineDescription(OpenNlpPosTagger.class),
                AnalysisEngineFactory.createEngineDescription(SnowballStemmer.class,SnowballStemmer.PARAM_LANGUAGE,"en")
                //AnalysisEngineFactory.createEngineDescription(TreeTaggerPosTagger.class),
                //AnalysisEngineFactory.createEngineDescription(CasDumpWriter.class)
        );
                
                  
        
    }
}
