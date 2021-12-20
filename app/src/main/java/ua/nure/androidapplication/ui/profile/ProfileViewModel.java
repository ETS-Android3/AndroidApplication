package ua.nure.androidapplication.ui.profile;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<Boolean> isLogged;
    private MutableLiveData<String> userName;
    private MutableLiveData<String> userLogin;
    private MutableLiveData<String> userPassword;
    private MutableLiveData<String> userPhoneNumber;

    public ProfileViewModel() {
        isLogged = new MutableLiveData<>(false);
        userName = new MutableLiveData<>();
        userLogin = new MutableLiveData<>();
        userPassword = new MutableLiveData<>();
        userPhoneNumber = new MutableLiveData<>();
    }

    public LiveData<Boolean> getIsLogged() {
        return isLogged;
    }

    public void setIsLogged(Boolean isLogged) {
        this.isLogged.setValue(isLogged);
    }

    public LiveData<String> getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.setValue(userName);
    }

    public LiveData<String> getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin.setValue(userLogin);
    }

    public LiveData<String> getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword.setValue(userPassword);
    }

    public LiveData<String> getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber.setValue(userPhoneNumber);
    }
}
