package ru.pnu.edu.articledatabase.servise.magazine.argument;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import ru.pnu.edu.articledatabase.entity.Release;

import java.util.Date;

@Value
@Builder
@AllArgsConstructor
public class MagazineUpdateArgument {
    String name;
    String linkOnPage;
    String EmailAddresses;
    Release releaseId;
    Date conferenceDate;
    String pageRange;
}
