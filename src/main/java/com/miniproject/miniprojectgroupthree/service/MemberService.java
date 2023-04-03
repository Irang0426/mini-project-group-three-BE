package com.miniproject.miniprojectgroupthree.service;

import com.miniproject.miniprojectgroupthree.domain.Member;
import com.miniproject.miniprojectgroupthree.domain.Role;
import com.miniproject.miniprojectgroupthree.error.AlreadyRegisteredUserException;
import com.miniproject.miniprojectgroupthree.form.MemberSaveForm;
import com.miniproject.miniprojectgroupthree.repository.MemberRepository;
import com.miniproject.miniprojectgroupthree.util.AES256Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final AES256Encoder aes256Encoder;


    /**
     * 회원 가입
     * aes256Encoder - account, member
     * passwordEncoder - password
     * 이미 가입한 계정 있을 경우 throw new AlreadyRegisteredUserException();
     *
     * @param form the form
     */
    public void signup(MemberSaveForm form) {
        memberRepository.findByAccount(aes256Encoder.encodeString(form.getAccount()))
                .ifPresentOrElse(
                        user -> { // ifPresent로 구성하여 그냥 아래 코드와 분리하기
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
                                            .role(Role.ROLE_USER)
                                            .build()
                            );

                            memberRepository.save(member);
                        }
                );
    }

}
