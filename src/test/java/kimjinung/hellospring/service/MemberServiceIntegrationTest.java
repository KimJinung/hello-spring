package kimjinung.hellospring.service;

import kimjinung.hellospring.domain.Member;
import kimjinung.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void join() throws Exception{
        Member member = new Member();
        member.setName("Jinung");

        Long saveId = memberService.join(member);

        Member findMember = memberRepository.findById(saveId).get();

        assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    public void checkDuplicateMember() throws Exception {
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