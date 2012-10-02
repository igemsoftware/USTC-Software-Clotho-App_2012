/*-----------------------------------------------------------*/
// This java class is for USTC Clotho App                    //
// Author: Francis Chen                                      //
// Usage: This class is mainly for Searching in G_O database //
// Copyrights Reserved                                      //
/*-----------------------------------------------------------*/

package USTC_Clotho_App;
import java.io.*;
import java.util.Vector;


public class Gene_Promoter {

	//user's matrix size
	private int matrixSize;
	
	//user's matrix
	private int[][] targetMatrix;
	
	//result matrix;
	public int[][][] result;
	
    //number of proper choice
	public int[] numberPossibleChoices = new int[1];
	
	//database matrix
	public static int[][] database;
	
	//number of genes
	public static int numOfGenes;
	
	//number of promoters
	public static int numOfPromoters;
	
	//gene names
	public static Vector<String> geneNames;
	
	//promoter names
	public static Vector<String> promoterNames;
	
	// main function of the class Gene_Promoter
	public Gene_Promoter(int Size,
						int[][] Matrix,
						int[][] DataBase,
						int NumOfGenes,
						int NumOfPromoters,
						Vector<String> GeneNames,
						Vector<String> PromoterNames)
						throws IOException{
		/*--------initializing target matrix---------*/
		matrixSize = Size;
		targetMatrix = new int[Size][Size];
		for(int i = 0; i < Size; i++){
			for(int j = 0; j < Size ; j++){
				targetMatrix[i][j] = Matrix[i][j];
			}
		}
		
		/*------initializing gene numbers and promoter numbers----*/
		numOfGenes = NumOfGenes;
		numOfPromoters = NumOfPromoters;
		
		/*------initializing database--------*/
		database = new int[numOfPromoters][numOfGenes];
		for(int i = 0; i < numOfPromoters; i++){
			for(int j = 0;j < numOfGenes; j++){
				database[i][j] = DataBase[i][j];
			}
		}
		
		/*-------initializing promoter names------*/
		promoterNames = new Vector<String>();
		for(int i = 0; i < numOfPromoters; i++){
			promoterNames.add(PromoterNames.get(i));
		}
		
		/*-------initializing gene names--------*/
		geneNames = new Vector<String>();
		for(int i = 0; i < numOfGenes; i++){
			geneNames.add(GeneNames.get(i));
		}
	
		/*---------initializing choicesPoolOfRows and choicesPoolOfColumns--*/
		int[] choicesPoolOfRows = new int[numOfPromoters];
		for(int i = 0;i <  numOfPromoters;i++){
			choicesPoolOfRows[i] = i;
		}
		int[] choicesPoolOfColumns = new int[numOfGenes];
		for(int i = 0; i < numOfGenes; i++){
			choicesPoolOfColumns[i] = i;
		}
	
		
		/*------------initializing some parameters------------------*/		
		int numberOfRowChoicesInPool = numOfPromoters; 
		int numberOfColmunsChoicesInPool = numOfGenes;
		int numberOfChoicesToBeChosen = matrixSize;
		numberPossibleChoices[0] = 0;
		
		
		/*----------------------generate result matrix------------------*/
		result = findMatrixRecursion2(database,targetMatrix,choicesPoolOfRows,choicesPoolOfColumns,numberOfRowChoicesInPool,numberOfColmunsChoicesInPool,numberOfChoicesToBeChosen,numberPossibleChoices);
	}


	//==============================================================================//
    //                                                                              //
    //  findMatrixRecurssion2 method                                                //
    //  purpose: find small matrix in a big matrix                                  //
    //  it uses recursion algorithm to find MxM matrix in NxN matrix, based on      //
    //      recursion of finding (M-1)x(M-1) matrix in (N-1)x(N-1) matrix           //
    //  parameters:                                                                 //
    //          databaseMatrix is the global giant matrix                           //
    //          targetMatrix is the current matrix to be find						//
	//																				//
    //          choicesPoolOfRows contains the indices of rows can be chosen		//
	//			choicesPoolOfColumns contains the indices of columns can be chosen	//	
    //																				//
	//			numberOfRowChoicesInPool is the size of choicesPool of rows			//
	//			numberOfColumnChoicesInPool is the size of choicesPool of columns	//
    //																				//
	//			numberOfChoicesToBeChosen is the size of targetMatrix               //
    //          numberOfPossibleChoicesSets is the number of possible solutions     //
    //  return value:                                                               //
    //          return value is the 2-d array that contains the possible solutions  //
    //                                                                              //
    //==============================================================================//
	public static int[][][] findMatrixRecursion2(
			int[][] databaseMatrix,
			int[][] targetMatrix,
			int[] choicesPoolOfRows,
			int[] choicesPoolOfColumns,
			int numberOfRowChoicesInPool,
			int numberOfColmunsChoicesInPool,
			int numberOfChoicesToBeChosen,
			int[] numberPossibleChoices
			){
		
		if(numberOfChoicesToBeChosen == 1){
			int numPossible = 0;
			Vector<int[][]> choicesVector = new Vector<int[][]>();
			for(int i = 0;i < numberOfRowChoicesInPool;i++){
				for(int j = 0;j<numberOfColmunsChoicesInPool;j++){
					if(databaseMatrix[choicesPoolOfRows[i]][choicesPoolOfColumns[j]] == 2 ||
					   databaseMatrix[choicesPoolOfRows[i]][choicesPoolOfColumns[j]] ==targetMatrix[0][0] ){
						numPossible ++;
						int[][] aChoice = new int[2][1];
						aChoice[0][0] = choicesPoolOfRows[i];
						aChoice[1][0] = choicesPoolOfColumns[j];
						choicesVector.add(aChoice);
					}
					
				}
			}
			int[][][] choices = new int[numPossible][2][1];
			for(int i = 0;i < numPossible; i++){
				choices[i] =(int[][]) (choicesVector.toArray())[i];
			}
			numberPossibleChoices[0] = numPossible;
			return choices;
		}
		
		
	int[][] newTargetMatrix = new int[numberOfChoicesToBeChosen-1][numberOfChoicesToBeChosen-1];
	int numOfWorkingChoices = 0;
	Vector<int[][]> choicesVector = new Vector<int[][]>();
	
	for(int i = 0;i < numberOfChoicesToBeChosen - 1;i++){
		for(int j = 0;j < numberOfChoicesToBeChosen - 1;j++){
			newTargetMatrix[i][j] = targetMatrix[i+1][j+1];
		}
	}
	
	int[][] firstElement = new int[2][1];
	for(int i = 0;i < numberOfRowChoicesInPool; i++){
		for(int j = 0;j < numberOfColmunsChoicesInPool;j++){
			if(databaseMatrix[choicesPoolOfRows[i]][choicesPoolOfColumns[j]] != 2&&
			   databaseMatrix[choicesPoolOfRows[i]][choicesPoolOfColumns[j]] != targetMatrix[0][0]){
			continue;}
		firstElement[0][0] = choicesPoolOfRows[i];
		firstElement[1][0] = choicesPoolOfColumns[j];	
		
		int[] newPoolOfRow = new int[numberOfRowChoicesInPool-1];
		for(int h = 0, k = 0; h < numberOfRowChoicesInPool; h ++){
			if( i != h){
				newPoolOfRow[k] = choicesPoolOfRows[h];
				k++;
			}
		}
		
		int[] newPoolOfColumn = new int[numberOfColmunsChoicesInPool - 1];
		for(int g = 0, k = 0; g < numberOfColmunsChoicesInPool; g ++){
			if( j != g){
				newPoolOfColumn[k] = choicesPoolOfColumns[g];
				k++;
			}
		}
		int[] numberOfNewSets = new int[1];
		numberOfNewSets[0] = 0; 
		int[][][]possibleSolutions = findMatrixRecursion2(databaseMatrix,newTargetMatrix,newPoolOfRow,newPoolOfColumn,numberOfRowChoicesInPool-1,numberOfColmunsChoicesInPool-1,numberOfChoicesToBeChosen-1,numberOfNewSets);
		
		for(int k = 0;k < numberOfNewSets[0];k++){
			boolean work = true;
			for(int m = 0; m < numberOfChoicesToBeChosen-1;m++){
				if((databaseMatrix[firstElement[0][0]][possibleSolutions[k][1][m]] != 2 &&
	               targetMatrix[0][m + 1] != databaseMatrix[firstElement[0][0]][possibleSolutions[k][1][m]]) ||
	               (databaseMatrix[possibleSolutions[k][0][m]][firstElement[1][0]] != 2 &&
	               targetMatrix[m + 1][0] != databaseMatrix[possibleSolutions[k][0][m]][firstElement[1][0]]) )
				{
					work = false;
					break;
				}
			}
			if(work){
				numOfWorkingChoices ++;
				int[][] aChoice = new int[2][numberOfChoicesToBeChosen];
				aChoice[0][0] = firstElement[0][0];
				aChoice[1][0] = firstElement[1][0];
				for(int n = 1; n < numberOfChoicesToBeChosen; n++ ){
						aChoice[0][n] = possibleSolutions[k][0][n - 1];
						aChoice[1][n] = possibleSolutions[k][1][n - 1];
					}
					choicesVector.add(aChoice);
				}
				
			}
		}
	}
		
	numberPossibleChoices[0] = numOfWorkingChoices;
	int[][][] choices = new int[numOfWorkingChoices][2][numberOfChoicesToBeChosen];
	for(int i =  0;i < numOfWorkingChoices;i++){
		choices[i] = (int[][])choicesVector.toArray()[i];
	}
	return choices;
	}
}