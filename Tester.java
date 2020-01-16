import java.util.ArrayList;

public class Tester {
    public static boolean eachStep = true; //DISPLAY MODE SWITCH
    public static void main(String[] args) {
        Alphabet alphabet = new Alphabet();
        Line line = new Line();

        /*CHANGE BELOW THAT LINE*/

        /*ALPABET*/
        char[] initialAlphabet = {'0', '1', '*', ' '};

        /*INPUT*/
        char[] initialLine = {'*', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '*'};
        
        /*CHANGE ABOVE THIS LINE*/

        for(char a : initialAlphabet) { alphabet.add(a); }
        for(char a : initialLine) { line.add(a); }
        System.out.print("Initial string: ");
        for (int i = 0; i < line.line.size(); i++) { System.out.print(line.line.get(i)); }
        System.out.print("\n");

        /*CHANGE BELOW THAT LINE*/

        State Q0 = new State(true); //END STATE

        /*CREATE STATES*/
        State Q1 = new State();
        State Q2 = new State();
        State Q3 = new State();
        State Q4 = new State();
        State Q5 = new State();
        State Q6 = new State();
        State Q7 = new State();
        State Q8 = new State();

        /*CREATE INSTRUCTIONS
        SUGNATURE: 'INPUT', 'OUTPTUP', 'DIRECTION' (1 - RIGHT, 2 - LEFT, 3 - STAY), 'NEXT STATE', 'ALPHABET' */
        Q1.subStateAdd('0', '0', 3, Q0, alphabet.alphabet);
        Q1.subStateAdd('1', '1', 3, Q0, alphabet.alphabet);
        Q1.subStateAdd('*', '*', 1, Q2, alphabet.alphabet);
        Q1.subStateAdd(' ', ' ', 3, Q0, alphabet.alphabet);

        Q2.subStateAdd('0', '0', 3, Q0, alphabet.alphabet);
        Q2.subStateAdd('1', '1', 3, Q3, alphabet.alphabet);
        Q2.subStateAdd('*', '0', 1, Q2, alphabet.alphabet);
        Q2.subStateAdd(' ', '*', 3, Q0, alphabet.alphabet);

        Q3.subStateAdd('0', '0', 1, Q3, alphabet.alphabet);
        Q3.subStateAdd('1', '1', 1, Q3, alphabet.alphabet);
        Q3.subStateAdd('*', '*', 1, Q3, alphabet.alphabet);
        Q3.subStateAdd(' ', ' ', 2, Q4, alphabet.alphabet);

        Q4.subStateAdd('0', '0', 3, Q0, alphabet.alphabet);
        Q4.subStateAdd('1', '*', 2, Q5, alphabet.alphabet);
        Q4.subStateAdd('*', ' ', 2, Q7, alphabet.alphabet);
        Q4.subStateAdd(' ', ' ', 3, Q0, alphabet.alphabet);

        Q5.subStateAdd('0', '0', 3, Q0, alphabet.alphabet);
        Q5.subStateAdd('1', '1', 2, Q5, alphabet.alphabet);
        Q5.subStateAdd('*', '*', 2, Q6, alphabet.alphabet);
        Q5.subStateAdd(' ', ' ', 3, Q0, alphabet.alphabet);

        Q6.subStateAdd('0', '1', 1, Q3, alphabet.alphabet);
        Q6.subStateAdd('1', '0', 2, Q6, alphabet.alphabet);
        Q6.subStateAdd('*', '*', 3, Q0, alphabet.alphabet);
        Q6.subStateAdd(' ', '1', 1, Q3, alphabet.alphabet);

        Q7.subStateAdd('0', '0', 3, Q0, alphabet.alphabet);
        Q7.subStateAdd('1', '1', 3, Q4, alphabet.alphabet);
        Q7.subStateAdd('*', '*', 2, Q8, alphabet.alphabet);
        Q7.subStateAdd(' ', ' ', 3, Q0, alphabet.alphabet);

        Q8.subStateAdd('0', '0', 2, Q8, alphabet.alphabet);
        Q8.subStateAdd('1', '1', 2, Q8, alphabet.alphabet);
        Q8.subStateAdd('*', '*', 2, Q8, alphabet.alphabet);
        Q8.subStateAdd(' ', '*', 3, Q0, alphabet.alphabet);

        Q1.execution(line.line, alphabet.alphabet, 0);

        /*CHANGE ABOVE THAT LINE*/
    }
}

class State {
    boolean END;
    State () { this.END = false; }
    State (boolean END) { this.END = END; }
    private ArrayList<SubState> states = new ArrayList<>();
    public void subStateAdd(char enter, char output, int direction, State nextState, ArrayList alphabet) {
        states.add(alphabet.indexOf(enter), new SubState(output, direction, nextState));
    }
    public void execution(ArrayList list, ArrayList alphabet, int current) {
        if(Tester.eachStep) {
            for (int i = 0; i < list.size(); i++) {
                if(current < 0 && i == 0) { System.out.print(' '); }
                System.out.print(list.get(i));
            }
            System.out.print("\n");
            for (int i = 0; i < list.size(); i++) {
                if(i == 0 && current == -1) { System.out.print('^'); }
                else if (i != current) { System.out.print(' '); }
                else if (current == list.size()){ System.out.print(" ^"); }
                else { System.out.print('^'); }
            }
            System.out.print("\n");
        }
        if(!END) {
            try {
                states.get(alphabet.indexOf(list.get(current))).execution(list, alphabet, current);
            } catch (Exception e) {
                if(current >= 0) {
                    list.add(' ');
                } else {
                    current++;
                    list.add(0, ' ');
                }
                states.get(alphabet.indexOf(list.get(current))).execution(list, alphabet, current);
            }
        } else {
            System.out.print("Final result: ");
            for(int i = 0; i < list.size(); i++) { System.out.print(list.get(i)); }
        }
    }
}

class SubState {
    private char output;
    private int direction;
    private State nextState;
    SubState(char output, int direction, State nextState) {
        this.output = output;
        this.direction = direction;
        this.nextState = nextState;
    }
    public void execution(ArrayList list, ArrayList alphabet, int current) {
        list.set(current, output);
        switch (direction) {
            case 1: nextState.execution(list, alphabet, ++current);
                break;
            case 2: nextState.execution(list, alphabet, --current);
                break;
            case 3: nextState.execution(list, alphabet, current);
                break;
        }
    }
}

class Alphabet {
    ArrayList<Character> alphabet = new ArrayList<>();
    public void add(char symbol) { alphabet.add(symbol); }
}

class Line {
    ArrayList<Character> line = new ArrayList<>();
    public void add(char symbol) { line.add(symbol); }
}