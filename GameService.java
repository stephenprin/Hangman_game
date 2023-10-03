package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GameService {

    private static final String SRC_HANGMAN_WORD_TXT = "/Users/prince/IdeaProjects/untitled/src/hangman/word.txt";


    public void start() throws FileNotFoundException {

        Scanner inputScanner=new Scanner(System.in);


       String newRandomWords= getRandomWord();
        char[] randomArray= newRandomWords.toCharArray();
        char[] guessArray= new char[newRandomWords.length()];
        Arrays.fill(guessArray,'-');
        int numOfChances= newRandomWords.length();
        System.out.println(guessArray);

        System.out.println("Starting a new Game!!!");
        System.out.println("The length of the word and the number of chances: " + numOfChances);


        while (true){
            System.out.println("---------------------");
            System.out.print("Please enter a letter: ");
            String inputLine=inputScanner.nextLine().toLowerCase();
            char letter= inputLine.charAt(0);

            boolean isGuessingCorrect=false;
            for (int i = 0; i < newRandomWords.length(); i++) {
                if(letter==randomArray[i]){
                    guessArray[i]=letter;
                    isGuessingCorrect=true;
                }
            }

            if(isGuessingCorrect){
                System.out.println("It was a good guess.");
                if(isGameFinished(guessArray)){
                    break;
                }
            }else{
                numOfChances--;

                if(numOfChances==0){
                    System.out.println("Sorry you run out of chances");
                    System.out.println("The word was : " + newRandomWords);
                    break;
                }
                System.out.println("Your number of chance has decrease to : " + numOfChances);
                System.out.println("Try another letter");
            }
            System.out.println("The word : " + new String(guessArray));
        }


    }

    private boolean isGameFinished(char[] guessArray) {
        for (int i = 0; i <guessArray.length ; i++) {
            if(guessArray[i]== '-'){
               return false;
            }
        }
        System.out.println("Congratulation, you won");
        System.out.println("The word : " + new String(guessArray));

        return true;
    }

    private String getRandomWord() throws FileNotFoundException {
        List<String> words=getTheWords();
     Random randomGenerator= new Random();
     int randInt= randomGenerator.nextInt(words.size());
     return words.get(randInt);
 }
    private List<String> getTheWords() throws FileNotFoundException {
        List<String> words= new ArrayList<>();

        File wordsFile= new File(SRC_HANGMAN_WORD_TXT);
        Scanner wordScanner= new Scanner(wordsFile);
        while (wordScanner.hasNextLine()){
            words.add(wordScanner.nextLine());
        }
        return words;
    }
}
