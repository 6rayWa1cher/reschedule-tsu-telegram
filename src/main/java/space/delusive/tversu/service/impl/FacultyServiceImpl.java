package space.delusive.tversu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import space.delusive.tversu.entity.WeekSign;
import space.delusive.tversu.rest.FacultyRepository;
import space.delusive.tversu.service.FacultyService;

import java.util.List;
import java.util.Set;

@Component
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<String> getFaculties() {
        return facultyRepository.getFaculties();
    }

    @Override
    public Set<String> getPrograms(String faculty) {
        return facultyRepository.getPrograms(faculty);
    }

    @Override
    public Set<Integer> getCourses(String faculty, String program) {
        return facultyRepository.getCourses(faculty, program);
    }

    @Override
    public Set<String> getGroups(String faculty, String program, int course) {
        return facultyRepository.getGroups(faculty, program, course);
    }

    @Override
    public int getSubgroupsCount(String faculty, String program, int course, String group) {
        return facultyRepository.getSubgroupsCount(faculty, program, course, group);
    }

    @Override
    public WeekSign getCurrentWeekSign(String faculty) {
        return facultyRepository.getCurrentWeekSign(faculty);
    }
}