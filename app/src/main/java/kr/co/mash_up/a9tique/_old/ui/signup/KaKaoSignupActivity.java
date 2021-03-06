package kr.co.mash_up.a9tique._old.ui.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import kr.co.mash_up.a9tique._old.common.AccountManager;
import kr.co.mash_up.a9tique._old.data.User;
import kr.co.mash_up.a9tique._old.ui.login.LoginActivity;
import kr.co.mash_up.a9tique._old.ui.products.ProductsActivity;


/**
 * 유효한 세션이 있다는 검증 후
 * me를 호출하여 가입 여부에 따라 가입 페이지를 그리던지 Main 페이지로 이동 시킨다.
 */
public class KaKaoSignupActivity extends AppCompatActivity {

    public static final String TAG = KaKaoSignupActivity.class.getSimpleName();

    private AccountManager mAccountManager;

    /**
     * Main으로 넘길지 가입 페이지를 그릴지 판단하기 위해 me를 호출한다.
     *
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAccountManager = AccountManager.getInstance();
//        requestMe();
    }

    protected void showSignup() {
    }

    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */
//    protected void requestMe() {
//        UserManagement.requestMe(new MeResponseCallback() {
//
//            @Override
//            public void onFailure(ErrorResult errorResult) {
//                String message = "failded to get user info. msg=" + errorResult;
//                Logger.d(message);
//
//                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
//                if (result == ErrorCode.CLIENT_ERROR_CODE) {
//                    Toast.makeText(KaKaoSignupActivity.this, getString(R.string.error_message_for_service_unavailable), Toast.LENGTH_SHORT).show();
//                    finish();
//                } else {
//                    redirectLoginActivity();
//                }
//            }
//
//            @Override
//            public void onSessionClosed(ErrorResult errorResult) {
//                Log.d(TAG, "onSessionClosed");
//                redirectLoginActivity();
//            }
//
//            @Override
//            public void onNotSignedUp() {
//                Toast.makeText(KaKaoSignupActivity.this, "카카오톡 회원가입이 필요합니다", Toast.LENGTH_SHORT).show();
////                showSignup();
//                finish();
//                Log.d(TAG, "onNotSignedUp");
//            }
//
//            //Todo: 유저 정보로 이메일을 사용하려면 따로 입력받아서 보내야 한다.
//            // userProfile: 앱연결과정에서 발급하는 고유아이디
//            @Override
//            public void onSuccess(UserProfile userProfile) {
//                Logger.d("UserProfile: " + userProfile);
//
//                // request access token
//                RequestUser requestUser = new RequestUser(
//                        String.valueOf(userProfile.getId()),
//                        RequestUser.OauthType.KAKAO);
//
//                BackendHelper.getInstance().login(requestUser, new ResultCallback<User>() {
//
//                    @Override
//                    public void onSuccess(User user) {
//                        initAccountData(user);
//                        redirectProductListActivity();
//                    }
//
//                    @Override
//                    public void onFailure() {
//                        Session.getCurrentSession().close();
//                        redirectLoginActivity();
//                    }
//                });
//            }
//        });
//    }

    /**
     * 사용자 정보를 SharedPreferences와 AccountManager에 setting
     *
     * @param user setting할 사용자 정보
     */
    private void initAccountData(User user) {
        mAccountManager.updateAccountInformation(KaKaoSignupActivity.this, user);
    }

    /**
     * 현재 화면을 종료하고 로그인 화면으로 이동
     */
    private void redirectLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    /**
     * 현재 화면을 종료하고 상품 리스트 화면으로 이동
     */
    private void redirectProductListActivity() {
        startActivity(new Intent(KaKaoSignupActivity.this, ProductsActivity.class));
        finish();
    }
}