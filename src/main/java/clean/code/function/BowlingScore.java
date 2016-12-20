package clean.code.function;

/**
 * Created by hwang on 17/12/2016.
 */
public class BowlingScore {

    public static final int FRAME_COUNT = 10;

    public static int score(String frames) {
        String[] framesSplit = frames.split(" ");
        if (framesSplit.length != FRAME_COUNT) {
            throw new IllegalArgumentException("A game must contain 10 and only 10 frames");
        }
        int nineFrames = calculateScoreForFirstNineFrames(framesSplit);
        int lastFrame = calculateScoreForLastFrame(framesSplit[framesSplit.length - 1]);
        return nineFrames + lastFrame;
    }

    private static int calculateScoreForLastFrame(String lastFrame) {
        if (hasThirdShot(lastFrame)) {
            return withThreeShots(lastFrame);
        } else {
            return withTwoShots(lastFrame);
        }
    }

    private static boolean hasThirdShot(String lastFrame) {
        return isStrikeShot(firstShot(lastFrame)) || isSpareShot(secondShot(lastFrame));
    }

    private static boolean isSpareShot(char shot) {
        return '/' == shot;
    }

    private static boolean isStrikeShot(char shot) {
        return 'X' == shot;
    }

    private static char secondShot(String lastFrame) {
        return lastFrame.charAt(1);
    }

    private static char firstShot(String frame) {
        return frame.charAt(0);
    }

    private static int withTwoShots(String lastFrame) {
        return normalShotScore(firstShot(lastFrame)) + normalShotScore(secondShot(lastFrame));
    }

    private static int withThreeShots(String tenthFrame) {
        if (isStrikeShot(firstShot(tenthFrame))) {
            return startingWithStrike(tenthFrame);
        } else {
            return startingWithSpare(tenthFrame);
        }
    }

    private static int startingWithSpare(String tenthFrame) {
        return 10 + nonSpareScore(thirdShot(tenthFrame));
    }

    private static int startingWithStrike(String tenthFrame) {
        return 10 + nonSpareScore(secondShot(tenthFrame)) + nonSpareScore(thirdShot(tenthFrame));
    }

    private static char thirdShot(String tenthFrame) {
        return tenthFrame.charAt(2);
    }

    private static int nonSpareScore(char shot) {
        if (isSpareShot(shot)) {
            throw new IllegalStateException("Shot must not be spare");
        }
        if (isStrikeShot(shot)) {
            return 10;
        } else {
            return normalShotScore(shot);
        }
    }

    private static int normalShotScore(char shot) {
        if (isGutterShot(shot)) {
            return 0;
        } else {
            return shot - '0';
        }
    }

    private static boolean isGutterShot(char shot) {
        return '-' == shot;
    }

    private static int calculateScoreForFirstNineFrames(String[] frames) {
        int score = 0;
        for (int frameIndex = 0; frameIndex < 9; frameIndex++) {
            score += scoreForFrameAt(frameIndex, frames);
        }
        return score;
    }

    private static int scoreForFrameAt(int frameIndex, String[] frames) {
        String currentFrame = frames[frameIndex];
        if (isStrikeFrame(currentFrame)) {
            return 10+ scoreOfNextTwoFrames(frameIndex, frames);
        } else {
            return scoreForNotStrikeAt(frameIndex, frames);
        }
    }

    private static boolean isStrikeFrame(String frame) {
        return isStrikeShot(frame.charAt(0));
    }

    private static int scoreForNotStrikeAt(int frameIndex, String[] frames) {
        String currentFrame = frames[frameIndex];
        if (isSpareShot(secondShot(currentFrame))) {
            return 10 + nonSpareScore(firstShot(frames[frameIndex + 1]));
        } else {
            return normalShotScore(firstShot(currentFrame)) +
                    normalShotScore(secondShot(currentFrame));
        }
    }

    private static int scoreOfNextTwoFrames(int frameIndex, String[] frames) {
        String nextFrame = frames[frameIndex + 1];
        if (is8thFrame(frameIndex)) {
            return scoreForNextFrameOf8thFrame(nextFrame);
        }
        if (isStrikeFrame(nextFrame)) {
            return 10 + scoreForNextNextFrame(frameIndex, frames[frameIndex + 2]);
        }
        if (isSpareShot(secondShot(nextFrame))) {
            return 10;
        }
        return normalShotScore(firstShot(nextFrame)) + normalShotScore(secondShot(nextFrame));
    }

    private static boolean is8thFrame(int frameIndex) {
        return frameIndex + 1 == 9;
    }

    private static int scoreForNextNextFrame(int frameIndex, String nextNextFrame) {
        if (is7thFrame(frameIndex)) {
            return nonSpareScore(firstShot(nextNextFrame));
        }
        if (isStrikeFrame(nextNextFrame)) {
            return 10;
        }
        if (isSpareShot(secondShot(nextNextFrame))) {
            return 10;
        }
        return normalShotScore(firstShot(nextNextFrame));
    }

    private static boolean is7thFrame(int frameIndex) {
        return frameIndex + 2 == 9;
    }

    private static int scoreForNextFrameOf8thFrame(String nextFrame) {
        if (isSpareShot(secondShot(nextFrame))) {
            return 10;
        }
        return nonSpareScore(firstShot(nextFrame)) +
                nonSpareScore(secondShot(nextFrame));
    }
}
