package com.nextstep.edu.tictactoe.domain.mode

import com.nextstep.edu.tictactoe.domain.RandomStrategy
import com.nextstep.edu.tictactoe.domain.TictactocStrategy
import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.TictactoeMap

interface RandomNormalTictactoe: TictactocStrategy {}


internal class RandomNormalTictactoeImpl(
    randomStrategy: RandomStrategy,
    val tictactoeMap: TictactoeMap
) : RandomNormalTictactoe,
    RandomStrategy by randomStrategy {

    override fun put(point: Point): GameResult {
        if (!isValidData(point = point, tictactoeMap = tictactoeMap)) {
            return if (tictactoeMap.getIsFinish()) GameResult.FINISH_GAME else GameResult.INVALID_POSITION
        }

        var gameResult = getGameResult(point = point, tictactoeMap = tictactoeMap)

        if (gameResult == GameResult.UNKNOWN) {
            gameResult = randomPut(point = point, tictactoeMap = tictactoeMap)
        }

        return gameResult
    }
}