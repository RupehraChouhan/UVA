import java.util.*;

//Most of the code is from competitive programming

public class GATTACA11512 {
    static int maxLength=10010;
    static int n;       //length of input string
    static int[] RA, tempRA; //rank array and temporary rank array
    static int[] SA, tempSA; //suffix array and temporary suffix array
    static int[] c, Phi, PLCP, LCP; //for counting;
    static char[] T;
    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for(int t=0; t<testCases; t++) {
            String str = sc.next();
            n = str.length() + 1;
            T = new char[n];
            for (int i = 0; i < str.length(); i++)
                T[i] = str.charAt(i);
            T[n - 1] = '$';
            constructSuffixArray();
            computeLCP();
            String lrs = LRS();
            int count = 0;
            for (int i = 0; i < n; i++) {
                String suffix = new String(T, SA[i], T.length - SA[i]);
                if (suffix.indexOf(lrs) == 0)
                    count++;
            }
            if(lrs.equals(""))
                System.out.println("No repetitions found!");
            else
                System.out.println(lrs + " " + count);
        }
    }

    private static String LRS() {
        int i, idx = 0, maxLCP = 0;

        for (i = 1; i < n; i++) {                                           // O(n)
            if (LCP[i] > maxLCP) {
                maxLCP = LCP[i];
                idx = i;
            }
        }
        return new String(T).substring(SA[idx], SA[idx]+maxLCP);
    }

    private static void computeLCP() {
        Phi = new int[maxLength]; PLCP = new int[maxLength]; LCP = new int[maxLength];
        int i, L;
        Phi[SA[0]] = -1;                                              // default value
        for (i = 1; i < n; i++)                                 // compute Phi in O(n)
            Phi[SA[i]] = SA[i-1];         // remember which suffix is behind this suffix
        for (i = L = 0; i < n; i++) {                  // compute Permuted LCP in O(n)
            if (Phi[i] == -1) { PLCP[i] = 0; continue; }                 // special case
            while (i + L < T.length && Phi[i] + L < T.length && T[i + L] == T[Phi[i] + L]) L++; // L will be increased max n times
            PLCP[i] = L;
            L = Math.max(L-1, 0);                          // L will be decreased max n times
        }
        for (i = 1; i < n; i++)                                 // compute LCP in O(n)
            LCP[i] = PLCP[SA[i]];   // put the permuted LCP back to the correct position
    }

    private static void constructSuffixArray() {
        RA = new int[maxLength]; SA = new int[maxLength]; int i,r;
        tempRA = new int[maxLength]; tempSA = new int[maxLength];
        for(i=0; i<n; i++)  //initial rankings
            RA[i] = T[i];
        for(i=0; i<n; i++)
            SA[i] = i;
        for(int k=1; k<n; k<<=1) {
            countingSort(k);
            countingSort(0);
            tempRA[SA[0]] = r = 0;                  // re-ranking; start from rank r = 0
            for (i = 1; i < n; i++)                         // compare adjacent suffices
                tempRA[SA[i]] =      // if same pair => same rank r; otherwise, increase r
                        (RA[SA[i]] == RA[SA[i-1]] && RA[SA[i]+k] == RA[SA[i-1]+k]) ? r : ++r;
            for (i = 0; i < n; i++)                          // update the rank array RA
                RA[i] = tempRA[i];
        }
    }

    private static void countingSort(int k) {
        int i, sum, maxi = Math.max(300, n);
        c = new int[maxLength];
        for (i = 0; i < n; i++)                    // count the frequency of each rank
            c[i + k < n ? RA[i + k] : 0]++;
        for (i = sum = 0; i < maxi; i++) {
            int t = c[i]; c[i] = sum; sum += t;
        }
        for (i = 0; i < n; i++)               // shuffle the suffix array if necessary
            tempSA[c[SA[i] + k < n ? RA[SA[i] + k] : 0]++] = SA[i];
        for (i = 0; i < n; i++)                          // update the suffix array SA
            SA[i] = tempSA[i];
    }
}
