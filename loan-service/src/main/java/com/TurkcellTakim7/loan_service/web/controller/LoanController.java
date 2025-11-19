package com.TurkcellTakim7.loan_service.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkcellTakim7.loan_service.application.commandHandlers.CreateLoanCommandHandler;
import com.TurkcellTakim7.loan_service.application.commandHandlers.ExtendLoanCommandHandler;
import com.TurkcellTakim7.loan_service.application.commandHandlers.ReturnLoanCommandHandler;
import com.TurkcellTakim7.loan_service.application.commands.CreateLoanCommand;
import com.TurkcellTakim7.loan_service.application.commands.ExtendLoanCommand;
import com.TurkcellTakim7.loan_service.application.commands.ReturnLoanCommand;
import com.TurkcellTakim7.loan_service.application.dtos.CreateLoanRequest;
import com.TurkcellTakim7.loan_service.application.dtos.CreatedLoanResponse;
import com.TurkcellTakim7.loan_service.application.dtos.ExtendLoanRequest;
import com.TurkcellTakim7.loan_service.application.dtos.LoanResponse;
import com.TurkcellTakim7.loan_service.application.dtos.ReturnLoanRequest;
import com.TurkcellTakim7.loan_service.application.queries.GetLoanByIdQuery;
import com.TurkcellTakim7.loan_service.application.queries.GetLoansByMemberQuery;
import com.TurkcellTakim7.loan_service.application.queryHandlers.GetLoanByIdQueryHandler;
import com.TurkcellTakim7.loan_service.application.queryHandlers.GetLoansByMemberQueryHandler;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    private final CreateLoanCommandHandler createLoanCommandHandler;
    private final ReturnLoanCommandHandler returnLoanCommandHandler;
    private final ExtendLoanCommandHandler extendLoanCommandHandler;
    private final GetLoanByIdQueryHandler getLoanByIdQueryHandler;
    private final GetLoansByMemberQueryHandler getLoansByMemberQueryHandler;

    public LoanController(CreateLoanCommandHandler createLoanCommandHandler,
            ReturnLoanCommandHandler returnLoanCommandHandler,
            ExtendLoanCommandHandler extendLoanCommandHandler,
            GetLoanByIdQueryHandler getLoanByIdQueryHandler,
            GetLoansByMemberQueryHandler getLoansByMemberQueryHandler) {
        this.createLoanCommandHandler = createLoanCommandHandler;
        this.returnLoanCommandHandler = returnLoanCommandHandler;
        this.extendLoanCommandHandler = extendLoanCommandHandler;
        this.getLoanByIdQueryHandler = getLoanByIdQueryHandler;
        this.getLoansByMemberQueryHandler = getLoansByMemberQueryHandler;
    }

    // 1) create loan
    @PostMapping
    public ResponseEntity<CreatedLoanResponse> create(@RequestBody CreateLoanRequest request) {
        System.out.println("Incoming memberId from request = " + request.memberId());
        CreatedLoanResponse response = createLoanCommandHandler.handle(new CreateLoanCommand(request.memberId(),
                request.bookId(), request.staffId(), request.loanDate(), request.dueDate()));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 2) get loan by id
    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getById(@PathVariable("id") String id) {
        LoanResponse response = getLoanByIdQueryHandler.handle(
                new GetLoanByIdQuery(id));
        return ResponseEntity.ok(response);
    }

    // 3) get loans by member
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<LoanResponse>> getByMember(@PathVariable("memberId") String memberId) {
        List<LoanResponse> response = getLoansByMemberQueryHandler.handle(
                new GetLoansByMemberQuery(memberId));
        return ResponseEntity.ok(response);
    }

    // 4) return loan
    @PostMapping("/{id}/return")
    public ResponseEntity<Void> returnLoan(@PathVariable("id") String id,
            @RequestBody(required = false) ReturnLoanRequest request) {

        returnLoanCommandHandler.handle(
                new ReturnLoanCommand(
                        id,
                        request != null ? request.returnDate() : null));

        return ResponseEntity.noContent().build();
    }

    // 5) extend loan
    @PostMapping("/{id}/extend")
    public ResponseEntity<Void> extendLoan(@PathVariable("id") String id,
            @RequestBody ExtendLoanRequest request) {

        extendLoanCommandHandler.handle(
                new ExtendLoanCommand(
                        id,
                        request.days()));

        return ResponseEntity.noContent().build();
    }
}
