package com.dxx.authclient.fallback;

import com.dxx.authclient.service.CallAuthService;
import com.dxx.common.bean.CommonResponse;
import com.dxx.common.contants.ResponseCode;
import org.springframework.stereotype.Component;

@Component
public class CallAuthFallback implements CallAuthService {
    @Override
    public CommonResponse newToken() {
        return new CommonResponse(ResponseCode.SERVER_DOWNGRADE);
    }

    @Override
    public CommonResponse refreshToken(String token) {
        return new CommonResponse(ResponseCode.SERVER_DOWNGRADE);
    }

    @Override
    public CommonResponse hasPermission(String token, String url) {
        return new CommonResponse(ResponseCode.SERVER_DOWNGRADE);
    }

    @Override
    public CommonResponse getAuth(String token) {
        return new CommonResponse(ResponseCode.SERVER_DOWNGRADE);
    }
}
