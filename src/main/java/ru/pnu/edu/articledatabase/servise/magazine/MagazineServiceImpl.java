package ru.pnu.edu.articledatabase.servise.magazine;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.pnu.edu.articledatabase.entity.ExportControl;
import ru.pnu.edu.articledatabase.entity.Magazine;
import ru.pnu.edu.articledatabase.repository.ExportControlRepository;
import ru.pnu.edu.articledatabase.repository.MagazineRepository;
import ru.pnu.edu.articledatabase.servise.magazine.argument.MagazineCreateArgument;
import ru.pnu.edu.articledatabase.servise.magazine.argument.MagazineUpdateArgument;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MagazineServiceImpl implements MagazineService {
    private final MagazineRepository repository;

    @Override
    @Transactional
    public Magazine create(MagazineCreateArgument argument) {
        return repository.save(Magazine.builder()
        .email_addresses(argument.getEmailAddresses())
        .link_on_page(argument.getLinkOnPage())
        .name(argument.getName())
        .release_id(argument.getReleaseId())
        .conference_date(argument.getConferenceDate())
        .page_range(argument.getPageRange())
        .build());
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(Integer id) {
        repository.delete(repository.getOne(id));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Magazine update(Integer id, MagazineUpdateArgument argument) {
        Magazine magazine = repository.getOne(id);
        magazine.setEmail_addresses(argument.getEmailAddresses());
        magazine.setLink_on_page(argument.getLinkOnPage());
        magazine.setName(argument.getName());
        magazine.setPage_range(argument.getPageRange());
        magazine.setConference_date(argument.getConferenceDate());
        magazine.setRelease_id(argument.getReleaseId());
        return repository.save(magazine);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Magazine> getAll() {
        return repository.findAll();
    }
}
