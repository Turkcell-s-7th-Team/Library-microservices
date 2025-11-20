package com.TurkcellTakim7.fine_service.domain.services;

import java.time.LocalDate;
import java.util.List;

import com.TurkcellTakim7.fine_service.domain.entities.Fine;
import com.TurkcellTakim7.fine_service.domain.enums.FineStatus;
import com.TurkcellTakim7.fine_service.domain.enums.FineType;
import com.TurkcellTakim7.fine_service.domain.exceptions.FineNotFoundException;
import com.TurkcellTakim7.fine_service.domain.exceptions.FineValidationException;
import com.TurkcellTakim7.fine_service.domain.exceptions.InvalidFineOperationException;
import com.TurkcellTakim7.fine_service.domain.exceptions.UnpaidFinesExistsException;
import com.TurkcellTakim7.fine_service.domain.repositories.FineRepository;
import com.TurkcellTakim7.fine_service.domain.valueobjects.Amount;
import com.TurkcellTakim7.fine_service.domain.valueobjects.FineId;
import com.TurkcellTakim7.fine_service.domain.valueobjects.LoanId;
import com.TurkcellTakim7.fine_service.domain.valueobjects.MemberId;

public class FineDomainService {

    private final FineRepository fineRepository;

    public FineDomainService(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

 
    public Fine createFine(MemberId memberId,
                           LoanId loanId,
                           FineType fineType,
                           Amount amount) {

        if (memberId == null) {
            throw new FineValidationException("MemberId cannot be null");
        }
        if (loanId == null) {
            throw new FineValidationException("LoanId cannot be null");
        }
        if (fineType == null) {
            throw new FineValidationException("FineType cannot be null");
        }
        if (amount == null) {
            throw new FineValidationException("Amount cannot be null");
        }

        Fine fine = Fine.create(
                memberId,
                loanId,
                fineType,
                amount,
                LocalDate.now()
        );

        return fineRepository.save(fine);
    }

    /**
     * Ceza getirir, yoksa FineNotFoundException fırlatır.
     */
    public Fine getFine(FineId fineId) {
        if (fineId == null) {
            throw new FineValidationException("FineId cannot be null");
        }

        return fineRepository.findById(fineId)
                .orElseThrow(() -> new FineNotFoundException(fineId));
    }

    /**
     * Bir üyeye ait tüm cezaları listeler.
     */
    public List<Fine> getFinesByMember(MemberId memberId) {
        if (memberId == null) {
            throw new FineValidationException("MemberId cannot be null");
        }

        return fineRepository.findByMemberId(memberId);
    }

    /**
     * Ceza tutarını günceller.
     * Ödenmiş cezaların güncellenmesine izin verilmeyebilir.
     */
    public Fine updateFineAmount(FineId fineId, Amount newAmount) {
        if (fineId == null) {
            throw new FineValidationException("FineId cannot be null");
        }
        if (newAmount == null) {
            throw new FineValidationException("Amount cannot be null");
        }

        Fine fine = fineRepository.findById(fineId)
                .orElseThrow(() -> new FineNotFoundException(fineId));

        if (fine.getStatus() == FineStatus.PAID) {
            throw new InvalidFineOperationException(
                    "Cannot update amount of a paid fine.",
                    fineId
            );
        }

        fine.updateAmount(newAmount);
        return fineRepository.save(fine);
    }

    /**
     * Ceza siler.
     * (İstersen burada da "paid" cezaların silinmesini engelleyebilirsin.)
     */
    public void deleteById(FineId fineId) {
        if (fineId == null) {
            throw new FineValidationException("FineId cannot be null");
        }

        Fine fine = fineRepository.findById(fineId)
                .orElseThrow(() -> new FineNotFoundException(fineId));

        // Örneğin: ödenmiş cezaları silmek istemiyorsan
        if (fine.getStatus() == FineStatus.PAID) {
            throw new InvalidFineOperationException(
                    "Cannot delete a paid fine.",
                    fineId
            );
        }

        fineRepository.deleteById(fineId);
    }

    /**
     * Cezayı öder.
     */
    public Fine payFine(FineId fineId, LocalDate paymentDate) {
        if (fineId == null) {
            throw new FineValidationException("FineId cannot be null");
        }
        if (paymentDate == null) {
            paymentDate = LocalDate.now();
        }

        Fine fine = fineRepository.findById(fineId)
                .orElseThrow(() -> new FineNotFoundException(fineId));

        if (fine.getStatus() == FineStatus.PAID) {
            throw new InvalidFineOperationException(
                    "Fine is already paid.",
                    fineId
            );
        }

        fine.markAsPaid(paymentDate);
        return fineRepository.save(fine);
    }

    /**
     * Üyenin ödenmemiş cezası var mı?
     */
    public boolean hasUnpaidFines(MemberId memberId) {
        if (memberId == null) {
            throw new FineValidationException("MemberId cannot be null");
        }

        return fineRepository.existsByMemberIdAndStatus(memberId, FineStatus.UNPAID);
    }

    /**
     * Üyenin ödenmemiş cezası varsa exception atar.
     * Loan / Reservation oluştururken bu metodu kullanabilirsin.
     */
    public void ensureMemberHasNoUnpaidFines(MemberId memberId) {
        if (hasUnpaidFines(memberId)) {
            throw new UnpaidFinesExistsException(memberId);
        }
    }

   
    public List<Fine> getFineList() {
        return fineRepository.getAllFines();
    }
    
    
    
}

