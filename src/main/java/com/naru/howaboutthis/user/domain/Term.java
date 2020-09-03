package com.naru.howaboutthis.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Term {

    private final String title;

    private final String[] contents;
}
