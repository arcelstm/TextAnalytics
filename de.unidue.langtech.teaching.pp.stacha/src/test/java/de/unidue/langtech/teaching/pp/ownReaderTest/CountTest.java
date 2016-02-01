package de.unidue.langtech.teaching.pp.ownReaderTest;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.junit.Assert.assertEquals;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.unidue.langtech.teaching.pp.type.MyType;

public class CountTest {

	@Test
	public void testCountE() throws Exception{

		String text = "Peter Piper picked a peck of pickled peppers.";

		// We don't have a pipeline here,
		// thus we create an empty document by hand,
		// the following utility-method call helps us
		JCas jcas = JCasFactory.createJCas();
		// We set the text to our empty document
		jcas.setDocumentText(text);

		AnalysisEngineDescription segmenter = createEngineDescription(BreakIteratorSegmenter.class);
		AnalysisEngine segEngine = createEngine(segmenter);
		segEngine.process(jcas);

		AnalysisEngineDescription countLetterE = createEngineDescription(de.unidue.langtech.teaching.pp.stacha.newType.LetterAnnotator.class);
		AnalysisEngine countE = createEngine(countLetterE);
		countE.process(jcas);

		MyType detectedLang = JCasUtil.selectSingle(jcas,MyType.class);

		assertEquals(8, detectedLang.getCountLetter());

	 }

}
