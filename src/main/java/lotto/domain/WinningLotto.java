package lotto.domain;

import java.util.Set;

public class WinningLotto {
    private static final int DEFAULT_COUNT = 0;
    private static final int PLUS_COUNT = 1;

    private final Lotto winningNumbers;

    public WinningLotto(final Set<LottoNumber> winningNumbers) {
        this.winningNumbers = new Lotto(winningNumbers);
    }

    public LottoReward calculateWinningResult(final Lotto lotto) {
        int matchCount = DEFAULT_COUNT;
        for (LottoNumber number : winningNumbers.getLottoNumbers()) {
            matchCount += countSameNumber(lotto, number);
        }
        return LottoReward.calculatePrize(matchCount);
    }

    private int countSameNumber(final Lotto lotto, final LottoNumber lottoNumber) {
        if (lotto.hasSameNumber(lottoNumber)) {
            return PLUS_COUNT;
        }
        return DEFAULT_COUNT;
    }
}
