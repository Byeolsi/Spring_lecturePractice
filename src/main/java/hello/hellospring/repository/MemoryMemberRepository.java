package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    // 회원을 메모리에 저장하기 위해 사용.
    private static long sequence = 0L;
    // '0L'은 'long 타입의 0'이라는 의미.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        // 이미 member라는 객체 안에는 name이 저장되어 있는 상태.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // findAny()에 의해 일치하는 것을 하나라도 찾는 순간, 그 객체를 바로 반환.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // store에 있는 값이 바로 멤버들임.
    }

    public void clearStore() {
        store.clear();
    }
}
