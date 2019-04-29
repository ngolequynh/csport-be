package sportstracker.controller;

import sportstracker.common.SportsTrackerUtils;
import sportstracker.common.property.SecurityProperties;
import sportstracker.dao.entity.Account;
import sportstracker.model.AccountDto;
import sportstracker.model.FacebookInformationDto;
import sportstracker.model.ProfileAccountDto;
import sportstracker.security.JwtService;
import sportstracker.service.AccountService;
import sportstracker.service.FacebookLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * RestController for Login Facebook
 *
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {

    private FacebookLoginService facebookLoginService;
    private AccountService accountService;
    private JwtService jwtService;
    private final SecurityProperties properties;

    @Autowired
    public LoginController(FacebookLoginService facebookLoginService, AccountService accountService, JwtService jwtService, SecurityProperties properties) {
        this.facebookLoginService = facebookLoginService;
        this.accountService = accountService;
        this.jwtService = jwtService;
        this.properties = properties;
    }

    /**
     * @param facebookInformationDTO facebook user data
     * @return ResponseEntity contains:
     *         + Header: HttpStatus.OK
     *         + Body: facebookInformationDTO
     */
    @PostMapping("/facebook")
    public ResponseEntity<Object> loginFacebook(@Valid @RequestBody FacebookInformationDto facebookInformationDTO) {
        facebookLoginService.saveToDBIfUserNotRegistered(facebookInformationDTO);
        return new ResponseEntity<>(facebookLoginService.getAccountIdByFbUserId(facebookInformationDTO.getFbUserId())
                                    , HttpStatus.OK);
    }

    /**
     * Return current-user data and an Authorization token ass a response header.
     */
    @GetMapping("/fetchnewaccesstoken")
    public ResponseEntity<Object> getLongLivedAccessTokenAfterSocialLoginSuccess(HttpServletResponse response) throws AccessDeniedException {
        AccountDto currentUser = SportsTrackerUtils.currentUser();
        if (currentUser == null)
            throw new AccessDeniedException("User is not exist");
        ProfileAccountDto profileAccountDto = accountService.findProfileUserByUserId(currentUser.getId());
        jwtService.addAuthHeader(response, currentUser.getId(), properties.getJwt().getExpirationTime());
        return new ResponseEntity<>(profileAccountDto, HttpStatus.OK);
    }

    @GetMapping("/getcurrentuser")
    public ResponseEntity<Object> getCurrentUser() throws AccessDeniedException {
        AccountDto currentUser = SportsTrackerUtils.currentUser();
        if (currentUser == null)
            throw new AccessDeniedException("User is not exist");
        ProfileAccountDto profileAccountDto = accountService.findProfileUserByUserId(currentUser.getId());
        return new ResponseEntity<>(profileAccountDto, HttpStatus.OK);
    }
}
