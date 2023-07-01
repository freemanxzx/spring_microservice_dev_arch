package ai.yuanxing;

import ai.yuanxing.profile.UserInfoProto;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import ai.yuanxing.auth.AuthServiceGrpc;
import ai.yuanxing.auth.AuthProto;

import java.util.logging.Logger;

@GrpcService
public class AuthServiceGrpcImp extends AuthServiceGrpc.AuthServiceImplBase{

    @Override
    public void doLogin(ai.yuanxing.auth.AuthProto.LoginReq request,
                        io.grpc.stub.StreamObserver<AuthProto.LoginRsp> responseObserver){

        switch (request.getELoginType()){
            case E_PHONECODE_LOGIN: {
                UserInfoProto.UserInfo userInfo = request.getStUserInfo();
                String sPhone = userInfo.getSPhone();
                if (!CommonUtil.PhoneVerificationCode.isValidChinesePhoneNumber(sPhone)){
                    System.err.println("phone error:"+sPhone);
                    break;
                }

                break;
            }
            default:
                System.err.println("only phone and verification code is supported currently");
                break;
        }

        AuthProto.LoginRsp loginRsp = AuthProto.LoginRsp.newBuilder()
                .setERetType(AuthProto.E_AUTH_RET_TYPE.E_ERROR_UNKNOWN_EXCEPTION)
                .build();

        responseObserver.onNext(loginRsp);
        responseObserver.onCompleted();
    }

    @Override
    public void genVerificationCode(ai.yuanxing.auth.AuthProto.GenVerificationCodeReq request,
                                    io.grpc.stub.StreamObserver<AuthProto.GenVerificationCodeRsp> responseObserver){

        String verifcationCode = CommonUtil.PhoneVerificationCode.generateVerificationCode();

        AuthProto.GenVerificationCodeRsp genVerificationCodeRsp = AuthProto.GenVerificationCodeRsp.newBuilder()
                .setERetType(AuthProto.E_AUTH_RET_TYPE.E_ERROR_UNKNOWN_EXCEPTION)
                .setSVerificationCode(verifcationCode)
                .build();

        responseObserver.onNext(genVerificationCodeRsp);
        responseObserver.onCompleted();
    }



}
