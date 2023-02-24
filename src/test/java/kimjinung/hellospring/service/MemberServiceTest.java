package kimjinung.hellospring.service;

import kimjinung.hellospring.domain.Member;
import kimjinung.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void AfterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() throws Exception{
        Member member = new Member();
        member.setName("Jinung");

        Long saveId = memberService.join(member);

        Member findMember = memberRepository.findById(saveId).get();

        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void checkDuplicateMember() {
        Member member1 = new Member();
        member1.setName("Jinung");

        Member member2 = new Member();
        member2.setName("Jinung");

        memberService.join(member1);

        IllegalStateException error = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        assertThat(error.getMessage()).isEqualTo("Already exist member name");
    }
}