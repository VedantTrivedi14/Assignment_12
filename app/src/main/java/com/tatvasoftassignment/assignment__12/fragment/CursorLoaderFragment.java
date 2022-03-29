package com.tatvasoftassignment.assignment__12.fragment;

import android.Manifest;
import android.annotation.SuppressLint;

import android.app.LoaderManager;
import android.content.Context;

import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.tatvasoftassignment.assignment__12.BuildConfig;
import com.tatvasoftassignment.assignment__12.R;
import com.tatvasoftassignment.assignment__12.databinding.FragmentCursorLoaderBinding;

import java.util.ArrayList;

public class CursorLoaderFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    FragmentCursorLoaderBinding binding;
    private Uri videoUri;
    private ArrayList<String> videoList;
    LoaderManager loaderManager;
    Context context;
    public CursorLoaderFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loaderManager = requireActivity().getLoaderManager();

    }

    void loadLoader() {
        if (loaderManager.getLoader(1) == null) {
            loaderManager.initLoader(1, null, this);
        } else {
            loaderManager.restartLoader(1, null, this);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCursorLoaderBinding.inflate(inflater, container, false);
        videoList = new ArrayList<>();
        checkPermission();
        return binding.getRoot();
    }



    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            mPermissionResult.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
        } else {
            loadLoader();
        }
    }
     ActivityResultLauncher<String> mPermissionResult = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            new ActivityResultCallback<Boolean>() {

                @Override
                public void onActivityResult(Boolean result) {
                        if(result){
                            loadLoader();
                        }else{
                            if(shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)){
                                showDialogOK(getString(R.string.alert_title),
                                        getString(R.string.alert_message),
                                        (dialog, which) -> {
                                            if (which == DialogInterface.BUTTON_POSITIVE) {
                                                checkPermission();
                                            }
                                        });
                            }else{
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null);
                                intent.setData(uri);
                                context.startActivity(intent);
                                Toast.makeText(context, R.string.permission_toast, Toast.LENGTH_SHORT).show();
                                binding.txtCursorDummyText.setVisibility(View.VISIBLE);
                            }
                        }
                }
            });


    private void showDialogOK(String title, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(requireContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, okListener)
                .create()
                .show();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        videoUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        return new CursorLoader(requireContext(), videoUri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data = requireContext().getContentResolver().query(videoUri, null, null, null, null);
        if (data != null) {
            while (data.moveToNext()) {
                @SuppressLint("Range")
                String video = data.getString(data.getColumnIndex(MediaStore.Video.Media.TITLE));
                videoList.add(video);
            }
            data.close();
        }
        binding.lstVideo.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, videoList));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}