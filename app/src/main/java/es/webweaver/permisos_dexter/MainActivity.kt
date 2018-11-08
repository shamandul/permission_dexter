package es.webweaver.permisos_dexter

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCamera.setOnClickListener{checkCameraPermissions()}
        buttonContacts.setOnClickListener{checkContactsPermissions()}
        buttonAudio.setOnClickListener{checkAudioPermissions()}

    }

    private fun checkCameraPermissions(){
        val context = this
        Dexter.withActivity(context)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object: PermissionListener{
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    textViewCamera.text = getString(R.string.permission_status_granted)
                    textViewCamera.setTextColor(ContextCompat.getColor(context, R.color.colorPermissionStatusGranted))
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    if (response.isPermanentlyDenied){
                        textViewCamera.text = getString(R.string.permission_status_denied_permanently)
                        textViewCamera.setTextColor(ContextCompat.getColor(context, R.color.colorPermissionStatusPermanentlyDenied))
                    }else {
                        textViewCamera.text = getString(R.string.permission_status_denied)
                        textViewCamera.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.colorPermissionStatusDenied
                            )
                        )
                    }
                 }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()

    }
    private fun checkContactsPermissions(){
        val context = this
        Dexter.withActivity(context)
            .withPermission(Manifest.permission.READ_CONTACTS)
            .withListener(object: PermissionListener{
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    textViewContact.text = getString(R.string.permission_status_granted)
                    textViewContact.setTextColor(ContextCompat.getColor(context, R.color.colorPermissionStatusGranted))
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    if (response.isPermanentlyDenied){
                        textViewContact.text = getString(R.string.permission_status_denied_permanently)
                        textViewContact.setTextColor(ContextCompat.getColor(context, R.color.colorPermissionStatusPermanentlyDenied))
                    }else {
                        textViewContact.text = getString(R.string.permission_status_denied)
                        textViewContact.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.colorPermissionStatusDenied
                            )
                        )
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()

    }
    private fun checkAudioPermissions(){
        val context = this
        Dexter.withActivity(context)
            .withPermission(Manifest.permission.RECORD_AUDIO)
            .withListener(object: PermissionListener{
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    textViewAudio.text = getString(R.string.permission_status_granted)
                    textViewAudio.setTextColor(ContextCompat.getColor(context, R.color.colorPermissionStatusGranted))
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    if (response.isPermanentlyDenied){
                        textViewAudio.text = getString(R.string.permission_status_denied_permanently)
                        textViewAudio.setTextColor(ContextCompat.getColor(context, R.color.colorPermissionStatusPermanentlyDenied))
                    }else {
                        textViewAudio.text = getString(R.string.permission_status_denied)
                        textViewAudio.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.colorPermissionStatusDenied
                            )
                        )
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()

    }

}
