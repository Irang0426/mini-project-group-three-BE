package com.miniproject.miniprojectgroupthree.dto;

import com.miniproject.miniprojectgroupthree.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {

  private Long id;
  private String memberEmail;
  private String memberPassword;
  private String memberName;

  public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setId(memberEntity.getId());
    memberDTO.setMemberEmail(memberEntity.getMemberEmail());
    memberDTO.setMemberPassword(memberEntity.getMemberPassword());
    memberDTO.setMemberName(memberEntity.getMemberName());
    return memberDTO;
  }


}
