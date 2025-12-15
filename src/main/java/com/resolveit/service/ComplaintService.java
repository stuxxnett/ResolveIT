package com.resolveit.service;

import com.resolveit.dto.ComplaintRequest;
import com.resolveit.model.Complaint;
import com.resolveit.model.User;
import com.resolveit.repository.ComplaintRepository;
import com.resolveit.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepo;
    private final UserRepository userRepo;

    public ComplaintService(ComplaintRepository complaintRepo, UserRepository userRepo) {
        this.complaintRepo = complaintRepo;
        this.userRepo = userRepo;
    }

    public Complaint createComplaint(ComplaintRequest request) {
        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Complaint complaint = new Complaint();
        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setStatus("PENDING");
        complaint.setUser(user);

        return complaintRepo.save(complaint);
    }

    public List<Complaint> getUserComplaints(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return complaintRepo.findByUser(user);
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepo.findAll();
    }

    public Complaint assignDepartment(Long complaintId, Long departmentId) {
        Complaint complaint = complaintRepo.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        complaint.setStatus("ASSIGNED");
        return complaintRepo.save(complaint);
    }

    public Complaint resolveComplaint(Long complaintId) {
        Complaint complaint = complaintRepo.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        complaint.setStatus("RESOLVED");
        return complaintRepo.save(complaint);
    }

}
