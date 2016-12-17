package clean.code.function;

/**
 * Created by hwang on 17/12/2016.
 */
public class BowlingScore {

    public static int score(String frames) {
        int score = 0;
        String[] framesSplit = frames.split(" ");
        for (int i = 0; i < 9; i++) {
            String currentFrame = framesSplit[i];
            if ("X".equals(currentFrame)) {
                score += 10;
                String nextFrame = framesSplit[i + 1];
                if(i+1 == 9) {
                    char first = nextFrame.charAt(0);
                    char second = nextFrame.charAt(1);
                    if('/' == second) {
                        score += 10;
                    } else {
                        if('X' == first) {
                            score += 10;
                        } else if('-' != first) {
                            score += first - '0';
                        }


                        if('X' == second) {
                            score += 10;
                        } else if('-' != second) {
                            score += first - '0';
                        }
                    }
                } else {
                    if ("X".equals(nextFrame)) {
                        score += 10;
                        String nextNextFrame = framesSplit[i + 2];
                        if (i + 2 != 9) {
                            if ("X".equals(nextNextFrame)) {
                                score += 10;
                            } else {
                                char first = nextNextFrame.charAt(0);
                                char second = nextNextFrame.charAt(1);
                                if ('/' == second) {
                                    score += 10;
                                } else {
                                    if ('-' != first) {
                                        score = score + first - '0';
                                    }
                                }
                            }
                        } else {
                            char first = nextNextFrame.charAt(0);
                            if ('X' == first) {
                                score += 10;
                            } else if ('-' != first) {
                                score += first - '0';
                            }
                        }

                    } else {
                        char first = nextFrame.charAt(0);
                        char second = nextFrame.charAt(1);
                        if ('/' == second) {
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
            } else {
                char first = currentFrame.charAt(0);
                char second = currentFrame.charAt(1);
                if ('/' == second) {
                    score += 10;
                    String nextFrame = framesSplit[i + 1];
                    if ("X".equals(nextFrame)) {
                        score += 10;
                    } else {
                        char firstNextFrame = nextFrame.charAt(0);
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
            }
        }

        String tenthFrame = framesSplit[9];
        char first = tenthFrame.charAt(0);
        char second = tenthFrame.charAt(1);
        if ('X' == first) {
            score += 10;
            if ('X' == second) {
                score += 10;
            } else if ('-' != second) {
                score += second - '0';
            }
            char third = tenthFrame.charAt(2);
            if ('X' == third) {
                score += 10;
            } else if ('-' != third) {
                score += second - '0';
            }
        } else if ('/' == second) {
            score += 10;
            char third = tenthFrame.charAt(2);
            if ('X' == third) {
                score += 10;
            } else if ('-' != third) {
                score += third - '0';
            }
        } else {
            if ('-' != first) {
                score = score + first - '0';
            }
            if ('-' != second) {
                score = score + first - '0';
            }
        }
        return score;
    }
}
