package com.kam.order.service;

import com.kam.order.models.user.Admin;
import com.kam.order.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;


    public Admin save(Admin admin) {

        if(this.adminRepository.findAdminByEmail(admin.getEmail()).isPresent())
            throw new IllegalArgumentException("Duplicate Email");
        else if(this.adminRepository.findAdminByUserName(admin.getUserName()).isPresent())
            throw new IllegalArgumentException("Duplicate username");

        return this.adminRepository.save(admin);
    }

    public void deleteById(long adminId) {
        Admin admin =  this.adminRepository.findAdminById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        this.adminRepository.deleteById(adminId);
    }

    public List<Admin> getAllAdmins() {
        return this.adminRepository.findAll();
    }

    public Admin getAdminById(long adminId) {
        return this.adminRepository.findAdminById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
    }

}
