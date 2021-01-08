package com.miewone.DeuNotice.Contorller;


import com.miewone.DeuNotice.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class index {
    private final PostService postService;
    @GetMapping("/")
    public String home(Model model)
    {
        List<String> te = postService.getDepartment();
        model.addAttribute("notices",postService.getMembers());
        return "save";
    }
}
