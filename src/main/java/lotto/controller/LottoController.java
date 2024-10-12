package lotto.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {
    public LottoController() {
    }

    public void run() {
        final LottoGenerator lottoGenerator = new LottoGenerator(InputView.getPurchasePrice());
        final Lottos lottos = lottoGenerator.getLottos();
        ResultView.printPurchaseLottoCount(lottos);

        final LottoResult lottoResult = lottos.calculateResult(generateWinningLotto());
        ResultView.printWinningStatistics(lottoResult.getResultMap());
        ResultView.printLottoProfit(lottoResult.calculateProfit());
    }

    private WinningLotto generateWinningLotto() {
        final List<Integer> winningNumbers = InputView.getWinningNumbers();
        Set<LottoNumber> winningLottos = winningNumbers.stream()
                .map(LottoNumber::generateByValue)
                .collect(Collectors.toSet());
        return new WinningLotto(winningLottos);
    }
}
