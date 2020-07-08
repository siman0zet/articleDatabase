package ru.pnu.edu.articledatabase.servise.magazine;

import ru.pnu.edu.articledatabase.entity.Magazine;
import ru.pnu.edu.articledatabase.servise.magazine.argument.MagazineCreateArgument;
import ru.pnu.edu.articledatabase.servise.magazine.argument.MagazineUpdateArgument;

import java.util.List;

public interface MagazineService {
    Magazine create(MagazineCreateArgument argument);

    void delete(Integer id);

    Magazine update(Integer id, MagazineUpdateArgument argument);

    List<Magazine> getAll();
}
