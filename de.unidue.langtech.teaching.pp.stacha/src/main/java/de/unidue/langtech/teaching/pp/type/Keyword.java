

/* First created by JCasGen Fri Jan 22 11:43:51 CET 2016 */
package de.unidue.langtech.teaching.pp.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Jan 22 11:43:51 CET 2016
 * XML source: C:/Users/marc1pan/git/TextAnalyticsStacha/de.unidue.langtech.teaching.pp.stacha/src/main/resources/desc/type/Keyword.xml
 * @generated */
public class Keyword extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Keyword.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Keyword() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Keyword(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Keyword(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Keyword(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: Candidate

  /** getter for Candidate - gets 
   * @generated
   * @return value of the feature 
   */
  public Annotation getCandidate() {
    if (Keyword_Type.featOkTst && ((Keyword_Type)jcasType).casFeat_Candidate == null)
      jcasType.jcas.throwFeatMissing("Candidate", "de.unidue.langtech.teaching.pp.type.Keyword");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Keyword_Type)jcasType).casFeatCode_Candidate)));}
    
  /** setter for Candidate - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCandidate(Annotation v) {
    if (Keyword_Type.featOkTst && ((Keyword_Type)jcasType).casFeat_Candidate == null)
      jcasType.jcas.throwFeatMissing("Candidate", "de.unidue.langtech.teaching.pp.type.Keyword");
    jcasType.ll_cas.ll_setRefValue(addr, ((Keyword_Type)jcasType).casFeatCode_Candidate, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    