package lotto.domain;

import java.util.Arrays;

public enum LottoReward {
    FIRST(6, 2000000000),
    SECOND(5, 1500000),
    THIRD(4, 50000),
    FOURTH(3, 5000),
    NONE(0,0);

    private final int matchCount;
    private final int prize;

    LottoReward(final int matchCount, final int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static LottoReward calculatePrize(final int matchCount) {
        return Arrays.stream(LottoReward.values())
                .filter(reward -> reward.matchCount == matchCount)
                .findFirst()
                .orElse(NONE);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }
}
