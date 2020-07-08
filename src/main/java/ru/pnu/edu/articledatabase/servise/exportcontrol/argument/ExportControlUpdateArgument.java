package ru.pnu.edu.articledatabase.servise.exportcontrol.argument;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
@AllArgsConstructor
public class ExportControlUpdateArgument {
    Integer number;
    Date date;
}
