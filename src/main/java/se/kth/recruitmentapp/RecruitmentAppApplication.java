package se.kth.recruitmentapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import se.kth.recruitmentapp.domain.Person;
import se.kth.recruitmentapp.repository.*;

import java.util.Optional;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})

public class RecruitmentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitmentAppApplication.class, args);
	}


	@Bean
	public CommandLineRunner dataLoader(ProfileRepository profileRepository, AvailabilityRepository availabilityRepository, PersonRepository personRepository, RoleRepository roleRepository, CompetenceRepository competenceRepository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

				System.out.println(roleRepository.findAll());
				System.out.println(competenceRepository.findAll());
				System.out.println(personRepository.findById(1));
				System.out.println(availabilityRepository.findById(18793));
				System.out.println(profileRepository.findById(5075));

				Optional<Person> person = personRepository.findById(11);
				System.out.println(profileRepository.findProfileByPerson(person.get()));

			}
		};
	}
}
