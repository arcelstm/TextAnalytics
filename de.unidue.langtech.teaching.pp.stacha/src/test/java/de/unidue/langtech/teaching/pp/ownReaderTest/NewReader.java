package de.unidue.langtech.teaching.pp.ownReaderTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.component.JCasCollectionReader_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unidue.langtech.teaching.pp.type.GoldLanguage;

public class NewReader
    extends JCasCollectionReader_ImplBase
{
    public static final String PARAM_INPUT_FILE = "InputFile";
    @ConfigurationParameter(name = PARAM_INPUT_FILE, mandatory = true)
    private File inputFile;

    private List<String> lines;
    private int currentLine;
  
    public void initialize(UimaContext context) throws ResourceInitializationException{
        super.initialize(context);

        try {
            lines = FileUtils.readLines(inputFile);
            currentLine = 0;
        }
        catch (IOException e) {
            throw new ResourceInitializationException(e);
        }
    }

    public boolean hasNext() throws IOException, CollectionException{
         return currentLine < lines.size();
    }

    public Progress[] getProgress()
    {
        return null;
    }

    public void getNext(JCas aJCas) throws IOException, CollectionException{
    
    	List<String> text = new ArrayList<String>();
    	String nextLine=null;
    	
    	for(;currentLine < lines.size(); currentLine++){
    	
    	   nextLine = lines.get(currentLine);
    	   
    	   if(nextLine.isEmpty()){
    		   currentLine++;
    		   break;
    	   }
    	
    	   text.add(nextLine);
    	}
    	
    	GoldLanguage goldLanguage = new GoldLanguage(aJCas);
    	goldLanguage.setLanguage(text.get(0));
    	//System.out.println(text.get(0));
    	goldLanguage.addToIndexes();
    	
    	String document ="";
    	
    	for(int i=1;i<text.size();i++){
    		String word = text.get(i);
    		document+=word;
    		
       		 int start = document.length() - word.length();
             int end = document.length();
             Token t = new Token(aJCas, start, end);
             t.addToIndexes();
             
    		document+=" ";
    	}
    	aJCas.setDocumentText(document.trim());
    }

}
