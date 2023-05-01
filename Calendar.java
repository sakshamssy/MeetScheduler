import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class Calendar{

    private Map<LocalDate, Map<LocalTime, String>> events;
    public LocalDate currentDate = LocalDate.now();
    public LocalTime currentTime = LocalTime.now();

    public Calendar() {
        events = new HashMap<>();
    }

    public void updateCalendarAtEndOfDay() {
        

        // Logic to check if the current time is at the end of the day
        // For example, if the current time is 23:59:59

        if (currentTime.equals(LocalTime.of(23, 59, 59))) {
            // Logic to update the calendar for the current date
            // This can include deleting the events for the current date
            // and generating a new calendar for the next day
            events.remove(currentDate);
            generateCalendar(currentDate.plusDays(1));
        }
    }

    public void generateCalendar( LocalDate currentDate) {
        // Get the current date

            // Iterate over the next seven days from the current date
            for (int i = 0; i < 7; i++) {
                // Create a map to store the time slots and events for the current date
                Map<LocalTime, String> timeSlots = new HashMap<>();

                // Logic to generate time slots for the day
                LocalTime startTime = LocalTime.of(9, 0); // Start time for the time slots
                LocalTime endTime = LocalTime.of(18, 0); // End time for the time slots
                int slotDurationMinutes = 60; // Duration of each time slot in minutes

                while (startTime.isBefore(endTime.plusMinutes(1))) {
                    // Add each time slot to the map with an initial value of an empty string for the event
                    timeSlots.put(startTime, "meeting");
                    System.out.println("printing start time"+startTime);
                    System.out.println(timeSlots);

                    startTime = startTime.plusMinutes(slotDurationMinutes);
                }
                // Store the time slots for the current date in the events map
                events.put(currentDate, timeSlots);

                System.out.println(timeSlots);

                // Logic to display the generated calendar in your user interface
                System.out.println("Calendar for " + currentDate.toString() + ":");
                for (LocalTime time : timeSlots.keySet()) {
                    System.out.println(time.toString()+" - " + timeSlots.get(time));
                }

                // Update the current date to the next day
               // LocalDate currentDate = LocalDate.now();
        		//LocalTime currentTime = LocalTime.now();
        		
                //if (currentTime.equals(LocalTime.of(23, 59, 59)))
               // {   //currentDate = currentDate.plusDays(1);
                  //  updateCalendarAtEndOfDay();
                ///}
                //else
                currentDate = currentDate.plusDays(1);
                currentTime = LocalTime.now();
                updateCalendarAtEndOfDay();
            }//for loop ends
        
        }//generate calendar ends
    
    public static void main (String args[])
    {   
        Calendar cal = new Calendar();
        cal.generateCalendar(cal.currentDate);
    }

}
