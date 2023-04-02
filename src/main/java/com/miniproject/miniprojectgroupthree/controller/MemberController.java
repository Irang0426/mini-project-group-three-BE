package com.miniproject.miniprojectgroupthree.controller;

import com.miniproject.miniprojectgroupthree.dto.MemberDTO;
import com.miniproject.miniprojectgroupthree.service.MemberService;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;
  @GetMapping("/member/update")
  public String updateForm(HttpSession session, Model model) {
    String myEmail = (String) session.getAttribute("LoginEmail");
    MemberDTO memberDTO = memberService.updateForm(myEmail);
    model.addAttribute("updateMember", memberDTO);
    return "update";
  }
  @PostMapping("/member/update")
  public String update(@ModelAttribute MemberDTO memberDTO) {
    memberService.update(memberDTO);
    return "redirect:/member/" + memberDTO.getId();
  }
  @GetMapping("/member/")
  public String findAll(Model model) {
    List<MemberDTO> memberDTOList = memberService.findAll();
    model.addAttribute("memberList", memberDTOList);
    return "list";
  }
  @GetMapping("/member/{id}")
  public String findById(@PathVariable Long id, Model model) {
   MemberDTO memberDTO =  memberService.findByID(id);
    model.addAttribute("member", memberDTO);
    return "detail";
  }
  @GetMapping("/member/delete/{id")
  public String deleteById(@PathVariable Long id) {
    memberService.deleteById(id);
    return "redirect:/member/";
  }
}
