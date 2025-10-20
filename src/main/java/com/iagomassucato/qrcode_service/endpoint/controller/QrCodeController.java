package com.iagomassucato.qrcode_service.endpoint.controller;

import com.google.zxing.WriterException;
import com.iagomassucato.qrcode_service.endpoint.service.QrCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("service/v1/qrcode")
@RequiredArgsConstructor
public class QrCodeController {

    private final QrCodeService qrCodeService;

    @GetMapping
    public ResponseEntity<byte[]> generateQrCode(@RequestParam String text) {
        try {
            byte[] image = qrCodeService.generateQRCode(text, 250, 250);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"qrcode.png\"")
                    .contentType(MediaType.IMAGE_PNG)
                    .body(image);

        } catch (WriterException | IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
