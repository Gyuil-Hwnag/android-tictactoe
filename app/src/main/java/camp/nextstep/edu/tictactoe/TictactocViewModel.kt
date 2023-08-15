package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.tictactoe.model.TictactocCell
import camp.nextstep.edu.tictactoe.utils.Event
import com.nextstep.edu.tictactoe.domain.GameResultManager
import com.nextstep.edu.tictactoe.domain.Tictactoe
import com.nextstep.edu.tictactoe.domain.model.GameMode
import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Turn

class TictactocViewModel : ViewModel() {

    private var tictactoe = Tictactoe(
        gameMode = GameMode.TWO_PLAYER,
        currentTurn = Turn.X,
        gameResultManager = GameResultManager()
    )

    private val _tictactoeBoard: MutableLiveData<Array<Array<Turn>>> = MutableLiveData()
    val tictactoeBoard: LiveData<Array<Array<Turn>>> = _tictactoeBoard

    private val _tictactocToastMessage: MutableLiveData<Event<TictactocToastMessage>> = MutableLiveData()
    val tictactocToastMessage: LiveData<Event<TictactocToastMessage>> = _tictactocToastMessage

    init {
        setBoardFromMap()
    }

    fun onSetGameMode(gameMode: GameMode) {
        tictactoe = Tictactoe(
            gameMode = gameMode,
            currentTurn = Turn.X,
            gameResultManager = GameResultManager()
        )
        onRestBoard()
    }

    fun onSetBoardPoint(tictactocCell: TictactocCell) {
        val point = TictactocCell.toPoint(tictactocCell)

        val gameResult = tictactoe.put(point)
        when (gameResult) {
            GameResult.FINISH_GAME -> {
                _tictactocToastMessage.value = Event(TictactocToastMessage.GameOver)
                return
            }
            GameResult.INVALID_POSITION -> {
                _tictactocToastMessage.value = Event(TictactocToastMessage.WrongClick)
                return
            }
            else -> {
                tictactoe.changeTurn()
                setBoardFromMap()
                gameResultToastEvent(gameResult = gameResult)
            }
        }
    }

    fun onRestBoard() {
        tictactoe.reset()
        setBoardFromMap()
    }

    private fun gameResultToastEvent(gameResult: GameResult) {
        when (gameResult) {
            GameResult.X_WIN -> _tictactocToastMessage.value = Event(TictactocToastMessage.XWin)
            GameResult.O_WIN -> _tictactocToastMessage.value = Event(TictactocToastMessage.OWin)
            GameResult.TIE -> _tictactocToastMessage.value = Event(TictactocToastMessage.Tie)
            else -> {}
        }
    }

    private fun setBoardFromMap() {
        _tictactoeBoard.value = tictactoe.getMap()
    }
}