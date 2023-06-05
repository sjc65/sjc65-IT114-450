import java.util.Arrays;

public class Problem2 {
    public static void main(String[] args) {
        //Don't edit anything here
        double[] a1 = new double[]{10.001, 11.591, 0.011, 5.991, 16.121, 0.131, 100.981, 1.001};
        double[] a2 = new double[]{1.99, 1.99, 0.99, 1.99, 0.99, 1.99, 0.99, 0.99};
        double[] a3 = new double[]{0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01};
        double[] a4 = new double[]{10.01, -12.22, 0.23, 19.20, -5.13, 3.12};
        
        getTotal(a1);
        getTotal(a2);
        getTotal(a3);
        getTotal(a4);
    }
    static void getTotal(double[] arr){
        System.out.println("Processing Array:" + Arrays.toString(arr));
        double total = 0;
        String totalOutput = "";
        //TODO add/edit code here
        //---------UCID: sjc65---------
        //---------Date: 06/05/2023----
        /*
          Explanation: As the code iterates over each value in the array sets, the code adds
          each value to the total sum. The total is then converted to string to change the
          decimal format to the hundredth place. Then the total is printed for each array set.
        */
        double sum = 0.0;
        String numString = "";

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        numString = String.format("%.2f", sum);
        total = Double.parseDouble(numString);

        //set the double to a string variable
        totalOutput = total+"";
        //end add/edit section
        System.out.println("Total is " + totalOutput);
        System.out.println("End process");
    }
    
}
