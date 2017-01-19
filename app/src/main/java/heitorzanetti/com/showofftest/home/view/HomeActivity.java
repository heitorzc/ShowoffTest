package heitorzanetti.com.showofftest.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import heitorzanetti.com.showofftest.R;
import heitorzanetti.com.showofftest.home.model.HomeAdapter;
import heitorzanetti.com.showofftest.home.presenter.HomePresenter;
import heitorzanetti.com.showofftest.home.utils.ApiUserMediaResponse;
import heitorzanetti.com.showofftest.home.utils.ApiUserProfileResponse;
import heitorzanetti.com.showofftest.login.model.IPrefsKeys;
import heitorzanetti.com.showofftest.login.view.LoginActivity;
import heitorzanetti.com.showofftest.networking.IHttpRequestErrorCodes;
import heitorzanetti.com.showofftest.widgets.OpenSansTextView;

/**
 * Created by heitorzc on 18/01/2017.
 */

public class HomeActivity extends AppCompatActivity implements IHomeView, IPrefsKeys, IHttpRequestErrorCodes {
    @Bind(R.id.toolbar)      Toolbar toolbar;
    @Bind(R.id.ivAvatar)     CircularImageView ivAvatar;
    @Bind(R.id.tvFullName)   OpenSansTextView tvFullName;
    @Bind(R.id.tvMedia)      OpenSansTextView tvMedia;
    @Bind(R.id.tvFollowing)  OpenSansTextView tvFollowing;
    @Bind(R.id.tvFollowers)  OpenSansTextView tvFollowers;
    @Bind(R.id.rvUserPosts)  RecyclerView rvUserPosts;


    private String token;
    private HomePresenter presenter;
    private HomeAdapter adapter;
    private Snackbar connMessage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        token = getIntent().getStringExtra(PREFS_AUTH_TOKEN_KEY);

        connMessage = Snackbar.make(getWindow().getDecorView(), R.string.connection_message_error, Snackbar.LENGTH_INDEFINITE);

        presenter = new HomePresenter(this);
        presenter.registerConnectivityStatusReceiver(this);
        presenter.requestUserProfileData(token);

    }


    private void showConnMessage(){
        if (connMessage != null) connMessage.show();
    }


    private void dismissConnMessage(){
        if (connMessage != null && connMessage.isShown()){
            connMessage.dismiss();
        }
    }



    @Override
    public void onUserProfileDataFetched(ApiUserProfileResponse userProfile) {

        String photo     = userProfile.getData().getProfile_picture();
        String fullName  = userProfile.getData().getFull_name();
        String media     = String.valueOf(userProfile.getData().getCounts().getMedia());
        String following = String.valueOf(userProfile.getData().getCounts().getFollows());
        String followers = String.valueOf(userProfile.getData().getCounts().getFollowed_by());

        Picasso.with(this).load(photo).placeholder(R.drawable.default_avatar).into(ivAvatar);

        tvFullName.setText(fullName);
        tvMedia.setText(media);
        tvFollowing.setText(following);
        tvFollowers.setText(followers);

        presenter.requestUserMediaData(token);

    }


    @Override
    public void onUserMediaDataFetched(ApiUserMediaResponse userMedia) {

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvUserPosts.setLayoutManager(mLayoutManager);
        rvUserPosts.setHasFixedSize(true);

        adapter = new HomeAdapter(this, userMedia.getData());
        rvUserPosts.setAdapter(adapter);

    }


    @Override
    public void onUserRequestDataFailed(int code) {

        switch (code){

            case ERROR_NO_INTERNET_CONNECTION:
                connMessage.show();
                break;
            case ERROR_API_CONNECTION:
                Toast.makeText(this, R.string.error_api_connection, Toast.LENGTH_SHORT).show();
                break;

        }

    }


    @Override
    public void onLogoutUser() {

        token = null;

        startActivity(new Intent(this, LoginActivity.class));
        finish();

    }


    @Override
    public void onInternetOnline() {

        if (adapter == null) presenter.requestUserProfileData(token);
        dismissConnMessage();

    }


    @Override
    public void onInternetOffline() {
        showConnMessage();
    }



    @Override
    protected void onPostResume() {
        super.onPostResume();

        if (token == null){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }


    @Override
    protected void onDestroy() {
        presenter.unregisterConnectivityStatusReceiver(this);
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        presenter.logoutUser(this);
        return true;

    }


}
