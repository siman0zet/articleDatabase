package ru.pnu.edu.articledatabase.servise.release;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pnu.edu.articledatabase.entity.Release;
import ru.pnu.edu.articledatabase.repository.ReleaseRepository;
import ru.pnu.edu.articledatabase.servise.release.argument.ReleaseCreateArgument;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReleaseServiceImpl implements ReleaseService {
    private final ReleaseRepository repository;

    @Override
    public Release create(ReleaseCreateArgument argument) {
        return repository.save(Release.builder()
                .number(argument.getNumber())
                .year(argument.getYear())
                .build());
    }

    @Override
    public List<Release> getAll() {
        return repository.findAll();
    }


}
