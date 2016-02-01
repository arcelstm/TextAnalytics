package de.unidue.langtech.teaching.pp.stacha;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.component.JCasCollectionReader_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

import de.unidue.langtech.teaching.pp.type.GoldLanguage;

/**
 * Example of a simple reader that reads a text file 
 * and puts each line of the file in a single document.
 * 
 * @author zesch
 *
 */
public class Reader
    extends JCasCollectionReader_ImplBase
{

    /**
     * Input file
     */
    public static final String PARAM_INPUT_FILE = "InputFile";
    @ConfigurationParameter(name = PARAM_INPUT_FILE, mandatory = true)
    private File inputFile;    
    
    private List<String> sentence;
    private int currentSentence;
    
    //initializes the reader
    public void initialize(UimaContext context) throws ResourceInitializationException{
    	
        super.initialize(context);
        
        try {
           sentence = FileUtils.readLines(inputFile);
           currentSentence = 0;
        }
        catch (IOException e) {
            throw new ResourceInitializationException(e);
        }
    }
    
    //true, if there is a next document, false otherwise
    public boolean hasNext() throws IOException, CollectionException{
    	return currentSentence < sentence.size();
    }
    
    //feeds the next document into the pipeline
    public void getNext(JCas jcas)throws IOException, CollectionException{
    	
        String[] parts = sentence.get(currentSentence).split(".");
        
        // it is always good to do some sanity checks
//        if (parts.length != 2) 
//        	throw new IOException("Wrong line format: " + sentence.get(currentSentence));
//        
        // add gold standard value as annotation
      //  GoldLanguage goldLanguage = new GoldLanguage(jcas);
      //  goldLanguage.addToIndexes();
        
        // add actual text of the document
        jcas.setDocumentText(parts[currentSentence]);
        currentSentence++;
    }
    
    //informs the pipeline about the current progress
    public Progress[] getProgress(){
        return new Progress[] { new ProgressImpl(currentSentence, sentence.size(), "sentence") };
    }
}