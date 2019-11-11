package project.utils;

/**
 * 
 * @author RuiMenoita
 */
public class ArrayUtil {



	

	/**
	 * 
	 * @param array array that have the item
	 * @param item	item to be searched for
	 * @return 
	 * returns the first occurrence index in array, returns -1 if item is not present in array
	 */
	public static <T> int getIndex(T[] array , T item) {
		for (int i = 0; i < array.length; i++) {
			if(array[i] != null) {
				if(array[i].equals(item))
					return i;
			}
		}
		return -1;
	}


	

	/**
	 * 
	 * @param array array that have the item
	 * @param item	item to be searched for
	 * @return 
	 * returns the first occurrence index in array, returns -1 if item is not present in array
	 */
	public static int getIndex(int[] array , int item) {
		for (int i = 0; i < array.length; i++) {
				if(array[i]==(item))
					return i;
		}
		return -1;
	}



	

	/**
	 * 
	 * @param array array that have the item
	 * @param item	item to be searched for
	 * @return 
	 * returns the first occurrence index in array, returns -1 if item is not present in array
	 */
	public static int getIndex(String[] array , String item) {
		for (int i = 0; i < array.length; i++) {
			if(array[i] != null) {
				if(array[i].equals(item))
					return i;
			}
		}
		return -1;
	}

	
	
	

	/**
	 * @param array array to be checked
	 * @param value value to be checked
	 * @return return true if the array given as the value given
	 */
	public static boolean contains(String[] array , String value) {
		boolean has = false;
		for (String string : array) 
			if(string != null)
				if(string.equals(value))
					has = true;
		return has;
	}






	/**
	 * @param array array to be checked
	 * @param value value to be checked
	 * @return return true if the array given as the value given
	 */
	public static boolean contains(int[] array , int value) {
		boolean has = false;
		for (int string : array) 
			if(string == value)
				has = true;
		return has;
	}






	/**
	 * @param array array to be checked
	 * @param value value to be checked
	 * @return return true if the array given as the value given
	 */
	public static boolean contains(double[] array , double value) {
		boolean has = false;
		for (double string : array) 
			if(string == value)
				has = true;
		return has;
	}






	/**
	 * @param array array to be checked
	 * @param value value to be checked
	 * @return return true if the array given as the value given
	 */
	public static <T> boolean contains(T[] array , T value) {
		boolean has = false;
		for (T string : array) 
			if(string != null)
				if(string.equals(value))
					has = true;
		return has;
	}







	/**
	 * This method shifts right the given array 
	 * the last position became the first 
	 * 
	 * @param arr array to be shifted
	 */
	public static <T> void shiftRight(T[] arr) {
		T buffer = arr[arr.length-1];
		for (int i = arr.length-1; i >= 0; i--) {

			if(i>0)  arr[i] = arr[i-1];
			else arr[0] = buffer;
		}
	}





	/**
	 * This method shifts right the given array 
	 * the last position became the first 
	 * 
	 * @param arr array to be shifted
	 */
	public static void shiftRight(int[] arr) {
		int buffer = arr[arr.length-1];
		for (int i = arr.length-1; i >= 0; i--) {

			if(i>0)  arr[i] = arr[i-1];
			else arr[0] = buffer;
		}
	}





	/**
	 * This method shifts right the given array 
	 * the last position became the first 
	 * 
	 * @param arr array to be shifted
	 */
	public static void shiftRight(String[] arr) {
		String buffer = arr[arr.length-1];
		for (int i = arr.length-1; i >= 0; i--) {

			if(i>0)  arr[i] = arr[i-1];
			else arr[0] = buffer;
		}
	}





	/**
	 * This method shifts right the given array 
	 * the last position became the first 
	 * 
	 * @param arr array to be shifted
	 */
	public static void shiftRight(double[] arr) {
		double buffer = arr[arr.length-1];
		for (int i = arr.length-1; i >= 0; i--) {

			if(i>0)  arr[i] = arr[i-1];
			else arr[0] = buffer;
		}
	}





	/**
	 * This method shifts right the given array 
	 * the last position became the first 
	 * 
	 * @param arr array to be shifted
	 */
	public static void shiftRight(long[] arr) {
		long buffer = arr[arr.length-1];
		for (int i = arr.length-1; i >= 0; i--) {

			if(i>0)  arr[i] = arr[i-1];
			else arr[0] = buffer;
		}
	}





	//to teste and debug
	public static void main(String[] args) {
		String s = "C:\\Users\\Rui Menoita\\Downloads\\Long-Method.xlsx";
		String[] sp = s.split("\\\\");
		for (String string : sp) 
			System.out.println(string);
	}

}
