
package com.resolveit.service;
import com.resolveit.model.User;
import com.resolveit.util.FileUploadUtil;
import com.resolveit.model.Department;

import org.springframework.web.multipart.MultipartFile;

import com.resolveit.dto.ComplaintRequest;
import com.resolveit.model.Complaint;
import com.resolveit.repository.ComplaintRepository;
import com.resolveit.repository.DepartmentRepository;
import com.resolveit.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepo;
    private final UserRepository userRepo;
    private final DepartmentRepository departmentRepo;


    public ComplaintService(ComplaintRepository complaintRepo, UserRepository userRepo, DepartmentRepository departmentRepo) {
        this.complaintRepo = complaintRepo;
        this.userRepo = userRepo;
        this.departmentRepo = departmentRepo;
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

    public Complaint createComplaintWithFile(
            ComplaintRequest request,
            MultipartFile file
    ) throws Exception {

        // Create complaint using existing logic
        Complaint complaint = createComplaint(request);

        // Handle file
        if (file != null && !file.isEmpty()) {

            String filePath = FileUploadUtil.saveFile(file);

            String contentType = file.getContentType();
            String type = contentType != null && contentType.startsWith("image")
                    ? "IMAGE"
                    : "VIDEO";

            complaint.setAttachmentPath(filePath);
            complaint.setAttachmentType(type);

            // ✅ NON-static call
            return complaintRepo.save(complaint);
        }

        return complaint;
    }



    public List<Complaint> getUserComplaints(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return complaintRepo.findByUser(user);
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepo.findAll();
    }

    public void assignDepartment(Long complaintId, Long departmentId) {

        Complaint complaint = complaintRepo.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        Department department = departmentRepo.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        complaint.setDepartment(department);

        complaintRepo.save(complaint);
    }


    public Complaint resolveComplaint(Long complaintId) {
        Complaint complaint = complaintRepo.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        complaint.setStatus("RESOLVED");
        return complaintRepo.save(complaint);
    }
    public Complaint addFeedback(Long complaintId, String feedback, Integer rating) {

        Complaint complaint = complaintRepo.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        if (!"RESOLVED".equals(complaint.getStatus())) {
            throw new RuntimeException("Feedback allowed only for resolved complaints");
        }

        complaint.setFeedback(feedback);
        complaint.setRating(rating);

        return complaintRepo.save(complaint);
    }


}
