import java.util.Scanner;

public class HistoryGrading111 {
    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);
        String[] n = sc.nextLine().split(" ");
        String[] correctAnswer = sc.nextLine().split(" ");
        String[] studentAnswer = sc.nextLine().split(" ");
        while(studentAnswer.length == Integer.valueOf(n[0])){
            if(studentAnswer.length== correctAnswer.length)
                calculateLCS(correctAnswer, studentAnswer);
            if(sc.hasNextLine()) {
                studentAnswer = sc.nextLine().split(" ");
                if(studentAnswer.length!=Integer.valueOf(n[0])){
                    if(studentAnswer.length==0) break;
                    n = studentAnswer;
                    correctAnswer = sc.nextLine().split(" ");
                    studentAnswer = sc.nextLine().split(" ");
                }
            } else break;
        }
    }

    private static void calculateLCS(String[] correctAnswer, String[] studentAnswer) {
        String[] correctCopy = new String[correctAnswer.length];
        String[] studentCopy = new String[studentAnswer.length];
        for(int i=0; i<correctAnswer.length; i++) {
            correctCopy[Integer.valueOf(correctAnswer[i])-1] = String.valueOf(i+1);
        }
        for(int i=0; i<correctAnswer.length; i++) {
            studentCopy[Integer.valueOf(studentAnswer[i])-1] = String.valueOf(i+1);
        }
        int lcs[][] = new int[correctCopy.length+1][correctCopy.length+1];
        for(int i=0; i<correctCopy.length; i++) {
            for(int j=0; j<studentCopy.length; j++) {
                if(studentCopy[i].equals(correctCopy[j])) {
                    lcs[i+1][j+1] = Math.max(Math.max(lcs[i][j+1], lcs[i+1][j]),
                            lcs[i][j]+1);
                }
                else {
                    lcs[i+1][j+1] = Math.max(lcs[i][j+1], lcs[i+1][j]);
                }
            }
        }
        System.out.println(lcs[correctCopy.length][correctCopy.length]);
    }
}
