package com.naru.howaboutthis.user.domain;

// 인스턴스를 여러 개 만들 필요가 없으므로 싱글턴 패턴을 적용하여
// 유일한 단일 객체로 만든다
public class PolicyFactory {

    static Policy policy;

    private PolicyFactory() {}

    public static Policy getInstance() {
        if (policy == null) {
            policy = new Policy();
        }
        return policy;
    }
}
