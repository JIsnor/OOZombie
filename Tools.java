public class Tools {

	//print the stack trace, used in conjunction with graceful failure throughout our code
	//courtesy of http://stackoverflow.com/questions/1069066/
	public static void printStackTrace() {
		for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
			System.out.println(ste);
		}
	}
	
	//return the integer distance between two points
	static int getDistance(int[] pointA, int[] pointB){
		if(pointA == null || pointB == null || pointA.length != 2 || pointB.length != 2){
			Tools.printStackTrace();
			System.exit(-1);
		}
		
		double distX = Math.pow(pointA[0] - pointB[0], 2);
		double distY = Math.pow(pointA[1] - pointB[1], 2);

		return (int) Math.ceil(Math.sqrt(distX + distY));
	}
}
