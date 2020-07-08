package ru.pnu.edu.articledatabase.servise.release.argument;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class ReleaseUpdateArgument {
    Integer year;
    Integer number;
}
