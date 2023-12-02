// red, green, or blue
// secret numbers are hidden
// goal is figure out number of cubes
// get random cubes, put them back "a few times"
// Game #: # blue, # red; # red # green, # blue; # green
/*  EXAMPLE
Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
*/
// which games are possible with 12 red, 13 green, 14 blue
// 1,2,5 would be POSSIBLE
// 3 IMPOSSIBLE, because 20 red > 12 red
// 4 IMPOSSIBLE, because 15 blue > 14 blue
// What is the SUM of the IDs? (given 12 red, 13 green, 14 blue)

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ColorGame {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.print("usage: 0: path, 1: red, 2: green, 3: blue");
        }
        String filePath = args[0];
        File file = new File(filePath);
        if (!file.isFile()) {
            System.out.printf("path %s does not point to a file", filePath);
            return;
        }
        int red = Integer.parseInt(args[1]);
        int green = Integer.parseInt(args[2]);
        int blue = Integer.parseInt(args[3]);
        int sum = 0;
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException ignored) {
            return;
        }
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();

            int gameTokenIndex = line.lastIndexOf("Game ")+"Game ".length();
            int columnIndex = line.indexOf(": ");
            String gameNumberString = line.substring(gameTokenIndex, columnIndex);
            int gameNumber = Integer.parseInt(gameNumberString);
            boolean possible = true;
            line = line.substring(columnIndex+2);
            String[] rounds = line.split("; ");
            for (String round : rounds) {
                String[] colors = round.split(", ");
                for (String color : colors) {
                    String[] s = color.split(" ");
                    int cubesNumber = Integer.parseInt(s[0]);
                    String cubesColor = s[1];
                    switch (cubesColor) {
                        case "red":
                            if (cubesNumber > red) {
                                possible = false;
                            }
                            break;
                        case "green":
                            if (cubesNumber > green) {
                                possible = false;
                            }
                            break;
                        case "blue":
                            if (cubesNumber > blue) {
                                possible = false;
                            }
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + cubesColor);
                    }

                }
            }
            System.out.printf("\n game #%d is possible: %b", gameNumber, possible);
            if (possible) {
                sum += gameNumber;
            }
        }
        System.out.printf("\n sum of gamenumbers is %d%n \n", sum);
    }
}
