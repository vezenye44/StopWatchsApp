import com.example.stopwatchsapp.data.TimestampFormatter
import org.junit.Assert.*
import org.junit.Test

class TimestampFormatterTest {

    private val timestampFormatter = TimestampFormatter()

    @Test
    fun timestampFormatter_correctFormationZeroNumber_returnTrue() {
        assertEquals(timestampFormatter.format(0L), "00:00.000")
    }

    @Test
    fun timestampFormatter_unCorrectFormationZeroNumber_returnFalse() {
        assertNotEquals(timestampFormatter.format(0L), "00:00:00")
    }

    @Test
    fun timestampFormatter_correctFormationArray_returnTrue() {
        assertArrayEquals(
            arrayOf(
                timestampFormatter.format(12345L),
                timestampFormatter.format(123456L),
                timestampFormatter.format(123456789L)
            ),
            arrayOf(
                "00:12.345",
                "02:03.456",
                "34:17:36"
            )
        )
    }
}