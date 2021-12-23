package ua.nure.myapplication.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.HashMap;
import java.util.Map;

public class WarningsHelper {
    private final Map<String, DialogFragment> warningsFragments = new HashMap<>();

    private WarningsHelper() {
        warningsFragments.put(WarningDialogNoExistingUser.class.getName(), new WarningDialogAlreadyExistingUser());
        warningsFragments.put(WarningDialogNoExistingUser.class.getName(), new WarningDialogNoExistingUser());
        warningsFragments.put(WarningDialogPasswordFailed.class.getName(), new WarningDialogPasswordFailed());
        warningsFragments.put(WarningDialogPasswordSucceed.class.getName(), new WarningDialogPasswordSucceed());
        warningsFragments.put(WarningDialogFilingTheGaps.class.getName(), new WarningDialogFilingTheGaps());
    }

    public static WarningsHelper getInstance() {
        return WarningsHelperHolder.INSTANCE;
    }

    private static class WarningsHelperHolder {
        private static final WarningsHelper INSTANCE = new WarningsHelper();
    }

    public void showFragment(AppCompatActivity appCompatActivity, String fragmentName) {
        DialogFragment currentDialogFragment = warningsFragments.get(fragmentName);
        assert currentDialogFragment != null;
        currentDialogFragment.show(appCompatActivity.getSupportFragmentManager(), "CUSTOM");
    }
}