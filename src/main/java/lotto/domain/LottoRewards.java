package lotto.domain;

import java.util.Arrays;

public enum LottoRewards {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0);

    private final int matchCount;
    private final int prize;

    LottoRewards(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }

    public static LottoRewards from(final int matchCount) {
        return Arrays.stream(values())
                .filter(lottoRewards -> findByCount(lottoRewards, matchCount))
                .findFirst()
                .orElse(NONE);
    }

    private static boolean findByCount(final LottoRewards lottoRewards, final int value) {
        return lottoRewards.getMatchCount() == value;
    }
}
