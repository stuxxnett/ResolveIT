package com.resolveit.controller;

import com.resolveit.dto.AssignDepartmentRequest;
import com.resolveit.dto.ComplaintRequest;
import com.resolveit.dto.FeedbackRequest;
import com.resolveit.model.Complaint;
import com.resolveit.service.ComplaintService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PutMapping("/feedback")
    public Complaint submitFeedback(@RequestBody FeedbackRequest request) {
        return complaintService.addFeedback(
                request.getComplaintId(),
                request.getFeedback(),
                request.getRating()
        );
    }

    @PostMapping
    public Complaint submitComplaint(@RequestBody ComplaintRequest request) {
        return complaintService.createComplaint(request);
    }

    @PostMapping(
            value = "/with-file",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public Complaint submitComplaintWithFile(
            @RequestParam("userId") Long userId,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws Exception {

        ComplaintRequest req = new ComplaintRequest();
        req.setUserId(userId);
        req.setTitle(title);
        req.setDescription(description);

        return complaintService.createComplaintWithFile(req, file);
    }

    @GetMapping("/user/{userId}")
    public List<Complaint> getUserComplaints(@PathVariable Long userId) {
        return complaintService.getUserComplaints(userId);
    }

    // ================= ADMIN APIs =================

    @GetMapping
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    @PutMapping("/assign")
    public void assignDepartment(@RequestBody AssignDepartmentRequest request) {
        complaintService.assignDepartment(
                request.getComplaintId(),
                request.getDepartmentId()
        );
    }

    @PutMapping("/{complaintId}/resolve")
    public Complaint resolveComplaint(@PathVariable Long complaintId) {
        return complaintService.resolveComplaint(complaintId);
    }
}
