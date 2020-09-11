package com.naru.howaboutthis.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

// 기본 생성자의 접근제한자를 private으로 하여 만약의 생성을 방지한다
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Policy {

    private static final int LENGTH = 20;

    // 길이를 정해놓고 생성하기 때문에 ArrayList 로 생성
    private static final List<Term> TERMS = new ArrayList<>();

    // Lombok의 @Getter를 사용하면 static 메소드로 Getter가 생성된다
    // static 메소드로 생성하면 Controller에서 호출이 불가능하므로 직접 Getter를 생성하였다
    public int getLENGTH() {
        return LENGTH;
    }

    public List<Term> getTERMS() {
        return TERMS;
    }

    static {
        addTerm("제 1 조", "주식회사 나루가 제공하는 서비스를 이용해 주셔서 감사합니다");
        addTerm("제 2 조", "이 이용약관은 아무 효력이 없습니다", "이 이용약관은 효력이 발생하지 않습니다");
    }

    // 가변인자로 생성하여 크기가 다른 항목들도 하나의 메소드로 처리할 수 있게 한다
    private static void addTerm(String title, String... contents) {
        TERMS.add(new Term(title, contents));
    }

    @Getter
    @AllArgsConstructor
    // 이너 클래스로 생성하여 외부에서 접근할 수 없도록 한다
    private static class Term {

        private final String title;

        private final String[] contents;
    }
}
