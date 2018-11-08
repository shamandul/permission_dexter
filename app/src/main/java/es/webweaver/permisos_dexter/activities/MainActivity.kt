package es.webweaver.permisos_dexter.activities

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import es.webweaver.permisos_dexter.R
import es.webweaver.permisos_dexter.enums.PermissionStatusEnum
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setButtonClicks()

    }

    private fun setButtonClicks() {
        buttonCamera.setOnClickListener { checkCameraPermissions() }
        buttonContacts.setOnClickListener { checkContactsPermissions() }
        buttonAudio.setOnClickListener { checkAudioPermissions() }
    }

    private fun checkCameraPermissions() = setPermissionHandler(Manifest.permission.CAMERA, textViewCamera)
    private fun checkContactsPermissions() = setPermissionHandler(Manifest.permission.READ_CONTACTS, textViewContact)
    private fun checkAudioPermissions() = setPermissionHandler(Manifest.permission.RECORD_AUDIO, textViewAudio)

    private fun setPermissionHandler(permission: String, textView: TextView){


        Dexter.withActivity(this)
            .withPermission(permission)
            .withListener(object: PermissionListener{
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    setPermissionStatus(textView, PermissionStatusEnum.GRANTED)
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    if (response.isPermanentlyDenied){
                        setPermissionStatus(textView, PermissionStatusEnum.PERMANENTLY_DENIED)
                    }else {
                        setPermissionStatus(textView, PermissionStatusEnum.DENIED)
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

    private fun setPermissionStatus(textView: TextView, status: PermissionStatusEnum){

        when(status){
            PermissionStatusEnum.GRANTED -> {
                textView.text = getString(R.string.permission_status_granted)
                textView.setTextColor(ContextCompat.getColor(this,
                    R.color.colorPermissionStatusGranted
                ))
            }
            PermissionStatusEnum.DENIED -> {
                textViewCamera.text = getString(R.string.permission_status_denied)
                textViewCamera.setTextColor(
                    ContextCompat.getColor(this,
                        R.color.colorPermissionStatusDenied
                    )
                )
            }
            PermissionStatusEnum.PERMANENTLY_DENIED -> {
                textView.text = getString(R.string.permission_status_denied_permanently)
                textView.setTextColor(ContextCompat.getColor(this,
                    R.color.colorPermissionStatusPermanentlyDenied
                ))
            }

        }
    }

}
