import java.util.*;

public class Musical11837 {
    static HashMap<String, Integer> hm = new HashMap<>();


    private static boolean runKMP(int[] original, int[] stolen) {

        //code from https://github.com/mission-peace/interview/blob/master/src/com/interview/string/SubstringSearch.java
        int[] lps = computeTemporaryArray(stolen);
        int i=0;
        int j=0;
        while(i<original.length && j<stolen.length) {
            if(original[i] == stolen[j]) {
                i++; j++;
            } else {
                if(j!=0){
                    j = lps[j-1];
                } else
                    i++;
            }
        }
        if(j==stolen.length)
            return true;
        return false;
    }

    private static int[] computeTemporaryArray(int[] stolen) {

        int [] lps = new int[stolen.length];
        int index =0;
        for(int i=1; i < stolen.length;){
            if(stolen[i] == stolen[index]){
                lps[i] = index + 1;
                index++;
                i++;
            }else{
                if(index != 0){
                    index = lps[index-1];
                }else{
                    lps[i] =0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static void main(String []args) {
        hm.put("A",0);
        hm.put("A#",1); hm.put("Bb",1);
        hm.put("B", 2);
        hm.put("Cb",2);
        hm.put("C",3);
        hm.put("B#",3);
        hm.put("Db",4); hm.put("C#",4);
        hm.put("D",5);
        hm.put("Eb",6); hm.put("D#",6);
        hm.put("E",7); hm.put("Fb",7);
        hm.put("F",8); hm.put("E#",8);
        hm.put("Gb",9); hm.put("F#",9);
        hm.put("G",10);
        hm.put("Ab",11); hm.put("G#",11);

        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int t = sc.nextInt();
        while(m!=0 && t!=0) {
            int[] real = new int[m];
            int[] fake = new int[t];
            for(int i=0; i<m; i++) {
                real[i] = hm.get(sc.next());
            }
            for(int i=0; i<t; i++){
                fake[i] = hm.get(sc.next());
            }
            int[] realDiff = new int[m-1];
            int[] fakeDiff = new int[t-1];
            for(int i=0; i<m-1; i++)
                realDiff[i] = Math.min(Math.abs(real[i]-real[i+1]),
                        (12-(Math.abs(real[i]-real[i+1])))%12);
            for(int i=0; i<t-1; i++)
                fakeDiff[i] = Math.min(Math.abs(fake[i]-fake[i+1]),
                        (12-(Math.abs(fake[i]-fake[i+1])))%12);
            System.out.println(runKMP(realDiff, fakeDiff)==true? 'S':'N');
            m = sc.nextInt();
            t = sc.nextInt();
        }
    }
}
