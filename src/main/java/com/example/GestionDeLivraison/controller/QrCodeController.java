package com.example.GestionDeLivraison.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/qr/qrcode")

public class QrCodeController {


    @PostMapping(value = "/generate/{commandeId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
    public void generateQRCodeFromCommande(@PathVariable Long commandeId, HttpServletResponse response) {
        try {
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Convertir l'identifiant 'commandeId' en chaîne de caractères
            String text = String.valueOf(commandeId);

            // Générer le QR code à partir du texte (le long converti en chaîne)
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, 200, 200, hints);

            // Définir le type de contenu pour la réponse
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            OutputStream outputStream = response.getOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
            outputStream.close();

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération du QR Code", e);
        }
    }}