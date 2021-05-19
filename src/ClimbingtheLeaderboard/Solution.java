package ClimbingtheLeaderboard;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {
    private static int lastRangeOfPlayer;

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        List<Integer> playersRanges = new ArrayList<>();
        ranked = makeListWithoutDuplicates(ranked);

        lastRangeOfPlayer = ranked.size() - 1;

        for (int points: player)
            playersRanges.add(takeRange(points, ranked));

        return playersRanges;
    }

    private static List<Integer> makeListWithoutDuplicates(List<Integer> list){
        List<Integer> listWithoutDuplicates = new ArrayList<>();
        listWithoutDuplicates.add(list.get(0));

        for (int number: list){
            if(listWithoutDuplicates.get(listWithoutDuplicates.size() - 1) != number)
                listWithoutDuplicates.add(number);
        }

        return listWithoutDuplicates;
    }

    private static int takeRange(int points, List<Integer> ranked){
        int i = lastRangeOfPlayer;

        while ((i >= 0) && (ranked.get(i) <= points))
            i--;

        lastRangeOfPlayer = i;
        return lastRangeOfPlayer + 2;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.climbingLeaderboard(ranked, player);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
