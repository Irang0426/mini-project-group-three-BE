package com.miniproject.miniprojectgroupthree.form;



import com.miniproject.miniprojectgroupthree.validation.ValidationSteps;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@GroupSequence({
        MemberSaveForm.class,
        ValidationSteps.Step1.class,
        ValidationSteps.Step2.class,
        ValidationSteps.Step3.class,
        ValidationSteps.Step4.class,
        ValidationSteps.Step5.class,
        ValidationSteps.Step6.class,
        ValidationSteps.Step7.class,
        ValidationSteps.Step8.class,
        ValidationSteps.Step9.class,
        ValidationSteps.Step10.class,
})
public class MemberSaveForm {

    @NotEmpty(groups = ValidationSteps.Step1.class,
            message = "{MemberSaveForm.account.NotEmpty}")
    @Email(groups = ValidationSteps.Step2.class,
            message = "{MemberSaveForm.account.Email}")
    private String account;

    @NotEmpty(groups = ValidationSteps.Step3.class,
            message = "{MemberSaveForm.password.NotEmpty}")
    // 숫자와 문자 포함 형태의 6~12자리 이내의 암호 정규식
    @Pattern(regexp = "^[A-Za-z0-9]{6,12}$", groups = ValidationSteps.Step4.class,
            message = "{MemberSaveForm.password.Pattern}")
    private String password;

    @NotEmpty(groups = ValidationSteps.Step5.class,
            message = "{MemberSaveForm.name.NotEmpty}")
    @Length(min = 2, max = 5, groups = ValidationSteps.Step6.class,
            message = "{MemberSaveForm.name.Length}")
    private String name;

    @NotNull(groups = ValidationSteps.Step7.class,
            message = "{MemberSaveForm.birth.NotEmpty}")
    @Past(groups = ValidationSteps.Step8.class,
            message = "{MemberSaveForm.birth.Past}")
    private LocalDate birth;

    @NotNull(groups = ValidationSteps.Step9.class,
            message = "{MemberSaveForm.phoneNumber.NotEmpty}")
    @Pattern(regexp = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", groups = ValidationSteps.Step10.class,
            message = "{MemberSaveForm.phoneNumber.Pattern}")
    private String phoneNumber;

}
