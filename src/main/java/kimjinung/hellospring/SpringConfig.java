package kimjinung.hellospring;

import kimjinung.hellospring.repository.JdbcMemberRepository;
import kimjinung.hellospring.repository.JdbcTemplateMemberRepository;
import kimjinung.hellospring.repository.MemberRepository;
import kimjinung.hellospring.repository.MemoryMemberRepository;
import kimjinung.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
