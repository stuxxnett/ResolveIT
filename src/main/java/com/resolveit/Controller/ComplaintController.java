package com.resolveit.controller;

import com.resolveit.dto.AssignDepartmentRequest;
import com.resolveit.dto.ComplaintRequest;
import com.resolveit.model.Complaint;
import com.resolveit.service.ComplaintService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin(origins = "*")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    // ================= USER APIs =================

    // User submits a complaint
    @PostMapping
    public Complaint submitComplaint(@RequestBody ComplaintRequest request) {
        return complaintService.createComplaint(request);
    }

    // User views their complaints
    @GetMapping("/user/{userId}")
    public List<Complaint> getUserComplaints(@PathVariable Long userId) {
        return complaintService.getUserComplaints(userId);
    }

    // ================= ADMIN APIs =================

    // Admin views ALL complaints
    @GetMapping
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    // Admin assigns department to complaint
    @PutMapping("/{complaintId}/assign")
    public Complaint assignDepartment(@PathVariable Long complaintId,
                                      @RequestBody AssignDepartmentRequest request) {
        return complaintService.assignDepartment(complaintId, request.getDepartmentId());
    }

    // Admin resolves a complaint
    @PutMapping("/{complaintId}/resolve")
    public Complaint resolveComplaint(@PathVariable Long complaintId) {
        return complaintService.resolveComplaint(complaintId);
    }



}
