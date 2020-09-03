package com.naru.howaboutthis.user.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Policy {

    private static final int LENGTH = 20;

    // 길이를 정해놓고 생성하기 때문에 ArrayList 로 생성
    private static final List<Term> TERMS = new ArrayList<>();

    static {
        addTerm("제 1 조", "주식회사 나루가 제공하는 서비스를 이용해 주셔서 감사합니다");
        addTerm("제 2 조", "본 약관에 동의한 여러분 모두에게 감사합니다", "이 이용약관은 아무 효력이 없습니다");
    }

    // 가변인자로 생성하여 크기가 다른 항목들도 하나의 메소드로 처리할 수 있게 한다
    private static void addTerm(String title, String... contents) {
        TERMS.add(new Term(title, contents));
    }

    // Lombok의 @Getter를 사용하면 static 메소드로 Getter가 생성된다
    // static 메소드로 생성하면 Controller에서 호출이 불가능하므로 직접 Getter를 생성하였다
    public int getLENGTH() {
        return LENGTH;
    }

    public List<Term> getTERMS() {
        return TERMS;
    }
}
