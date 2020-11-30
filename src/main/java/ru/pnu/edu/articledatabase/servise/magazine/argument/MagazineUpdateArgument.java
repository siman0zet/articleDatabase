package ru.pnu.edu.articledatabase.servise.magazine.argument;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
@AllArgsConstructor
public class MagazineUpdateArgument {
    String name;
    String linkOnPage;
    String emailAddresses;
    Date conferenceDate;
    Double impact;
    String isbn;
    String issn;
    String city;
    String publishing;
}
