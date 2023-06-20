import com.example.stopwatchsapp.data.ElapsedTimeCalculator
import com.example.stopwatchsapp.data.StopwatchStateCalculator
import com.example.stopwatchsapp.domain.model.StopwatchState
import org.junit.Assert.assertNotSame
import org.junit.Assert.assertSame
import org.junit.Test

class StopwatchStateCalculatorTest {

    private val stopwatchStateCalculator = StopwatchStateCalculator(
        {1000L},
        ElapsedTimeCalculator { 1000L }
    )

    private val runningState = StopwatchState.Running(0L,0L)
    private val pauseState = StopwatchState.Pause(100L)

    @Test
    fun stopwatchStateCalculator_correctCalculateRunningStateFromRunningState_returnTrue() {
        assertSame(
            stopwatchStateCalculator.calculateRunningState(runningState),
            runningState
        )
    }

    @Test
    fun stopwatchStateCalculator_correctCalculateRunningStateFromPauseState_returnFalse() {
        assertNotSame(
            stopwatchStateCalculator.calculateRunningState(pauseState),
            runningState
        )
    }

    @Test
    fun stopwatchStateCalculator_correctCalculatePauseStateFromPauseState_returnTrue() {
        assertSame(
            stopwatchStateCalculator.calculatePauseState(pauseState),
            pauseState
        )
    }

    @Test
    fun stopwatchStateCalculator_correctCalculatePauseStateFromRunningState_returnFalse() {
        assertNotSame(
            stopwatchStateCalculator.calculatePauseState(runningState),
            pauseState
        )
    }
}