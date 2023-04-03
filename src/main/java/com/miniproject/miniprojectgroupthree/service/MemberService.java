package com.miniproject.miniprojectgroupthree.service;

import com.miniproject.miniprojectgroupthree.dto.MemberDTO;
import com.miniproject.miniprojectgroupthree.entity.MemberEntity;
import com.miniproject.miniprojectgroupthree.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  public MemberDTO updateForm(String myEmail) {
   Optional<MemberEntity> optionalMemberEntity =  memberRepository.findByMemberEmail(myEmail);
   if (optionalMemberEntity.isPresent()) {
     return MemberDTO.toMemberDTO(optionalMemberEntity.get());
   } else {
     return null;
   }
  }
  public List<MemberDTO> findAll() {
    List<MemberEntity> memberEntityList = memberRepository.findAll();
    List<MemberDTO> memberDTOList = new ArrayList<>();
    for (MemberEntity memberEntity: memberEntityList) {
      memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
    }
    return memberDTOList;
  }
  public MemberDTO findByID(Long id) {
    Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
    if (optionalMemberEntity.isPresent()) {
      return MemberDTO.toMemberDTO(optionalMemberEntity.get());
    }else {
      return null;
    }
  }
  public void update(MemberDTO memberDTO) {
    memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
  }

  public void deleteById(Long id) {
    memberRepository.deleteById(id);
  }
}
