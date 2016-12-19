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

    private static boolean isGutterShot(char third) {
        return '-' == third;
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
        if ("X".equals(currentFrame)) {
            return scoreForStrikeAt(frameIndex, frames);
        } else {
            return scoreForNotStrikeAt(frameIndex, frames, currentFrame);
        }
    }

    private static int scoreForNotStrikeAt(int i, String[] frames, String currentFrame) {
        int score = 0;
        char first = firstShot(currentFrame);
        char second = secondShot(currentFrame);
        if (isSpareShot(second)) {
            score += 10;
            String nextFrame = frames[i + 1];
            if ("X".equals(nextFrame)) {
                score += 10;
            } else {
                char firstNextFrame = firstShot(nextFrame);
                if ('-' != firstNextFrame) {
                    score += firstNextFrame - '0';
                }
            }
        } else {
            if ('-' != first) {
                score = score + first - '0';
            }

            if ('-' != second) {
                score = score + second - '0';
            }
        }
        return score;
    }

    private static int scoreForStrikeAt(int i, String[] frames) {
        int score = 10;
        String nextFrame = frames[i + 1];
        if (i + 1 == 9) {
            score = score + calculateScore(nextFrame);
        } else {
            if ("X".equals(nextFrame)) {
                score += 10;
                String nextNextFrame = frames[i + 2];
                if (i + 2 != 9) {
                    if ("X".equals(nextNextFrame)) {
                        score += 10;
                    } else {
                        char first = firstShot(nextNextFrame);
                        char second = secondShot(nextNextFrame);
                        if (isSpareShot(second)) {
                            score += 10;
                        } else {
                            if ('-' != first) {
                                score = score + first - '0';
                            }
                        }
                    }
                } else {
                    char first = firstShot(nextNextFrame);
                    score += nonSpareScore(first);
                }

            } else {
                char first = firstShot(nextFrame);
                char second = secondShot(nextFrame);
                if (isSpareShot(second)) {
                    score += 10;
                } else {
                    if ('-' != first) {
                        score = score + first - '0';
                    }

                    if ('-' != second) {
                        score = score + second - '0';
                    }
                }

            }

        }
        return score;
    }

    private static int calculateScore(String nextFrame) {
        int score = 0;
        char firstShot = firstShot(nextFrame);
        char secondShot = secondShot(nextFrame);
        if (isSpareShot(secondShot)) {
            score += 10;
        } else {
            score += nonSpareScore(firstShot);


            if (isStrikeShot(secondShot)) {
                score += 10;
            } else if ('-' != secondShot) {
                score += firstShot - '0';
            }
        }
        return score;
    }
}
