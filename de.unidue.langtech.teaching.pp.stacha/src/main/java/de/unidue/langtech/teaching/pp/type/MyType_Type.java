
/* First created by JCasGen Thu Sep 04 09:09:22 CEST 2014 */
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
 * Updated by JCasGen Thu Nov 19 11:07:10 CET 2015
 * @generated */
public class MyType_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (MyType_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = MyType_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new MyType(addr, MyType_Type.this);
  			   MyType_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new MyType(addr, MyType_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = MyType.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.unidue.langtech.teaching.pp.type.MyType");
 
  /** @generated */
  final Feature casFeat_countLetter;
  /** @generated */
  final int     casFeatCode_countLetter;
public int casFeatCode_countLetterE;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getcountLetter(int addr) {
        if (featOkTst && casFeat_countLetter == null)
      jcas.throwFeatMissing("countLetter", "de.unidue.langtech.teaching.pp.type.MyType");
    return ll_cas.ll_getIntValue(addr, casFeatCode_countLetter);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCountLetter(int addr, int v) {
        if (featOkTst && casFeat_countLetter == null)
      jcas.throwFeatMissing("countLetter", "de.unidue.langtech.teaching.pp.type.MyType");
    ll_cas.ll_setIntValue(addr, casFeatCode_countLetter, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public MyType_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_countLetter = jcas.getRequiredFeatureDE(casType, "countLetter", "uima.cas.Integer", featOkTst);
    casFeatCode_countLetter  = (null == casFeat_countLetter) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_countLetter).getCode();

  }
}



    