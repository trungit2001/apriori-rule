/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab06;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Debug.Random;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author ThanhTrungK15
 */

public class NaiveBayesClassifier extends Classification {
    NaiveBayes naiveBayes;
    
    public NaiveBayesClassifier() {
        naiveBayes = new NaiveBayes();
    }

    @Override
    public void fit() throws Exception {
        super.trainSet.setClassIndex(super.trainSet.numAttributes() - 1);
        naiveBayes.buildClassifier(super.trainSet);
    }

    @Override
    public void crossValidation(int k, int seed) throws Exception {
        super.testSet.setClassIndex(super.testSet.numAttributes() - 1);
        Random rd = new Random(seed);
        super.eval = new Evaluation(super.trainSet);
        super.eval.crossValidateModel(naiveBayes, super.testSet, k, rd);
    }
    
    @Override
    public void evalPerformance() throws Exception {
        super.testSet.setClassIndex(super.testSet.numAttributes() - 1);
        super.eval = new Evaluation(super.trainSet);
        eval.evaluateModel(naiveBayes, super.testSet);
    }
    
    @Override
    public Instances predict(Instances newDataset) throws Exception {
        newDataset.setClassIndex(newDataset.numAttributes() - 1);
        
        for (Instance instance: newDataset) {
            double predVal = naiveBayes.classifyInstance(instance);
            instance.setClassValue(predVal);
        }
        
        return newDataset;
    }

    public String printSummary() {
        return naiveBayes.toString();
    }
    
    public String printEvalResult() throws Exception {
        return String.format("%s\n%s", eval.toSummaryString(), eval.toMatrixString());
    }
}
