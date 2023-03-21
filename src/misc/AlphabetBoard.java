package misc;

public class AlphabetBoard {

    private final String[] board = {"abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"};

    public String alphabetBoardPath(String target) {

        StringBuilder ans = new StringBuilder();
        int cr = 0, cc = 0;
        for (int index = 0; index < target.length(); ) {
            int d = target.charAt(index) - 'a';

            int er = d / 5;
            int ec = d - er * 5;

            if (er == 5 && cc > 0 && cc < 5) {
                er = 4;
            }

            while (cr < er) {
                ans.append("D");
                ++cr;
            }

            while (cc < ec && cr < 5) {
                ans.append("R");
                ++cc;
            }

            while (cr > er) {
                ans.append("U");
                --cr;
            }

            while (cc > ec) {
                ans.append("L");
                --cc;
            }

            if (getChar(cr, cc) == target.charAt(index)) {
                ans.append("!");
                ++index;
            }
        }

        return ans.toString();
    }

    private char getChar(int cr, int cc) {
        return board[cr].charAt(cc);
    }

    private String findAndAppend(String target, int t, int[] currPos) {

        StringBuilder ans = new StringBuilder();
        if (t < target.length()) {
            int d = target.charAt(t) - 'a';

            int cr = currPos[0];
            int cc = currPos[1];

            int er = d / 5;
            int ec = d - er * 5;

            if (er == 5 && cc > 0 && cc < 5) {
                er = 4;
            }

            while (cr < er) {
                //System.out.print("D");
                ans.append("D");
                ++cr;
            }
            while (cc < ec && cr < 5) {
                //System.out.print("R");
                ans.append("R");
                ++cc;
            }
            while (cr > er) {
                //System.out.print("U");
                ans.append("U");
                --cr;
            }
            while (cc > ec) {
                //System.out.print("L");
                ans.append("L");
                --cc;
            }
            if (getChar(cr, cc) == target.charAt(t)) {
                //System.out.print("!");
                ans.append("!");
                ++t;
            }
            ans.append(findAndAppend(target, t, new int[]{cr, cc}));
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(new AlphabetBoard().alphabetBoardPath("zdz"));
        //.concat(new AlphabetBoard().alphabetBoardPath("e"))
        //.concat(new AlphabetBoard().alphabetBoardPath("e"))
        //.concat(new AlphabetBoard().alphabetBoardPath("t")));
    }

}
