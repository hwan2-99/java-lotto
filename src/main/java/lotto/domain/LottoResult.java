package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private static final int DEFAULT_VALUE = 0;

    private final Map<LottoReward, Integer> resultMap;

    public LottoResult(final List<LottoReward> lottoRewards) {
        resultMap = new EnumMap<>(LottoReward.class);
        for (LottoReward lottoReward : lottoRewards) {
            resultMap.put(lottoReward,Collections.frequency(lottoRewards, lottoReward));
        }
    }

    public Map<LottoReward, Integer> getResultMap() {
        return Collections.unmodifiableMap(resultMap);
    }

    public double calculateProfit() {
        int purchaseCount = calculatePurchasedMoneyCount();
        return calculateTotalPrize() / ((double) purchaseCount * LottoGenerator.PRICE_MONEY_UNIT);
    }

    private int calculatePurchasedMoneyCount() {
        return resultMap.values().stream()
                .reduce(DEFAULT_VALUE, Integer::sum);
    }

    private int calculateTotalPrize() {
        return resultMap.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }
}
