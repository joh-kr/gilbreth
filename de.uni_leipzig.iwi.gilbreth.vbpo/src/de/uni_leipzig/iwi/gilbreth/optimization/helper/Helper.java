package de.uni_leipzig.iwi.gilbreth.optimization.helper;

public class Helper {
	
	public static String toString(Object[] vector){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int j = 0; j < vector.length - 1; j++) {
			sb.append(vector[j].toString() + ", ");
		}
		sb.append(vector[vector.length - 1].toString() + "]\n");
		
		return sb.toString();
	}

	public static String toString(Object[][] matrix){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < matrix.length; i++) {
			sb.append("| ");
			for (int j = 0; j < matrix[0].length - 1; j++) {
				sb.append(matrix[i][j].toString() + ", ");
			}
			sb.append(matrix[i][matrix[0].length - 1].toString() + " |\n");
		}
		return sb.toString();
	}
	
	public static String toString(boolean[] vector){
		Object[] o = new Object[vector.length];
		for(int i = 0; i < vector.length; i++){
			o[i] = vector[i] ? 1 : 0;
		}
		return toString(o);
	}
	
	
	public static String toString(boolean[][] matrix){
		Object[][] o = new Object[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				o[i][j] = matrix[i][j] ? 1 : 0;
			}
		}
		return toString(o);
	}
	
	
	public static String toString(int[] q) {
		Object[] o = new Object[q.length];
		for(int i = 0; i < q.length; i++){
			o[i] = q[i];
		}
		return toString(o);
	}

	public static String toString(double[] q) {
		Object[] o = new Object[q.length];
		for(int i = 0; i < q.length; i++){
			o[i] = q[i];
		}
		return toString(o);
	}
	
	public static String toString(double[][] matrix){
		Object[][] o = new Object[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				o[i][j] = matrix[i][j];
			}
		}
		return toString(o);
	}

}
