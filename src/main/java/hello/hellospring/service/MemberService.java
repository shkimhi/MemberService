package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemoryMemberRepository memoryMemberRepository;

    @Autowired
    public MemberService(MemoryMemberRepository memoryMemberRepository){
        this.memoryMemberRepository = memoryMemberRepository;
    }

    public Long join(Member member){
        validateDuplicateMember(member);
        memoryMemberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        memoryMemberRepository.findByName(member.getName()).ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원 입니다.");
        });
    }

    public List<Member> findMembers(){
        return memoryMemberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memoryMemberRepository.findById(memberId);
    }


}
