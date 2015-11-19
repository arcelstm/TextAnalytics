

/* First created by JCasGen Thu Nov 19 11:07:10 CET 2015 */
package de.unidue.langtech.teaching.pp.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Nov 19 11:07:10 CET 2015
 * XML source: C:/Users/Marcel/git/TextAnalyticsStacha/de.unidue.langtech.teaching.pp.stacha/src/main/resources/desc/type/MyType.xml
 * @generated */
public class MyType2 extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(MyType2.class);
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
  protected MyType2() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public MyType2(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public MyType2(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public MyType2(JCas jcas, int begin, int end) {
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
  //* Feature: countLetterA

  /** getter for countLetterA - gets 
   * @generated
   * @return value of the feature 
   */
  public int getCountLetterA() {
    if (MyType2_Type.featOkTst && ((MyType2_Type)jcasType).casFeat_countLetterA == null)
      jcasType.jcas.throwFeatMissing("countLetterA", "de.unidue.langtech.teaching.pp.type.MyType2");
    return jcasType.ll_cas.ll_getIntValue(addr, ((MyType2_Type)jcasType).casFeatCode_countLetterA);}
    
  /** setter for countLetterA - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCountLetterA(int v) {
    if (MyType2_Type.featOkTst && ((MyType2_Type)jcasType).casFeat_countLetterA == null)
      jcasType.jcas.throwFeatMissing("countLetterA", "de.unidue.langtech.teaching.pp.type.MyType2");
    jcasType.ll_cas.ll_setIntValue(addr, ((MyType2_Type)jcasType).casFeatCode_countLetterA, v);}    
  }

    