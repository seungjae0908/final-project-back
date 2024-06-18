package com.example.final_project_java.charger.api;

import com.example.final_project_java.auth.TokenUserInfo;
import com.example.final_project_java.charger.Entity.ChargingStation;
import com.example.final_project_java.charger.dto.request.ReservationChargerModifyRequestDTO;
import com.example.final_project_java.charger.dto.request.ReservationChargerRequestDTO;
import com.example.final_project_java.charger.dto.response.ChargerListResponseDTO;
import com.example.final_project_java.charger.dto.response.ReservationChargerResponseDTO;
import com.example.final_project_java.charger.service.ChargerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/charge")
public class ChargerController {

    private final ChargerService chargerService;

    // 충전소 목록 요청
    @GetMapping
    public ResponseEntity<?> retrieveChargerList() {
        log.info("/charge : GET!");

        ChargerListResponseDTO responseDTO = chargerService.retrieve();
        return ResponseEntity.ok().body(responseDTO);
    }

    // 충전소 예약하기
    @PostMapping("/reservation")
    public ResponseEntity<?> reservationCharge(
//            @AuthenticationPrincipal TokenUserInfo userInfo,
            @Validated @RequestBody ReservationChargerRequestDTO requestDTO,
            BindingResult result
    ) {
        log.info("/charge/reservation : POST - dto : {}", requestDTO);
//        log.info("TokenUserInfo : {}", userInfo);

//        ResponseEntity<List<FieldError>> validatedResult = getValidatedResult(result);
//        if (validatedResult != null) return validatedResult;

        ReservationChargerResponseDTO responseDTO = chargerService.reservation(requestDTO, requestDTO.getUserId(), requestDTO.getId());
        return ResponseEntity.ok().body(responseDTO);
    }

    // 충전소 예약 취소
//    @DeleteMapping("/reservation/{chargeNo}")
//    public ResponseEntity<?> deleteReservation(
//            @PathVariable("no") int chargeNo
//    ) {
//        log.info("/charge/reservation/{} : DELETE", chargeNo);
//
//        try {
//            ReservationChargerResponseDTO responseDTO = chargerService.delete(chargeNo);
//            return ResponseEntity.ok().body(responseDTO);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    // 충전소 예약 변경
//    @PatchMapping
//    public ResponseEntity<?> updateReservation(
//            @Validated @RequestBody ReservationChargerModifyRequestDTO requestDTO,
//            BindingResult result
//    ) {
////        ResponseEntity<List<FieldError>> validatedResult = getValidatedResult(result);
////        if (validatedResult != null) return validatedResult;
//
//        try {
//            return ResponseEntity.ok().body(chargerService.update(requestDTO));
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError()
//                    .body(e.getMessage());
//        }
//    }

}