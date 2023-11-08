package pl.edu.uwm.student.aleksiejczyk.bartlomiej;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateToFractional {
    public static double convertDateToFractionalYear(LocalDateTime date) {
        LocalDateTime endOfYear = LocalDateTime.of(date.getYear() + 1, 1, 1, 0, 0);
        LocalDateTime startOfYear = LocalDateTime.of(date.getYear(), 1, 1, 0, 0);

        double passedTime = Duration.between(startOfYear, date).getSeconds();
        double totalSecondsInYear = Duration.between(startOfYear, endOfYear).getSeconds();

        return date.getYear() + (passedTime / totalSecondsInYear);
    }
}
