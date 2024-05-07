package com.capston.project.merchantmanagement.config;

import com.capston.project.merchantmanagement.entities.Users;
import com.capston.project.merchantmanagement.repo.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.apache.commons.codec.binary.Hex;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private String decryptedText = "";
    private SecretKeySpec skeySpec;
    private IvParameterSpec ivSpec;
    private String finalEncryptedPayload ="";
    @Autowired
    private UserRepository repo;

    @Autowired
    private BCryptPasswordEncoder pwd;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        // Implement your logic to verify username and password against a database or other source
        try {
            if (isValidUsernameAndPassword(username, password)) {
                // If valid, create a new authenticated object with authorities
                List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));
                return new UsernamePasswordAuthenticationToken(username, password, authorities);
            } else {
                throw new BadCredentialsException("Invalid username or password");
            }
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isValidUsernameAndPassword(String username, String password) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Users user = repo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        if(pwd.encode(password).equals(decrypt(user.getPassword(),"77636d706c65496466654145536b658573626e716c65496468634245536d7488")))
            return true;
        return false; // Replace with your actual validation logic
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


    private String decrypt(String encrypted, String secretKey)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        try {
            decryptedText = "";
            //Decoding the Hexadecimal key to bytes
            byte[] secretKeyHexbytes = Hex.decodeHex(secretKey.toCharArray());
            //Creating the Secret key spec

            if (secretKeyHexbytes.length == 32 || secretKeyHexbytes.length == 24 || secretKeyHexbytes.length == 16) {
                SecretKeySpec skeySpec = new SecretKeySpec(secretKeyHexbytes, "AES");
            } else {
                throw new BadRequestException("Invalid Key Length, Must be 16/24/32 bytes");
            }

            //Decoding the Base64 string to combined byte array
            byte[] encryptedCombinedBytes = Base64.getDecoder().decode(encrypted);
            //Getting the IV from combined byte array
            byte[] iv = Arrays.copyOfRange(encryptedCombinedBytes, 0, 16); // IV String
            //Get the encrypted bytes from combined array for decryption
            byte[] encryptedPayload = Arrays.copyOfRange(encryptedCombinedBytes, iv.length,
                    encryptedCombinedBytes.length); // encrypted text

            // creating the ivspec
            ivSpec = new IvParameterSpec(iv);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //Initialize the Cipher
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);

            //Decrypting the payload
            byte[] decryptedBytes = cipher.doFinal(encryptedPayload);

            //Creating a string from the decrypted bytes
            decryptedText = new String(decryptedBytes);

            return decryptedText;

        } catch (NoSuchAlgorithmException exc) {
            System.out.println(exc.getMessage());
            return decryptedText;
        } catch (NoSuchPaddingException exc) {
            System.out.println(exc.getMessage());
            return decryptedText;
        } catch (InvalidKeyException exc) {
            System.out.println(exc.getMessage());
            return decryptedText;
        } catch (InvalidAlgorithmParameterException exc) {
            System.out.println(exc.getMessage());
            return decryptedText;
        } catch (IllegalBlockSizeException exc) {
            System.out.println(exc.getMessage());
            return decryptedText;
        } catch (BadPaddingException exc) {
            System.out.println(exc.getMessage());
            return decryptedText;
        } catch (BadRequestException exc) {
            System.out.println(exc.getMessage());
            return finalEncryptedPayload;
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            return decryptedText;

        }
    }
}
