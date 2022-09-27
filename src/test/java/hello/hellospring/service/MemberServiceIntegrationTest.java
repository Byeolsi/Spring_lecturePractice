package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

//    MemberService memberService = new MemberService();
    // 새로운 객체를 생성했음. 내용물이 실제와 다를 수 있음.
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // 필요한 것을 그저 Injection하고, 테스트만 하고 끝이므로, 그냥 바로 @Autowired를 씀.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    // 테스트의 경우, 과감하게 이름을 한글로 작성해도 됨.
    void 회원가입() {
        // given: 무언가가 주어짐.
        Member member = new Member();
        member.setName("spring");

        // when: 이를 실행했을 때,
        Long saveId = memberService.join(member);

        // then: 결과가 어떤 것이 나와야 함.
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        // 이름이 "spring"으로 같음.

        // when
//        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        // 좀 더 간단한 방법
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // 'validateDuplicateMember'가 올바르게 작동한다면, 예외가 발생해야 함.
        // 예외가 발생했다면, 그 메시지의 내용은 "이미 존재하는 회원입니다."임.
        // 메시지 내용이 "이미 존재하는 회원입니다."이라면, 정상적으로 테스트가 진행됨.

        // then
    }
}