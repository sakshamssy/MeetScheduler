package com.example.Event.Management;

import com.example.Event.Management.Repos.MeetingService;
import com.example.Event.Management.Repos.MemberService;
import com.example.Event.Management.Repos.CalendarService;
import com.example.Event.Management.models.Meets;
import com.example.Event.Management.models.Member;
import com.example.Event.Management.models.Calendar;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan(basePackages = {"com.example.Event.Management", "com.example.Event.Management.models"})
@EnableMongoRepositories(basePackages = "com.example.Event.Management.Repos")

public class EventManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventManagementApplication.class, args);}

	//Member member = new Member();
	@Bean
	CommandLineRunner runner(MemberService memberService, MeetingService meetingService, CalendarService calendarService){
		return args -> {
			Member member1 = new Member("1797", "Anurag", "anurag");
			Member member2 = new Member("1791", "Virat", "virat");
			Member member3 = new Member("1800", "Rohit", "rohit");

			memberService.addMember(member1);
			memberService.addMember(member2);
			memberService.addMember(member3);
			LocalTime localTime = LocalTime.of(13, 0);
			Meets meet1 = new Meets(LocalDate.now(),localTime , member1, member2);
			Meets meet2 = new Meets(LocalDate.now().plusDays(2), LocalTime.now().plusHours(4), member2, member3);
			Meets meet3 = new Meets(LocalDate.now().plusDays(3), LocalTime.now().plusHours(7), member2, member1);

			meetingService.addMeeting(meet1);
			meetingService.addMeeting(meet2);
			meetingService.addMeeting(meet2);
			LocalDate date = meet1.startDateTime.toLocalDate();
			LocalTime time = meet1.startDateTime.toLocalTime();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			String formattedTime = time.format(formatter);
			LocalTime t = LocalTime.parse(formattedTime);
			//DateTimeFormatter t = DateTimeFormatter.ofPattern("HH:mm:ss");
			if(t== LocalTime.of(13, 0)) {
				System.out.println(date);
				System.out.println(t);
			}


			Calendar calendar1 = new Calendar(member1.getBITSid()); //generated calenadars for all three members using
			calendar1.generateCalendar(calendar1.currentDate);
			//member1.calendar = calendar1;							//Bitsid. Then initialesd the calendar object attribute
			//member1.events = calendar1.getcal();			//inside member class by using the generated calendar object
															//using getcal to return th hashmap that stores the schedule

			Calendar calendar2 = new Calendar(member2.getBITSid());
			calendar2.generateCalendar(calendar2.currentDate);
			//member2.calendar = calendar2;
			//member2.events = calendar2.getcal();


			Calendar calendar3 = new Calendar(member3.getBITSid());
			calendar3.generateCalendar(calendar3.currentDate);
			//member3.calendar = calendar3;
			//member3.events = calendar3.getcal();

			calendarService.addCalendar(calendar1);
			calendarService.addCalendar(calendar2);
			calendarService.addCalendar(calendar3);

			memberService.updateMember(member1);
			memberService.updateMember(member2);
			memberService.updateMember(member3);


		};
	}
}



