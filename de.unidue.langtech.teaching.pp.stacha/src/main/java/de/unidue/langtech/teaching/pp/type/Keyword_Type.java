
/* First created by JCasGen Fri Jan 22 11:43:51 CET 2016 */
package de.unidue.langtech.teaching.pp.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Fri Jan 22 11:43:51 CET 2016
 * @generated */
public class Keyword_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Keyword_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Keyword_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Keyword(addr, Keyword_Type.this);
  			   Keyword_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Keyword(addr, Keyword_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Keyword.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.unidue.langtech.teaching.pp.type.Keyword");
 
  /** @generated */
  final Feature casFeat_Candidate;
  /** @generated */
  final int     casFeatCode_Candidate;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getCandidate(int addr) {
        if (featOkTst && casFeat_Candidate == null)
      jcas.throwFeatMissing("Candidate", "de.unidue.langtech.teaching.pp.type.Keyword");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Candidate);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCandidate(int addr, int v) {
        if (featOkTst && casFeat_Candidate == null)
      jcas.throwFeatMissing("Candidate", "de.unidue.langtech.teaching.pp.type.Keyword");
    ll_cas.ll_setRefValue(addr, casFeatCode_Candidate, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Keyword_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Candidate = jcas.getRequiredFeatureDE(casType, "Candidate", "uima.tcas.Annotation", featOkTst);
    casFeatCode_Candidate  = (null == casFeat_Candidate) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Candidate).getCode();

  }
}



    