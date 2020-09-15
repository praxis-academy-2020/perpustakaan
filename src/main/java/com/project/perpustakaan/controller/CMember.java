package com.project.perpustakaan.controller;

import java.util.List;
import java.util.Optional;

import com.project.perpustakaan.model.Member;
import com.project.perpustakaan.repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/member")
public class CMember {
    @Autowired
    private MemberRepo memberRepo;
    
    //menampilkan semua
    @GetMapping(path = "/get")
    public List<Member> get_all(){
        return memberRepo.findAll();
    }

    //get by Id
    @GetMapping(path= "/get/{id}")
    public Optional<Member> idMember(@PathVariable Long id){
        return memberRepo.findById(id);
    }

    //post
    @PostMapping(path="/post")
    public Member addMember(@RequestBody Member member){
        return memberRepo.save(member);
    }

    //update
    // @PutMapping("/{id}")
    // Member updatemember(@RequestBody Member newUser, @PathVariable Long id) {
      
    //   return memberRepository.findById(id)
    //   .map(member -> {
    //     member.setNama(newUser.getNama());
    //     member.setEmail(newUser.getEmail());
    //     member.setNo_hp(newUser.getNo_hp());
    //     return katalogRepository.save(member);

    //   })
    //   .orElseGet(() -> {
    //      newUser.setId(id);
    //     return memberRepository.save(newUser);
    //   });
    // }
    
    //delete
    @DeleteMapping(path= "/delete/{id}")
    public void deleteMember(@PathVariable Long id){
        memberRepo.deleteById(id);
    }     
}
