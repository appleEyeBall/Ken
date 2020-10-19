public class ScoresCalculator {
    /*
    * This static class calculates the drawings score and total score
    * based on the rules in the odds image
    * */

    public static String calculateDrawScores(String numberOfSpots, String interceptsCount){
        String drawScore = "0";

        // one spot option
        if (numberOfSpots.equals("1")){
            drawScore = "2";
        }
        // four spot option
        else if (numberOfSpots.equals("4")){
            if (interceptsCount.equals("4")){
                drawScore = "75";

            }
            else if (interceptsCount.equals("3")){
                drawScore = "5";
            }
            else if (interceptsCount.equals("2")){
                drawScore = "1";
            }

        }
        // eight spot option
        else if (numberOfSpots.equals("8")){
            System.out.println("got here, 8 spots");
            if (interceptsCount == "8"){
                drawScore = "10000";

            }
            else if (interceptsCount.equals("7")){
                drawScore = "750";
            }
            else if (interceptsCount.equals("6")){
                drawScore = "50";
            }
            else if (interceptsCount.equals("5")){
                drawScore = "12";
            }
            else if (interceptsCount.equals("4")){

                drawScore = "2";
            }

        }
        // 10 spots selected
        else {
            if (interceptsCount.equals("10")){
                drawScore = "100000";

            }
            else if (interceptsCount.equals("9")){
                drawScore = "4250";
            }
            else if (interceptsCount.equals("8")){
                drawScore = "450";

            }
            else if (interceptsCount.equals("7")){
                drawScore = "40";
            }
            else if (interceptsCount.equals("6")){
                drawScore = "15";
            }
            else if (interceptsCount.equals("5")){
                drawScore = "2";
            }
            else if (interceptsCount.equals("0")){
                drawScore = "5";
            }
        }

        System.out.println(" draw total is "+drawScore);

        return drawScore;
    }

    // using the scores from each draw, this function calculates the total prize after the game ends
    public static String calculateTotalScores(String numberOfSpots, String interceptCount,  String lastScoreVal){
        System.out.println("in totalScores spots Type is "+numberOfSpots);
        System.out.println("in totalScores Intercepts is "+interceptCount);
        int prevDrawVal = Integer.valueOf(calculateDrawScores(numberOfSpots, interceptCount));  //calculate the previous drawing
        int prevTotal = Integer.valueOf(lastScoreVal);
        prevTotal += prevDrawVal;

        System.out.println("score total is "+prevTotal);
        return String.valueOf(prevTotal);
    }
}
