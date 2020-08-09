package ru.otus.erinary.hw09.library.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentModel {

    private Long id;
    private String text;
    private String user;
    private String date;
    private Long bookId;
}
