package longestTS;

	import java.util.Random;
	 
	public class MyStringRandomGen {
	 
	    private static final String CHAR_LIST = 
	        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	    public int RANDOM_STRING_LENGTH;
	     
	    /**
	     * This method generates random string
	     * @return
	     */
	    public String generateRandomString(int l){
	         this.RANDOM_STRING_LENGTH=l;
	        StringBuffer randStr = new StringBuffer();
	        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
	            int number = getRandomNumber();
	            char ch = CHAR_LIST.charAt(number);
	            randStr.append(ch);
	        }
	        return randStr.toString();
	    }
	     
	    /**
	     * This method generates random numbers
	     * @return int
	     */
	    private int getRandomNumber() {
	        int randomInt = 0;
	        Random randomGenerator = new Random();
	        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
	        if (randomInt - 1 == -1) {
	            return randomInt;
	        } else {
	            return randomInt - 1;
	        }
	    }
	     
	    /*public static void main(String a[]){
	    	MyStringRandomGen msr = new MyStringRandomGen();
	        System.out.println(msr.generateRandomString(10));
	        System.out.println(msr.generateRandomString(10));
	        System.out.println(msr.generateRandomString(10));
	        System.out.println(msr.generateRandomString(10));
	        System.out.println(msr.generateRandomString(9));
	        System.out.println(msr.generateRandomString(5));
	        System.out.println(msr.generateRandomString(4));
	    }*/
}
