package com.fiap.tech.challenge.global.util;

import com.fiap.tech.challenge.global.exception.HashingException;
import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.util.Strings;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Log
@UtilityClass
public class HashUtil {

    public static String hash(String text) {
        try {
            return DigestUtils.md2Hex(text);
        } catch (Exception ex) {
            return Strings.EMPTY;
        }
    }

    public static String md5(File file) {
        try {
            return DigestUtils.md5Hex(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            throw new HashingException(e.getMessage());
        }
    }

    public static String md5(byte[] bytes) {
        try {
            return DigestUtils.md5Hex(bytes);
        } catch (Exception e) {
            throw new HashingException(e.getMessage());
        }
    }

    public static String generate() {
        try {
            return UUID.randomUUID().toString().replaceAll("-", Strings.EMPTY).toUpperCase();
        } catch (Exception ex) {
            return Strings.EMPTY;
        }
    }

    public static String generateHash() {
        return UUID.randomUUID().toString().replaceAll("-", Strings.EMPTY);
    }

    public static String generateCode() {
        return generate().substring(0, 6);
    }
}
