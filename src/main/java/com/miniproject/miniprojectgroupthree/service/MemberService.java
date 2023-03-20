package com.miniproject.miniprojectgroupthree.service;

import com.miniproject.miniprojectgroupthree.domain.Member;
import com.miniproject.miniprojectgroupthree.error.AlreadyRegisteredUserException;
import com.miniproject.miniprojectgroupthree.form.MemberSaveForm;
import com.miniproject.miniprojectgroupthree.repository.MemberRepository;
import com.miniproject.miniprojectgroupthree.util.AES256Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final AES256Encoder aes256Encoder;


    public void signup(MemberSaveForm form) {
        memberRepository.findByAccount(form.getAccount())
                .ifPresentOrElse(
                        user -> {
                            throw new AlreadyRegisteredUserException();
                        },
                        () -> {
                            Member member = aes256Encoder.encodeMember(
                                    Member.builder()
                                    .account(form.getAccount())
                                    .password(passwordEncoder.encode(form.getPassword()))
                                    .name(form.getName())
                                    .birth(form.getBirth().toString())
                                    .phoneNumber(form.getPhoneNumber())
                                    .authority("ROLE_USER")
                                    .build()
                            );

                            memberRepository.save(member);
                        }
                );
    }

//    @Override
//    public UserDetails loadUserByUsername(String account) {
//        return memberRepository.findByAccount(aes256Encoder.encodeString(account))
//                .orElseThrow(() ->
//                        new UsernameNotFoundException("사용자가 없습니다."));
//    }
}
