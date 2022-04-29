package com.kharigo.exploria;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class uses_permission {

    public void permition(final Context context) {
        Dexter.withActivity((Activity) context)
                .withPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                )
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            //      Toast.makeText(context.getApplicationContext(), "All permissions are granted!", Toast.LENGTH_SHORT).show();
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                        }
//                        System.out.print("____________________"+report);
//                        Log.i("-----------",""+report);

//                        if (report.isAnyPermissionPermanentlyDenied()) {
//                            Log.i("----xxx-------",""+report);
//                            Intent intent = new Intent();
//                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                            Uri uri = Uri.fromParts("package",context.getPackageName(),null);
//                            intent.setData(uri);
//                            context.startActivity(intent);
//                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }


                }).check();


    }


}
