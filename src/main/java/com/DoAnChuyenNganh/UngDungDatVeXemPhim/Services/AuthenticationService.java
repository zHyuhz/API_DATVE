package com.DoAnChuyenNganh.UngDungDatVeXemPhim.Services;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Entity.Accounts;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Exception.AppException;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Exception.ErrorCode;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.Repo.AccountRepo;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request.AuthenticationRequest;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.request.IntroSpectTokenRequest;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.AuthenticationResponse;
import com.DoAnChuyenNganh.UngDungDatVeXemPhim.dto.response.IntroSpectTokenResponse;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Service
public class AuthenticationService {
	@Autowired
	AccountRepo accountRepo;
	@Value("${jwt.signerKey}")
	protected String SIGNER_KEY;
	
	//Xác thực token
	public IntroSpectTokenResponse introspect(IntroSpectTokenRequest request) throws JOSEException, ParseException {
		var token = request.getToken();
		
		JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
		
		SignedJWT signedJWT = SignedJWT.parse(token);
		
		
		Date expityTimeToken = signedJWT.getJWTClaimsSet().getExpirationTime();
		
		var verified = signedJWT.verify(verifier);
		
		return IntroSpectTokenResponse.builder()
				.valid(verified && expityTimeToken.after(new Date()))
				.build();
	}
	
	
	//Trả về API có token
	public AuthenticationResponse Authenticate(AuthenticationRequest request) throws KeyLengthException, JOSEException {
		var account = accountRepo.findByuserName(request.getUserName())
				.orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

		boolean result = passwordEncoder.matches(request.getPassword(), account.getPassword());

		if (!result) {
			throw new AppException(ErrorCode.LOGIN_FAILED);
		}

		var token = generateToken(account);

		return AuthenticationResponse.builder()
				.token(token)
				.Authenticated(true)
				.build();

	}

	//Tạo token
	private String generateToken(Accounts accounts) throws KeyLengthException, JOSEException {
		//Tạo header
		JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

		JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
				.subject(accounts.getUserName())
				.issuer("HuyDev.com")
				.issueTime(new Date())
				.expirationTime(new Date(
						Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli())) //Token có hạn là 1 tiếng
				.claim("scope", accounts.getAccountRole())
				.build();

		Payload payload = new Payload(jwtClaimsSet.toJSONObject());

		JWSObject jwsObject = new JWSObject(header, payload);

		try {
			jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
			return jwsObject.serialize();
		} catch (JOSEException e) {
			throw new RuntimeException(e);
		}

	}
}
