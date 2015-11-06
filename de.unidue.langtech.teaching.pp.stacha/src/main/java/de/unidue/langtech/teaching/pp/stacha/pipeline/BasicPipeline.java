package de.unidue.langtech.teaching.pp.stacha.pipeline;

import org.apache.uima.fit.component.CasDumpWriter;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.snowball.SnowballStemmer;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.tudarmstadt.ukp.dkpro.core.treetagger.TreeTaggerPosTagger;
import de.unidue.langtech.teaching.pp.stacha.BaselineExample;
import de.unidue.langtech.teaching.pp.stacha.EvaluatorExample;
import de.unidue.langtech.teaching.pp.stacha.ReaderExample;

public class BasicPipeline
{

    public static void main(String[] args)
        throws Exception
    {
        SimplePipeline.runPipeline(
                CollectionReaderFactory.createReader(
                        ReaderExample.class,
                        ReaderExample.PARAM_INPUT_FILE, "src/test/resources/test/input.txt"
                ),
                AnalysisEngineFactory.createEngineDescription(BaselineExample.class),
                AnalysisEngineFactory.createEngineDescription(EvaluatorExample.class),
                AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class),
                AnalysisEngineFactory.createEngineDescription(SnowballStemmer.class,SnowballStemmer.PARAM_LANGUAGE,"en"),
                //  exception  
                //AnalysisEngineFactory.createEngineDescription(TreeTaggerPosTagger.class),
                AnalysisEngineFactory.createEngineDescription(CasDumpWriter.class)
        );
    }
}
