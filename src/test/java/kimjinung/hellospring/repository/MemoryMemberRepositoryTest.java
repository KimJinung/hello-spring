package kimjinung.hellospring.repository;

import kimjinung.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setName("Jinung");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        assertThat(result).isEqualTo(member);
    }

    @Test
    void findByName() {
        Member member1 = new Member();
        member1.setName("Jinung");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring");
        repository.save(member2);

        Member resultOfMember1 = repository.findByName("Jinung").get();

        assertThat(resultOfMember1).isEqualTo(member1);

        Member resultOfMember2 = repository.findByName("Spring").get();

        assertThat(resultOfMember2).isEqualTo(member2);
    }

    @Test
    void findAll() {
        Member member1 = new Member();
        member1.setName("Jinung");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}