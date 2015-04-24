package logic;

import com.google.common.base.Optional;
import data.PillEventDAO;
import data.PrescriptionDAO;
import data.RemindersDAO;
import models.PillEvent;
import models.Prescription;
import models.Reminder;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by keegan on 4/23/15.
 */
public class RemindersHandler {

    private RemindersDAO remindersDAO;
    private PillEventDAO pillEventDAO;

    @Inject
    public RemindersHandler(RemindersDAO remindersDAO, PillEventDAO pillEventDAO) {
        this.remindersDAO = remindersDAO;
        this.pillEventDAO = pillEventDAO;
    }

    public List<Reminder> generateReminders(int prescriptionId, long time) {
        List<Reminder> result = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(time));


        List<PillEvent> events = pillEventDAO.getAllByPrescriptionIdAndDay(prescriptionId, cal.DAY_OF_WEEK);

        for (PillEvent event : events) {
            cal.set(cal.YEAR, cal.MONTH, cal.DATE, event.getHour(), event.getMinute());
            long eventTime = cal.getTimeInMillis();
            Optional<Reminder> reminder = Optional.of(remindersDAO.getByPrescriptionIdAndTime(prescriptionId, eventTime));
            if (!reminder.isPresent()) {
                int reminderId = remindersDAO.insert(prescriptionId, false, eventTime);
                result.add(remindersDAO.get(reminderId));
            } else {
                result.add(reminder.get());
            }
        }

        return result;
    }

    public void deletePastTime(int prescriptionId, long time) {
        List<Reminder> reminders = remindersDAO.getPastTime(prescriptionId, time);
        for (Reminder reminder : reminders) {
            remindersDAO.delete(reminder.getId());
        }
    }
}
