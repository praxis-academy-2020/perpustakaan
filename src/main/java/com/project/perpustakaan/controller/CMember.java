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
    @GetMapping(path = "/")
    public List<Member> get_all(){
        return memberRepo.findAll();
    }

    //get by Id
    @GetMapping(path= "/{id}")
    public Optional<Member> idMember(@PathVariable Long id){
        return memberRepo.findById(id);
    }

    //post
    @PostMapping(path="/")
    public Member addMember(@RequestBody Member member){
        return memberRepo.save(member);
    }

    //update
    @PutMapping("/{id}")
    Member updatemember(@RequestBody Member newMember, @PathVariable Long id) {
      
      return memberRepo.findById(id)
      .map(member -> {
        member.setNama(newMember.getNama());
        member.setEmail(newMember.getEmail());
        member.setNoHp(newMember.getNoHp());
        return memberRepo.save(member);

      })
      .orElseGet(() -> {
        return memberRepo.save(newMember);
      });
    }
    
    //delete
    @DeleteMapping(path= "/{id}")
    public void deleteMember(@PathVariable Long id){
        memberRepo.deleteById(id);
    }     
}
