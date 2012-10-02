/*------------------------------------------------------------*/
// This java class is for USTC Clotho App                     //
// Author: Francis Chen                                       //
// Usage: This class is mainly for Searching in O_O database  //
// Copyrights Reserved                                        //
/*------------------------------------------------------------*/

package USTC_Clotho_App;
import java.io.*;

import java.util.Vector;

public class Operon_Operon {
	
	
	//user's matrix size
	private int matrixSize;
		
	//user's matrix
	private int[][] targetMatrix;
		
	//result matrix
	public int[][] result;
		
	//number of proper choice
	public int[] numberPossibleChoices = new int[1];
	
	
	//database matrix
	public static int[][] database;
	
	//number of operons
	public static int numOfOperons;
	
	//operon names
	public static Vector<String> operonNames;
	
	//constructor of class Operon_Operon
	public Operon_Operon(int Size,
						int[][] Matrix,
						int[][] DataBase,
						int NumOfOperons,
						Vector<String> OperonNames) 
						throws IOException{
		/*------initializing target Matrix-----*/
		matrixSize= Size;
		targetMatrix = new int[Size][Size];
		for(int i = 0; i < Size ; i++){
			for(int j = 0; j < Size; j++){
				targetMatrix[i][j] = Matrix[i][j];
			}
		}
		
		
		/*-------initializing operon numbers------*/
		numOfOperons = NumOfOperons;
		
		
		/*-------initializing database -------*/
		database = new int[numOfOperons][numOfOperons];
		for(int i = 0;i < numOfOperons; i++){
			for(int j = 0; j < numOfOperons; j++){
				database[i][j] = DataBase[i][j];
			}
		}
		
		/*--------initializing operon names------*/
		operonNames = new Vector<String>();
		for(int i = 0;i < numOfOperons; i++){
			operonNames.add(OperonNames.get(i));
		}
		
		/*---------initializing choicePool-------*/
		int[] choicesPool = new int[numOfOperons];
		for(int i = 0;i < numOfOperons; i++ ){
			choicesPool[i] = i;
		}
		
		
		/*------------initializing some parameters------------------*/
		int numberOfChoicesInPool = numOfOperons;
		int numberOfChoicesToBeChosen = matrixSize;
		numberPossibleChoices[0] = 0;
		


 		
		/*----------------------generate result matrix---------------------------*/
		result = findMatrixRecursion(database,targetMatrix,choicesPool,numberOfChoicesInPool,numberOfChoicesToBeChosen,numberPossibleChoices);
		
		/*
		System.out.print("\n"+numberPossibleChoices[0]+"\n");
		for(int i = 0;i < numberPossibleChoices[0]; i++){
			System.out.print(i+"\n");
			for(int j = 0;j < matrixSize ; j++){
				System.out.print(result[i][j]+"\t");
				System.out.println(operonNames.get(result[i][j])+"\t");
			}
			System.out.print("\n\n");
		}
		*/

	}
	
	//==============================================================================//
	//  //
	//findMatrixRecurssion method                                                 //
	//purpose: find small matrix in a big matrix                                  //
	//it uses recursion algorithm to find MxM matrix in NxN matrix, based on     //
	//recursion of finding (M-1)x(M-1) matrix in (N-1)x(N-1) matrix           //
	//parameters:                                                                 //
	//databaseMatrix is the global giant matrix                           //
	//targetMatrix is the current matrix to be find                       //
	//choicesPool contains the indices of rows or columns can be chosen   //
	//numberOfChoicesInPool is the size of choicesPool                    //
	//numberOfChoicesToBeChosen is the size of targetMatrix               //
	//numberOfPossibleChoicesSets is the number of possible solutions     //
	//return value:                                                               //
	//return value is the 2-d array that contains the possible solutions  //
	//  //
	//==============================================================================//
	
	public static int[][] findMatrixRecursion(
			int[][] databaseMatrix,
			int[][] targetMatrix,
			int[] choicesPool,
			int numberOfChoicesInPool,
			int numberOfChoicesToBeChosen,
			int[] numberPossibleChoices
			){
		if(numberOfChoicesToBeChosen == 1){
			int numPossible = 0;
			Vector<int[]> choicesVector = new Vector<int[]>();
			for(int i = 0; i < numberOfChoicesInPool; i ++){
				if(databaseMatrix[choicesPool[i]][choicesPool[i]] == 2 || databaseMatrix[choicesPool[i]][choicesPool[i]] == targetMatrix[0][0]){
					numPossible ++;
					int[] aChoice = new int [1];
					aChoice[0] = choicesPool[i];
					choicesVector.add(aChoice);
				}
			}
		
			int[][] choices = new int[numPossible][1];//?
			for(int i = 0; i < numPossible; i++){
			choices[i] = (int[])(choicesVector.toArray())[i];
			}
			numberPossibleChoices[0] = numPossible;
			return choices;
		}
	
		int[][] newTargetMatrix = new int[numberOfChoicesToBeChosen-1][numberOfChoicesToBeChosen-1];
		int numOfWorkingChoices = 0;
		Vector<int[]> choicesVector = new Vector<int[]>();
	
		for(int i = 0;i < numberOfChoicesToBeChosen - 1;i ++){
			for(int j = 0;j < numberOfChoicesToBeChosen - 1;j ++){
				newTargetMatrix[i][j] = targetMatrix[i + 1][j + 1];
			}
		}
	
		for(int i = 0; i < numberOfChoicesInPool; i ++){
			int firstElement = choicesPool[i];
			if(databaseMatrix[choicesPool[i]][choicesPool[i]] != 2 && databaseMatrix[choicesPool[i]][choicesPool[i]] != targetMatrix[0][0]){
			continue;
			}
		
			int[] newPool = new int[numberOfChoicesInPool-1];
			for(int h = 0, k = 0; h < numberOfChoicesInPool; h ++){
				if( i != h){
					newPool[k] = choicesPool[h];
					k++;
				}
			}
			int[] numberOfNewSets = new int[1];
			numberOfNewSets[0] = 0;
			int[][] possibleSolutions = findMatrixRecursion(databaseMatrix,newTargetMatrix,newPool,numberOfChoicesInPool - 1,numberOfChoicesToBeChosen - 1,numberOfNewSets);
			for(int k = 0; k < numberOfNewSets[0]; k++){
				boolean work = true;
				for(int m = 0; m < numberOfChoicesToBeChosen - 1; m ++){
					if((databaseMatrix[firstElement][possibleSolutions[k][m]] != 2 && targetMatrix[0][m + 1] != databaseMatrix[firstElement][possibleSolutions[k][m]]) || (databaseMatrix[possibleSolutions[k][m]][firstElement] != 2 && targetMatrix[m + 1][0] != databaseMatrix[possibleSolutions[k][m]][firstElement])){
						work = false;
						break;
					}
				}
			
				if(work) {
					numOfWorkingChoices ++;
					int[] aChoice = new int[numberOfChoicesToBeChosen];
					aChoice[0] = firstElement;
					for(int n = 1; n < numberOfChoicesToBeChosen; n ++){
						aChoice[n] = possibleSolutions[k][n-1];
					}
					choicesVector.add(aChoice);
				}
			}
		}
		numberPossibleChoices[0] = numOfWorkingChoices;
		int[][] choices = new int[numOfWorkingChoices][numberOfChoicesToBeChosen];
		for(int i = 0; i < numOfWorkingChoices;i++){
			choices[i] = (int[])choicesVector.toArray()[i];
		}
		return choices;
	}
}





















